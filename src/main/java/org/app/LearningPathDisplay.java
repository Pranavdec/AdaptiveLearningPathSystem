package org.app;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;

public class LearningPathDisplay {

    public void showInputScreen(JFrame frame, ActionListener exitAction, ActionListener backToHomeAction, String learningPath) {
        frame.getContentPane().removeAll();

        // Create a JPanel to hold the topics
        JPanel topicsPanel = new JPanel();
        topicsPanel.setLayout(new BoxLayout(topicsPanel, BoxLayout.Y_AXIS));

        // Split the learningPath string into separate lines
        String[] topics = learningPath.split("\\n");

        int gap = 10;

        for (String topic : topics) {
            JLabel topicLabel = new JLabel(topic);
            // Add an empty border for spacing between lines
            topicLabel.setBorder(BorderFactory.createEmptyBorder(gap / 2, 0, gap / 2, 0));
            topicsPanel.add(topicLabel);
        }

        // Create a JPanel to hold the topicsPanel and other components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        frame.setTitle("Learning Path");
        JLabel label = new JLabel("Learning Path");
        panel.add(label, BorderLayout.NORTH);

        // Add the topicsPanel to the main panel
        panel.add(topicsPanel, BorderLayout.CENTER);

        // Add Save and Back to Home buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton saveButton = new JButton("Save");
        buttonPanel.add(saveButton);

        JButton backButton = new JButton("Back to Home");
        buttonPanel.add(backButton);

        JButton exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(backToHomeAction);
        backButton.addActionListener(backToHomeAction);
        exitButton.addActionListener(exitAction);

        // Create a JScrollPane and set the panel as the viewport
        JScrollPane scrollPane = new JScrollPane(panel);

        // Set the scroll pane as the content pane of the frame
        frame.setContentPane(scrollPane);

        frame.revalidate();
        frame.repaint();
    }
}
