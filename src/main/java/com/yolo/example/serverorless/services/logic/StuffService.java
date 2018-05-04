package com.yolo.example.serverorless.services.logic;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.yolo.example.serverorless.model.Stuff;
import com.yolo.example.serverorless.utils.AbstractDynamoBackedService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StuffService extends AbstractDynamoBackedService {
    public static final String TMP_TABLE_NAME = "Stuff";

    @PostConstruct
    public void init() {
        try {
            DescribeTableResult desc = dynamodb.describeTable(TMP_TABLE_NAME);
        } catch (ResourceNotFoundException rne) {
            CreateTableRequest req = mapper.generateCreateTableRequest(Stuff.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
            dynamodb.createTable(req);
        }
    }

    public Stuff saveStuff(Stuff s) {
        mapper.save(s);
        return s;
    }

    public Stuff getStuffById(String id) {
        Stuff ret = mapper.load(Stuff.class, id);
        return ret;
    }

    public List<Stuff> listStuff() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        // .withFilterExpression("Price < :val1 and ProductCategory = :val2").withExpressionAttributeValues(eav);

        PaginatedScanList<Stuff> page = mapper.scan(Stuff.class, scanExpression);

        List<Stuff> ret = new ArrayList<>();

        page.forEach((s) -> ret.add(s));

        return ret;
    }
}
