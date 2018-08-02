
package com.yolo.example.serverorless.model;

/**
 * Created by mandrewes on 2/08/2018.
 */
public class Pesticide {

    private String sampleId;
    private String commodity;
    private String name;
    private String state;

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
}
