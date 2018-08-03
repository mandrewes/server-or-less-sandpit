package com.yolo.example.serverorless.fascades.springboot;

import com.yolo.example.serverorless.model.CollectionStatistics;
import com.yolo.example.serverorless.services.GenerateRandomRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
public class GenerationRestFascade {
    private static final Logger LOG = Logger.getLogger(GenerationRestFascade.class);

    @Autowired
    private GenerateRandomRecordService genService;

    @RequestMapping(value = "/docs/generate", method = RequestMethod.POST)
    @ResponseBody
    public CollectionStatistics generateNDocs(@RequestParam("n") int n) {

        CollectionStatistics ret = genService.generateNRandomDescriptors(n, "single", 0);

        return ret;
    }

    @RequestMapping(value = "/docs/generate/concurrent", method = RequestMethod.POST)
    @ResponseBody
    public CollectionStatistics triggerGenerateDocs(@RequestParam("jobs") int jobs
            , @RequestParam("jobSize") int jobSize
            , @RequestParam("throttleA") int throttleA
            , @RequestParam("throttleB") int throttleB
    ) {

        CollectionStatistics ret = genService.generateConcurrently(jobs, jobSize, throttleA, throttleB);

        return ret;
    }

}