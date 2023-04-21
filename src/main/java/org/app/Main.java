package org.app;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.*;
import java.util.Set;

public class Main {

    private static UserManager userManager;
    private static JFrame frame;

    public static void main(String[] args) {

        // Create a new user manager and JFrame
        userManager = new UserManager("users.ser");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        showLoginScreen();

        // Add a window listener to save the users when the program is closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                userManager.saveUsers();
            }
        });
        frame.setVisible(true);

    }

    // Method to show the login screen
    public static void showLoginScreen() {
        LoginScreen loginScreen = new LoginScreen(frame, userManager, (user, learningPaths) -> startUserInterface(user, userManager));
        loginScreen.show();
    }

    // Method to show the signup screen
    public static void showSignupScreen() {
        SignupScreen signupScreen = new SignupScreen(frame, userManager);
        signupScreen.show();
    }

    // Method to start the user interface
    public static void startUserInterface(User user, UserManager userManager) {
        System.out.println("User data: " + user);
        if (checkInternetConnection()) {
            // If the user has an internet connection, start the program
            Set<String> learningPaths = user.getLearningPaths().keySet();
            System.out.println("Learning paths: " + learningPaths);
            UserInterface userInterface = new UserInterface(user, learningPaths, userManager);
            userInterface.start();
        } else {
            // If the user does not have an internet connection, start the program without the AI
            UserInterfaceNoInternet userInterface = new UserInterfaceNoInternet();
            userInterface.start();
        }
    }

    // Method to check if the user has an internet connection
    private static boolean checkInternetConnection() {
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(3000);
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
