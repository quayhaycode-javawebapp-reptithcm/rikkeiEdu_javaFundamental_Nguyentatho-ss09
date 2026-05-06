Phần 1: Tại sao đoạn if (guestName == null) lại vô dụng?
Lý do nằm ở Cơ chế kiểm duyệt (Validation) của Spring MVC:

Tính mặc định của @CookieValue: Giống như @RequestParam, annotation @CookieValue có một thuộc tính ngầm định là 
required = true.

Người gác cổng nghiêm khắc: Khi một request gửi đến, Spring Boot đóng vai trò như một "người gác cổng". Nó sẽ soi xét 
các tham số bạn khai báo ở hàm homePage. Nếu nó thấy bạn yêu cầu một Cookie có tên guest_name mà trong túi của khách 
hàng (Request Header) không có, nó sẽ lập tức chặn lại và ném ra ngoại lệ MissingRequestCookieException.

Hậu quả: Vì bị chặn ngay từ "vòng gửi xe", luồng xử lý không bao giờ chạm được vào bên trong thân hàm. Do đó, dòng 
code if (guestName == null) của bạn dù có viết hay đến đâu cũng trở nên vô nghĩa vì nó chưa kịp chạy thì chương trình 
đã báo lỗi 400 rồi.