package com.example.demo.services.implementations;

import com.example.demo.configurations.Constants;
import com.example.demo.entities.Seller;
import com.example.demo.services.SellerService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {
    private final Gson gson;

    public SellerServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public List<Seller> seedSellers(String replace){
        try {
            String current = String.join("", Files.readAllLines
                    (Path.of(replace)));
            Seller[] sellers =gson.fromJson(current, Seller[].class);
            return Arrays.stream(sellers).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(Constants.SELLER_ERROR_NOT_FOUND);
            return null;
        } catch (NullPointerException n){
            System.out.println(Constants.SELLER_ERROR_EMPTY);
            return null;
        }
    }
}
