USE quan_ly_quan_an;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE phan_quyen;
TRUNCATE TABLE tai_khoan;
TRUNCATE TABLE nguoi_dung;
TRUNCATE TABLE nhan_vien;
TRUNCATE TABLE danh_muc_mon_an;
TRUNCATE TABLE mon_an;
TRUNCATE TABLE ban_an;
TRUNCATE TABLE don_hang;
TRUNCATE TABLE chi_tiet_don_hang;
TRUNCATE TABLE thanh_toan;
TRUNCATE TABLE nha_cung_cap;
TRUNCATE TABLE kho;
TRUNCATE TABLE nguyen_lieu;
TRUNCATE TABLE cong_thuc_mon;
TRUNCATE TABLE phieu_nhap_xuat;

SET FOREIGN_KEY_CHECKS = 1;

-- 1. Phân quyền
INSERT INTO phan_quyen (ten_pq, mo_ta) VALUES
('ADMIN', 'Quản trị viên toàn quyền'),
('QUAN_LY', 'Quản lý cửa hàng'),
('NHAN_VIEN_PHUC_VU', 'Nhân viên phục vụ bàn'),
('DAU_BEP', 'Đầu bếp');

-- 2. Tài khoản
INSERT INTO tai_khoan (username, password, email, pq_id) VALUES
('admin', '123456', 'admin@quan.vn', 1),
('quanly01', '123456', 'ql01@quan.vn', 2),
('phucvu01', '123456', 'pv01@quan.vn', 3),
('daubep01', '123456', 'db01@quan.vn', 4);

-- 3. Người dùng
INSERT INTO nguoi_dung (ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, dia_chi, tai_khoan_id) VALUES
('Trần Văn Quản', '1990-01-01', 'Nam', '0901234567', 'Q.1, TP.HCM', 1),
('Lê Thị Lý', '1992-05-15', 'Nữ', '0912345678', 'Q.2, TP.HCM', 2),
('Nguyễn Văn Phục', '1998-10-20', 'Nam', '0923456789', 'Q.Bình Thạnh, TP.HCM', 3),
('Phạm Thị Bếp', '1985-12-30', 'Nữ', '0934567890', 'Q.Gò Vấp, TP.HCM', 4);

-- 4. Nhân viên
INSERT INTO nhan_vien (user_id, chuc_vu, luong, ngay_vao_lam) VALUES
(1, 'Chủ quán', 20000000, '2023-01-01'),
(2, 'Quản lý', 15000000, '2023-01-01'),
(3, 'Phục vụ', 8000000, '2023-06-01'),
(4, 'Đầu bếp chính', 18000000, '2023-01-01');

-- 5. Danh mục món ăn
INSERT INTO danh_muc_mon_an (ma_danhmuc, ten_danhmuc, mo_ta) VALUES
(1, 'Món chính', 'Cơm, phở, bún, mì...'),
(2, 'Món ăn vặt', 'Khoai tây chiên, nem chua, gà rán...'),
(3, 'Nước uống', 'Nước ngọt, cà phê, trà trái cây...'),
(4, 'Tráng miệng', 'Trái cây, chè, flan, sữa chua...'),
(5, 'Món lẩu & Nướng', 'Các set lẩu hải sản, lẩu gà, thịt nướng than hoa'),
(6, 'Khai vị & Salad', 'Salad rau tươi, nộm ngó sen, chả giò giòn rụm');

