package org.app;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserInterfaceNoInternet {

    //Instance variables
    private final JFrame frame;
    private final TopicInput topicInput;

    //Constructor for UserInterfaceNoInternet class
    public UserInterfaceNoInternet() {
        frame = new JFrame("Learning Path Generator");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);


        topicInput = new TopicInput();
    }


    //Starts the program
    public void start() {
        topicInput.showInputScreen(frame, this::handleTopicInput);
        topicInput.add_dropdown();
        frame.setVisible(true);
    }

    //Handles the input from the user
    private void handleTopicInput(ActionEvent e) {
        String topic = topicInput.getTextArea();
        Dictionary dictionary = new Dictionary();
        String learningPath = dictionary.getLearningPath(topic);

        LearningPathDisplay learningPathDisplay = new LearningPathDisplay();
        learningPathDisplay.showInputScreen(frame, this::handelExit, this::backToHome, learningPath);
    }

    //Handles the exit button
    public void handelExit(ActionEvent e){
        //close the program
        System.exit(0);
    }

    //Handles the back to home button
    public void backToHome(ActionEvent e) {
        topicInput.showInputScreen(frame, this::handleTopicInput);
    }

}
