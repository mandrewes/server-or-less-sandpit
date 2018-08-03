package com.yolo.example.serverorless.handlers;

import com.yolo.example.serverorless.model.CollectionStatistics;
import com.yolo.example.serverorless.services.GenerateRandomRecordService;
import org.testng.annotations.Test;

public class GenerateRandomRecordServiceTest extends AbstractDynamoTest {

    GenerateRandomRecordService svc;

    @Test
    public void shouldPrint() {
        svc = new GenerateRandomRecordService();
        svc.init();

        CollectionStatistics stats = svc.generateNRandomDescriptors(100, "test", 0);
        System.out.println(stats);
    }
}
