package com.example.demo.services;

import com.example.demo.entities.Seller;

import java.util.List;

public interface SellerService {

    List<Seller> seedSellers(String replace);
}
