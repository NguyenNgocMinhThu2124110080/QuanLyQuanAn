package com.example.demo.service;

import com.example.demo.dto.NguyenLieuTonKhoResponse;
import com.example.demo.dto.NhapXuatKhoRequest;
import com.example.demo.dto.ThongKeKhoResponse;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 4: Nghiệp vụ Quản lý Kho, Tự động trừ kho theo công thức món ăn & Thống kê Kho
@Service
public class KhoService {

    private final KhoRepository khoRepository;
    private final NguyenLieuRepository nguyenLieuRepository;
    private final PhieuNhapXuatRepository phieuNhapXuatRepository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final NhanVienRepository nhanVienRepository;
    private final CongThucMonRepository congThucMonRepository;
    private final MonAnRepository monAnRepository;

    public KhoService(KhoRepository khoRepository,
                      NguyenLieuRepository nguyenLieuRepository,
                      PhieuNhapXuatRepository phieuNhapXuatRepository,
                      NhaCungCapRepository nhaCungCapRepository,
                      NhanVienRepository nhanVienRepository,
                      CongThucMonRepository congThucMonRepository,
                      MonAnRepository monAnRepository) {
        this.khoRepository = khoRepository;
        this.nguyenLieuRepository = nguyenLieuRepository;
        this.phieuNhapXuatRepository = phieuNhapXuatRepository;
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.congThucMonRepository = congThucMonRepository;
        this.monAnRepository = monAnRepository;
    }

