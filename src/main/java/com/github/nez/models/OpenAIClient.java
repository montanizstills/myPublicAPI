package com.github.nez.models;

import org.springframework.web.client.RestTemplate;

public class OpenAIClient {

    private final String API_KEY;
    private final RestTemplate restTemplate;
    private final String API_URL;

    public OpenAIClient(String apiKey, String apiURL) {
        this.restTemplate = new RestTemplate();
        this.API_KEY = apiKey;
        this.API_URL = apiURL;
    }

}
