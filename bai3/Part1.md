BÁO CÁO PHÂN TÍCH VÀ THIẾT KẾ GIẢI PHÁP: TÍNH NĂNG DARK MODE
1. Phân tích Luồng Dữ liệu (I/O)
   Đầu vào (Input): Lựa chọn giao diện của người dùng (Sáng hoặc Tối) gửi từ Browser.

Xử lý (Process): Server tạo Cookie, thiết lập bảo mật và thời gian sống 30 ngày.

Đầu ra (Output): Trình duyệt lưu trữ cấu hình và tự động gửi kèm trong các lần truy cập sau.

2. Lựa chọn Công cụ Lưu trữ: Cookie hay Session?
   Dựa trên yêu cầu của phòng Marketing, chúng ta chọn Cookie thay vì Session vì các lý do sau:

Đối với Session:

Lưu trữ trên máy chủ và sẽ bị xóa ngay khi người dùng đóng trình duyệt (tắt máy đi ngủ).

Không đáp ứng được yêu cầu lưu trữ dài hạn (30 ngày).

Đối với Cookie:

Lưu trữ trực tiếp tại trình duyệt của khách hàng.

Cho phép thiết lập thuộc tính Max-Age, giúp dữ liệu tồn tại bền vững ngay cả khi tắt máy tính.

Kết luận: Đây là lựa chọn duy nhất đáp ứng đúng nghiệp vụ đề ra.

3. Thiết kế Giải pháp & Chống bẫy dữ liệu (XSS)
   Vấn đề bảo mật
   Kẻ gian có thể dùng thẻ <script> (tấn công XSS) để truy cập biến document.cookie nhằm đánh cắp thông tin cấu hình.

Giải pháp kỹ thuật bảo mật
Để chặn đứng hành vi này, chúng ta áp dụng các thiết lập sau cho Cookie:

Cờ HttpOnly (Bắt buộc): Thiết lập setHttpOnly(true). Khi bật cờ này, trình duyệt sẽ cấm hoàn toàn các đoạn mã JavaScript
truy cập vào Cookie. Hacker không thể dùng document.cookie để lấy dữ liệu.

Cấu hình thời hạn sống: Thiết lập setMaxAge(2592000) (tương đương 30 ngày) để đảm bảo tính năng ghi nhớ hoạt động đúng 
như yêu cầu của Marketing.

Phạm vi áp dụng: Thiết lập setPath("/") để Cookie có hiệu lực trên toàn bộ trang web RikkeiMall.

4. Tổng kết quy trình vận hành
   Bước 1: Client gửi yêu cầu thay đổi Theme lên Server qua @PostMapping.

Bước 2: Server tạo Cookie, gắn cờ HttpOnly và thời hạn 30 ngày, sau đó gửi về Client.

Bước 3: Ở các lần truy cập sau, Server dùng @CookieValue để đọc cấu hình và hiển thị đúng màu sắc giao diện mà người dùng đã chọn.