# ĐỒ ÁN MÔN HỌC: HỆ THỐNG QUẢN LÝ QUÁN ĂN
**Nhóm thực hiện:** Nhóm 10  
**Công nghệ:** Java 21, Spring Boot 4.x, Spring Data JPA, Hibernate, MySQL, Spring Security, JWT, BCrypt, Swagger/OpenAPI, Postman  

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
    - API Đăng nhập: `POST /api/tai-khoan/dang-nhap` (kiểm tra trạng thái tài khoản, mật khẩu, xác thực vai trò).
    - API Đổi mật khẩu: `POST /api/tai-khoan/doi-mat-khau`.
    - API Khoá/Mở khoá tài khoản: `PUT /api/tai-khoan/{id}/trang-thai`.
- **Quản lý Hồ sơ Nhân sự:**
  - `NhanVienService.java` & `NhanVienController.java`:
    - API Tạo nhân viên trọn gói: `POST /api/nhan-vien/tao-tron-goi` (khởi tạo liên kết `TaiKhoan` -> `NguoiDung` -> `NhanVien` với transaction `@Transactional`).
    - API Cập nhật lương và chức vụ: `PUT /api/nhan-vien/{id}/luong-chuc-vu`.
- **Quy trình Thanh toán & Giải phóng Bàn:**
  - `ThanhToanService.java` & `ThanhToanController.java`:
    - API Thanh toán đơn hàng: `POST /api/thanh-toan/thuc-hien`.
    - Tự động chuyển trạng thái đơn hàng sang `DA_THANH_TOAN` và giải phóng bàn ăn về `TRONG`.

#### 👤 2. Nguyễn Ngọc Minh Thư
- **Quản lý Nhập / Xuất Kho & Cập nhật Tồn kho tự động:**
  - `KhoService.java` & `PhieuNhapXuatController.java`:
    - API Lập phiếu Nhập/Xuất: `POST /api/phieu-nhap-xuat/lap-phieu`.
    - Tự động cộng tồn khi nhập, trừ tồn khi xuất, chặn xuất nếu thiếu hàng.
- **Báo cáo & Cảnh báo Tồn kho:**
  - `NguyenLieuController.java`:
    - API Báo cáo tồn kho: `GET /api/nguyen-lieu/bao-cao-ton-kho` (phân loại `AN_TOAN`, `SAP_HET`, `HET_HANG`).
    - Tìm kiếm nguyên liệu theo tên và lọc theo Nhà cung cấp.

#### 👤 3. Nguyễn Xuân Quý
- **Mở Bàn, Đặt Món & Tự Động Tính Tiền:**
  - `DonHangService.java` & `DonHangController.java`:
    - API Mở bàn tạo đơn: `POST /api/don-hang/mo-ban-tao-don` (đổi trạng thái bàn sang `CO_KHACH`).
    - API Thêm món: `POST /api/don-hang/them-mon` (tự tính thành tiền và cộng dồn vào tổng tiền đơn).
    - API Huỷ món: `DELETE /api/don-hang/huy-mon/{maCtdh}`.
    - API Chi tiết hoá đơn: `GET /api/don-hang/{maDon}/chi-tiet-hoa-don`.
    - API Huỷ đơn hàng: `PUT /api/don-hang/{maDon}/huy-don`.

---

### 📍 BUỔI 4: BẢO MẬT JWT, TÍCH HỢP LIÊN MODULE & KIỂM THỬ TOÀN DIỆN

#### 👤 1. Đặng Vũ Thanh Phát (Bảo mật JWT, BCrypt, 401/403 & Doanh thu)
- **Nâng cấp Bảo mật toàn diện:**
  - Tích hợp **BCrypt Password Encoder** mã hóa an toàn mật khẩu người dùng trong cơ sở dữ liệu.
  - Xây dựng **JwtTokenProvider** & **JwtAuthenticationFilter**: tự động sinh mã Token JWT khi đăng nhập và chặn các request gửi thiếu Bearer Token.
  - Phân quyền theo vai trò (Role-based Authorization):
    - Cấu hình bắt lỗi **401 Unauthorized** khi chưa đăng nhập hoặc Token sai/hết hạn (`JwtAuthenticationEntryPoint`).
    - Cấu hình bắt lỗi **403 Forbidden** khi tài khoản không có quyền truy cập API chức năng (`CustomAccessDeniedHandler`).
    - Các API quản lý nhân sự & phân quyền chỉ cho phép `ADMIN` hoặc `QUAN_LY`.
- **Thống kê Doanh thu theo ngày:**
  - API: `GET /api/thanh-toan/doanh-thu-theo-ngay?ngay=YYYY-MM-DD`
  - Thống kê tổng số tiền thu được, tổng số đơn đã thanh toán và phân loại theo hình thức thanh toán (Tiền mặt, Chuyển khoản, Thẻ).

