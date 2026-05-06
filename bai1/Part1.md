Phần 1: Phân tích logic - Tại sao Giỏ hàng bị "mất trí nhớ"?
Vấn đề nằm ở việc chọn nhầm "Phạm vi lưu trữ" (Scope) của dữ liệu.

Cơ chế của HttpServletRequest: Đối tượng request chỉ có vòng đời trong một lần yêu cầu duy nhất từ trình duyệt gửi lên 
. Ngay sau khi server phản hồi (response) xong, đối tượng request đó sẽ bị hủy bỏ.

Cơ chế của redirect: Khi bạn dùng lệnh return "redirect:/checkout", server sẽ gửi về trình duyệt một mã phản hồi 
(thường là 302). Trình duyệt nhận được sẽ tự động tạo một Request hoàn toàn mới đến đường dẫn /checkout.

Điểm gãy logic: * Ở /add-to-cart, bạn lưu giỏ hàng vào request.

Khi lệnh redirect xảy ra, cái request cũ (chứa giỏ hàng) đã "tan thành mây khói".

Tại /checkout, bạn cố gắng lấy giỏ hàng từ một request mới tinh, nên kết quả luôn là null.

Kết luận: Lỗi do bạn sử dụng cơ chế lưu trữ của HttpServletRequest (vốn chỉ sống trong 1 request) để phục vụ một luồng 
nghiệp vụ cần duy trì dữ liệu qua nhiều request (Redirect).