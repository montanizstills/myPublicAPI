package com.github.nez.models.requests;

import com.github.nez.models.OpenAIClient;
import com.github.nez.models.interfaces.IOpenAIRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder(setterPrefix = "set")
@Getter
public class ModerationRequest implements IOpenAIRequest {
    private final OpenAIClient openAIClient;

    @Override
    public IOpenAIRequest createRequest(Map<String, Object> parameters) {
        return null;
    }
}