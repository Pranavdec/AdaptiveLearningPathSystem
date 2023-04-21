package org.app;

import org.json.JSONArray;
import org.json.JSONObject;

public class OpenAIResponse {
    private final String result;

    // Constructor for the OpenAIResponse class, initializes the required variables
    public OpenAIResponse(String rawResponse) {

        JSONObject jsonResponse = new JSONObject(rawResponse);
        JSONArray choices = jsonResponse.getJSONArray("choices");
        this.result = choices.getJSONObject(0).getJSONObject("message").getString("content").trim();
    }


    // Getter for the result
    public String getResult() {
        return result;
    }
}

