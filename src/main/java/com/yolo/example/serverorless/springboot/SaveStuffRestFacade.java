package com.yolo.example.serverorless.springboot;

import com.yolo.example.serverorless.lambda.ListStuffLambdaFacade;
import com.yolo.example.serverorless.model.Stuff;
import com.yolo.example.serverorless.services.logic.StuffService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class SaveStuffRestFacade {

    private static final Logger LOG = Logger.getLogger(ListStuffLambdaFacade.class);

    @Autowired
    private StuffService stuffService;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }


    @RequestMapping("/list")
    @ResponseBody
    public List<Stuff> listStuff() {
        List<Stuff> ret = stuffService.listStuff();
        return ret;
    }

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SampleController.class, args);
//    }
}