package com.yolo.example.serverorless.model;

import java.util.Map;

public class SearchRequest {

    private String folder;
    private String groupId;
    private String customerId;
    private String path;
    private Map<String,String> freeForm;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getFreeForm() {
        return freeForm;
    }

    public void setFreeForm(Map<String, String> freeForm) {
        this.freeForm = freeForm;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
