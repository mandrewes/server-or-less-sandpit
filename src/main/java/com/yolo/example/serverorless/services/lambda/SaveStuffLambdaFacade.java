package com.yolo.example.serverorless.services.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.yolo.example.serverorless.model.ApiGatewayResponse;
import com.yolo.example.serverorless.model.Stuff;
import com.yolo.example.serverorless.services.logic.StuffService;
import com.yolo.example.serverorless.utils.AbstractLambdaSpringService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;


public class SaveStuffLambdaFacade extends AbstractLambdaSpringService<Stuff, ApiGatewayResponse> {

    private static final Logger LOG = Logger.getLogger(SaveStuffLambdaFacade.class);

    @Autowired
    private StuffService stuffService;

    public SaveStuffLambdaFacade() {
        super();
        wire(this);
    }

    @Autowired
    @Required
    public void setStuffService(StuffService stuffService) {
        this.stuffService = stuffService;
    }

    @Override
    public Class getModelClass() {
        return Stuff.class;
    }


    @Override
    public ApiGatewayResponse handleRequest(APIGatewayProxyRequestEvent proxyEvent, Stuff model, Context context) {

        Stuff ret = stuffService.saveStuff(model);

        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(ret)
                .build();
    }

}
