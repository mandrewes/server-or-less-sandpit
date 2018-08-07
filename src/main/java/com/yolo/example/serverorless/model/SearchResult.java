package com.yolo.example.serverorless.model;

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
}
