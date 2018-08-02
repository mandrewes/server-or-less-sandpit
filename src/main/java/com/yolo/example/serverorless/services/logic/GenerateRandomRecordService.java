package com.yolo.example.serverorless.services.logic;

import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yolo.example.serverorless.model.DocDescriptor;
import com.yolo.example.serverorless.model.FrenchCheeseRecord;
import com.yolo.example.serverorless.model.Pesticide;
import com.yolo.example.serverorless.model.PesticideRecord;
import com.yolo.example.serverorless.utils.AbstractDynamoBackedService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by mandrewes on 2/08/2018.
 */
@Component
public class GenerateRandomRecordService extends AbstractDynamoBackedService {
    public static final String TMP_TABLE_NAME = "DocMeta";
    ObjectMapper jsonMapper = null;

    @PostConstruct
    public void init() {
        jsonMapper = new ObjectMapper();
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            DescribeTableResult desc = dynamodb.describeTable(TMP_TABLE_NAME);
        } catch (ResourceNotFoundException rne) {
            CreateTableRequest req = mapper.generateCreateTableRequest(DocDescriptor.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
            dynamodb.createTable(req);
        }
    }

    public long generateNRandom(long n) {
        List<FrenchCheeseRecord> cheeses ; getCheeses();
        List<Pe`>
    }

    public List<FrenchCheeseRecord> getCheeses() {
        try {
            InputStream is = new ClassPathResource("/frenchcheese.json").getInputStream();
            String json = FileCopyUtils.copyToString(new InputStreamReader(is));
            List<FrenchCheeseRecord> recs = jsonMapper.readValue(json, new TypeReference<List<FrenchCheeseRecord>>() {
            });
            return recs;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<PesticideRecord> getPesticides() {
        try {
            InputStream is = new ClassPathResource("/pdp.json").getInputStream();
            String json = FileCopyUtils.copyToString(new InputStreamReader(is));
            List<PesticideRecord> recs = jsonMapper.readValue(json, new TypeReference<List<FrenchCheeseRecord>>() {
            });
            return recs;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
