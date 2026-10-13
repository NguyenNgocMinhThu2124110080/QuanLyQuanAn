# ĐỒ ÁN MÔN HỌC: HỆ THỐNG QUẢN LÝ QUÁN ĂN
**Nhóm thực hiện:** Nhóm 10  
**Công nghệ:** Java 21, Spring Boot 4.x, Spring Data JPA, Hibernate, MySQL, Swagger/OpenAPI  

---

## 👥 BẢNG PHÂN CÔNG CÔNG VIỆC THEO TUẦN

### 📍 BUỔI 2: KHỞI TẠO BỘ KHUNG BACKEND & 15 BẢNG CRUD
| STT | Thành viên | Nhiệm vụ / Module phụ trách | Các file mã nguồn (Entity, Repository, Controller) |
| :---: | :--- | :--- | :--- |
| **1** | **Nguyễn Xuân Quý** | **Quản lý Bàn ăn, Món ăn & Đơn hàng** | • `BanAn`<br>• `DanhMucMonAn`<br>• `MonAn`<br>• `DonHang`<br>• `ChiTietDonHang`<br>• Giao diện Web mẫu `index.html` |
| **2** | **Nguyễn Ngọc Minh Thư** | **Quản lý Kho, Nguyên liệu & Nhà cung cấp** | • `Kho`<br>• `NguyenLieu`<br>• `NhaCungCap`<br>• `CongThucMon` & `CongThucMonId`<br>• `PhieuNhapXuat` |
| **3** | **Đặng Vũ Thanh Phát** | **Quản lý Người dùng, Phân quyền & Thanh toán** | • `TaiKhoan`<br>• `PhanQuyen`<br>• `NguoiDung`<br>• `NhanVien`<br>• `ThanhToan` |

---

### 📍 BUỔI 3: NGHIỆP VỤ NÂNG CAO (BUSINESS LOGIC & SERVICE LAYER)

#### 👤 1. Đặng Vũ Thanh Phát
- **Xác thực Đăng nhập & Quản lý Tài khoản:**
  - `TaiKhoanService.java` & `TaiKhoanController.java`:
    - API Đăng nhập: `POST /api/tai-khoan/dang-nhap` (kiểm tra trạng thái tài khoản, mật khẩu, xác thực vai trò và thông tin cá nhân).
    - API Đổi mật khẩu: `POST /api/tai-khoan/doi-mat-khau`.
    - API Khoá/Mở khoá tài khoản: `PUT /api/tai-khoan/{id}/trang-thai`.
- **Quản lý Hồ sơ Nhân sự:**
  - `NhanVienService.java` & `NhanVienController.java`:
    - API Tạo nhân viên trọn gói: `POST /api/nhan-vien/tao-tron-goi` (khởi tạo liên kết `TaiKhoan` -> `NguoiDung` -> `NhanVien` với transaction `@Transactional`).
    - API Cập nhật lương và chức vụ: `PUT /api/nhan-vien/{id}/luong-chuc-vu`.
- **Quy trình Thanh toán & Giải phóng Bàn:**
  - `ThanhToanService.java` & `ThanhToanController.java`:
    - API Thanh toán đơn hàng: `POST /api/thanh-toan/thuc-hien`.
    - Tự động chuyển trạng thái đơn hàng sang `DA_THANH_TOAN`.
    - Tự động giải phóng bàn ăn về trạng thái `TRONG`.
    - API Tra cứu giao dịch theo đơn: `GET /api/thanh-toan/don-hang/{maDon}`.
- **DTOs liên quan:**
  - `LoginRequest`, `LoginResponse`, `DoiMatKhauRequest`, `TaoNhanVienRequest`, `ThanhToanRequest`.

#### 👤 2. Nguyễn Ngọc Minh Thư
- **Quản lý Nhập / Xuất Kho & Cập nhật Tồn kho tự động:**
  - `KhoService.java` & `PhieuNhapXuatController.java`:
    - API Lập phiếu Nhập/Xuất: `POST /api/phieu-nhap-xuat/lap-phieu`.
    - Nghiệp vụ Nhập: Tự động cộng dồn số lượng tồn kho nguyên liệu, cập nhật đơn giá nhập.
    - Nghiệp vụ Xuất: Tự động trừ số lượng tồn kho, kiểm tra và chặn xuất nếu không đủ hàng tồn.
    - API Lịch sử nhập xuất theo nguyên liệu: `GET /api/phieu-nhap-xuat/lich-su/nguyen-lieu/{nlId}`.
