USE quan_ly_quan_an;
SET NAMES utf8mb4;

-- 1. Thêm 2 danh mục mới
INSERT INTO danh_muc_mon_an (ma_danhmuc, ten_danhmuc, mo_ta, trang_thai) VALUES
(5, 'Món lẩu & Nướng', 'Các set lẩu hải sản, lẩu gà, thịt nướng than hoa', 1),
(6, 'Khai vị & Salad', 'Salad rau tươi, nộm ngó sen, chả giò giòn rụm', 1)
ON DUPLICATE KEY UPDATE ten_danhmuc = VALUES(ten_danhmuc);

-- 2. Thêm 2 nhà cung cấp mới
INSERT INTO nha_cung_cap (ncc_id, ten_ncc, dia_chi, so_dien_thoai, email, trang_thai) VALUES
(4, 'Hải sản Tươi sống Biển Đông', 'Chợ đầu mối Bình Điền, Q.8, TP.HCM', '0284444555', 'biendong@ncc.vn', 1),
(5, 'Nông sản & Trái cây Miền Tây', 'Huyện Cái Bè, Tỉnh Tiền Giang', '0285555666', 'mientay@ncc.vn', 1)
ON DUPLICATE KEY UPDATE ten_ncc = VALUES(ten_ncc);

-- 3. Thêm 15 nguyên liệu kho mới (nl_id 20 -> 34)
INSERT INTO nguyen_lieu (nl_id, ten_nguyen_lieu, don_vi_tinh, gia_nhap, so_luong_ton, ncc_id, trang_thai) VALUES
(20, 'Cốt lẩu Thái Tomyum', 'hộp', 45000, 30.000, 3, 1),
(21, 'Nấm kim châm tươi', 'kg', 40000, 25.000, 1, 1),
(22, 'Ba chỉ bò Mỹ', 'kg', 190000, 30.000, 2, 1),
(23, 'Cánh gà tươi CP', 'kg', 65000, 35.000, 2, 1),
(24, 'Ngó sen tươi', 'kg', 50000, 20.000, 1, 1),
(25, 'Bánh tráng cuốn chả giò', 'hộp', 20000, 40.000, 1, 1),
(26, 'Xà lách & Cà chua bi', 'kg', 30000, 25.000, 1, 1),
(27, 'Hủ tiếu gạo Sa Đéc', 'kg', 35000, 30.000, 1, 1),
(28, 'Mì Ý Spaghetti', 'kg', 45000, 25.000, 3, 1),
(29, 'Thịt bò lúc lắc', 'kg', 260000, 20.000, 2, 1),
(30, 'Mãng cầu xiêm tươi', 'kg', 40000, 30.000, 5, 1),
(31, 'Ổi hồng ruột đỏ', 'kg', 35000, 30.000, 5, 1),
(32, 'Trái bơ sáp Đắk Lắk', 'kg', 55000, 25.000, 5, 1),
(33, 'Dừa xiêm tươi Bến Tre', 'trái', 15000, 50.000, 5, 1),
(34, 'Sữa chua dẻo & Dâu tây', 'hộp', 40000, 30.000, 3, 1)
ON DUPLICATE KEY UPDATE ten_nguyen_lieu = VALUES(ten_nguyen_lieu), gia_nhap = VALUES(gia_nhap);

-- 4. Thêm 15 món ăn mới (ma_mon 20 -> 34)
INSERT INTO mon_an (ma_mon, ten_mon, gia, mo_ta, ma_danhmuc, trang_thai) VALUES
(20, 'Lẩu Thái hải sản thập cẩm', 220000, 'Tôm sú, mực ống, nấm kim châm và rau lẩu chua cay', 5, 1),
(21, 'Ba chỉ bò Mỹ cuộn nấm', 85000, 'Ba chỉ bò Mỹ nướng sốt BBQ kèm nấm kim châm', 5, 1),
(22, 'Cánh gà chiên nước mắm', 65000, 'Cánh gà vàng ruộm ngấm sốt mắm tỏi ớt đậm đà', 5, 1),
(23, 'Sườn nướng tảng sốt tiêu', 120000, 'Sườn heo non ướp tiêu đen nướng than hoa thơm lừng', 5, 1),
(24, 'Salad bò dầu giấm', 55000, 'Thịt bò áp chảo trộn xà lách sốt dầu giấm thanh mát', 6, 1),
(25, 'Nộm ngó sen tôm thịt', 65000, 'Ngó sen giòn ngọt, tôm thịt kèm bánh phồng tôm', 6, 1),
(26, 'Chả giò hải sản chiên xù', 50000, 'Chả giò tôm mực giòn rụm chấm sốt mayonnaise', 6, 1),
(27, 'Cơm bò lúc lắc khoai tây', 65000, 'Bò lúc lắc sốt bơ tỏi ăn kèm cơm và khoai tây chiên', 1, 1),
(28, 'Hủ tiếu Nam Vang đặc biệt', 55000, 'Hủ tiếu tôm thịt cật heo nước dùng hầm xương ngọt thanh', 1, 1),
(29, 'Mì Ý bò bằm sốt cà chua', 60000, 'Mì Spaghetti sốt bò bằm đậm đà chuẩn vị Ý', 1, 1),
(30, 'Trà mãng cầu xiêm', 35000, 'Trà mãng cầu tươi chua ngọt mát lạnh giải nhiệt', 3, 1),
(31, 'Trà ổi hồng hạt lựu', 35000, 'Trà ổi hồng thơm ngát kèm thạch hạt lựu giòn giòn', 3, 1),
(32, 'Sinh tố bơ sáp cốt dừa', 40000, 'Bơ sáp dẻo quánh xay béo ngậy cùng nước cốt dừa', 3, 1),
(33, 'Nước dừa xiêm ướp lạnh', 30000, 'Dừa xiêm Bến Tre ngọt thanh tự nhiên 100%', 3, 1),
(34, 'Sữa chua dẻo dâu tây', 32000, 'Sữa chua dẻo cắt miếng phủ dâu tây tươi và sốt', 4, 1)
ON DUPLICATE KEY UPDATE ten_mon = VALUES(ten_mon), gia = VALUES(gia), ma_danhmuc = VALUES(ma_danhmuc);

