package com.example.ss9.bai4.repository;


import com.rikkei.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}