-- 6. Món ăn
INSERT INTO mon_an (ma_mon, ten_mon, gia, mo_ta, ma_danhmuc) VALUES
(1, 'Cơm gà nướng mật ong', 55000, 'Cơm tấm gà nướng mật ong vàng ruộm kèm đồ chua', 1),
(2, 'Phở bò tái nạm đặc biệt', 60000, 'Phở bò nước hầm xương 24h, kèm quẩy giòn', 1),
(3, 'Khoai tây chiên giòn bơ tỏi', 30000, 'Khoai tây chiên giòn rụm thơm lừng sốt bơ tỏi', 2),
(4, 'Trà đào cam sả thanh mát', 35000, 'Trà đào thơm nức hòa quyện cam tươi và sả', 3),
(5, 'Cơm sườn nướng mật ong', 45000, 'Cơm tấm sườn nướng mật ong đậm đà thơm ngậy', 1),
(6, 'Cơm sườn bì chả nướng', 50000, 'Cơm tấm sườn nướng mỡ hành đậm đà', 1),
(7, 'Bún bò Huế giò heo', 55000, 'Bún bò cay nồng chuẩn vị cố đô', 1),
(8, 'Mì xào giòn hải sản', 65000, 'Tôm mực tươi rói sốt nấm rau củ', 1),
(9, 'Cơm chiên Dương Châu', 45000, 'Cơm chiên hạt vàng lạp xưởng tôm khô', 1),
(10, 'Nem chua rán Hà Nội', 35000, 'Nem chua lăn bột chiên xù chấm tương ớt', 2),
(11, 'Gà rán sốt cay Hàn Quốc', 48000, 'Gà giòn tan đẫm sốt cay ngọt mè rang', 2),
(12, 'Mực chiên giòn muối tiêu', 65000, 'Mực ống giòn thơm kèm tương ớt xí muội', 2),
(13, 'Trà sữa trân châu đường đen', 38000, 'Trà sữa đậm vị kèm trân châu hoàng kim dai giòn', 3),
(14, 'Cà phê muối béo ngậy', 28000, 'Cà phê phin truyền thống phủ lớp kem muối sánh mịn', 3),
(15, 'Nước ép dưa hấu tươi', 30000, 'Nước ép dưa hấu 100% nguyên chất giải nhiệt', 3),
(16, 'Trà tắc hạt chia mật ong', 25000, 'Trà tắc mát lạnh hạt chia giòn sần sật', 3),
(17, 'Chè khúc bạch thanh mát', 35000, 'Khúc bạch phô mai hạnh nhân nước đường phèn', 4),
(18, 'Chè bưởi nước cốt dừa', 25000, 'Cùi bưởi giòn giòn hòa quyện cốt dừa béo ngậy', 4),
(19, 'Bánh Flan trứng sữa caramen', 20000, 'Bánh flan mềm mịn thơm phức cà phê sữa', 4),
(20, 'Lẩu Thái hải sản thập cẩm', 220000, 'Tôm sú, mực ống, nấm kim châm và rau lẩu chua cay', 5),
(21, 'Ba chỉ bò Mỹ cuộn nấm', 85000, 'Ba chỉ bò Mỹ nướng sốt BBQ kèm nấm kim châm', 5),
(22, 'Cánh gà chiên nước mắm', 65000, 'Cánh gà vàng ruộm ngấm sốt mắm tỏi ớt đậm đà', 5),
(23, 'Sườn nướng tảng sốt tiêu', 120000, 'Sườn heo non ướp tiêu đen nướng than hoa thơm lừng', 5),
(24, 'Salad bò dầu giấm', 55000, 'Thịt bò áp chảo trộn xà lách sốt dầu giấm thanh mát', 6),
(25, 'Nộm ngó sen tôm thịt', 65000, 'Ngó sen giòn ngọt, tôm thịt kèm bánh phồng tôm', 6),
(26, 'Chả giò hải sản chiên xù', 50000, 'Chả giò tôm mực giòn rụm chấm sốt mayonnaise', 6),
(27, 'Cơm bò lúc lắc khoai tây', 65000, 'Bò lúc lắc sốt bơ tỏi ăn kèm cơm và khoai tây chiên', 1),
(28, 'Hủ tiếu Nam Vang đặc biệt', 55000, 'Hủ tiếu tôm thịt cật heo nước dùng hầm xương ngọt thanh', 1),
(29, 'Mì Ý bò bằm sốt cà chua', 60000, 'Mì Spaghetti sốt bò bằm đậm đà chuẩn vị Ý', 1),
(30, 'Trà mãng cầu xiêm', 35000, 'Trà mãng cầu tươi chua ngọt mát lạnh giải nhiệt', 3),
(31, 'Trà ổi hồng hạt lựu', 35000, 'Trà ổi hồng thơm ngát kèm thạch hạt lựu giòn giòn', 3),
(32, 'Sinh tố bơ sáp cốt dừa', 40000, 'Bơ sáp dẻo quánh xay béo ngậy cùng nước cốt dừa', 3),
(33, 'Nước dừa xiêm ướp lạnh', 30000, 'Dừa xiêm Bến Tre ngọt thanh tự nhiên 100%', 3),
(34, 'Sữa chua dẻo dâu tây', 32000, 'Sữa chua dẻo cắt miếng phủ dâu tây tươi và sốt', 4);

