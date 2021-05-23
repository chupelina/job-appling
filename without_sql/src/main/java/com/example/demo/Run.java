package com.example.demo;

import com.example.demo.configurations.Constants;
import com.example.demo.entities.Report;
import com.example.demo.entities.Seller;
import com.example.demo.services.ReportService;
import com.example.demo.services.SellerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

@Component
public class Run implements CommandLineRunner {
    private final SellerService sellerService;
    private final ReportService reportService;

    public Run(SellerService sellerService, ReportService reportService) {
        this.sellerService = sellerService;
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Seller> sellers = new ArrayList<>();
        while (sellers.isEmpty()) {
            System.out.println("Please enter the path to the sellers file(starting from your local disk and ending with the file name with its extension, with '\\' for separator):");
            sellers = sellerService.seedSellers(scanner.nextLine().trim().replace("\\", "/"));
        }
        Report report = null;
        while (report == null) {
            System.out.println("Please enter the path to the report file(starting from your local disk and ending with the file name with its extension, with '\\' for separator):");
           report = reportService.getReport(scanner.nextLine().trim().replace("\\", "/"));
        }
        String output = formattingResult(creatingMap(sellers, report));
        System.out.println("Do you want to enter a path for the new file(Y/N)?");
        String answer = scanner.nextLine().trim();
        String path;
        if(answer.compareToIgnoreCase("y")==0){
            System.out.println("Enter the new file path(starting from your local disk and ending with the file name with its extension, with '\\' for separator):");
            path = scanner.nextLine().trim().replace("\\", "/");
        }else{
           path = Constants.RESULT_PATH;
        }
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        writer.write(output);
        writer.close();
        System.out.printf("Everything was fine, please check %s%n", path);
    }

    private static String formattingResult(Map<String, Double> entries) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.FIRST_LINE_RESULT).append(System.lineSeparator());
        if (entries.isEmpty()) {
            sb.append(Constants.NO_SELLERS);
        } else {
            for (Map.Entry<String, Double> stringDoubleEntry : entries.entrySet()) {
                sb.append(stringDoubleEntry.getKey()).append(", ").append(stringDoubleEntry.getValue())
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    private static Map<String, Double> creatingMap(List<Seller> sellers, Report report) {
        Map<String, Double> map = new LinkedHashMap<>();
        for (Seller seller : sellers) {
            if (seller.getSalesPeriod() <= report.getPeriodLimit()) {
                double score = report.isUseExprienceMultiplier()
                        ?
                        seller.getTotalSales() * 1.0 / seller.getSalesPeriod()
                        :
                        seller.getTotalSales() * 1.0 / seller.getSalesPeriod() * seller.getExperienceMultiplier();
                map.put(seller.getName(), score);
            }
        }
        return map;
    }
}
