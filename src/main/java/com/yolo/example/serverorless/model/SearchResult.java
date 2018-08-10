package com.yolo.example.serverorless.model;

import com.amazonaws.services.dynamodbv2.model.ConsumedCapacity;

import java.util.List;

/**
 * Created by mandrewes on 7/08/2018.
 */
public class SearchResult {
    private long timeTaken;
    private int rowsProcessed;
//    private int totalMatches;
    private int resultsReturned;
    private int limitedAt;
    private String indexUsed;
    private Integer scannedCount;
    private Integer count;
    private ConsumedCapacity consumedCapacity;
    private List<DocDescriptor> results;


    public String getIndexUsed() {
        return indexUsed;
    }

    public void setIndexUsed(String indexUsed) {
        this.indexUsed = indexUsed;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public int getResultsReturned() {
        return resultsReturned;
    }

    public void setResultsReturned(int resultsReturned) {
        this.resultsReturned = resultsReturned;
    }

    public int getLimitedAt() {
        return limitedAt;
    }

    public void setLimitedAt(int limitedAt) {
        this.limitedAt = limitedAt;
    }

    public List<DocDescriptor> getResults() {
        return results;
    }

    public void setResults(List<DocDescriptor> results) {
        this.results = results;
    }

    public int getRowsProcessed() {
        return rowsProcessed;
    }

    public void setRowsProcessed(int rowsProcessed) {
        this.rowsProcessed = rowsProcessed;
    }

    public Integer getScannedCount() {
        return scannedCount;
    }

    public void setScannedCount(Integer scannedCount) {
        this.scannedCount = scannedCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ConsumedCapacity getConsumedCapacity() {
        return consumedCapacity;
    }

    public void setConsumedCapacity(ConsumedCapacity consumedCapacity) {
        this.consumedCapacity = consumedCapacity;
    }
}
