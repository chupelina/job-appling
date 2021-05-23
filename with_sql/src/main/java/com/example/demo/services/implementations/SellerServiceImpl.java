package com.example.demo.services.implementations;

import com.example.demo.configurations.Constants;
import com.example.demo.entities.Report;
import com.example.demo.entities.Seller;
import com.example.demo.repositories.SellerRepository;
import com.example.demo.services.SellerService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


@Service
public class SellerServiceImpl implements SellerService {
    private final Gson gson;
    private final SellerRepository sellerRepository;

    public SellerServiceImpl(Gson gson, SellerRepository sellerRepository) {
        this.gson = gson;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean seedSellers(String replace){
        try {
            String current = String.join("", Files.readAllLines
                    (Path.of(replace)));
            Seller[] sellers =gson.fromJson(current, Seller[].class);
            Arrays.stream(sellers).forEach(sellerRepository::save);
            return  true;
        } catch (IOException e) {
            System.out.println(Constants.SELLER_ERROR_NOT_FOUND);
            return false;
        } catch (NullPointerException n){
            System.out.println(Constants.SELLER_ERROR_EMPTY);
            return false;
        }
    }

    @Override
    public String findAllSellersByReport(Report report) {
        List<Seller> sellers = sellerRepository.findAllBySalesPeriodIsLessThanEqual(report.getPeriodLimit());
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.FIRST_LINE_RESULT).append(System.lineSeparator());
        if(sellers.isEmpty()){
            sb.append(Constants.NO_SELLERS);
        }else{
            for (Seller seller : sellers) {
                double score = report.isUseExprienceMultiplier()
                        ?
                        seller.getTotalSales() * 1.0 / seller.getSalesPeriod()
                        :
                        seller.getTotalSales() * 1.0 / seller.getSalesPeriod() * seller.getExperienceMultiplier();
                sb.append(seller.getName()).append(", ").append(score).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
