package com.example.ss9.bai4.controller;

import com.rikkei.seller.dto.SellerRegistrationDTO;
import com.rikkei.seller.service.SellerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/seller/register")
@SessionAttributes("sellerForm")
public class SellerRegistrationController {

    private final SellerService sellerService;

    public SellerRegistrationController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    // Khởi tạo object trong session
    @ModelAttribute("sellerForm")
    public SellerRegistrationDTO initForm() {
        return new SellerRegistrationDTO();
    }

    // ===== STEP 1 =====
    @GetMapping("/step1")
    public String step1() {
        return "step1";
    }

    @PostMapping("/step1")
    public String handleStep1(
            @Valid @ModelAttribute("sellerForm") SellerRegistrationDTO form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "step1";
        }
        return "redirect:/seller/register/step2";
    }

    // ===== STEP 2 =====
    @GetMapping("/step2")
    public String step2() {
        return "step2";
    }

    @PostMapping("/step2")
    public String handleStep2(
            @Valid @ModelAttribute("sellerForm") SellerRegistrationDTO form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "step2";
        }
        return "redirect:/seller/register/step3";
    }

    // ===== STEP 3 =====
    @GetMapping("/step3")
    public String step3(Model model) {
        return "step3";
    }

    @PostMapping("/complete")
    public String complete(
            @ModelAttribute("sellerForm") SellerRegistrationDTO form,
            SessionStatus sessionStatus
    ) {
        // 1. Lưu DB
        sellerService.register(form);

        // 2. 🚨 QUAN TRỌNG: Dọn session (tránh tràn RAM)
        sessionStatus.setComplete();

        return "redirect:/seller/register/success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}