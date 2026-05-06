package com.example.ss9.bai2.controller;

import jakarta.enterprise.inject.Model;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class CartController {

    // API 1: Khách hàng bấm nút Thêm vào giỏ
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") String productId, HttpSession session) {
        // Lấy giỏ hàng từ Session thay vì Request
        List<String> cart = (List<String>) session.getAttribute("myCart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        cart.add(productId);

        // Lưu lại vào Session để các Request sau (như /checkout) có thể đọc được
        session.setAttribute("myCart", cart);

        // Chuyển hướng sang trang thanh toán
        return "redirect:/checkout";
    }

    // API 2: Hiển thị trang thanh toán
    @GetMapping("/checkout")
    public String viewCheckout(HttpSession session, Model model) {
        // Lấy giỏ hàng từ Session
        List<String> cart = (List<String>) session.getAttribute("myCart");

        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống!");
        } else {
            model.addAttribute("message", "Bạn có " + cart.size() + " sản phẩm trong giỏ hàng.");
        }

        return "checkout-page";
    }

    @GetMapping("/home")
    public String homePage(
            @CookieValue(value = "guest_name", defaultValue = "Khách lạ") String guestName,
            Model model
    ) {
        // Lúc này guestName sẽ không bao giờ bị null.
        // Nếu không có Cookie, nó sẽ mang giá trị "Khách lạ"

        if (guestName.equals("Khách lạ")) {
            model.addAttribute("msg", "Chào khách lạ!");
        } else {
            model.addAttribute("msg", "Chào mừng " + guestName + " trở lại!");
        }

        return "home-page";
    }
}