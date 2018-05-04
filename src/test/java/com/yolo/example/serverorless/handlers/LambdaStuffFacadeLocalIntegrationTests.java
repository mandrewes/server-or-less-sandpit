package com.yolo.example.serverorless.handlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yolo.example.serverorless.model.ApiGatewayResponse;
import com.yolo.example.serverorless.model.Stuff;
import com.yolo.example.serverorless.services.lambda.SaveStuffLambdaFacade;
import com.yolo.example.serverorless.utils.LamdaSpringUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;

public class LambdaStuffFacadeLocalIntegrationTests extends AbstractDynamoTest {


//    @Autowired
    SaveStuffLambdaFacade saveStuffFacade;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("dynamo.region","us-west-2");
        System.setProperty("dynamo.endpoint","http://localhost:8000");
        super.setUp();

        LamdaSpringUtil.setGlobalRootContextPath("/spring/test-app-ctx.xml");
        LamdaSpringUtil.wireInSpring(this,"nada");

        saveStuffFacade = new SaveStuffLambdaFacade();//LamdaSpringUtil.getCtx().getBean(SaveStuffLambdaFacade.class);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void shouldSetAndGetMessage() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = "stuff " + new Date();

        Stuff s1 = new Stuff();
        s1.setContent(msg);
        APIGatewayProxyRequestEvent setMessageEvent = new APIGatewayProxyRequestEvent();
        setMessageEvent.setBody(objectMapper.writeValueAsString(s1));

        ApiGatewayResponse r1 = saveStuffFacade.handleRequest(setMessageEvent, null);
        Stuff saveStuffResponse = objectMapper.readValue(r1.getBody(),Stuff.class);
//
        Assert.assertNotNull(saveStuffResponse.getId());



    }


}