-- 5. Thêm công thức định lượng (cong_thuc_mon)
INSERT INTO cong_thuc_mon (ma_mon, nl_id, so_luong_dung) VALUES
(20, 20, 0.200),
(20, 9,  0.100),
(20, 8,  0.100),
(20, 21, 0.150),
(21, 22, 0.200),
(21, 21, 0.100),
(22, 23, 0.300),
(23, 6,  0.350),
(24, 2,  0.120),
(24, 26, 0.150),
(25, 24, 0.200),
(25, 9,  0.080),
(26, 25, 1.000),
(26, 9,  0.080),
(27, 29, 0.150),
(27, 4,  0.100),
(27, 10, 0.150),
(28, 27, 0.180),
(28, 9,  0.060),
(29, 28, 0.150),
(29, 2,  0.100),
(30, 5,  0.020),
(30, 30, 0.150),
(31, 5,  0.020),
(31, 31, 0.150),
(32, 32, 0.200),
(33, 33, 1.000),
(34, 34, 1.000)
ON DUPLICATE KEY UPDATE so_luong_dung = VALUES(so_luong_dung);

-- 6. Phiếu nhập kho ban đầu cho các nguyên liệu mới
INSERT INTO phieu_nhap_xuat (loai_phieu, ngay_nhap_xuat, so_luong, don_gia, tong_tien, ghi_chu, nl_id, ncc_id, nv_id, kho_id) VALUES
('NHAP', DATE_SUB(NOW(), INTERVAL 3 DAY), 30, 45000, 1350000, 'Nhập cốt lẩu Thái Tomyum đợt 1', 20, 3, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 3 DAY), 25, 40000, 1000000, 'Nhập nấm kim châm tươi Hàn Quốc', 21, 1, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 2 DAY), 30, 190000, 5700000, 'Nhập ba chỉ bò Mỹ nhập khẩu', 22, 2, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 2 DAY), 35, 65000, 2275000, 'Nhập cánh gà tươi CP', 23, 2, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 2 DAY), 20, 50000, 1000000, 'Nhập ngó sen tươi Đồng Tháp', 24, 1, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 40, 20000, 800000, 'Nhập bánh tráng cuốn chả giò rế', 25, 1, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 25, 30000, 750000, 'Nhập xà lách & cà chua bi Đà Lạt', 26, 1, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 30, 35000, 1050000, 'Nhập hủ tiếu gạo Sa Đéc', 27, 1, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 25, 45000, 1125000, 'Nhập mì Ý Spaghetti Barilla', 28, 3, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 20, 260000, 5200000, 'Nhập thăn bò cắt hạt lựu lúc lắc', 29, 2, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 30, 40000, 1200000, 'Nhập mãng cầu xiêm Tiền Giang', 30, 5, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 30, 35000, 1050000, 'Nhập ổi hồng ruột đỏ Bến Tre', 31, 5, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 25, 55000, 1375000, 'Nhập bơ sáp Đắk Lắk dẻo béo', 32, 5, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 50, 15000, 750000, 'Nhập dừa xiêm ngọt Bến Tre', 33, 5, 2, 1),
('NHAP', DATE_SUB(NOW(), INTERVAL 1 DAY), 30, 40000, 1200000, 'Nhập sữa chua dẻo & dâu tây tươi', 34, 3, 2, 1);
