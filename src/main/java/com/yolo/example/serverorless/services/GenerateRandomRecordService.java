package com.yolo.example.serverorless.services;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.codahale.metrics.Timer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yolo.example.serverorless.model.*;
import com.yolo.example.serverorless.utils.AbstractDynamoBackedService;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import tech.rsqn.useful.things.concurrency.ThreadUtil;
import tech.rsqn.useful.things.metrics.Metrics;
import tech.rsqn.useful.things.util.RandomUtil;

import javax.annotation.PostConstruct;
import javax.validation.metadata.ExecutableDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by mandrewes on 2/08/2018.
 */
@Component
public class GenerateRandomRecordService extends AbstractDynamoBackedService {
    public static final String TMP_TABLE_NAME = "DocMeta";
    private static final Logger LOG = Logger.getLogger(AbstractDynamoBackedService.class);

    ObjectMapper jsonMapper = null;
    List<FrenchCheeseRecord> cheeses;
    List<PesticideRecord> pdc;

    Executor executor;

    @PostConstruct
    public void init() {
        jsonMapper = new ObjectMapper();
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        cheeses = loadCheeses();
        pdc = loadPesticides();
        executor = Executors.newFixedThreadPool(20);
    }

    public CollectionStatistics generateConcurrently(int numJobs, final int sizePerJob, long throttleA, long throttleB) {
        for (int i = 0; i < numJobs; i++) {
            final int j = i;

            Runnable worker = new Runnable() {
                @Override
                public void run() {
                    try {
                        ThreadUtil.doSleep(throttleA);
                        LOG.info("Job " + j + " inserting " + sizePerJob + " items");
                        generateNRandomDescriptors(sizePerJob, "Job " + j, throttleB);
                        LOG.info("Job " + j + " done " + sizePerJob);
                    } catch (Exception ex) {
                        LOG.warn("Job " + j + " " + ex.getMessage(), ex);
                    }
                }
            };
            executor.execute(worker);
        }

        return new CollectionStatistics();

    }

    public CollectionStatistics generateNRandomDescriptors(int n, String name, long throttleB) {
        int batchSize = 25;

        CollectionStatsRecorder rec = new CollectionStatsRecorder();

        Date now = new Date();
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat hourMin = new SimpleDateFormat("HH-mm");
        SimpleDateFormat hourMinSec = new SimpleDateFormat("HH-mm-ss");

        long ts = System.currentTimeMillis();

        List<DocDescriptor> batch = new ArrayList<>();

        Timer t = Metrics.timer(GenerateRandomRecordService.class, "batchSave");

        String rv = RandomUtil.getRandomString(8);

        for (int i = 0; i < n; i++) {

            if ( i % 50 == 0) {
                rv = RandomUtil.getRandomString(8);
            }

            Timer.Context c = t.time();

            DocDescriptor d = new DocDescriptor();
            FrenchCheese cheese = getRandomCheese();
            Pesticide pdc = getRandomPdc();

            // use cheese data as categories
            String folder = "/" + cheese.getMilk() + "/" + cheese.getCheese();
            folder = folder.replaceAll(" ", "");
            folder = folder.toLowerCase();

            d.setFolder(folder);

            rec.submit("folders", d.getFolder());
            // use pdc as detail
            d.setGroupId(pdc.getSampleId() + "-" + rv);
            d.setAccountId(pdc.getSampleId() + hourMinSec.format(now) + rv);
            d.setFileName(pdc.getSampleId() + "-" + i + ".txt");
            d.setCustomerId("cust-" + d.getGroupId());

            rec.submit("groupId", d.getGroupId());


            // add some random free form data
            d.getFreeFormMetadata().put("day", day.format(now));
            d.getFreeFormMetadata().put("insTime", now.toString());
            d.getFreeFormMetadata().put("i", "" + i);
            d.getFreeFormMetadata().put("n", "" + n);
            d.getFreeFormMetadata().put("lab", pdc.getLaboratory());
            d.getFreeFormMetadata().put("high_level_category", pdc.getCommodity());
            d.getFreeFormMetadata().put("low_level_category", pdc.getConcentration());
            d.getFreeFormMetadata().put("rand", RandomUtil.getRandomString(8));


            // fill in extra data
            d.setMimeType("text/plain");
            d.setContentLength(9);
            d.setDocumentUrl("sample-url");
            d.setCreator("g" + (ts / 1000));

            batch.add(d);

            if (batch.size() == batchSize) {
                List<DynamoDBMapper.FailedBatch> failures = mapper.batchSave(batch);
                for (DynamoDBMapper.FailedBatch failure : failures) {
                    System.out.println(failure.getException());
                }
                batch.clear();
                if (throttleB > 0) {
                    ThreadUtil.doSleep(throttleB);
                }
            }

            if (i % 1000 == 0) {
                LOG.info("Generator " + name + " is " + i + " items through " + n);
            }
            c.stop();

        }

        mapper.batchSave(batch);

        LOG.info("Generator " + name + " complete");
        return rec.getStats();
    }

    private FrenchCheese getRandomCheese() {
        return cheeses.get(RandomUtil.getRange(0, cheeses.size() - 1)).getCheese();
    }

    private Pesticide getRandomPdc() {
        return pdc.get(RandomUtil.getRange(0, pdc.size() - 1)).getPdc();
    }

    private List<FrenchCheeseRecord> loadCheeses() {
        try {
            InputStream is = new ClassPathResource("/frenchcheese.json").getInputStream();
            String json = FileCopyUtils.copyToString(new InputStreamReader(is));
            List<FrenchCheeseRecord> recs = jsonMapper.readValue(json, new TypeReference<List<FrenchCheeseRecord>>() {
            });
            return recs;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private List<PesticideRecord> loadPesticides() {
        try {
            InputStream is = new ClassPathResource("/pdp.json").getInputStream();
            String json = FileCopyUtils.copyToString(new InputStreamReader(is));
            List<PesticideRecord> recs = jsonMapper.readValue(json, new TypeReference<List<PesticideRecord>>() {
            });
            return recs;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
