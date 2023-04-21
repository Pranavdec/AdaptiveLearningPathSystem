package org.app;

import javax.swing.*;
import java.awt.*;


public class SignupScreen {

    // Instance variables
    private final JFrame frame;
    private final UserManager userManager;

    // Constructor for SignupScreen class
    public SignupScreen(JFrame frame, UserManager userManager) {
        this.frame = frame;
        this.userManager = userManager;
    }

    // Method to show the signup screen
    public void show() {
        frame.getContentPane().removeAll();

        frame.setTitle("Sign Up");
        frame.setLayout(new GridLayout(5, 2));

        // Create the components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField();

        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (password.equals(confirmPassword)) {
                if (userManager.registerUser(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Account created. Please log in.");
                    Main.showLoginScreen(); // Redirect to log in screen after successful signup
                } else {
                    JOptionPane.showMessageDialog(frame, "Username already exists.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.");
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> Main.showLoginScreen());


        // Add the components to the frame
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(confirmPasswordLabel);
        frame.add(confirmPasswordField);
        frame.add(signupButton);
        frame.add(backButton);

        frame.revalidate();
        frame.repaint();
    }
}
