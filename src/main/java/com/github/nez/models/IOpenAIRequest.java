package com.github.nez.models;

/**
 * A generic request object for OpenAI API interaction.
 **/
public interface IOpenAIRequest {

    OpenAIClient getOpenAIClient();

    default <TypeOfRequest extends IOpenAIRequest> TypeOfRequest create() {
        return null;
    }

}
