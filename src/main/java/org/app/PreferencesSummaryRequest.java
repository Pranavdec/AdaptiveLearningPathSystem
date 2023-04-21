package org.app;

class PreferencesSummaryRequest extends OpenAIRequest {
    @Override
    public String buildPrompt(UserInput userInput) {
        String preferences = userInput.getTextArea();
        // Split the text into individual lines
        String[] lines = preferences.split("\\n");

        // Build the prompt
        StringBuilder promptBuilder = new StringBuilder("Given the following user responses, please summarize the main points into a single paragraph:");
        for (int i = 0; i < lines.length; i++) {
            if (i > 0) {
                promptBuilder.append("; ");
            }
            promptBuilder.append((i + 1)).append(". ").append(lines[i]);
        }

        prompt = promptBuilder.toString();

        return prompt;
    }
}