- **Báo cáo & Cảnh báo Tồn kho:**
  - `NguyenLieuController.java`:
    - API Báo cáo tồn kho: `GET /api/nguyen-lieu/bao-cao-ton-kho` (phân loại `AN_TOAN`, `SAP_HET`, `HET_HANG`).
    - API Tìm kiếm nguyên liệu theo tên: `GET /api/nguyen-lieu/tim-kiem?ten=...`.
    - API Lọc nguyên liệu theo Nhà cung cấp: `GET /api/nguyen-lieu/nha-cung-cap/{nccId}`.
- **Quản lý Định lượng & Công thức Món:**
  - `CongThucMonController.java`:
    - API Xem định lượng nguyên liệu theo món: `GET /api/cong-thuc-mon/mon/{maMon}`.
    - API Thêm/xóa định lượng nguyên liệu cho món ăn.
- **DTOs liên quan:**
  - `NhapXuatKhoRequest`, `NguyenLieuTonKhoResponse`.

#### 👤 3. Nguyễn Xuân Quý
- **Mở Bàn, Đặt Món & Tự Động Tính Tiền:**
  - `DonHangService.java` & `DonHangController.java`:
    - API Mở bàn tạo đơn: `POST /api/don-hang/mo-ban-tao-don` (tự động chuyển trạng thái bàn sang `CO_KHACH`).
    - API Thêm món (Gọi món): `POST /api/don-hang/them-mon` (tự động tính thành tiền và cộng dồn vào tổng tiền đơn hàng).
    - API Huỷ món khỏi đơn: `DELETE /api/don-hang/huy-mon/{maCtdh}` (tự động trừ tiền khỏi tổng đơn hàng).
    - API Chi tiết hoá đơn: `GET /api/don-hang/{maDon}/chi-tiet-hoa-don` (hiển thị danh sách món đã gọi).
    - API Huỷ đơn hàng: `PUT /api/don-hang/{maDon}/huy-don` (tự động trả bàn ăn về `TRONG`).
- **Quản lý Danh Mục & Món Ăn:**
  - `MonAnController.java` & `DanhMucMonAnController.java`:
    - API Tìm kiếm món ăn theo tên: `GET /api/mon-an/tim-kiem?ten=...`.
    - API Lọc món theo danh mục: `GET /api/mon-an/danh-muc/{maDanhmuc}`.
    - API Đổi trạng thái món ăn: `PUT /api/mon-an/{id}/trang-thai`.
- **Quản lý Bàn Ăn:**
  - `BanAnController.java`:
    - API Lấy danh sách bàn đang trống: `GET /api/ban-an/ban-trong`.
    - API Cập nhật trạng thái bàn: `PUT /api/ban-an/{id}/trang-thai`.
- **DTOs liên quan:**
  - `TaoDonHangRequest`, `ThemMonVaoDonRequest`, `DonHangChiTietResponse`.

---

## 🚀 HƯỚNG DẪN CÀI ĐẶT & CHẠY DỰ ÁN

### 1. Chuẩn bị Cơ sở dữ liệu (MySQL / XAMPP)
1. Khởi động **MySQL** trong XAMPP (Port: `3306`).
2. Nhập file CSDL nằm trong thư mục `database/`:
   - `database/schema.sql` (Cấu trúc 15 bảng)
   - `database/data.sql` (Dữ liệu mẫu tiếng Việt)

### 2. Khởi chạy Backend
1. Mở dự án trong VSCode / IntelliJ IDEA.
2. Cấu hình cổng: `9090` (trong `application.properties`).
3. Chạy ứng dụng thông qua file `DemoApplication.java` hoặc lệnh:
   ```bash
   ./mvnw clean spring-boot:run
   ```

### 3. Kiểm thử API & Giao diện
- **Giao diện Web:** Truy cập [http://localhost:9090/](http://localhost:9090/) (Tài khoản: `admin` / Mật khẩu: `123456`)
- **Tài liệu API Swagger UI:** Truy cập [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html) để kiểm thử đầy đủ API.
