package org.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AnswersInput extends UserInput {

    private final JTextArea questions = new JTextArea();

    // Shows the input screen
    public void showInputScreen(JFrame frame, ActionListener action) {
        frame.getContentPane().removeAll();

        // Set the layout
        frame.setTitle("User Knowledge verification");
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);

        frame.setTitle("User Knowledge verification");

        // Create the components
        label = new JLabel("Please answer the following questions: ");

        textArea = new JTextArea("""
                1. Question1
                2. Question2
                3. Question3
                """);
        textArea.setForeground(Color.gray);
        // When the text area is clicked, the text is removed and the color is changed to black
        textArea.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals("""
                        1. Question1
                        2. Question2
                        3. Question3
                        """)) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().trim().equals("")) {
                    textArea.setText("""
                            1. Question1 answer
                            2. Question2 answer
                            3. Question3 answer
                            """);
                    textArea.setForeground(Color.GRAY);
                }
            }
        });

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        textAreaScrollPane.setPreferredSize(new Dimension(500, 200));

        submitButton = new JButton("Submit");

        questions.setBackground(Color.gray);
        questions.setLineWrap(true);
        questions.setWrapStyleWord(true);
        questions.setEditable(false);
        JScrollPane questionsScrollPane = new JScrollPane(questions);
        questionsScrollPane.setPreferredSize(new Dimension(500, 200));

        // Add the components to the frame
        frame.add(label);
        frame.add(textAreaScrollPane);
        frame.add(submitButton);
        frame.add(questionsScrollPane);

        layout.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, frame);

        layout.putConstraint(SpringLayout.WEST, questionsScrollPane, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, questionsScrollPane, 10, SpringLayout.SOUTH, label);

        layout.putConstraint(SpringLayout.WEST, textAreaScrollPane, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, textAreaScrollPane, 10, SpringLayout.SOUTH, questionsScrollPane);

        layout.putConstraint(SpringLayout.WEST, submitButton, 10, SpringLayout.WEST, frame);
        layout.putConstraint(SpringLayout.NORTH, submitButton, 10, SpringLayout.SOUTH, textAreaScrollPane);

        submitButton.addActionListener(action);
        submitButton.setActionCommand("answers_submit");

        frame.validate();
        frame.repaint();
    }

    public void setTextArea(String text) {
        questions.setText(text);
    }
}
