package com.github.nez.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class GenericOpenAIRequest implements OpenAIRequest {
    private Integer temperature;
    private String model;
    private Integer maxTokens;
    private Integer topP;
    private Integer frequencyPenalty;
    private Integer presencePenalty;
    private Map logitBias;
    private String prompt;
    private Integer bestOf;
    private String user;
    private Integer logProbs;
    private Integer n;
    private Boolean echo;
    private Boolean stream;
    private Integer stop;
    private String suffix;

    /**
     * @param temperature      Between 0-1, Higher values means the model will take more risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer. We generally recommend altering this or top_p but not both.
     *                         Defaults to 1.
     *
     * @param model            ID of the model to use. You can use the List models API to see all of your available models, or see our Model overview for descriptions of them.
     *                         https://beta.openai.com/docs/models/gpt-3.
     *                         There are 3 distinct model templates: GPT-3 for natural language, Codex for code generation, and Context filter for sensitive/unsafe text detection (Use 'moderation' endpoint https://beta.openai.com/docs/api-reference/moderations/create.)
     *
     * @param maxTokens        The maximum number of tokens that can be returned in a single request. The maximum number of tokens to generate in the completion.
     *                         The token count of your prompt plus max_tokens cannot exceed the model's context length. Most models have a context length of 2048 tokens (except for the newest models, which support 4096).
     *                         Defaults to 16.
     *
     * @param topP             An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     *                         We generally recommend altering this or temperature but not both.
     *
     * @param frequencyPenalty Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
     *                         Defaults to 0.
     *
     * @param presencePenalty  Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.
     *                         Defaults to 0.
     *
     * @param bestOf           Generates best_of completions server-side and returns the "best" (the one with the highest log probability per token). Results cannot be streamed.
     *                         When used with n, best_of controls the number of candidate completions and n specifies how many to return – best_of must be greater than n.
     *                         Note: Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     *
     * @param n                How many completions to generate for each prompt.
     *                         Note: Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     *
     * @param echo             Echo back the prompt in addition to the completion
     *                         Defaults to false.
     *
     * @param logProbs         Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens. For example, if logprobs is 5, the API will return a list of the 5 most likely tokens. The API will always return the logprob of the sampled token, so there may be up to logprobs+1 elements in the response.
     *                         The maximum value for logprobs is 5.
     *                         Defaults to null.
     *
     * @param prompt           The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens, or array of token arrays.
     *                         Note that <|endoftext|> is the document separator that the model sees during training, so if a prompt is not specified the model will generate as if from the beginning of a new document.
     *
     * @param stop             Up to 4 sequences where the API will stop generating further tokens.
     *                         The returned text will not contain the stop sequence.
     *                         Defaults to null.
     *
     * @param stream           Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent events as they become available, with the stream terminated by a data: [DONE] message.
     *                         Defaults to false.
     *
     * @param logitBias        Modify the likelihood of specified tokens appearing in the completion.
     *                         Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this tokenizer tool (which works for both GPT-2 and GPT-3) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token. As an example, you can pass {"50256": -100} to prevent the <|endoftext|> token from being generated.
     *                         Defaults to null.
     *
     * @param suffix           The suffix that comes after a completion of inserted text. Defaults to null.
     */


    public String generateText(String prompt) {
        return this.generateText(prompt, this.model, this.maxTokens, this.temperature);
    }

    public String generateText(String prompt, String model, int maxTokens, float temperature) {
        // Build the request payload
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);
        requestBody.put("model", model);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", temperature);

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Build the request object
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // Send the request and get the response
        ResponseEntity<Map> response = restTemplate.postForEntity(API_URL + "?api_key=" + API_KEY, request, Map.class);

        // Extract the generated text from the response
        Map<String, Object> responseBody = response.getBody();
        return (String) responseBody.get("text");
    }
}
