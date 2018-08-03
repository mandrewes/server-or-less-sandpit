package com.yolo.example.serverorless.handlers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tech.rsqn.useful.things.lambda.LambdaSpringUtil;

public class LambdaStuffFacadeLocalIntegrationTests extends AbstractDynamoTest {


//    @Autowired
//    SaveStuffLambdaFacade saveStuffFacade;

    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("dynamo.region","us-west-2");
        System.setProperty("dynamo.endpoint","http://localhost:8000");
        super.setUp();

        LambdaSpringUtil.setGlobalRootContextPath("/spring/test-app-ctx.xml");
        LambdaSpringUtil.wireInSpring(this,"nada");

//        saveStuffFacade = new SaveStuffLambdaFacade();//LambdaSpringUtil.getCtx().getBean(SaveStuffLambdaFacade.class);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void shouldSetAndGetMessage() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String msg = "stuff " + new Date();
//
//        Stuff s1 = new Stuff();
//        s1.setContent(msg);
//        APIGatewayProxyRequestEvent setMessageEvent = new APIGatewayProxyRequestEvent();
//        setMessageEvent.setBody(objectMapper.writeValueAsString(s1));
//
//        ApiGatewayResponse r1 = saveStuffFacade.handleRequest(setMessageEvent, null);
//        Stuff saveStuffResponse = objectMapper.readValue(r1.getBody(),Stuff.class);
////
//        Assert.assertNotNull(saveStuffResponse.getId());



    }


}
