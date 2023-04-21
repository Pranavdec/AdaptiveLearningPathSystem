package org.app;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class OpenAIRequest {

    // API key and URL
    protected static final String API_KEY = "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";
    protected static final String API_URL = "https://api.openai.com/v1/chat/completions";
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected String prompt;

    public abstract String buildPrompt(UserInput userInput);

    // Sends a request to the OpenAI API
    public String sendRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Build the request body
        String requestBodyJson = String.format("{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}", prompt);
        RequestBody requestBody = RequestBody.create(JSON, requestBodyJson);

        // Build the request
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(requestBody)
                .build();

        // Send the request
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            return response.body().string();
        }
    }
}