-- 7. Bàn ăn
INSERT INTO ban_an (ten_ban, so_cho_ngoi) VALUES
('Bàn 01', 4),
('Bàn 02', 4),
('Bàn 03', 2),
('Bàn 04', 4),
('Bàn 05', 4),
('Bàn 06', 6),
('Bàn 07 (Sân vườn)', 4),
('Bàn 08 (Sân vườn)', 6),
('Bàn VIP 01 (Phòng hoa)', 8),
('Bàn VIP 02 (Phòng tiệc)', 12);

-- 8. Nhà cung cấp
INSERT INTO nha_cung_cap (ncc_id, ten_ncc, dia_chi, so_dien_thoai, email) VALUES
(1, 'Vựa rau Đà Lạt', 'Chợ đầu mối Nông Sản', '0281111111', 'rau@ncc.vn'),
(2, 'Cửa hàng Thịt Sạch Bình Minh', 'Q.3, TP.HCM', '0282222222', 'binhminh@ncc.vn'),
(3, 'Đại lý Đồ uống Hòa Bình', 'Q.5, TP.HCM', '0283333333', 'hoabinh@ncc.vn'),
(4, 'Hải sản Tươi sống Biển Đông', 'Chợ đầu mối Bình Điền, Q.8, TP.HCM', '0284444555', 'biendong@ncc.vn'),
(5, 'Nông sản & Trái cây Miền Tây', 'Huyện Cái Bè, Tỉnh Tiền Giang', '0285555666', 'mientay@ncc.vn');

-- 9. Kho
INSERT INTO kho (ten_kho, dia_chi) VALUES
(1, 'Kho chính', 'Tầng trệt'),
(2, 'Kho lạnh',  'Sau bếp');

