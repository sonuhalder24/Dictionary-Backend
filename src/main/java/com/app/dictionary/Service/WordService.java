package com.app.dictionary.Service;

import com.app.dictionary.api.response.WordResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class WordService {

    private static final String API = "https://api.dictionaryapi.dev/api/v2/entries/en/WORD";

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<WordResponse> getWordMeaning(String wd) {
        String fAPI = API.replace("WORD", wd);
        try {
            // Make the API call
            ResponseEntity<String> response = restTemplate.exchange(fAPI, HttpMethod.GET, null, String.class);

            // Parse the raw JSON response
            JsonNode rootNode = objectMapper.readTree(response.getBody());

            if (rootNode.isObject() && rootNode.has("title") && rootNode.has("message")) {
                return Collections.emptyList();
            } else if (rootNode.isArray()) {
                // Success Response
                WordResponse[] wordResponses = objectMapper.treeToValue(rootNode, WordResponse[].class);
                return List.of(wordResponses);
            } else {
                return Collections.emptyList();
            }
        } catch (RestClientException e) {
            // Handle RestTemplate exceptions (e.g., connectivity issues)
            System.err.println("Error while calling the API: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
