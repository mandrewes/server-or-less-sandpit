package com.yolo.example.serverorless.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mandrewes on 2/08/2018.
 */
public class FrenchCheeseRecord {

    @JsonProperty("datasetid")
    private String dataSetId;

    @JsonProperty("recordid")
    private String recordId;

    @JsonProperty("fields")
    private FrenchCheese cheese;

    public String getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(String dataSetId) {
        this.dataSetId = dataSetId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public FrenchCheese getCheese() {
        return cheese;
    }

    public void setCheese(FrenchCheese cheese) {
        this.cheese = cheese;
    }
}
