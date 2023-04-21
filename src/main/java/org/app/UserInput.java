package org.app;
import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class UserInput {

    //Instance variables
    protected JButton submitButton;
    protected JLabel label;
    protected JTextArea textArea;

    //abstract method to show the input screen
    public abstract void showInputScreen(JFrame frame, ActionListener action);

    //method to get the text from the text area
    public String getTextArea() {
        return textArea.getText();
    }


}
