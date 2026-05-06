package com.example.ss9.bai3.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThemeController {


    @GetMapping("/")
    public String index(
            @CookieValue(value = "app_theme", defaultValue = "light") String theme,
            Model model
    ) {
        model.addAttribute("currentTheme", theme);

        return "index";
    }

    @PostMapping("/change-theme")
    public String changeTheme(
            @RequestParam("theme") String theme,
            HttpServletResponse response
    ) {
        Cookie themeCookie = new Cookie("app_theme", theme);

        int maxAge = 30 * 24 * 60 * 60;
        themeCookie.setMaxAge(maxAge);

        themeCookie.setHttpOnly(true);

        themeCookie.setPath("/");

        response.addCookie(themeCookie);

        return "redirect:/";
    }
}