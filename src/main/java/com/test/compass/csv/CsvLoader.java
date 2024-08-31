package com.test.compass.csv;

import com.opencsv.CSVReader;
import com.test.compass.dto.Client;
import com.test.compass.dto.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class CsvLoader {

    @Autowired
    private ResourceLoader resourceLoader;

    private List<Client> loadCsv() {

        List<Client> clients = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:data.csv");
        File file = null;
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                clients.add(new Client(Integer.valueOf(nextLine[0]), nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("CSV validation error: " + e.getMessage());
        }

        return clients;
    }

    public List<Result> compare() {
        List<Client> clients = loadCsv();
        List<Result> results = new ArrayList<>();
        Set<Integer> analyzed = new HashSet<>();

        for (Client client : clients) {

            for (Client compare : clients) {

                if (!Objects.equals(client.getId(), compare.getId())) {

                    if (!analyzed.contains(client.getId())) {

                        Result result = new Result(client.getId(), compare.getId());

                        if (notEmptyFields(client.getName(), compare.getName()) && client.getName().equalsIgnoreCase(compare.getName())) {
                            result.plusScore();
                        }

                        if (notEmptyFields(client.getLastname(), compare.getLastname()) && client.getLastname().equalsIgnoreCase(compare.getLastname())) {
                            result.plusScore();
                        }

                        if (notEmptyFields(client.getEmail(), compare.getEmail()) && client.getEmail().equalsIgnoreCase(compare.getEmail())) {
                            result.plusScore();
                        }

                        if (notEmptyFields(client.getAddress(), compare.getAddress()) && client.getAddress().equalsIgnoreCase(compare.getAddress())) {
                            result.plusScore();
                        }

                        if (notEmptyFields(client.getPostalZip(), compare.getPostalZip()) && client.getPostalZip().equalsIgnoreCase(compare.getPostalZip())) {
                            result.plusScore();
                        }

                        if (result.getScore() > 0) {
                            results.add(result);
                            analyzed.add(compare.getId());
                        }
                    }
                }
            }
            analyzed.add(client.getId());
        }

        results.forEach(System.out::println);

        return results;
    }

    private boolean notEmptyFields(String a, String b) {
        return !Strings.isEmpty(a) && !Strings.isEmpty(b);
    }
}
