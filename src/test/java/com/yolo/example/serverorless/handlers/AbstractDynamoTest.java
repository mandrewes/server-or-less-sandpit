package com.yolo.example.serverorless.handlers;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractDynamoTest {

//    static {
//
//    }
    protected DynamoDBProxyServer server = null;
    protected AmazonDynamoDB dynamodb = null;

    @BeforeMethod
    public void setUp() throws Exception {

        System.setProperty("aws.accessKeyId","its");
        System.setProperty("aws.secretKey","local");
        System.setProperty("sqlite4java.library.path", "native-libs");
        String[] localArgs = { "-inMemory" };
        server = ServerRunner.createServerFromCommandLineArgs(localArgs);
        server.start();
        dynamodb = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2")).build();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        server.stop();
    }

}
