package com.example.ss9.bai4.dto;

import jakarta.validation.constraints.*;

public class SellerRegistrationDTO {

    // STEP 1
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    // STEP 2
    @NotBlank(message = "Tên shop không được để trống")
    private String shopName;

    @NotBlank(message = "Địa chỉ shop không được để trống")
    private String shopAddress;

    // getter/setter
}