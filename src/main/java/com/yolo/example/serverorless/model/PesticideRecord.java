package com.yolo.example.serverorless.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mandrewes on 2/08/2018.
 */
public class PesticideRecord {

    @JsonProperty("datasetid")
    private String dataSetId;

    @JsonProperty("recordid")
    private String recordId;

    @JsonProperty("fields")
    private Pesticide pdc;

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

    public Pesticide getPdc() {
        return pdc;
    }

    public void setPdc(Pesticide pdc) {
        this.pdc = pdc;
    }
}