    /**
     * Xử lý tạo phiếu Nhập kho hoặc Xuất kho nguyên liệu:
     * - Tự động cộng/trừ số lượng tồn kho
     * - Chặn xuất nếu số lượng tồn không đủ
     */
    @Transactional
    public PhieuNhapXuat xuLyNhapXuat(NhapXuatKhoRequest req) {
        // 1. Kiểm tra nguyên liệu
        NguyenLieu nl = nguyenLieuRepository.findById(req.getNlId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nguyên liệu ID: " + req.getNlId()));

        // 2. Kiểm tra kho và nhân viên
        Kho kho = khoRepository.findById(req.getKhoId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kho lưu trữ ID: " + req.getKhoId()));

        NhanVien nv = nhanVienRepository.findById(req.getNvId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên thủ kho ID: " + req.getNvId()));

        NhaCungCap ncc = null;
        if (req.getNccId() != null) {
            ncc = nhaCungCapRepository.findById(req.getNccId()).orElse(null);
        }

        BigDecimal soLuong = req.getSoLuong();
        BigDecimal donGia = req.getDonGia() != null ? req.getDonGia() : (nl.getGiaNhap() != null ? nl.getGiaNhap() : BigDecimal.ZERO);
        BigDecimal tongTien = donGia.multiply(soLuong);

        // 3. Xử lý logic tồn kho theo loại phiếu
        if ("NHAP".equalsIgnoreCase(req.getLoaiPhieu())) {
            // Cộng dồn tồn kho
            BigDecimal tonMoi = (nl.getSoLuongTon() != null ? nl.getSoLuongTon() : BigDecimal.ZERO).add(soLuong);
            nl.setSoLuongTon(tonMoi);
            if (req.getDonGia() != null) {
                nl.setGiaNhap(req.getDonGia()); // Cập nhật giá nhập mới nhất
            }
            if (ncc != null) {
                nl.setNhaCungCap(ncc);
            }
        } else if ("XUAT".equalsIgnoreCase(req.getLoaiPhieu())) {
            // Kiểm tra số lượng tồn
            BigDecimal tonHienTai = nl.getSoLuongTon() != null ? nl.getSoLuongTon() : BigDecimal.ZERO;
            if (tonHienTai.compareTo(soLuong) < 0) {
                throw new IllegalStateException("Kho không đủ số lượng để xuất! Hiện tại chỉ còn: " + tonHienTai + " " + nl.getDonViTinh());
            }
            // Trừ tồn kho
            nl.setSoLuongTon(tonHienTai.subtract(soLuong));
        } else {
            throw new IllegalArgumentException("Loại phiếu không hợp lệ (chỉ chấp nhận 'NHAP' hoặc 'XUAT')!");
        }

        nguyenLieuRepository.save(nl);

        // 4. Tạo bản ghi phiếu nhập xuất
        PhieuNhapXuat pnx = new PhieuNhapXuat();
        pnx.setLoaiPhieu(req.getLoaiPhieu().toUpperCase());
        pnx.setNgayNhapXuat(LocalDateTime.now());
        pnx.setSoLuong(soLuong);
        pnx.setDonGia(donGia);
        pnx.setTongTien(tongTien);
        pnx.setGhiChu(req.getGhiChu());
        pnx.setNguyenLieu(nl);
        pnx.setNhaCungCap(ncc);
        pnx.setNhanVien(nv);
        pnx.setKho(kho);

        return phieuNhapXuatRepository.save(pnx);
    }

    /**
     * TÍNH NĂNG LIÊN THÔNG ĐẶC BIỆT (BUỔI 4):
     * Tự động trừ kho nguyên liệu theo Công thức món ăn khi bán:
     * - Tra định lượng các nguyên liệu của món trong bảng cong_thuc_mon
     * - Kiểm tra số lượng tồn trong kho, nếu thiếu ném Exception chặn lại
     * - Tự động trừ tồn kho và tạo Phiếu Xuất Kho cho từng nguyên liệu
     */
    @Transactional
    public List<PhieuNhapXuat> truKhoKhiBanMon(Integer maMon, Integer soLuongMon, Integer nvId, Integer khoId) {
        MonAn monAn = monAnRepository.findById(maMon)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy món ăn ID: " + maMon));

        List<CongThucMon> dsCongThuc = congThucMonRepository.findById_MaMon(maMon);
        if (dsCongThuc.isEmpty()) {
            return new ArrayList<>(); // Món không cần nguyên liệu định lượng sẵn
        }

        // Chọn kho và nhân viên mặc định nếu không truyền
        Kho kho = (khoId != null) 
                ? khoRepository.findById(khoId).orElse(khoRepository.findAll().stream().findFirst().orElse(null))
                : khoRepository.findAll().stream().findFirst().orElse(null);

        NhanVien nv = (nvId != null)
                ? nhanVienRepository.findById(nvId).orElse(nhanVienRepository.findAll().stream().findFirst().orElse(null))
                : nhanVienRepository.findAll().stream().findFirst().orElse(null);

        // BƯỚC 1: Kiểm tra tính sẵn sàng của TOÀN BỘ nguyên liệu trước (tránh trường hợp trừ dở dang)
        for (CongThucMon ct : dsCongThuc) {
            NguyenLieu nl = ct.getNguyenLieu();
            if (nl == null && ct.getId() != null) {
                nl = nguyenLieuRepository.findById(ct.getId().getNlId()).orElse(null);
            }
            if (nl != null) {
                BigDecimal dinhLuong = ct.getSoLuongDung() != null ? ct.getSoLuongDung() : BigDecimal.ZERO;
                BigDecimal soLuongCanDung = dinhLuong.multiply(BigDecimal.valueOf(soLuongMon));
                BigDecimal tonHienTai = nl.getSoLuongTon() != null ? nl.getSoLuongTon() : BigDecimal.ZERO;

                if (tonHienTai.compareTo(soLuongCanDung) < 0) {
                    throw new IllegalStateException("Kho không đủ nguyên liệu '" + nl.getTenNguyenLieu() + 
                            "' để chế biến món '" + monAn.getTenMon() + "'! Cần: " + soLuongCanDung + " " + nl.getDonViTinh() + 
                            ", nhưng trong kho chỉ còn: " + tonHienTai + " " + nl.getDonViTinh());
                }
            }
        }

        // BƯỚC 2: Thực hiện trừ kho và lưu phiếu xuất tự động
        List<PhieuNhapXuat> phieuXuatList = new ArrayList<>();
        for (CongThucMon ct : dsCongThuc) {
            NguyenLieu nl = ct.getNguyenLieu();
            if (nl == null && ct.getId() != null) {
                nl = nguyenLieuRepository.findById(ct.getId().getNlId()).orElse(null);
            }
            if (nl != null) {
                BigDecimal dinhLuong = ct.getSoLuongDung() != null ? ct.getSoLuongDung() : BigDecimal.ZERO;
                BigDecimal soLuongCanDung = dinhLuong.multiply(BigDecimal.valueOf(soLuongMon));

                nl.setSoLuongTon(nl.getSoLuongTon().subtract(soLuongCanDung));
                nguyenLieuRepository.save(nl);

                // Tạo phiếu xuất kho tự động
                PhieuNhapXuat pnx = new PhieuNhapXuat();
                pnx.setLoaiPhieu("XUAT");
                pnx.setNgayNhapXuat(LocalDateTime.now());
                pnx.setSoLuong(soLuongCanDung);
                pnx.setDonGia(nl.getGiaNhap() != null ? nl.getGiaNhap() : BigDecimal.ZERO);
                pnx.setTongTien(pnx.getDonGia().multiply(soLuongCanDung));
                pnx.setGhiChu("Tự động xuất kho chế biến món: " + monAn.getTenMon() + " (SL: " + soLuongMon + ")");
                pnx.setNguyenLieu(nl);
                pnx.setKho(kho);
                pnx.setNhanVien(nv);

                phieuXuatList.add(phieuNhapXuatRepository.save(pnx));
            }
        }

        return phieuXuatList;
    }

    /**
     * Báo cáo tồn kho toàn bộ nguyên liệu kèm mức độ cảnh báo (AN TOÀN / SẮP HẾT / HẾT HÀNG)
     */
    public List<NguyenLieuTonKhoResponse> baoCaoTonKho() {
        List<NguyenLieu> list = nguyenLieuRepository.findAll();
        List<NguyenLieuTonKhoResponse> result = new ArrayList<>();

        for (NguyenLieu nl : list) {
            BigDecimal ton = nl.getSoLuongTon() != null ? nl.getSoLuongTon() : BigDecimal.ZERO;
            String mucDo = "AN_TOAN";
            if (ton.compareTo(BigDecimal.ZERO) <= 0) {
                mucDo = "HET_HANG";
            } else if (ton.compareTo(new BigDecimal("10.0")) <= 0) {
                mucDo = "SAP_HET";
            }

            result.add(NguyenLieuTonKhoResponse.builder()
                    .nlId(nl.getNlId())
                    .tenNguyenLieu(nl.getTenNguyenLieu())
                    .donViTinh(nl.getDonViTinh())
                    .soLuongTon(ton)
                    .giaNhap(nl.getGiaNhap())
                    .tenNhaCungCap(nl.getNhaCungCap() != null ? nl.getNhaCungCap().getTenNcc() : "Chưa có")
                    .mucDoCanhBao(mucDo)
                    .build());
        }
        return result;
    }

    /**
     * Thống kê tổng hợp số lượng phiếu và số tiền Nhập / Xuất kho theo khoảng thời gian
     */
    public ThongKeKhoResponse thongKeNhapXuat(LocalDate tuNgay, LocalDate denNgay) {
        if (tuNgay == null) tuNgay = LocalDate.now().minusMonths(1);
        if (denNgay == null) denNgay = LocalDate.now();

        LocalDateTime batDau = tuNgay.atStartOfDay();
        LocalDateTime ketThuc = denNgay.atTime(LocalTime.MAX);

        List<PhieuNhapXuat> danhSach = phieuNhapXuatRepository.findByNgayNhapXuatBetween(batDau, ketThuc);

        long soPhieuNhap = 0;
        long soPhieuXuat = 0;
        BigDecimal tongTienNhap = BigDecimal.ZERO;
        BigDecimal tongTienXuat = BigDecimal.ZERO;

        for (PhieuNhapXuat pnx : danhSach) {
            BigDecimal tien = pnx.getTongTien() != null ? pnx.getTongTien() : BigDecimal.ZERO;
            if ("NHAP".equalsIgnoreCase(pnx.getLoaiPhieu())) {
                soPhieuNhap++;
                tongTienNhap = tongTienNhap.add(tien);
            } else if ("XUAT".equalsIgnoreCase(pnx.getLoaiPhieu())) {
                soPhieuXuat++;
                tongTienXuat = tongTienXuat.add(tien);
            }
        }

        long soNlSapHet = nguyenLieuRepository.findAll().stream()
                .filter(nl -> nl.getSoLuongTon() != null && nl.getSoLuongTon().compareTo(new BigDecimal("10.0")) <= 0)
                .count();

        return ThongKeKhoResponse.builder()
                .tuNgay(tuNgay)
                .denNgay(denNgay)
                .tongSoPhieuNhap(soPhieuNhap)
                .tongSoPhieuXuat(soPhieuXuat)
                .tongTienNhap(tongTienNhap)
                .tongTienXuat(tongTienXuat)
                .soNguyenLieuSapHet(soNlSapHet)
                .build();
    }

    /**
     * Tra cứu lịch sử nhập xuất của một nguyên liệu
     */
    public List<PhieuNhapXuat> lichSuTheoNguyenLieu(Integer nlId) {
        return phieuNhapXuatRepository.findByNguyenLieu_NlId(nlId);
    }
}
