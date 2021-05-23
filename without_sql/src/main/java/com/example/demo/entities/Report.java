package com.example.demo.entities;

import javax.persistence.criteria.CriteriaBuilder;

public class Report{

    private Integer topPerformersThreshold;
    private boolean useExprienceMultiplier;
    private Integer periodLimit;

    public Report() {
    }

    public Integer getTopPerformersThreshold() {
        return topPerformersThreshold;
    }

    public Report setTopPerformersThreshold(Integer topPerformersThreshold) {
        this.topPerformersThreshold = topPerformersThreshold;
        return this;
    }

    public boolean isUseExprienceMultiplier() {
        return useExprienceMultiplier;
    }

    public Report setUseExprienceMultiplier(boolean useExprienceMultiplier) {
        this.useExprienceMultiplier = useExprienceMultiplier;
        return this;
    }

    public Integer getPeriodLimit() {
        return periodLimit;
    }

    public Report setPeriodLimit(Integer periodLimit) {
        this.periodLimit = periodLimit;
        return this;
    }
}