-- 10. Nguyên liệu
INSERT INTO nguyen_lieu (nl_id, ten_nguyen_lieu, don_vi_tinh, gia_nhap, so_luong_ton, ncc_id) VALUES
(1, 'Thịt gà',    'kg',  90000, 30.000, 2),
(2, 'Thịt bò',    'kg', 250000, 20.000, 2),
(3, 'Rau mồng tơi', 'kg', 15000, 20.000, 1),
(4, 'Khoai tây',  'kg',  25000, 35.000, 1),
(5, 'Trà đen',    'kg', 120000, 15.000, 3),
(6, 'Sườn heo non', 'kg', 110000, 30.000, 2),
(7, 'Giò heo rút xương', 'kg', 85000, 25.000, 2),
(8, 'Mực ống tươi', 'kg', 140000, 25.000, 4),
(9, 'Tôm sú tươi', 'kg', 180000, 20.000, 4),
(10, 'Gạo thơm ST25', 'kg', 25000, 50.000, 1),
(11, 'Nem chua tươi', 'kg', 90000, 20.000, 2),
(12, 'Trứng gà & Sữa tươi', 'kg', 40000, 30.000, 3),
(13, 'Trân châu đường đen', 'kg', 50000, 25.000, 3),
(14, 'Cà phê Robusta mộc', 'kg', 120000, 20.000, 3),
(15, 'Kem béo & Muối hồng', 'kg', 70000, 20.000, 3),
(16, 'Dưa hấu Long An', 'kg', 18000, 40.000, 5),
(17, 'Quả tắc & Hạt chia', 'kg', 45000, 20.000, 5),
(18, 'Khúc bạch & Hạnh nhân', 'kg', 80000, 20.000, 3),
(19, 'Cùi bưởi & Cốt dừa', 'kg', 60000, 25.000, 5),
(20, 'Cốt lẩu Thái Tomyum', 'hộp', 45000, 30.000, 3),
(21, 'Nấm kim châm tươi', 'kg', 40000, 25.000, 1),
(22, 'Ba chỉ bò Mỹ', 'kg', 190000, 30.000, 2),
(23, 'Cánh gà tươi CP', 'kg', 65000, 35.000, 2),
(24, 'Ngó sen tươi', 'kg', 50000, 20.000, 1),
(25, 'Bánh tráng cuốn chả giò', 'hộp', 20000, 40.000, 1),
(26, 'Xà lách & Cà chua bi', 'kg', 30000, 25.000, 1),
(27, 'Hủ tiếu gạo Sa Đéc', 'kg', 35000, 30.000, 1),
(28, 'Mì Ý Spaghetti', 'kg', 45000, 25.000, 3),
(29, 'Thịt bò lúc lắc', 'kg', 260000, 20.000, 2),
(30, 'Mãng cầu xiêm tươi', 'kg', 40000, 30.000, 5),
(31, 'Ổi hồng ruột đỏ', 'kg', 35000, 30.000, 5),
(32, 'Trái bơ sáp Đắk Lắk', 'kg', 55000, 25.000, 5),
(33, 'Dừa xiêm tươi Bến Tre', 'trái', 15000, 50.000, 5),
(34, 'Sữa chua dẻo & Dâu tây', 'hộp', 40000, 30.000, 3);

-- 11. Công thức món
INSERT INTO cong_thuc_mon (ma_mon, nl_id, so_luong_dung) VALUES
(1, 1, 0.200),
(2, 2, 0.150),
(3, 4, 0.300),
(4, 5, 0.050),
(5, 6, 0.250),
(6, 6, 0.200),
(7, 2, 0.100),
(7, 7, 0.150),
(8, 8, 0.100),
(8, 9, 0.100),
(9, 10, 0.150),
(9, 12, 0.050),
(10, 11, 0.200),
(11, 1, 0.250),
(12, 8, 0.250),
(13, 5, 0.030),
(13, 13, 0.050),
(14, 14, 0.030),
(14, 15, 0.050),
(15, 16, 0.400),
(16, 5, 0.020),
(16, 17, 0.050),
(17, 18, 0.150),
(18, 19, 0.150),
(19, 12, 0.100),
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
(34, 34, 1.000);

-- 12. Đơn hàng (Dữ liệu mẫu)
INSERT INTO don_hang (tong_tien, trang_thai, ma_ban, ma_nv) VALUES
(90000, 'DA_THANH_TOAN', 1, 3);

-- 13. Chi tiết đơn hàng
INSERT INTO chi_tiet_don_hang (so_luong, don_gia, thanh_tien, ma_don, ma_mon) VALUES
(1, 55000, 55000, 1, 1),
(1, 35000, 35000, 1, 4);

-- 14. Thanh toán
INSERT INTO thanh_toan (phuong_thuc, so_tien, ma_don, ma_nv) VALUES
('TIEN_MAT', 90000, 1, 2);

-- 15. Phiếu nhập xuất
INSERT INTO phieu_nhap_xuat (loai_phieu, so_luong, don_gia, tong_tien, nl_id, ncc_id, nv_id, kho_id) VALUES
('NHAP', 10, 90000, 900000, 1, 2, 2, 2);
