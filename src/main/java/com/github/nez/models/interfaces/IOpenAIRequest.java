package com.github.nez.models.interfaces;

import com.github.nez.models.OpenAIClient;

import java.util.Map;

/**
 * A generic request object for OpenAI API interaction.
 **/
public interface IOpenAIRequest<IOpenAIRequestType extends IOpenAIRequest> {

    OpenAIClient getOpenAIClient();

    IOpenAIRequestType createRequest(Map<String,Object> parameters);

}
