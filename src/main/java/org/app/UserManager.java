package org.app;

import java.io.*;
import java.util.HashMap;

class UserManager {
    private HashMap<String, User> users;
    private final String filePath;

    // Constructor for the UserManager class, initializes the required variables
    public UserManager(String filePath) {
        this.filePath = filePath;
        loadUsers();
    }

    // Saves a user object to the users HashMap and saves the users HashMap to the file
    public void saveUser(User user) {
        users.put(user.getUsername(), user);
        saveUsers();
    }

    // Saves the users HashMap to the file
    public void saveUsers() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads users HashMap from the file if it exists
    @SuppressWarnings("unchecked")
    private void loadUsers() {
        System.out.println("Before loading users: " + users);

        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream(filePath);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                users = (HashMap<String, User>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();

                System.out.println("Loaded users: " + users);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            users = new HashMap<>();
        }

        System.out.println("After loading users: " + users);
    }

    // Registers a new user if the username is not already taken
    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password, this));
        saveUsers();
        return true;
    }

    // Logs in a user if the provided username and password are correct
    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            user.setUserManager(this);
            return user;
        }
        return null;
    }

}
