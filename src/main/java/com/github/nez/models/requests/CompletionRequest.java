package com.github.nez.models.requests;

import com.github.nez.models.interfaces.IOpenAIRequest;
import com.github.nez.models.OpenAIClient;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder(setterPrefix = "set")
public class CompletionRequest implements IOpenAIRequest {

    /**
     * @param echo             Echo back the prompt in addition to the completion
     * Defaults to false.
     */
    private Boolean echo;
    /**
     * @param stream           Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent events as they become available, with the stream terminated by a data: [DONE] message.
     * Defaults to false.
     */
    private Boolean stream;
    /**
     * @param bestOf           Generates best_of completions server-side and returns the "best" (the one with the highest log probability per token). Results cannot be streamed.
     * When used with n, best_of controls the number of candidate completions and n specifies how many to return – best_of must be greater than n.
     * Note: Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     * Defaults to 1.
     */
    private Integer bestOf;
    /**
     * @param frequencyPenalty Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model's likelihood to repeat the same line verbatim.
     * Defaults to 0.
     */
    private Integer frequencyPenalty;
    /**
     * @param logProbs         Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens. For example, if logprobs is 5, the API will return a list of the 5 most likely tokens. The API will always return the logprob of the sampled token, so there may be up to logprobs+1 elements in the response.
     * The maximum value for logprobs is 5.
     * Defaults to null.
     */
    private Integer logProbs;
    /**
     * @param maxTokens        The maximum number of tokens that can be returned in a single request. The maximum number of tokens to generate in the completion.
     * The token count of your prompt plus max_tokens cannot exceed the model's context length. Most models have a context length of 2048 tokens (except for the newest models, which support 4096).
     * Defaults to 16.
     */
    private Integer maxTokens;
    /**
     * @param n                How many completions to generate for each prompt.
     * Note: Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     * Defaults to 1.
     */
    private Integer n;
    /**
     * @param presencePenalty  Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model's likelihood to talk about new topics.
     * Defaults to 0.
     */
    private Integer presencePenalty;
    /**
     * @param stop             Up to 4 sequences where the API will stop generating further tokens.
     * The returned text will not contain the stop sequence.
     * Defaults to null.
     */
    private Integer stop;
    /**
     * @param temperature      Between 0-1, Higher values means the model will take more risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer. We generally recommend altering this or top_p but not both.
     * Defaults to 1.
     */
    private Integer temperature;
    /**
     * @param topP             An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     * We generally recommend altering this or temperature but not both.
     * Defaults to 1.
     */
    private Integer topP;
    /**
     * @param logitBias        Modify the likelihood of specified tokens appearing in the completion.
     * Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100. You can use this tokenizer tool (which works for both GPT-2 and GPT-3) to convert text to token IDs. Mathematically, the bias is added to the logits generated by the model prior to sampling. The exact effect will vary per model, but values between -1 and 1 should decrease or increase likelihood of selection; values like -100 or 100 should result in a ban or exclusive selection of the relevant token. As an example, you can pass {"50256": -100} to prevent the <|endoftext|> token from being generated.
     * Defaults to null.
     */
    private Map<String, String> logitBias;
    /**
     * @param model            ID of the model to use. You can use the List models API to see all of your available models, or see our Model overview for descriptions of them.
     * https://beta.openai.com/docs/models/gpt-3.
     * There are 3 distinct model templates: GPT-3 for natural language, Codex for code generation, and Context filter for sensitive/unsafe text detection (Use 'moderation' endpoint https://beta.openai.com/docs/api-reference/moderations/create.)
     */
    private String model;
    /**
     * @param prompt           The prompt(s) to generate completions for, encoded as a string, array of strings, array of tokens, or array of token arrays.
     * Note that <|endoftext|> is the document separator that the model sees during training, so if a prompt is not specified the model will generate as if from the beginning of a new document.
     */
    private String prompt;
    /**
     * @param suffix           The suffix that comes after a completion of inserted text.
     * Defaults to null.
     */
    private String suffix;
    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse
     */
    private String user;

    /**
     * The type of request -- used to identify self
     * */
    private String requestType;

    private final OpenAIClient openAIClient;

    @Override
    public IOpenAIRequest createRequest(Map<String, Object> parameters) {
        return CompletionRequest.builder()
                .setN(Integer.valueOf(String.valueOf(parameters.get("n"))))
                .setOpenAIClient((OpenAIClient) parameters.get("openAIClient"))
                .setBestOf(Integer.valueOf(String.valueOf(parameters.get("bestOf"))))
                .setEcho(Boolean.valueOf(String.valueOf(parameters.get("echo"))))
                .setLogitBias((Map<String, String>) parameters.get("logitBias"))
                .setLogProbs(Integer.valueOf(String.valueOf(parameters.get("logProbs"))))
                .setModel(String.valueOf(parameters.get("model")))
                .setFrequencyPenalty(Integer.valueOf(String.valueOf(parameters.get("frequencyPenalty"))))
                .setMaxTokens(Integer.valueOf(String.valueOf(parameters.get("maxTokens"))))
                .setPrompt(String.valueOf(parameters.get("prompt")))
                .setStop(Integer.valueOf(String.valueOf(parameters.get("stop"))))
                .setStream(Boolean.valueOf(String.valueOf(parameters.get("stream"))))
                .setSuffix(String.valueOf(parameters.get("suffix")))
                .setTemperature(Integer.valueOf(String.valueOf(parameters.get("temperature"))))
                .setUser(String.valueOf(parameters.get("user")))
                .setTopP(Integer.valueOf(String.valueOf(parameters.get("topP"))))
                .setPresencePenalty(Integer.valueOf(String.valueOf(parameters.get("presencePenalty"))))
                .build();
    }


}
