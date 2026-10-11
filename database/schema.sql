-- ==========================================================
-- QUẢN LÝ QUÁN ĂN - NHÓM 10
-- Schema MySQL 8.x theo ERD cuối cùng
-- ==========================================================
DROP DATABASE IF EXISTS quan_ly_quan_an;
CREATE DATABASE quan_ly_quan_an CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE quan_ly_quan_an;

-- ==========================================================
-- NHÓM TÀI KHOẢN - NGƯỜI DÙNG - NHÂN VIÊN - PHÂN QUYỀN (Phát)
-- ==========================================================
CREATE TABLE phan_quyen (
    pq_id      INT AUTO_INCREMENT PRIMARY KEY,
    ten_pq     VARCHAR(50) NOT NULL UNIQUE,
    mo_ta      TEXT,
    trang_thai TINYINT NOT NULL DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE tai_khoan (
    tai_khoan_id INT AUTO_INCREMENT PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    pq_id        INT NOT NULL,
    trang_thai   TINYINT NOT NULL DEFAULT 1,
    CONSTRAINT fk_taikhoan_phanquyen FOREIGN KEY (pq_id) REFERENCES phan_quyen(pq_id)
) ENGINE=InnoDB;

CREATE TABLE nguoi_dung (
    user_id       INT AUTO_INCREMENT PRIMARY KEY,
    ho_ten        VARCHAR(100) NOT NULL,
    ngay_sinh     DATE,
    gioi_tinh     VARCHAR(10),
    so_dien_thoai VARCHAR(20),
    dia_chi       TEXT,
    trang_thai    TINYINT NOT NULL DEFAULT 1,
    tai_khoan_id  INT NOT NULL UNIQUE,
    CONSTRAINT fk_nguoidung_taikhoan FOREIGN KEY (tai_khoan_id) REFERENCES tai_khoan(tai_khoan_id)
) ENGINE=InnoDB;

CREATE TABLE nhan_vien (
    nv_id         INT AUTO_INCREMENT PRIMARY KEY,
    user_id       INT NOT NULL UNIQUE,
    chuc_vu       VARCHAR(50),
    luong         DECIMAL(10,2) NOT NULL DEFAULT 0 CHECK (luong >= 0),
    ngay_vao_lam  DATE,
    trang_thai    TINYINT NOT NULL DEFAULT 1,
    CONSTRAINT fk_nhanvien_nguoidung FOREIGN KEY (user_id) REFERENCES nguoi_dung(user_id)
) ENGINE=InnoDB;

-- ==========================================================
-- NHÓM MÓN ĂN & ĐƠN HÀNG (Quý)
-- ==========================================================
CREATE TABLE danh_muc_mon_an (
    ma_danhmuc  INT AUTO_INCREMENT PRIMARY KEY,
    ten_danhmuc VARCHAR(100) NOT NULL UNIQUE,
    mo_ta       TEXT,
    trang_thai  TINYINT NOT NULL DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE mon_an (
    ma_mon      INT AUTO_INCREMENT PRIMARY KEY,
    ten_mon     VARCHAR(100) NOT NULL,
    gia         DECIMAL(10,2) NOT NULL CHECK (gia >= 0),
    hinh_anh    VARCHAR(255),
    mo_ta       TEXT,
    trang_thai  TINYINT NOT NULL DEFAULT 1,
    ma_danhmuc  INT NOT NULL,
    CONSTRAINT fk_monan_danhmuc FOREIGN KEY (ma_danhmuc) REFERENCES danh_muc_mon_an(ma_danhmuc)
) ENGINE=InnoDB;

CREATE TABLE ban_an (
    ma_ban      INT AUTO_INCREMENT PRIMARY KEY,
    ten_ban     VARCHAR(50) NOT NULL UNIQUE,
    so_cho_ngoi INT NOT NULL CHECK (so_cho_ngoi > 0),
    trang_thai  VARCHAR(50) NOT NULL DEFAULT 'TRONG'   -- TRONG / DANG_SU_DUNG
) ENGINE=InnoDB;

CREATE TABLE don_hang (
    ma_don     INT AUTO_INCREMENT PRIMARY KEY,
    ngay_dat   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tong_tien  DECIMAL(10,2) NOT NULL DEFAULT 0 CHECK (tong_tien >= 0),
    trang_thai VARCHAR(50) NOT NULL DEFAULT 'MOI',     -- MOI / DANG_CHE_BIEN / HOAN_THANH / DA_THANH_TOAN / HUY
    ma_ban     INT NOT NULL,
    ma_nv      INT NOT NULL,
    CONSTRAINT fk_donhang_ban FOREIGN KEY (ma_ban) REFERENCES ban_an(ma_ban),
    CONSTRAINT fk_donhang_nv  FOREIGN KEY (ma_nv)  REFERENCES nhan_vien(nv_id)
) ENGINE=InnoDB;

CREATE TABLE chi_tiet_don_hang (
    ma_ctdh    INT AUTO_INCREMENT PRIMARY KEY,
    so_luong   INT NOT NULL CHECK (so_luong > 0),
    don_gia    DECIMAL(10,2) NOT NULL CHECK (don_gia >= 0),
    thanh_tien DECIMAL(10,2) NOT NULL CHECK (thanh_tien >= 0),
    ma_don     INT NOT NULL,
    ma_mon     INT NOT NULL,
    CONSTRAINT fk_ctdh_don FOREIGN KEY (ma_don) REFERENCES don_hang(ma_don) ON DELETE CASCADE,
    CONSTRAINT fk_ctdh_mon FOREIGN KEY (ma_mon) REFERENCES mon_an(ma_mon)
) ENGINE=InnoDB;

CREATE TABLE thanh_toan (
    ma_tt       INT AUTO_INCREMENT PRIMARY KEY,
    phuong_thuc VARCHAR(50) NOT NULL,                  -- TIEN_MAT / CHUYEN_KHOAN / THE
    so_tien     DECIMAL(10,2) NOT NULL CHECK (so_tien >= 0),
    thoi_gian   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    trang_thai  VARCHAR(50) NOT NULL DEFAULT 'THANH_CONG',
    ma_don      INT NOT NULL UNIQUE,                   -- 1 đơn - 1 thanh toán
    ma_nv       INT NOT NULL,
    CONSTRAINT fk_tt_don FOREIGN KEY (ma_don) REFERENCES don_hang(ma_don),
    CONSTRAINT fk_tt_nv  FOREIGN KEY (ma_nv)  REFERENCES nhan_vien(nv_id)
) ENGINE=InnoDB;

-- ==========================================================
-- NHÓM KHO - NGUYÊN LIỆU - NHÀ CUNG CẤP (Thư)
-- ==========================================================
CREATE TABLE nha_cung_cap (
    ncc_id        INT AUTO_INCREMENT PRIMARY KEY,
    ten_ncc       VARCHAR(100) NOT NULL,
    dia_chi       TEXT,
    so_dien_thoai VARCHAR(20),
    email         VARCHAR(100),
    trang_thai    TINYINT NOT NULL DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE kho (
    kho_id     INT AUTO_INCREMENT PRIMARY KEY,
    ten_kho    VARCHAR(100) NOT NULL,
    dia_chi    TEXT,
    trang_thai TINYINT NOT NULL DEFAULT 1
) ENGINE=InnoDB;

CREATE TABLE nguyen_lieu (
    nl_id           INT AUTO_INCREMENT PRIMARY KEY,
    ten_nguyen_lieu VARCHAR(100) NOT NULL,
    don_vi_tinh     VARCHAR(20) NOT NULL,
    gia_nhap        DECIMAL(10,2) NOT NULL CHECK (gia_nhap >= 0),
    so_luong_ton    DECIMAL(10,3) NOT NULL DEFAULT 0 CHECK (so_luong_ton >= 0),
    trang_thai      TINYINT NOT NULL DEFAULT 1,
    ncc_id          INT NOT NULL,
    CONSTRAINT fk_nl_ncc FOREIGN KEY (ncc_id) REFERENCES nha_cung_cap(ncc_id)
) ENGINE=InnoDB;

CREATE TABLE cong_thuc_mon (
    ma_mon        INT NOT NULL,
    nl_id         INT NOT NULL,
    so_luong_dung DECIMAL(10,3) NOT NULL CHECK (so_luong_dung > 0),
    PRIMARY KEY (ma_mon, nl_id),
    CONSTRAINT fk_ctm_mon FOREIGN KEY (ma_mon) REFERENCES mon_an(ma_mon) ON DELETE CASCADE,
    CONSTRAINT fk_ctm_nl  FOREIGN KEY (nl_id)  REFERENCES nguyen_lieu(nl_id)
) ENGINE=InnoDB;

CREATE TABLE phieu_nhap_xuat (
    pn_id          INT AUTO_INCREMENT PRIMARY KEY,
    loai_phieu     VARCHAR(20) NOT NULL CHECK (loai_phieu IN ('NHAP','XUAT')),
    ngay_nhap_xuat DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    so_luong       DECIMAL(10,3) NOT NULL CHECK (so_luong > 0),
    don_gia        DECIMAL(10,2) NOT NULL CHECK (don_gia >= 0),
    tong_tien      DECIMAL(10,2) NOT NULL CHECK (tong_tien >= 0),
    ghi_chu        TEXT,
    nl_id          INT NOT NULL,
    ncc_id         INT NULL,                           -- NULL với phiếu xuất
    nv_id          INT NOT NULL,
    kho_id         INT NOT NULL,
    CONSTRAINT fk_pnx_nl  FOREIGN KEY (nl_id)  REFERENCES nguyen_lieu(nl_id),
    CONSTRAINT fk_pnx_ncc FOREIGN KEY (ncc_id) REFERENCES nha_cung_cap(ncc_id),
    CONSTRAINT fk_pnx_nv  FOREIGN KEY (nv_id)  REFERENCES nhan_vien(nv_id),
    CONSTRAINT fk_pnx_kho FOREIGN KEY (kho_id) REFERENCES kho(kho_id)
) ENGINE=InnoDB;
