package com.test.compass.service;

import com.test.compass.csv.CsvLoader;
import com.test.compass.dto.Client;
import com.test.compass.dto.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CsvLoaderTest {

    @InjectMocks
    private CsvLoader csvLoader;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private Resource resource;

    @Mock
    private File file;

    private List<Client> clients;

    @Before
    public void setup() {
        clients = new ArrayList<>();
        clients.add(new Client(1, "John", "Doe", "john.doe@example.com", "123 Main St", "12345"));
        clients.add(new Client(2, "Jane", "Doe", "jane.doe@example.com", "456 Elm St", "67890"));
        clients.add(new Client(3, "John", "Doe", "john.doe@example.com", "123 Main St", "12345"));
    }

    @Test
    public void testLoadCsv() throws IOException {
        // Given
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getFile()).thenReturn(file);

        // When
        List<Client> loadedClients = csvLoader.loadCsv();

        // Then
        assertEquals(clients, loadedClients);
    }

    @Test
    public void testCompare() {
        // Given
        when(csvLoader.loadCsv()).thenReturn(clients);

        // When
        List<Result> results = csvLoader.compare();

        // Then
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    public void testCompare_NoMatches() {
        // Given
        when(csvLoader.loadCsv()).thenReturn(new ArrayList<>());

        // When
        List<Result> results = csvLoader.compare();

        // Then
        assertNotNull(results);
        assertEquals(0, results.size());
    }

}
