package com.example.demo.repositories;

import com.example.demo.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    List<Seller> findAllBySalesPeriodIsLessThanEqual(int salePeriod);
}
