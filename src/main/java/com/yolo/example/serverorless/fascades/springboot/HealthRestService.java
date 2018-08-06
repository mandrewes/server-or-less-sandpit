package com.yolo.example.serverorless.fascades.springboot;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
public class HealthRestService {
    private static final Logger LOG = Logger.getLogger(HealthRestService.class);


    @RequestMapping(value = "/health", method = RequestMethod.GET)
    @ResponseBody
    public String generateNDocs() {
        return "OK";
    }

}