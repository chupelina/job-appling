package com.example.demo.services;

import com.example.demo.entities.Report;

public interface SellerService {

    boolean seedSellers(String replace);

    String findAllSellersByReport(Report report);
}
