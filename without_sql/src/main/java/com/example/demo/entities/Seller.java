package com.example.demo.entities;

public class Seller {
    private String name;
    private Integer totalSales;
    private Integer salesPeriod;
    private Double experienceMultiplier;

    public Seller() {
    }

    public String getName() {
        return name;
    }

    public Seller setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public Seller setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
        return this;
    }

    public Integer getSalesPeriod() {
        return salesPeriod;
    }

    public Seller setSalesPeriod(Integer salesPeriod) {
        this.salesPeriod = salesPeriod;
        return this;
    }

    public Double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public Seller setExperienceMultiplier(Double experienceMultiplier) {
        this.experienceMultiplier = experienceMultiplier;
        return this;
    }
}
