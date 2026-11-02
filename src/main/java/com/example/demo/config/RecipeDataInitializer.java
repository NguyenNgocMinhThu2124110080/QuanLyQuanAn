package com.example.demo.config;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

// Tự động khởi tạo đầy đủ công thức định lượng nguyên liệu cho toàn bộ 19 món ăn
@Component
public class RecipeDataInitializer implements CommandLineRunner {

    private final NguyenLieuRepository nguyenLieuRepository;
    private final CongThucMonRepository congThucMonRepository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final MonAnRepository monAnRepository;
    private final NguoiDungRepository nguoiDungRepository;

    public RecipeDataInitializer(NguyenLieuRepository nguyenLieuRepository,
                                 CongThucMonRepository congThucMonRepository,
                                 NhaCungCapRepository nhaCungCapRepository,
                                 MonAnRepository monAnRepository,
                                 NguoiDungRepository nguoiDungRepository) {
        this.nguyenLieuRepository = nguyenLieuRepository;
        this.congThucMonRepository = congThucMonRepository;
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.monAnRepository = monAnRepository;
        this.nguoiDungRepository = nguoiDungRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Chuẩn hóa tên hiển thị của 4 tài khoản nhân sự nếu đang dùng tên mẫu cũ
        updateAccountDisplayNameIfLegacy(1, "Trần Văn Quản", "Nguyễn Minh Hoàng", "Nam");
        updateAccountDisplayNameIfLegacy(2, "Lê Thị Lý", "Lê Ngọc Bảo Trân", "Nữ");
        updateAccountDisplayNameIfLegacy(3, "Nguyễn Văn Phục", "Võ Hoàng Nam", "Nam");
        updateAccountDisplayNameIfLegacy(4, "Phạm Thị Bếp", "Phạm Gia Huy", "Nam");

        Map<String, NguyenLieu> nlMap = new HashMap<>();
        for (NguyenLieu nl : nguyenLieuRepository.findAll()) {
            if (nl.getTenNguyenLieu() != null) {
                nlMap.put(nl.getTenNguyenLieu().trim().toLowerCase(), nl);
            }
        }

        NhaCungCap nccRau = nhaCungCapRepository.findById(1).orElse(null);
        NhaCungCap nccThit = nhaCungCapRepository.findById(2).orElse(null);
        NhaCungCap nccDoUong = nhaCungCapRepository.findById(3).orElse(null);
        NhaCungCap nccHaiSan = nhaCungCapRepository.findById(4).orElse(null);
        NhaCungCap nccMienTay = nhaCungCapRepository.findById(5).orElse(null);

        createIngredientIfMissing(nlMap, "Sườn heo non", "kg", new BigDecimal("110000"), new BigDecimal("30"), nccThit);
        createIngredientIfMissing(nlMap, "Giò heo rút xương", "kg", new BigDecimal("85000"), new BigDecimal("25"), nccThit);
        createIngredientIfMissing(nlMap, "Mực ống tươi", "kg", new BigDecimal("140000"), new BigDecimal("25"), nccHaiSan != null ? nccHaiSan : nccThit);
        createIngredientIfMissing(nlMap, "Tôm sú tươi", "kg", new BigDecimal("180000"), new BigDecimal("20"), nccHaiSan != null ? nccHaiSan : nccThit);
        createIngredientIfMissing(nlMap, "Gạo thơm ST25", "kg", new BigDecimal("25000"), new BigDecimal("50"), nccRau);
        createIngredientIfMissing(nlMap, "Nem chua tươi", "kg", new BigDecimal("90000"), new BigDecimal("20"), nccThit);
        createIngredientIfMissing(nlMap, "Trứng gà & Sữa tươi", "kg", new BigDecimal("40000"), new BigDecimal("30"), nccDoUong);
        createIngredientIfMissing(nlMap, "Trân châu đường đen", "kg", new BigDecimal("50000"), new BigDecimal("25"), nccDoUong);
        createIngredientIfMissing(nlMap, "Cà phê Robusta mộc", "kg", new BigDecimal("120000"), new BigDecimal("20"), nccDoUong);
        createIngredientIfMissing(nlMap, "Kem béo & Muối hồng", "kg", new BigDecimal("70000"), new BigDecimal("20"), nccDoUong);
        createIngredientIfMissing(nlMap, "Dưa hấu Long An", "kg", new BigDecimal("18000"), new BigDecimal("40"), nccRau);
        createIngredientIfMissing(nlMap, "Quả tắc & Hạt chia", "kg", new BigDecimal("45000"), new BigDecimal("20"), nccRau);
        createIngredientIfMissing(nlMap, "Khúc bạch & Hạnh nhân", "kg", new BigDecimal("80000"), new BigDecimal("20"), nccDoUong);
        createIngredientIfMissing(nlMap, "Cùi bưởi & Cốt dừa", "kg", new BigDecimal("60000"), new BigDecimal("25"), nccRau);

        // Nguyên liệu mở rộng cho thực đơn phong phú
        createIngredientIfMissing(nlMap, "Cốt lẩu Thái Tomyum", "hộp", new BigDecimal("45000"), new BigDecimal("30"), nccDoUong);
        createIngredientIfMissing(nlMap, "Nấm kim châm tươi", "kg", new BigDecimal("40000"), new BigDecimal("25"), nccRau);
        createIngredientIfMissing(nlMap, "Ba chỉ bò Mỹ", "kg", new BigDecimal("190000"), new BigDecimal("30"), nccThit);
        createIngredientIfMissing(nlMap, "Cánh gà tươi CP", "kg", new BigDecimal("65000"), new BigDecimal("35"), nccThit);
        createIngredientIfMissing(nlMap, "Ngó sen tươi", "kg", new BigDecimal("50000"), new BigDecimal("20"), nccRau);
        createIngredientIfMissing(nlMap, "Bánh tráng cuốn chả giò", "hộp", new BigDecimal("20000"), new BigDecimal("40"), nccRau);
        createIngredientIfMissing(nlMap, "Xà lách & Cà chua bi", "kg", new BigDecimal("30000"), new BigDecimal("25"), nccRau);
        createIngredientIfMissing(nlMap, "Hủ tiếu gạo Sa Đéc", "kg", new BigDecimal("35000"), new BigDecimal("30"), nccRau);
        createIngredientIfMissing(nlMap, "Mì Ý Spaghetti", "kg", new BigDecimal("45000"), new BigDecimal("25"), nccDoUong);
        createIngredientIfMissing(nlMap, "Thịt bò lúc lắc", "kg", new BigDecimal("260000"), new BigDecimal("20"), nccThit);
        createIngredientIfMissing(nlMap, "Mãng cầu xiêm tươi", "kg", new BigDecimal("40000"), new BigDecimal("30"), nccMienTay != null ? nccMienTay : nccRau);
        createIngredientIfMissing(nlMap, "Ổi hồng ruột đỏ", "kg", new BigDecimal("35000"), new BigDecimal("30"), nccMienTay != null ? nccMienTay : nccRau);
        createIngredientIfMissing(nlMap, "Trái bơ sáp Đắk Lắk", "kg", new BigDecimal("55000"), new BigDecimal("25"), nccMienTay != null ? nccMienTay : nccRau);
        createIngredientIfMissing(nlMap, "Dừa xiêm tươi Bến Tre", "trái", new BigDecimal("15000"), new BigDecimal("50"), nccMienTay != null ? nccMienTay : nccRau);
        createIngredientIfMissing(nlMap, "Sữa chua dẻo & Dâu tây", "hộp", new BigDecimal("40000"), new BigDecimal("30"), nccDoUong);

        // Nạp lại danh sách nguyên liệu
        nlMap.clear();
        for (NguyenLieu nl : nguyenLieuRepository.findAll()) {
            if (nl.getTenNguyenLieu() != null) {
                nlMap.put(nl.getTenNguyenLieu().trim().toLowerCase(), nl);
            }
        }

        // Định nghĩa công thức chuẩn cho toàn bộ 34 món ăn
        addRecipeIfMissing(1, "thịt gà", new BigDecimal("0.20"), nlMap);
        addRecipeIfMissing(2, "thịt bò", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(3, "khoai tây", new BigDecimal("0.30"), nlMap);
        addRecipeIfMissing(4, "trà đen", new BigDecimal("0.05"), nlMap);
        addRecipeIfMissing(5, "sườn heo non", new BigDecimal("0.25"), nlMap);
        addRecipeIfMissing(6, "sườn heo non", new BigDecimal("0.20"), nlMap);
        addRecipeIfMissing(7, "thịt bò", new BigDecimal("0.10"), nlMap);
        addRecipeIfMissing(7, "giò heo rút xương", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(8, "mực ống tươi", new BigDecimal("0.10"), nlMap);
        addRecipeIfMissing(8, "tôm sú tươi", new BigDecimal("0.10"), nlMap);
        addRecipeIfMissing(9, "gạo thơm st25", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(9, "trứng gà & sữa tươi", new BigDecimal("0.05"), nlMap);
        addRecipeIfMissing(10, "nem chua tươi", new BigDecimal("0.20"), nlMap);
        addRecipeIfMissing(11, "thịt gà", new BigDecimal("0.25"), nlMap);
        addRecipeIfMissing(12, "mực ống tươi", new BigDecimal("0.25"), nlMap);
        addRecipeIfMissing(13, "trà đen", new BigDecimal("0.03"), nlMap);
        addRecipeIfMissing(13, "trân châu đường đen", new BigDecimal("0.05"), nlMap);
        addRecipeIfMissing(14, "cà phê robusta mộc", new BigDecimal("0.03"), nlMap);
        addRecipeIfMissing(14, "kem béo & muối hồng", new BigDecimal("0.05"), nlMap);
        addRecipeIfMissing(15, "dưa hấu long an", new BigDecimal("0.40"), nlMap);
        addRecipeIfMissing(16, "trà đen", new BigDecimal("0.02"), nlMap);
        addRecipeIfMissing(16, "quả tắc & hạt chia", new BigDecimal("0.05"), nlMap);
        addRecipeIfMissing(17, "khúc bạch & hạnh nhân", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(18, "cùi bưởi & cốt dừa", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(19, "trứng gà & sữa tươi", new BigDecimal("0.10"), nlMap);

        // Công thức cho các món mới (20 -> 34)
        addRecipeIfMissing(20, "cốt lẩu thái tomyum", new BigDecimal("0.20"), nlMap);
        addRecipeIfMissing(20, "tôm sú tươi", new BigDecimal("0.10"), nlMap);
        addRecipeIfMissing(20, "mực ống tươi", new BigDecimal("0.10"), nlMap);
        addRecipeIfMissing(20, "nấm kim châm tươi", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(21, "ba chỉ bò mỹ", new BigDecimal("0.20"), nlMap);
        addRecipeIfMissing(21, "nấm kim châm tươi", new BigDecimal("0.10"), nlMap);
        addRecipeIfMissing(22, "cánh gà tươi cp", new BigDecimal("0.30"), nlMap);
        addRecipeIfMissing(23, "sườn heo non", new BigDecimal("0.35"), nlMap);
        addRecipeIfMissing(24, "thịt bò", new BigDecimal("0.12"), nlMap);
        addRecipeIfMissing(24, "xà lách & cà chua bi", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(25, "ngó sen tươi", new BigDecimal("0.20"), nlMap);
        addRecipeIfMissing(25, "tôm sú tươi", new BigDecimal("0.08"), nlMap);
        addRecipeIfMissing(26, "bánh tráng cuốn chả giò", new BigDecimal("1.00"), nlMap);
        addRecipeIfMissing(26, "tôm sú tươi", new BigDecimal("0.08"), nlMap);
        addRecipeIfMissing(27, "thịt bò lúc lắc", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(27, "khoai tây", new BigDecimal("0.10"), nlMap);
        addRecipeIfMissing(27, "gạo thơm st25", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(28, "hủ tiếu gạo sa đéc", new BigDecimal("0.18"), nlMap);
        addRecipeIfMissing(28, "tôm sú tươi", new BigDecimal("0.06"), nlMap);
        addRecipeIfMissing(29, "mì ý spaghetti", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(29, "thịt bò", new BigDecimal("0.10"), nlMap);
        addRecipeIfMissing(30, "trà đen", new BigDecimal("0.02"), nlMap);
        addRecipeIfMissing(30, "mãng cầu xiêm tươi", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(31, "trà đen", new BigDecimal("0.02"), nlMap);
        addRecipeIfMissing(31, "ổi hồng ruột đỏ", new BigDecimal("0.15"), nlMap);
        addRecipeIfMissing(32, "trái bơ sáp đắk lắk", new BigDecimal("0.20"), nlMap);
        addRecipeIfMissing(33, "dừa xiêm tươi bến tre", new BigDecimal("1.00"), nlMap);
        addRecipeIfMissing(34, "sữa chua dẻo & dâu tây", new BigDecimal("1.00"), nlMap);
    }

    private void updateAccountDisplayNameIfLegacy(Integer userId, String oldName, String newName, String gioiTinh) {
        nguoiDungRepository.findById(userId).ifPresent(nd -> {
            if (oldName.equalsIgnoreCase(nd.getHoTen())) {
                nd.setHoTen(newName);
                nd.setGioiTinh(gioiTinh);
                nguoiDungRepository.save(nd);
            }
        });
    }

    private void createIngredientIfMissing(Map<String, NguyenLieu> map, String name, String dvt, BigDecimal gia, BigDecimal ton, NhaCungCap ncc) {
        String key = name.trim().toLowerCase();
        if (!map.containsKey(key)) {
            NguyenLieu nl = new NguyenLieu();
            nl.setTenNguyenLieu(name);
            nl.setDonViTinh(dvt);
            nl.setGiaNhap(gia);
            nl.setSoLuongTon(ton);
            nl.setTrangThai(1);
            nl.setNhaCungCap(ncc);
            nguyenLieuRepository.save(nl);
            map.put(key, nl);
        }
    }

    private void addRecipeIfMissing(Integer maMon, String nlName, BigDecimal soLuongDung, Map<String, NguyenLieu> map) {
        NguyenLieu nl = map.get(nlName.trim().toLowerCase());
        if (nl == null || nl.getNlId() == null) return;

        CongThucMonId id = new CongThucMonId(maMon, nl.getNlId());
        if (!congThucMonRepository.existsById(id)) {
            if (monAnRepository.existsById(maMon)) {
                CongThucMon ctm = new CongThucMon();
                ctm.setId(id);
                ctm.setSoLuongDung(soLuongDung);
                congThucMonRepository.save(ctm);
            }
        }
    }
}
