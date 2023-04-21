package org.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Set;

public class UserInterface {

    //Instance variables
    private final JFrame frame;
    private TopicInput topicInput;
    private final AnswersInput answersInput;
    private String topic;
    private final User user;
    private final UserManager userManager;


    //Constructor for UserInterface class
    public UserInterface(User user, Set<String> previousLearningPaths, UserManager userManager) {
        frame = new JFrame("Learning Path Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        topicInput = new TopicInput(previousLearningPaths);
        answersInput = new AnswersInput();

        this.user = user;
        this.userManager = userManager;
    }

    //Starts the program
    public void start() {
        topicInput.showInputScreen(frame, this::handleTopicInput);
        frame.setVisible(true);
    }

    //Handles the topic input from the user
    private void handleTopicInput(ActionEvent e) {
        String selectedTopic = topicInput.getTextArea();
        boolean useDropdown = false;

        if (selectedTopic.trim().isEmpty() || selectedTopic.trim().equalsIgnoreCase("e.g. Java, Python, C++")) {
            selectedTopic = (String) topicInput.previousLearningPathsDropdown.getSelectedItem();
            useDropdown = true;
        }

        if (useDropdown || topicInput.previousLearningPathsDropdown.getSelectedIndex() > 0) {
            String learningPath = user.getLearningPath(selectedTopic);
            LearningPathDisplay learningPathDisplay = new LearningPathDisplay();
            learningPathDisplay.showInputScreen(frame, this::handelExit, this::backToHome, learningPath);
        } else {
            // If the user selects a new topic
            try {
                // Send a request to OpenAI to get follow-up questions
                FollowUpQuestionsRequest followUpQuestionsRequest = new FollowUpQuestionsRequest();
                topic = selectedTopic;
                String followUpPrompt = followUpQuestionsRequest.buildPrompt(topicInput);
                String followUpResponse = followUpQuestionsRequest.sendRequest();

                // Get the follow-up questions from the response
                OpenAIResponse openAIResponse = new OpenAIResponse(followUpResponse);
                String followUpQuestions = openAIResponse.getResult();

                // Display the follow-up questions to the user
                answersInput.setTextArea(followUpQuestions);
                answersInput.showInputScreen(frame, this::handlePreferencesInput);
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(frame, "Error: " + exception.getMessage());
            }
        }
    }


    //Handles the preference input from the user
    private void handlePreferencesInput(ActionEvent e) {
        try {
            //Send a request to OpenAI to get the user's preferences summary
            PreferencesSummaryRequest preferencesSummaryRequest = new PreferencesSummaryRequest();
            String preferences = preferencesSummaryRequest.buildPrompt(answersInput);
            String preferencesSummaryResponse = preferencesSummaryRequest.sendRequest();
            System.out.println(preferencesSummaryResponse);
            OpenAIResponse openAIResponse = new OpenAIResponse(preferencesSummaryResponse);
            String preferencesSummary = openAIResponse.getResult();


            //Send a request to OpenAI to get the learning path
            LearningPathRequest learningPathRequest = new LearningPathRequest();
            String learningPathResponse = learningPathRequest.sendRequest(preferencesSummary);
            openAIResponse = new OpenAIResponse(learningPathResponse);
            String learningPath = openAIResponse.getResult();

            //Display the learning path to the user
            LearningPathDisplay learningPathDisplay = new LearningPathDisplay();
            learningPathDisplay.showInputScreen(frame, this::handelExit, this::backToHome, learningPath);

            // Store the learning path for the user
            user.storeLearningPath(topic, learningPath);

            // Save the updated user data to the file
            userManager.saveUsers();

        } catch (IOException exception) {
            JOptionPane.showMessageDialog(frame, "Error: " + exception.getMessage());
        }
        
    }

    //Handles the exit button
    public void handelExit(ActionEvent e) {
        // Save user data before closing the program
        saveUserData();

        // Close the program
        System.exit(0);
    }

    //Handles the back to home button
    private void backToHome(ActionEvent e) {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new SpringLayout());
        frame.setContentPane(panel); // Set the new JPanel as the content pane
        topicInput = new TopicInput(user.getLearningPaths().keySet());
        topicInput.showInputScreen(frame, this::handleTopicInput);
    }


    // Save the user data to the file
    private void saveUserData() {
        // Save the user data
        userManager.saveUser(user);
    }

}
