package com.yolo.example.serverorless.utils;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public abstract class AbstractDynamoBackedService {
    private static final Logger LOG = Logger.getLogger(AbstractDynamoBackedService.class);

    protected AmazonDynamoDB dynamodb = null;
    protected DynamoDBMapper mapper = null;
    private String region = null;
    private String endpoint = null;

    public AbstractDynamoBackedService() {

        region = System.getProperty("dynamo.region");
        endpoint = System.getProperty("dynamo.endpoint");

        Regions r = Regions.AP_SOUTHEAST_2;
        if ( region != null && region.length() > 0) {
            r = Regions.fromName(region);
        }

        if (endpoint != null && endpoint.length() > 0) {
            dynamodb = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration(endpoint, r.getName())).build();
        } else {
            dynamodb = AmazonDynamoDBClientBuilder.standard().withRegion(r).build();
        }

        mapper = new DynamoDBMapper(dynamodb);
    }

}
