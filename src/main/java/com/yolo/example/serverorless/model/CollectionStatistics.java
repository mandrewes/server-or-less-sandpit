package com.yolo.example.serverorless.model;

import java.util.HashMap;
import java.util.Map;

public class CollectionStatistics {
    private Map<String,Integer> categoryCounts = new HashMap<>();

    private int distinctCategories;
    private int totalNonDistinctValues;
    private int avgValuesPerCategory;

    public Map<String, Integer> getCategoryCounts() {
        return categoryCounts;
    }

    public void setCategoryCounts(Map<String, Integer> categoryCounts) {
        this.categoryCounts = categoryCounts;
    }

    public int getTotalNonDistinctValues() {
        return totalNonDistinctValues;
    }

    public void setTotalNonDistinctValues(int totalNonDistinctValues) {
        this.totalNonDistinctValues = totalNonDistinctValues;
    }

    public int getDistinctCategories() {
        return distinctCategories;
    }

    public void setDistinctCategories(int distinctCategories) {
        this.distinctCategories = distinctCategories;
    }

    public int getAvgValuesPerCategory() {
        return avgValuesPerCategory;
    }

    public void setAvgValuesPerCategory(int avgValuesPerCategory) {
        this.avgValuesPerCategory = avgValuesPerCategory;
    }

    @Override
    public String toString() {
        return "CollectionStatistics{" +
                "categoryCounts=" + categoryCounts +
                ", distinctCategories=" + distinctCategories +
                ", totalNonDistinctValues=" + totalNonDistinctValues +
                ", avgValuesPerCategory=" + avgValuesPerCategory +
                '}';
    }
}
