package com.yolo.example.serverorless.springboot;

import com.yolo.example.serverorless.services.logic.DocService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
public class CreateNRandomRecordsRestFascade {

    private static final Logger LOG = Logger.getLogger(CreateNRandomRecordsRestFascade.class);

    @Autowired
    private DocService stuffService;

    @RequestMapping(value = "/meta/generate", method = RequestMethod.GET)
    @ResponseBody
    public String listStuff() {
//        List<StuffService> ret = stuffService.listStuff();
        return "OK";
    }

//
//    @RequestMapping(value = "/stuff/",method = RequestMethod.POST, produces="application/json", consumes="application/json")
//    @ResponseBody
//    public Stuff listStuff(@RequestBody Stuff s) {
//        Stuff ret = stuffService.saveStuff(s);
//        return ret;
//    }

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(SampleController.class, args);
//    }
}