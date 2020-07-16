package com.yolo.example.serverorless.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.yolo.example.serverorless.services.logic.StuffService;

import java.util.Date;

@DynamoDBTable(tableName= StuffService.TMP_TABLE_NAME)
public class Stuff {
    private String id;
    private Date createdTs;
    private String content;

    public Stuff () {
        createdTs = new Date();
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBHashKey (attributeName="id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return this.id;
    }

    public void setCreatedTs(Date d) {
        this.createdTs = d;
    }

    @DynamoDBAttribute(attributeName="createdTs")
    public Date getCreatedTs() {
        return this.createdTs;
    }

    @DynamoDBAttribute(attributeName="content")

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}