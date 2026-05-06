PHẦN 1 - PHÂN TÍCH VÀ ĐỀ XUẤT GIẢI PHÁP
Giải pháp 1: Sử dụng input hidden (Client-side)

Ý tưởng:
Dữ liệu của các bước trước sẽ được nhúng vào HTML dưới dạng các thẻ input hidden. Khi người dùng chuyển sang bước tiếp 
theo, toàn bộ dữ liệu (bao gồm cả dữ liệu cũ và mới) sẽ được gửi lại về server.

Cách hoạt động:

Bước 1: Người dùng nhập dữ liệu và submit. Server trả về trang Bước 2 kèm theo các input hidden chứa dữ liệu của Bước 1.
Bước 2: Người dùng nhập thêm dữ liệu và submit. Server trả về trang Bước 3 kèm theo input hidden chứa dữ liệu của Bước 1
và Bước 2.
Bước 3: Khi submit, server nhận toàn bộ dữ liệu và thực hiện lưu vào database.

Ví dụ:

#<input type="hidden" name="fullName" th:value="${user.fullName}">
<input type="hidden" name="email" th:value="${user.email}">

Ưu điểm:

Không sử dụng session nên không tiêu tốn bộ nhớ server.
Dễ quan sát và debug vì dữ liệu nằm trực tiếp trong HTML.

Nhược điểm:

Người dùng có thể chỉnh sửa dữ liệu bằng công cụ trình duyệt (F12), dẫn đến rủi ro bảo mật.
Khi số lượng trường dữ liệu lớn, form sẽ trở nên cồng kềnh.
Việc quản lý và bảo trì trở nên khó khăn khi form có nhiều bước và nhiều trường dữ liệu.
Giải pháp 2: Sử dụng @SessionAttributes (Server-side)

Ý tưởng:
Dữ liệu được lưu tạm thời trên server trong session. Mỗi bước chỉ gửi phần dữ liệu mới, còn dữ liệu cũ được giữ lại 
trong session.

Cách hoạt động:

Bước 1: Dữ liệu được lưu vào một object và đưa vào session.
Bước 2: Dữ liệu mới được cập nhật vào object đã có trong session.
Bước 3: Toàn bộ dữ liệu được lấy ra từ session để lưu vào database. Sau đó session sẽ được xóa.

Ví dụ:

@Controller
@SessionAttributes("sellerForm")
public class SellerController {
}

Ưu điểm:

Dữ liệu không hiển thị ở phía client nên an toàn hơn.
Code gọn hơn, không cần truyền dữ liệu qua lại bằng hidden field.
Dễ quản lý khi số lượng trường dữ liệu lớn.

Nhược điểm:

Tốn bộ nhớ server nếu có nhiều người dùng đồng thời.
Cần xử lý vòng đời của session cẩn thận để tránh lỗi hoặc rò rỉ bộ nhớ.
PHẦN 2 - SO SÁNH VÀ LỰA CHỌN

So sánh theo từng tiêu chí:

Về bảo mật:
Giải pháp dùng input hidden có mức độ bảo mật thấp vì người dùng có thể chỉnh sửa dữ liệu trên trình duyệt trước khi 
gửi đi.
Giải pháp dùng session có mức độ bảo mật cao hơn vì dữ liệu được lưu trên server.

Về độ phức tạp khi lập trình:
Giải pháp input hidden đơn giản hơn vì không cần quản lý session.
Giải pháp session phức tạp hơn do cần cấu hình và xử lý vòng đời dữ liệu.

Về quản lý dữ liệu:
Giải pháp input hidden khó quản lý khi dữ liệu nhiều và form có nhiều bước.
Giải pháp session giúp quản lý tập trung dữ liệu trong một object duy nhất.

Về hiệu năng và bộ nhớ:
Giải pháp input hidden không sử dụng bộ nhớ server nên an toàn về tài nguyên.
Giải pháp session có thể gây tăng sử dụng bộ nhớ nếu nhiều người dùng không hoàn thành form.

Về rủi ro tràn bộ nhớ:
Giải pháp input hidden gần như không có rủi ro này.
Giải pháp session có rủi ro nếu không xóa session sau khi hoàn tất.

Kết luận lựa chọn

Giải pháp phù hợp hơn là sử dụng @SessionAttributes.

Lý do:

Đảm bảo an toàn dữ liệu vì không cho phép người dùng chỉnh sửa trực tiếp.
Dễ quản lý khi form có nhiều trường dữ liệu.
Phù hợp với các hệ thống thực tế yêu cầu tính bảo mật cao.