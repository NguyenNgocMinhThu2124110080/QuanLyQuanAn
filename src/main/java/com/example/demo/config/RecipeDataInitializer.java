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

    public RecipeDataInitializer(NguyenLieuRepository nguyenLieuRepository,
                                 CongThucMonRepository congThucMonRepository,
                                 NhaCungCapRepository nhaCungCapRepository,
                                 MonAnRepository monAnRepository) {
        this.nguyenLieuRepository = nguyenLieuRepository;
        this.congThucMonRepository = congThucMonRepository;
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.monAnRepository = monAnRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, NguyenLieu> nlMap = new HashMap<>();
        for (NguyenLieu nl : nguyenLieuRepository.findAll()) {
            if (nl.getTenNguyenLieu() != null) {
                nlMap.put(nl.getTenNguyenLieu().trim().toLowerCase(), nl);
            }
        }

        NhaCungCap nccRau = nhaCungCapRepository.findById(1).orElse(null);
        NhaCungCap nccThit = nhaCungCapRepository.findById(2).orElse(null);
        NhaCungCap nccDoUong = nhaCungCapRepository.findById(3).orElse(null);

        createIngredientIfMissing(nlMap, "Sườn heo non", "kg", new BigDecimal("110000"), new BigDecimal("30"), nccThit);
        createIngredientIfMissing(nlMap, "Giò heo rút xương", "kg", new BigDecimal("85000"), new BigDecimal("25"), nccThit);
        createIngredientIfMissing(nlMap, "Mực ống tươi", "kg", new BigDecimal("140000"), new BigDecimal("25"), nccThit);
        createIngredientIfMissing(nlMap, "Tôm sú tươi", "kg", new BigDecimal("180000"), new BigDecimal("20"), nccThit);
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

        // Nạp lại danh sách nguyên liệu
        nlMap.clear();
        for (NguyenLieu nl : nguyenLieuRepository.findAll()) {
            if (nl.getTenNguyenLieu() != null) {
                nlMap.put(nl.getTenNguyenLieu().trim().toLowerCase(), nl);
            }
        }

        // Định nghĩa công thức chuẩn cho 19 món ăn
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
