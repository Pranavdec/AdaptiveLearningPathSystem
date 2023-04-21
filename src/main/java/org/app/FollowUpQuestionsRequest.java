package org.app;

class FollowUpQuestionsRequest extends OpenAIRequest {

    // Build the prompt
    @Override
    public void buildPrompt(UserInput userInput) {
        String topic = userInput.getTextArea();
        prompt = String.format("User wants to learn %s. They have basic programming experience. Generate 5 follow-up questions to assess their knowledge and preferences.", topic);
    }
}
