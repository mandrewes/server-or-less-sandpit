package com.yolo.example.serverorless.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.yolo.example.serverorless.model.ApiGatewayResponse;
import com.yolo.example.serverorless.services.logic.DocService;
import com.yolo.example.serverorless.utils.AbstractLambdaSpringService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class CreateNRandomRecords extends AbstractLambdaSpringService<Object, ApiGatewayResponse> {

    private static final Logger LOG = Logger.getLogger(CreateNRandomRecords.class);

    @Autowired
    private DocService stuffService;

    public CreateNRandomRecords() {
        super();
        wire(this);
    }

    @Override
    public Class getModelClass() {
        return Object.class;
    }

    @Override
    public ApiGatewayResponse handleRequest(APIGatewayProxyRequestEvent proxyEvent, Object model, Context context) {

//        List<Stuff> ret = stuffService.listStuff();

        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody("yoo")
                .build();
    }

}
