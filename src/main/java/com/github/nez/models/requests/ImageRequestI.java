package com.github.nez.models.requests;

import com.github.nez.models.OpenAIClient;
import com.github.nez.models.IOpenAIRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageRequestI implements IOpenAIRequest {
    private final OpenAIClient openAIClient;
    /**
     * @param n               The number of images to generate.
     * Defaults to 1.
     */
    private Integer n;
    /**
     * @param size            The size of the generated images (Must be one of 256x256, 512x512, or 1024x1024)
     * Defaults to 1024x1024
     */
    private String size;
    /**
     * @param response_format The format in which the generated images are returned. Must be one of url or b64_json.
     * Defaults to url.
     */
    private String response_format;
    /**
     * @param user            A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * Defaults to null.
     */
    private String user;


}
