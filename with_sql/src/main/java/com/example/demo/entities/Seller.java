package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer totalSales;
    private Integer salesPeriod;
    private Double experienceMultiplier;

    public Seller() {
    }

    public Long getId() {
        return id;
    }

    public Seller setId(Long id) {
        this.id = id;
        return this;
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
