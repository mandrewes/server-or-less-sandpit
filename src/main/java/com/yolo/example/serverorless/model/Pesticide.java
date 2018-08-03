
package com.yolo.example.serverorless.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mandrewes on 2/08/2018.
 */
public class Pesticide {

    @JsonProperty("sample_id")
    private String sampleId;

    @JsonProperty("commodity")
    private String commodity;

    @JsonProperty("pesticide_name")
    private String name;

    @JsonProperty("state")
    private String state;

    @JsonProperty("pesticide_code")
    private String code;

    @JsonProperty("laboratory")
    private String laboratory;

    @JsonProperty("concentration")
    private String concentration;

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getConcentration() {
        return concentration;
    }

    public void setConcentration(String concentration) {
        this.concentration = concentration;
    }
}
