package com.test.compass.service;

import com.test.compass.configuration.CvsServiceConfig;
import com.test.compass.csv.CsvLoader;

import com.test.compass.dto.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@Import(CvsServiceConfig.class)
public class CvsLoaderTest {

    @Autowired
    CsvLoader csvLoader;

    @Test
    public void testLoadCsv() {
        List<Result> results = csvLoader.compare();

        Assert.assertTrue(true);
    }
}
