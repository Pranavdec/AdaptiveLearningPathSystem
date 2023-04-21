package org.app;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class User implements Serializable {

    // Instance variables
    private final String username;
    private final String password;
    private final Map<String, String> learningPaths;
    private transient UserManager userManager;

    // Constructor for the User class, initializes the required variables
    public User(String username, String password, UserManager userManager) {
        this.username = username;
        this.password = password;
        this.learningPaths = new HashMap<>();
        this.userManager = userManager;
    }

    // Getter for the username
    public String getUsername() {
        return username;
    }

    // Checks if the provided password matches the stored password
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    // Stores a learning path for a given topic and saves the user
    public void storeLearningPath(String topic, String learningPath) {
        learningPaths.put(topic, learningPath);
        userManager.saveUser(this);

        // Add a print statement to check if learningPaths are updated correctly
        System.out.println("Updated learningPaths: " + learningPaths);
    }

    // Retrieves the learning path for a given topic
    public String getLearningPath(String topic) {
        return learningPaths.get(topic);
    }

    // Returns a set of topics for which learning paths are stored
    public Set<String> getTopicsWithLearningPaths() {
        return learningPaths.keySet();
    }

    // Sets the userManager for the user
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    // Getter for the learningPaths map
    public Map<String, String> getLearningPaths() {
        return learningPaths;
    }
}
