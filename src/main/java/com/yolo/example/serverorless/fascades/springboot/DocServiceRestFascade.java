package com.yolo.example.serverorless.fascades.springboot;

import com.yolo.example.serverorless.model.CollectionStatistics;
import com.yolo.example.serverorless.model.DocDescriptor;
import com.yolo.example.serverorless.model.SearchRequest;
import com.yolo.example.serverorless.services.DocService;
import com.yolo.example.serverorless.services.GenerateRandomRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@EnableAutoConfiguration
public class DocServiceRestFascade {

    private static final Logger LOG = Logger.getLogger(DocServiceRestFascade.class);

    @Autowired
    private DocService docService;

    @RequestMapping(value = "/docs/any", method = RequestMethod.GET)
    @ResponseBody
    public List<DocDescriptor> generateNDocs() {
        List<DocDescriptor> ret = docService.retrieveSomeDocs();
        return ret;
    }

    @RequestMapping(value = "/docs/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public DocDescriptor getDoc(@PathVariable String id) {
        DocDescriptor ret = docService.getDocDescriptor(id);

        return ret;
    }

    @RequestMapping(value = "/docs/search", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<DocDescriptor> searchDocs(@RequestBody SearchRequest req) {
        List<DocDescriptor> ret = docService.searchDocuments(req);
        return ret;
    }


}