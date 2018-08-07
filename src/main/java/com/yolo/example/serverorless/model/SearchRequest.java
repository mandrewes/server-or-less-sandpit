package com.yolo.example.serverorless.model;

import java.util.Map;

public class SearchRequest {

    private String folder;
    private String groupId;
    private String customerId;
    private String accountId;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
