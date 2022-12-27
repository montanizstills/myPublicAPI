package com.github.nez.models;

public class GenericImageRequest {
    private Integer n;
    private String size;
    private String response_format;
    private String user;

    /**
     * @param n               The number of images to generate. Defaults to 1.
     * @param size            The size of the generated images (Must be one of 256x256, 512x512, or 1024x1024) Defaults to 1024x1024
     * @param response_format The format in which the generated images are returned. Must be one of url or b64_json. Defaults to url.
     * @param user            A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse.
     **/
    GenericImageRequest(Integer n, String size, String response_format, String user) {
    }
}
