package com.github.nez.models.requests;

import com.github.nez.models.OpenAIClient;
import com.github.nez.models.IOpenAIRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditRequestI implements IOpenAIRequest {
    private final OpenAIClient openAIClient;

}
