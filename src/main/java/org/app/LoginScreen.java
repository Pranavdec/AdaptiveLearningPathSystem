package org.app;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.function.BiConsumer;

public class LoginScreen {

    private final JFrame frame;
    private final UserManager userManager;
    private final BiConsumer<User, Set<String>> onSuccess;

    // Constructor
    public LoginScreen(JFrame frame, UserManager userManager, BiConsumer<User, Set<String>> onSuccess) {
        this.userManager = userManager;
        this.frame = frame;
        this.onSuccess = onSuccess;
    }

    // Method to show the login screen
    public void show() {
        frame.getContentPane().removeAll();

        frame.setTitle("Login");
        frame.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = userManager.loginUser(username, password);
            if (user != null) {
                // Pass the user's learningPaths when creating a new UserInterface instance
                onSuccess.accept(user, user.getTopicsWithLearningPaths());
                frame.dispose(); // Dispose the login screen JFrame after a successful login
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
            }
        });



        JButton signupButton = new JButton("Sign up");
        signupButton.addActionListener(e -> Main.showSignupScreen());

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(signupButton);

        frame.revalidate();
        frame.repaint();
    }
}
