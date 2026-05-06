package com.example.ss9.bai4.service.impl.service;

import com.rikkei.seller.dto.SellerRegistrationDTO;
import com.rikkei.seller.entity.Seller;
import com.rikkei.seller.repository.SellerRepository;
import com.rikkei.seller.service.SellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository repository;

    public SellerServiceImpl(SellerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void register(SellerRegistrationDTO dto) {
        Seller seller = new Seller();
        seller.setFullName(dto.getFullName());
        seller.setEmail(dto.getEmail());
        seller.setPhone(dto.getPhone());
        seller.setShopName(dto.getShopName());
        seller.setShopAddress(dto.getShopAddress());

        repository.save(seller);
    }
}