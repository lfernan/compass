package com.test.compass.csv;

import com.opencsv.CSVReader;
import com.test.compass.dto.Client;
import com.test.compass.dto.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class CsvLoader {

    @Value("${csv.name}")
    private String csvResource;
    private final ResourceLoader resourceLoader;

    @Autowired
    public CsvLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Client> loadCsv() {

        List<Client> clients = new ArrayList<>();
        Resource resource = resourceLoader.getResource(csvResource);

        try (CSVReader reader = new CSVReader(new FileReader(resource.getFile()))) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                clients.add(new Client(Integer.valueOf(nextLine[0]), nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5]));
            }

        } catch (IOException e) {
            System.err.println("CSV reading error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("CSV validation error: " + e.getMessage());
        }

        return clients;
    }

    public List<Result> compare() {
        List<Client> clients = loadCsv();
        List<Result> results = new ArrayList<>();

        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);

            List<Client> subList = clients.subList(i + 1, clients.size());
            for (Client compare : subList) {

                if (!Objects.equals(client.getId(), compare.getId())) {


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
                    }
                }
            }
        }

        results.forEach(System.out::println);

        return results;
    }

    public boolean notEmptyFields(String a, String b) {
        return !Strings.isEmpty(a) && !Strings.isEmpty(b);
    }
}
