package com.github.nez.models;

public class OpenAIEndpoints {
    private static final String COMPLETIONS = "https://api.openai.com/v1/completions";
    private static final String MODERATIONS = "https://api.openai.com/v1/moderations"; // Given a input text, outputs if the model classifies it as violating OpenAI's content policy. Classifies if text violates OpenAI's Content Policy
    private static final String EDITS = "https://api.openai.com/v1/edits"; // Given a prompt and an instruction, the model will return an edited version of the prompt. Creates a new edit for the provided input, instruction, and parameters
    private static final String IMAGE_GENERATIONS = "https://api.openai.com/v1/images/generations";
    private static final String IMAGE_EDITS = "https://api.openai.com/v1/images/edits";
    private static final String IMAGE_VARIATIONS = "https://api.openai.com/v1/images/variations";
    private static final String EMBEDDINGS = "https://api.openai.com/v1/embeddings"; // Get a vector representation of a given input that can be easily consumed by machine learning models and algorithms.
    private static final String ALL_MODELS = "https://api.openai.com/v1/models"; // Lists available models.
    private static final String RETRIEVE_MODEL = "https://api.openai.com/v1/models/{model}"; // Retrieves a model instance.
    private static final String ALL_FILES = "https://api.openai.com/v1/files"; // Returns a list of files that belong to the user's organization.
    private static final String DELETE_FILE = "https://api.openai.com/v1/files/{file_id}"; // Deletes a file
    private static final String RETRIEVE_FILE = "https://api.openai.com/v1/files/{file_id}"; //Returns information (metadata) about a specific file.
    private static final String CONTENT_FILE = "https://api.openai.com/v1/files/{file_id}/content"; //Returns the contents of the file.
    private static final String CREATE_FINE_TUNE = "https://api.openai.com/v1/fine-tunes"; // Creates a job that fine tunes a specified model from a given dataset.
    private static final String ALL_FINE_TUNES = "https://api.openai.com/v1/fine-tunes"; // Lists all organizations fine-tuning jobs.
    private static final String RETRIEVE_FINE_TUNE = "https://api.openai.com/v1/fine-tunes/{fine_tune_id}"; //Gets info about the fine tune job.
    private static final String CANCEL_FINE_TUNE = "https://api.openai.com/v1/fine-tunes/{fine_tune_id}/cancel"; // Immediately cancel a fine-tune job.
    private static final String ALL_FINE_TUNE_EVENTS = "https://api.openai.com/v1/fine-tunes/{fine_tune_id}/events"; // Gets fine-grained (metadata) status updates for a fine-tune job.
    private static final String DELETE_FINE_TUNE_MODEL = "https://api.openai.com/v1/models/{model}"; // Delete a fine-tuned model. Must have the 'Owner' role in the organization.
}
