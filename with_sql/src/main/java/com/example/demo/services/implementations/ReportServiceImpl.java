package com.example.demo.services.implementations;

import com.example.demo.configurations.Constants;
import com.example.demo.entities.Report;
import com.example.demo.services.ReportService;
import com.example.demo.services.SellerService;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ReportServiceImpl implements ReportService {
    private final Gson gson;
    private final SellerService sellerService;

    public ReportServiceImpl(Gson gson, SellerService sellerService) {
        this.gson = gson;
        this.sellerService = sellerService;
    }

    @Override
    public String getReport(String replace) {
        try {
            String reportString = String.join("", Files.readAllLines
                    (Path.of(replace)));
            Report report = gson.fromJson(reportString, Report.class);
            return sellerService.findAllSellersByReport(report);

        } catch (IOException e) {
            System.out.println(Constants.REPORT_ERROR_NOT_FOUND);
            return null;
        } catch (NullPointerException n) {
            System.out.println(Constants.REPORT_ERROR_EMPTY);
            return null;
        }
    }
}
