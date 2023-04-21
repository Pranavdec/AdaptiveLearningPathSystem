package org.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;
import java.util.Set;

public class TopicInput extends UserInput {

    private JComboBox<String> dropdown;
    private JLabel no_internet;

    public JComboBox<String> previousLearningPathsDropdown = new JComboBox<>();

    // Constructor to initialize the previousLearningPathsDropdown
    public TopicInput(Set<String> previousLearningPaths) {
        setPreviousLearningPaths(previousLearningPaths);
    }

    public TopicInput() {
    }

    public void showInputScreen(JFrame frame, ActionListener action) {
        frame.getContentPane().removeAll();

        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);

        frame.setTitle("Topic Selection");
        JLabel label = new JLabel("What do you want to learn?");


        textArea = new JTextArea();
        textArea.setText("e.g. Java, Python, C++");
        textArea.setForeground(Color.gray);
        textArea.setBackground(Color.white);

        textArea.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals("e.g. Java, Python, C++")) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().trim().equals("")) {
                    textArea.setText("e.g. Java, Python, C++");
                    textArea.setForeground(Color.GRAY);
                }
            }
        });

        String[] options = {"Python", "Java", "C language"};
        dropdown = new JComboBox<>(options);
        dropdown.setSelectedIndex(0);
        no_internet = new JLabel("No internet connection. Please select a topic from the dropdown menu.");

        dropdown.setVisible(false);
        no_internet.setVisible(false);

        JLabel previousLearningPathsLabel = new JLabel("Select a previous learning path:");



        submitButton = new JButton("Submit");

        frame.add(label);
        frame.add(dropdown);
        frame.add(no_internet);
        frame.add(previousLearningPathsLabel);
        frame.add(previousLearningPathsDropdown);

        frame.add(textArea);
        frame.add(submitButton);

        layout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, frame);

        layout.putConstraint(SpringLayout.WEST, textArea, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, textArea, 10, SpringLayout.SOUTH, label);

        layout.putConstraint(SpringLayout.WEST, dropdown, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, dropdown, 10, SpringLayout.SOUTH, textArea);

        layout.putConstraint(SpringLayout.WEST, no_internet, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, no_internet, 10, SpringLayout.SOUTH, dropdown);


        layout.putConstraint(SpringLayout.WEST, submitButton, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, submitButton, 50, SpringLayout.SOUTH, no_internet);

        layout.putConstraint(SpringLayout.WEST, previousLearningPathsLabel, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, previousLearningPathsLabel, 10, SpringLayout.SOUTH, textArea);

        layout.putConstraint(SpringLayout.WEST, previousLearningPathsDropdown, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, previousLearningPathsDropdown, 10, SpringLayout.SOUTH, previousLearningPathsLabel);




        submitButton.addActionListener(action);

        frame.setFocusTraversalPolicy(new LayoutFocusTraversalPolicy());
        frame.validate();
        frame.repaint();
    }


    public void add_dropdown(){
        dropdown.setVisible(true);
        no_internet.setVisible(true);
    }

    @Override
    public String getTextArea(){
        if (dropdown.isVisible()){
            return Objects.requireNonNull(dropdown.getSelectedItem()).toString();
        } else {
            return textArea.getText();
        }
    }

    private class LayoutFocusTraversalPolicy extends FocusTraversalPolicy {
        @Override
        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
            if (aComponent == textArea) {
                return submitButton;
            } else {
                return textArea;
            }
        }

        @Override
        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
            if (aComponent == textArea) {
                return submitButton;
            } else {
                return textArea;
            }
        }

        @Override
        public Component getDefaultComponent(Container focusCycleRoot) {
            return submitButton;
        }

        @Override
        public Component getFirstComponent(Container focusCycleRoot) {
            return submitButton;
        }

        @Override
        public Component getLastComponent(Container focusCycleRoot) {
            return textArea;
        }
    }

    public void setPreviousLearningPaths(Set<String> previousLearningPaths) {
        System.out.println("Previous learning paths: " + previousLearningPaths);
        if (previousLearningPathsDropdown != null) {
            previousLearningPathsDropdown.removeAllItems(); // Clear the dropdown items

            if (previousLearningPaths != null && !previousLearningPaths.isEmpty()) {
                // Fill the dropdown with the previous learning paths
                for (String learningPath : previousLearningPaths) {
                    previousLearningPathsDropdown.addItem(learningPath);
                    previousLearningPathsDropdown.setSelectedIndex(-1);
                }
                previousLearningPathsDropdown.setVisible(true);
            } else {
                // Hide or disable the dropdown if there are no previous learning paths
                previousLearningPathsDropdown.setVisible(false);
            }

            // Update the UI after adding items to the dropdown
            previousLearningPathsDropdown.revalidate();
            previousLearningPathsDropdown.repaint();
        }
    }



}
