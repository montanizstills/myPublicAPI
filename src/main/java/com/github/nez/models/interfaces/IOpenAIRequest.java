package com.github.nez.models.interfaces;

import com.github.nez.models.OpenAIClient;

import java.util.Map;

/**
 * A generic request object for OpenAI API interaction.
 **/
public interface IOpenAIRequest {

    OpenAIClient getOpenAIClient();

    IOpenAIRequest createRequest(Map<String,Object> parameters);

}
