📘 QUẢN LÝ CHƯƠNG TRÌNH HỌC

📝 GIỚI THIỆU:
  Hệ thống quản lý chương trình học theo chuyên ngành, khóa học, và các loại học phần. Hỗ trợ xây dựng, tìm kiếm và kiểm tra chương trình đào tạo một cách linh hoạt, thuận tiện cho nhà trường và sinh viên.

🚀 CÁC TÍNH NĂNG CHÍNH XÂY DỰNG

I. Phân quyền: 
  - Quyền admin:
    + Quản trị
    + Thư ký
  - Quyền Sinh viên
  - Quyền Giảng viên
  
II. Quản lý học phần (Đối với quyền Admin)
  1. Thêm / sửa / xoá / cập nhật học phần
  2. Thêm / sửa / xoá / học phần thay thế
  3. Tìm kiếm học phần theo mã hoặc tên
  4. Hiển thị danh sách tất cả học phần kèm học phần thay thế

III. Quản lý chương trình học (Đối với quyền Admin)
  1. Tạo, sửa, xoá chương trình học theo: Chuyên ngành và Khoá áp dụng
  2. Phân loại chương trình học theo Loại học phần:
     - Kèm các điều kiện: Số tín chỉ tối thiểu, Mã bắt đầu được tính
  3. Phân loại Loại học phần theo tổ hợp học phần: Học phần trong tổ hợp và Học phần ngoài tổ hợp. 
  4. Kèm điều kiện tổ hợp: Bắt buộc học tất cả
                            Học số lượng môn tối thiểu
                            Học số tín chỉ tối thiểu
  5. Phân loại tổ hợp theo nhóm môn: Điều kiện nhóm môn: Bắt buộc học tất cả

IV. Hiển thị danh sách chương trình học (Cho mọi quyền)
  Hiển thị các chương trình học theo chuyên ngành và khóa
  Hiển thị mỗi chương trình học theo loại học phần, tổ hợp, nhóm môn kèm các điều kiện
  Hiển thị kèm thao tác xử lí (CRUD) đối với quyền Admin

V. Tìm kiếm (Cho mọi quyền)
1. Tìm kiếm học phần theo Mã học phần / Tên học phần / Keyword
2. Tìm kiếm học phần thuộc chương trình học nào theo Mã học phần / Tên học phần / Keyword. 
  - Trả về các chương trình học của khóa - chuyên ngành chứa học phần đó, kèm loại học phần
3. Tìm kiếm học phần để thêm vào chương trình học
Đối với quyền Admin có thể thao tác CRUD trên kết quả tìm kiếm

VI. Lọc học phần còn thiếu 
Trả về danh sách các loại học phần mà sinh viên còn thiếu (chưa học hoặc đang học chưa có điểm), dựa theo chương trình học của chuyên ngành và khóa.
- Đối với quyền admin + Giảng viên: Tìm kiếm học phần còn thiếu bằng cách nhập Mã sinh viên:
- Đối với quyền Sinh viên: Chỉ xem được học phần còn thiếu của chính mình
  
Logic xử lý lọc học phần còn thiếu:
1. Tìm sinh viên từ maSinhVien 
  - Từ chuyên ngành (nganh) và khóa (khoa), tìm chương trình học tương ứng.    
2. Lấy dữ liệu liên quan:
  - Tất cả học phần (HocPhan)
  - Danh sách điểm (BangDiem) của sinh viên
  - Danh sách đăng ký học phần đang học (DangKy)
  - Danh sách học phần thay thế (HocPhanThayThe)
3. Xác định các mã học phần đã qua (điểm >= 0):
  - Bao gồm cả học phần gốc và học phần thay thế
4. Thêm học phần đang học (chưa có điểm):
  - Và cả mã gốc nếu học phần đang học là học phần thay thế
5. Duyệt từng tổ hợp trong loại học phần:
  - Nếu tổ hợp là nhóm môn: kiểm tra đã hoàn thành một nhóm chưa        
  - Nếu bắt buộc học tất cả: kiểm tra thiếu học phần nào      
  - Nếu có điều kiện tín chỉ tối thiểu hoặc số môn tối thiểu: kiểm tra số tín chỉ hoặc số môn đã học      
6. Xử lý prefix maBatDauDuocTinh nếu có:
  - Dùng để cộng tín chỉ từ các mã học phần bắt đầu bằng các prefix      
  - Bỏ qua học phần đã thuộc loại học phần khác hoặc đã có trong loại này
7. Xử lý học phần ngoài tổ hợp
  - Với mỗi học phần yêu cầu trong loại này, nếu chưa học => đưa vào danh sách thiếu.

🏗️ CÔNG NGHỆ SỬ DỤNG
Backend: Java Spring Boot (REST API)	
Frontend: HTML/CSS/JS thuần (jQuery)	
Database: MySQL / H2 (demo)

🗃️ CẤU TRÚC DỰ ÁN
src/main/java/com/thanglong/chonlichthilai/chuongtrinhhoc

├── controller        // Các controller xử lý request
├── dto              // Định nghĩa các lớp truyền dữ liệu
├── entity           // Các entity ánh xạ database
├── mapper           // Các lớp ánh xạ giữa entity và DTO
├── repository       // Các interface JPA repository
└── service          // Logic xử lý và nghiệp vụ

src/main/java/com/thanglong/chonlichthilai/hocphan
src/main/java/com/thanglong/chonlichthilai/sinhvien
src/main/resources/static
└── chuongTrinhHoc.html

