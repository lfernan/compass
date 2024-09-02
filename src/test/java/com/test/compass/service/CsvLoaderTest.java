package com.test.compass.service;

import com.test.compass.csv.CsvLoader;
import com.test.compass.dto.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CsvLoaderTest {

    private static final String LOW = "Low";
    private static final String MEDIUM = "Medium";
    private static final String HIGH = "High";

    @Autowired
    CsvLoader csvLoader;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(csvLoader, "csvResource", "classpath:test.csv");
    }

    @Test
    void compare() {
        List<Result> result = csvLoader.compare();
        assertEquals(result.get(0).getAccuracy(), LOW);
        assertEquals(result.get(1).getAccuracy(), MEDIUM);
        assertEquals(result.get(2).getAccuracy(), HIGH);
        assertEquals(result.get(3).getAccuracy(), LOW);
        assertEquals(result.get(4).getAccuracy(), MEDIUM);
    }
}