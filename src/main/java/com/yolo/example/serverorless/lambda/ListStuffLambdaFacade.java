package com.yolo.example.serverorless.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.yolo.example.serverorless.model.ApiGatewayResponse;
import com.yolo.example.serverorless.model.Stuff;
import com.yolo.example.serverorless.services.logic.StuffService;
import com.yolo.example.serverorless.utils.AbstractLambdaSpringService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ListStuffLambdaFacade extends AbstractLambdaSpringService<Stuff, ApiGatewayResponse> {

    private static final Logger LOG = Logger.getLogger(ListStuffLambdaFacade.class);

    @Autowired
    private StuffService stuffService;

    public ListStuffLambdaFacade() {
        super();
        wire(this);
    }

    @Override
    public Class getModelClass() {
        return Stuff.class;
    }

    @Override
    public ApiGatewayResponse handleRequest(APIGatewayProxyRequestEvent proxyEvent, Stuff model, Context context) {

        List<Stuff> ret = stuffService.listStuff();

        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(ret)
                .build();
    }

}