#### 👤 2. Nguyễn Ngọc Minh Thư (Tự trừ kho theo công thức & Thống kê Kho)
- **Tính năng liên thông Tự động trừ kho theo công thức món ăn:**
  - `KhoService.java`: Hàm `truKhoKhiBanMon(maMon, soLuongMon, nvId, khoId)`.
  - Khi một món ăn được chế biến, hệ thống tra cứu định lượng trong bảng `cong_thuc_mon` và **tự động trừ số lượng nguyên liệu tương ứng trong kho**, đồng thời tạo bản ghi Phiếu Xuất Kho tự động.
  - **Kiểm soát chặt chẽ:** Nếu bất kỳ nguyên liệu nào không đủ số lượng trong kho, hệ thống ném ngoại lệ chặn lại ngay lập tức.
- **Thống kê Nhập / Xuất kho:**
  - API: `GET /api/phieu-nhap-xuat/thong-ke?tuNgay=...&denNgay=...`
  - Báo cáo tổng số phiếu nhập, tổng số phiếu xuất, tổng tiền nhập hàng, tổng tiền xuất dùng và số nguyên liệu đang ở mức báo động sắp hết.

#### 👤 3. Nguyễn Xuân Quý (Vòng đời đơn hàng, Chặn sửa/xóa đơn đã thanh toán & Mã lỗi chuẩn)
- **Quản lý Vòng đời trạng thái đơn hàng:**
  - API: `PUT /api/don-hang/{id}/trang-thai?trangThai=...`
  - Kiểm soát luồng trạng thái chặt chẽ: `MOI` ➔ `DANG_CHE_BIEN` ➔ `HOAN_THANH` ➔ `DA_THANH_TOAN` / `HUY`.
- **Chốt chặn nghiệp vụ quan trọng:**
  - Tuyệt đối **KHÔNG ĐƯỢC PHÉP sửa hoặc xóa đơn hàng đã thanh toán thành công** (trả về lỗi HTTP 400 Bad Request nếu vi phạm).
  - Không cho phép gọi thêm món hay huỷ món vào đơn hàng đã thanh toán hoặc đã đóng.
- **Xử lý mã lỗi chuẩn HTTP (GlobalExceptionHandler):**
  - Trả về mã lỗi **404 Not Found** khi ID món ăn, bàn ăn hoặc đơn hàng không tồn tại.
  - Trả về mã lỗi **400 Bad Request** khi tham số sai hoặc vi phạm ràng buộc nghiệp vụ.
  - Kết nối trực tiếp với hàm trừ kho của bạn Thư ngay khi phục vụ thêm món vào đơn.

#### 👥 4. Kiểm thử Tích hợp Cả Nhóm & Kịch bản Postman (E2E Testing)
- Đã xuất bản file kịch bản Postman chuẩn: **`database/QuanLyQuanAn_Buoi4_Postman_Collection.json`**.
- File gồm 4 thư mục kiểm thử đầy đủ kịch bản:
  1. Test riêng module bạn Phát: Đăng nhập thành công (lấy Token), đăng nhập sai (400), truy cập không token (401), thống kê doanh thu.
  2. Test riêng module bạn Thư: Lập phiếu nhập kho, xuất kho quá tồn kho (400), báo cáo cảnh báo tồn, thống kê nhập/xuất.
  3. Test riêng module bạn Quý: Mở bàn, gọi món, tính tiền, gọi món ID sai (404), xóa đơn đã thanh toán (400).
  4. **Test Luồng Tích Hợp Toàn Bộ Nhóm (Khép kín E2E):**
     * Đăng nhập lấy Token ➔ Mở bàn (bàn sang `CO_KHACH`) ➔ Gọi món (tự tính tiền + tự trừ kho nguyên liệu theo công thức) ➔ Bếp cập nhật trạng thái `HOAN_THANH` ➔ Thu ngân bấm Thanh toán (đơn sang `DA_THANH_TOAN`, bàn tự giải phóng về `TRONG`) ➔ Xem Doanh thu được cộng dồn theo ngày.

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

### 3. Kiểm thử qua Postman
1. Mở ứng dụng **Postman** trên máy tính.
2. Nhấn nút **Import** ở góc trên bên trái ➔ Chọn file `database/QuanLyQuanAn_Buoi4_Postman_Collection.json`.
3. Bấm chạy lần lượt từng request trong các thư mục để kiểm tra kết quả và chụp hình làm báo cáo.

### 4. Kiểm thử qua Swagger UI
- Truy cập [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html) để kiểm thử trực quan trên trình duyệt.
