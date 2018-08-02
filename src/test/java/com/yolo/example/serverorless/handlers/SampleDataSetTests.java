package com.yolo.example.serverorless.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yolo.example.serverorless.model.FrenchCheeseRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by mandrewes on 2/08/2018.
 */
public class SampleDataSetTests {

    ObjectMapper mapper;

    @BeforeMethod
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    public void shouldLoadFrenchCheese() throws Exception {

        InputStream is = new ClassPathResource("/frenchcheese.json").getInputStream();
        String json = FileCopyUtils.copyToString(new InputStreamReader(is));
        List<FrenchCheeseRecord> cheeses = mapper.readValue(json, new TypeReference<List<FrenchCheeseRecord>>(){});

        System.out.printf("na");
        System.out.println(cheeses.get(0));


    }
}
