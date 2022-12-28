package com.github.nez.models;

public enum OpenAIModels {
    GPT_DAVINCI_003("text-davinci-003", 4000),
    GPT_CURIE_001("text-curie-001", 2048),
    GPT_BABBAGE_001("text-babbage-001", 2048),
    GPT_ADA_001("text-ada-001", 2048),
    CODE_DAVINCI_002("code-curie-001", 8000),
    CODE_CUSHMAN_001("code-cushman-001", 2048),
    EMBED_DAVINCI_001("embedding-davinci-001", 2048),
    EMBED_CURIE_001("embedding-curie-001", 2048),
    EMBED_BABBAGE_001("embedding-babbage-001", 2048),
    EMBED_ADA_001("embedding-ada-001", 2048),
    EMBED_ADA_002("text-embedding-ada-002", 2048),
    //    BASIC_COMPLETION("",COMPLETIONS)
    ;

    private String model;
    private Integer endpointTokenLimit;

    OpenAIModels(String model, Integer endpointTokenLimit) {
        this.model = model;
        this.endpointTokenLimit = endpointTokenLimit;
    }


    public String getModel() {
        return this.model;
    }

    public Integer getEndpointTokenLimit() {
        return this.endpointTokenLimit;
    }
}
