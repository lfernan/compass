package com.test.compass.configuration;

import com.test.compass.csv.CsvLoader;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CvsServiceConfig {

        @Bean
        public CsvLoader employeeService() {
            return new CsvLoader();
        }

}
