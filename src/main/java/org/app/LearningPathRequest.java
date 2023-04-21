package org.app;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.app.OpenAIRequest.*;

public class LearningPathRequest {
    public String sendRequest(String half_prompt) throws IOException {
        // Split the text into individual lines
        String test = half_prompt;
        String[] lines = test.split("\\n");

        // Build the prompt
        StringBuilder promptBuilder = new StringBuilder("Create a 6 weeks personalised learning path with following information.");
        for (int i = 0; i < lines.length; i++) {
            if (i > 0) {
                promptBuilder.append("; ");
            }
            promptBuilder.append((i + 1)).append(". ").append(lines[i]);
        }
        String prompt = promptBuilder.toString();

        // Send the request
        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        String requestBodyJson = String.format("{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}", prompt);
        RequestBody requestBody = RequestBody.create(JSON, requestBodyJson);

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(requestBody)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}
