package com.github.nez.models.requests;

import com.github.nez.models.OpenAIClient;
import com.github.nez.models.interfaces.IOpenAIRequest;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder(setterPrefix = "set")
public class ImageRequest implements IOpenAIRequest<ImageRequest> {
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
    private String responseFormat;
    /**
     * @param user            A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     * Defaults to null.
     */
    private String user;


    @Override
    public ImageRequest createRequest(Map<String, Object> parameters) {
        return ImageRequest.builder()
                .setN(Integer.valueOf(String.valueOf(parameters.get("n"))))
                .setOpenAIClient((OpenAIClient) parameters.get("openAIClient"))
                .setUser(String.valueOf(parameters.get("user")))
                .setSize(String.valueOf(parameters.get("size")))
                .setResponseFormat(String.valueOf(parameters.get("responseFormat")))
                .build();
    }
}
