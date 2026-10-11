# ĐỒ ÁN MÔN HỌC: HỆ THỐNG QUẢN LÝ QUÁN ĂN
**Nhóm thực hiện:** Nhóm 10  
**Công nghệ:** Java 21, Spring Boot 4.x, Spring Data JPA, Hibernate, MySQL, Swagger/OpenAPI  

---

## 👥 BẢNG TIẾN ĐỘ & PHÂN CÔNG CÔNG VIỆC THEO TUẦN

### 📍 BUỔI 2: KHỞI TẠO BỘ KHUNG BACKEND & 15 BẢNG CRUD
| STT | Thành viên | Nhiệm vụ / Module phụ trách | Các file mã nguồn (Entity, Repository, Controller) |
| :---: | :--- | :--- | :--- |
| **1** | **Nguyễn Xuân Quý** | **Quản lý Bàn ăn, Món ăn & Đơn hàng** | • `BanAn`<br>• `DanhMucMonAn`<br>• `MonAn`<br>• `DonHang`<br>• `ChiTietDonHang`<br>• Giao diện Web mẫu `index.html` |
| **2** | **Nguyễn Ngọc Minh Thư** | **Quản lý Kho, Nguyên liệu & Nhà cung cấp** | • `Kho`<br>• `NguyenLieu`<br>• `NhaCungCap`<br>• `CongThucMon` & `CongThucMonId`<br>• `PhieuNhapXuat` |
| **3** | **Đặng Vũ Thanh Phát** | **Quản lý Người dùng, Phân quyền & Thanh toán** | • `TaiKhoan`<br>• `PhanQuyen`<br>• `NguoiDung`<br>• `NhanVien`<br>• `ThanhToan` |

---

### 📍 BUỔI 3: NGHIỆP VỤ NÂNG CAO (BUSINESS LOGIC & SERVICE LAYER)
> Nhánh đang thực hiện: `buoi3-thu` (Tách từ `buoi-3`)

#### 👤 1. Đặng Vũ Thanh Phát (ĐÃ HOÀN THÀNH 100%):
1. **Xác thực Đăng nhập & Quản lý Tài khoản:**
   - `TaiKhoanService.java` & `TaiKhoanController.java`:
     - API Đăng nhập: `POST /api/tai-khoan/dang-nhap` (kiểm tra trạng thái khoá, mật khẩu, trả về vai trò và họ tên nhân viên).
     - API Đổi mật khẩu: `POST /api/tai-khoan/doi-mat-khau`.
     - API Khoá/Mở khoá tài khoản: `PUT /api/tai-khoan/{id}/trang-thai`.
2. **Quản lý Hồ sơ Nhân sự trọn gói:**
   - `NhanVienService.java` & `NhanVienController.java`:
     - API Tạo nhân viên trọn gói: `POST /api/nhan-vien/tao-tron-goi` (Tự động khởi tạo `TaiKhoan` -> `NguoiDung` -> `NhanVien` trong một giao dịch an toàn `@Transactional`).
     - API Cập nhật lương và chức vụ: `PUT /api/nhan-vien/{id}/luong-chuc-vu`.
3. **Quy trình Thanh toán & Giải phóng Bàn:**
   - `ThanhToanService.java` & `ThanhToanController.java`:
     - API Thanh toán đơn hàng: `POST /api/thanh-toan/thuc-hien`.
     - Tự động chuyển trạng thái đơn hàng sang `DA_THANH_TOAN`.
     - Tự động giải phóng bàn ăn chuyển sang trạng thái `TRONG`.
     - API Tra cứu giao dịch theo đơn: `GET /api/thanh-toan/don-hang/{maDon}`.
4. **DTOs Chuyên dụng:**
   - `LoginRequest`, `LoginResponse`, `DoiMatKhauRequest`, `TaoNhanVienRequest`, `ThanhToanRequest`.

#### 👤 2. Nguyễn Ngọc Minh Thư (ĐÃ HOÀN THÀNH 100%):
1. **Quản lý Nhập / Xuất Kho & Cập nhật Tồn kho tự động:**
   - `KhoService.java` & `PhieuNhapXuatController.java`:
     - API Lập phiếu Nhập/Xuất: `POST /api/phieu-nhap-xuat/lap-phieu`.
     - Nghiệp vụ Nhập: Tự động cộng số lượng tồn kho nguyên liệu, cập nhật đơn giá nhập mới nhất.
     - Nghiệp vụ Xuất: Tự động trừ số lượng tồn kho nguyên liệu, **chặn xuất nếu số lượng tồn trong kho không đủ** (báo lỗi tức thì).
     - API Lịch sử nhập xuất theo nguyên liệu: `GET /api/phieu-nhap-xuat/lich-su/nguyen-lieu/{nlId}`.
2. **Báo cáo & Cảnh báo Tồn kho:**
   - `NguyenLieuController.java`:
     - API Báo cáo tồn kho: `GET /api/nguyen-lieu/bao-cao-ton-kho` (Tự động phân loại `AN_TOAN`, `SAP_HET`, `HET_HANG`).
     - API Tìm kiếm nguyên liệu theo tên: `GET /api/nguyen-lieu/tim-kiem?ten=...`.
     - API Lọc nguyên liệu theo Nhà cung cấp: `GET /api/nguyen-lieu/nha-cung-cap/{nccId}`.
3. **Quản lý Định lượng & Công thức Món:**
   - `CongThucMonController.java`:
     - API Xem định lượng nguyên liệu theo món: `GET /api/cong-thuc-mon/mon/{maMon}`.
     - API Thêm/xóa định lượng nguyên liệu cho món ăn.
4. **DTOs Chuyên dụng:**
   - `NhapXuatKhoRequest`, `NguyenLieuTonKhoResponse`.

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
