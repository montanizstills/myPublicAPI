package com.github.nez.models;

import com.github.nez.models.interfaces.IOpenAIRequest;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Data
public class OpenAIClient {

    private final String API_KEY;
    private final RestTemplate restTemplate;
    private final String API_URL;

    public OpenAIClient(String apiKey, String apiURL) {
        this.restTemplate = new RestTemplate();
        this.API_KEY = apiKey;
        this.API_URL = apiURL;
    }

    public <RequestType extends IOpenAIRequest> RequestType makeRequest(IOpenAIRequest typeOfRequest, Map<String,String> params){
        return null;
    }

}
