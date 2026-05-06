package com.example.ss9.bai4.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String shopName;
    private String shopAddress;

    // getter/setter
}