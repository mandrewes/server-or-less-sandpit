package com.yolo.example.serverorless.utils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.IOException;


public abstract class AbstractLambdaSpringService<C, R> implements RequestHandler<APIGatewayProxyRequestEvent, R> {
    private static final Logger LOG = Logger.getLogger(AbstractLambdaSpringService.class);

    public AbstractLambdaSpringService() {
        BasicConfigurator.configure();
    }

    protected void wire(Object o) {
        LamdaSpringUtil.wireInSpring(o, o.getClass().getSimpleName());
    }

    public abstract R handleRequest(APIGatewayProxyRequestEvent proxyEvent, C model, Context context);

    public abstract Class getModelClass();

    public R handleRequest(APIGatewayProxyRequestEvent proxyEvent, Context context) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Lambda is supposed to magically do this - but didnt seem to work for me after a few go's
        C model = null;
        try {
            if ( proxyEvent.getBody() != null && proxyEvent.getBody().length() > 0) {
                model = (C) objectMapper.readValue(proxyEvent.getBody(), getModelClass());
            }

            return this.handleRequest(proxyEvent, model, context);
        } catch (IOException e) {
            LOG.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }


}
