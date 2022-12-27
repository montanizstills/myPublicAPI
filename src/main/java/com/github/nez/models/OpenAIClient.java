package com.github.nez.models;

import com.github.nez.models.requests.CompletionRequestI;
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

    public <RequestType extends IOpenAIRequest> RequestType makeRequest(Class<IOpenAIRequest> typeOfRequest, Map<String,String> params){
        return null;
    }

    public CompletionRequestI create(Boolean echo, Boolean stream, Integer bestOf, Integer frequencyPenalty, Integer logProbs, Integer maxTokens, Integer n, Integer presencePenalty, Integer stop, Integer temperature, Integer topP, Map<String, String> logitBias, String model, String prompt, String suffix, String user, OpenAIClient openAIClient){
        return CompletionRequestI.builder()
                .setN(n)
                .setOpenAIClient(openAIClient)
                .setBestOf(bestOf)
                .setEcho(echo)
                .setLogitBias(logitBias)
                .setLogProbs(logProbs)
                .setModel(model)
                .setFrequencyPenalty(frequencyPenalty)
                .setMaxTokens(maxTokens)
                .setPrompt(prompt)
                .setStop(stop)
                .setStream(stream)
                .setSuffix(suffix)
                .setTemperature(temperature)
                .setUser(user)
                .setTopP(topP)
                .setPresencePenalty(presencePenalty)
                .build();
    }
}
