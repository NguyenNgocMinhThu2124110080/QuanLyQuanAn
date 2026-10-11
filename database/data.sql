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
INSERT INTO danh_muc_mon_an (ten_danhmuc, mo_ta) VALUES
('Món chính', 'Cơm, phở, bún...'),
('Món ăn vặt', 'Khoai tây chiên, nem chua...'),
('Nước uống', 'Nước ngọt, bia, trà đá...'),
('Tráng miệng', 'Trái cây, chè...');

-- 6. Món ăn
INSERT INTO mon_an (ten_mon, gia, mo_ta, ma_danhmuc) VALUES
('Cơm gà nướng', 55000, 'Cơm tấm gà nướng mật ong', 1),
('Phở bò', 60000, 'Phở bò tái nạm', 1),
('Khoai tây chiên', 30000, 'Khoai tây chiên giòn', 2),
('Trà đào cam sả', 35000, 'Trà đào thanh mát', 3);

-- 7. Bàn ăn
INSERT INTO ban_an (ten_ban, so_cho_ngoi) VALUES
('Bàn 01', 4), ('Bàn 02', 4), ('Bàn 03', 2), ('Bàn VIP 01', 8);

-- 8. Nhà cung cấp
INSERT INTO nha_cung_cap (ten_ncc, dia_chi, so_dien_thoai, email) VALUES
('Vựa rau Đà Lạt', 'Chợ đầu mối Nông Sản', '0281111111', 'rau@ncc.vn'),
('Cửa hàng Thịt Sạch Bình Minh', 'Q.3, TP.HCM', '0282222222', 'binhminh@ncc.vn'),
('Đại lý Đồ uống Hòa Bình', 'Q.5, TP.HCM', '0283333333', 'hoabinh@ncc.vn');

-- 9. Kho
INSERT INTO kho (ten_kho, dia_chi) VALUES
('Kho chính', 'Tầng trệt'),
('Kho lạnh',  'Sau bếp');

-- 10. Nguyên liệu
INSERT INTO nguyen_lieu (ten_nguyen_lieu, don_vi_tinh, gia_nhap, so_luong_ton, ncc_id) VALUES
('Thịt gà',    'kg',  90000, 20.000, 2),
('Thịt bò',    'kg', 250000, 15.000, 2),
('Rau mồng tơi', 'kg', 15000, 10.000, 1),
('Khoai tây',  'kg',  25000, 30.000, 1),
('Trà đen',    'kg', 120000,  5.000, 3);

-- 11. Công thức món
INSERT INTO cong_thuc_mon (ma_mon, nl_id, so_luong_dung) VALUES
(1, 1, 0.200),
(2, 2, 0.150),
(3, 4, 0.300),
(4, 5, 0.050);

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
