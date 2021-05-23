package com.example.demo;

import com.example.demo.configurations.Constants;
import com.example.demo.services.ReportService;
import com.example.demo.services.SellerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

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
        boolean isSeed= false;
        while (!isSeed){
            System.out.println("Please enter the path to the sellers file(eg. C:\\users\\user\\desktop\\sellers.json):");
            isSeed = sellerService.seedSellers(scanner.nextLine().trim().replace("\\", "/"));
        }

        String output = null;
        while (output==null){
            System.out.println("Please enter the path to the report file(eg. C:\\users\\user\\desktop\\report.json):");
            output = reportService.getReport(scanner.nextLine().trim().replace("\\", "/"));
        }


        System.out.println("Do you want to enter a path for the new file(Y/N)?");
        String answer = scanner.nextLine().trim();
        String path;
        if(answer.compareToIgnoreCase("y")==0){
            System.out.println("Enter the new file path(eg. C:\\users\\user\\desktop\\result.csv):");
            path = scanner.nextLine().trim().replace("\\", "/");
        }else{
           path = Constants.RESULT_PATH;
        }
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        writer.write(output);
        writer.close();
        System.out.printf("The file was saved, please check %s%n", path);
    }

}
