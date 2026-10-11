# ĐỒ ÁN MÔN HỌC: HỆ THỐNG QUẢN LÝ QUÁN ĂN
**Nhóm thực hiện:** Nhóm 10  
**Công nghệ:** Java 21, Spring Boot 4.x, Spring Data JPA, Hibernate, MySQL, Swagger/OpenAPI  

---

## 👥 BẢNG PHÂN CÔNG CÔNG VIỆC (BUỔI 2: XÂY DỰNG BACKEND & CRUD)

| STT | Thành viên | Nhiệm vụ / Module phụ trách | Các file mã nguồn (Entity, Repository, Controller) |
| :---: | :--- | :--- | :--- |
| **1** | **Nguyễn Xuân Quý** | **Quản lý Bàn ăn, Món ăn & Đơn hàng** | • `BanAn` (Entity, Repository, Controller)<br>• `DanhMucMonAn`<br>• `MonAn`<br>• `DonHang`<br>• `ChiTietDonHang`<br>• Giao diện Web mẫu `index.html` |
| **2** | **Nguyễn Ngọc Minh Thư** | **Quản lý Kho, Nguyên liệu & Nhà cung cấp** | • `Kho` (Entity, Repository, Controller)<br>• `NguyenLieu`<br>• `NhaCungCap`<br>• `CongThucMon` & `CongThucMonId`<br>• `PhieuNhapXuat` |
| **3** | **Đặng Vũ Thanh Phát** | **Quản lý Người dùng, Phân quyền & Thanh toán** | • `TaiKhoan` (Entity, Repository, Controller)<br>• `PhanQuyen`<br>• `NguoiDung`<br>• `NhanVien`<br>• `ThanhToan` |

> 📌 *Lưu ý: Trong từng file mã nguồn Java, đầu mỗi class đều có chú thích `// PHẦN CỦA: [TÊN THÀNH VIÊN]` để thầy/cô tiện tra cứu và chấm điểm.*

---

## 🚀 HƯỚNG DẪN CÀI ĐẶT & CHẠY DỰ ÁN

### 1. Chuẩn bị Cơ sở dữ liệu (MySQL / XAMPP)
1. Khởi động **MySQL** trong XAMPP (Port mặc định: `3306`).
2. Nhập file CSDL nằm trong thư mục `database/`:
   - `database/schema.sql` (Cấu trúc 15 bảng)
   - `database/data.sql` (Dữ liệu mẫu tiếng Việt)

### 2. Khởi chạy Backend
1. Mở dự án trong VSCode / IntelliJ IDEA.
2. Cấu hình cổng: `9090` (được cấu hình trong `application.properties`).
3. Chạy ứng dụng thông qua file `DemoApplication.java` hoặc lệnh:
   ```bash
   ./mvnw clean spring-boot:run
   ```

### 3. Kiểm thử API & Giao diện
- **Giao diện Web:** Truy cập [http://localhost:9090/](http://localhost:9090/) (Tài khoản: `admin` / Mật khẩu: `123456`)
- **Tài liệu API Swagger UI:** Truy cập [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html) để test đầy đủ 15 Controller.
