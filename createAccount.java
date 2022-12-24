package dataGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class createAccount extends JFrame { // setting variables
    public static final String emailPattern = "^(.+)@(\\S+)$";// the format for email, just to validate the email input given
    public JPanel createAccountPanel;
    public JTextField emailTF;
    public JTextField passwordTF;
    public JTextField confirmPasswordTF;
    public JButton loginButton;
    public JButton createAccountButton1;
    private JTextField lastnameTF;
    private JTextField firstNameTF;

    /**
     * the below class are the listeners for the buttons
     * and also a switch to validate the text fields
     */
    public createAccount() {
        //the next line of code is to display the frame for login during run
        setContentPane(createAccountPanel);
        setTitle("Create account");
        setMinimumSize(new Dimension(450, 474));
        setVisible(true); // passing true to ensure the frame is visible during run

        // these one below are for the create account button, when it is clicked, it validates the inputs given and then open the file in the user directory
        createAccountButton1.addActionListener(e -> {
            createAcctForm();
            openFile();
        });
        loginButton.addActionListener(e -> {
            if (e.getSource() == loginButton){ // I have written this if, to open the login window when the login button is clicked
                Login loginWindow = new Login();
                loginWindow.next();
                dispose();
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    /** method to store the information from the text fields**/
    public void createAcctForm(){
        String firstName = firstNameTF.getText();
        String lastName = lastnameTF.getText();
        String email = emailTF.getText();
        String passWord = String.valueOf(passwordTF.getText());// shall convert the password input given from any type it's written in to a String
        String confirmPassWord = String.valueOf(confirmPasswordTF.getText());//same as above


        //this is me validating the text fields
        if (firstName.isEmpty() || email.isEmpty() || lastName.isEmpty() || passWord.isEmpty() || confirmPassWord.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "please check all required field",
                    "try again", JOptionPane.ERROR_MESSAGE);
        }
        else if(!passWord.equals(confirmPassWord)){
            JOptionPane.showMessageDialog(this,
                    "passwords do not match");
        }
        else if (!email.matches(emailPattern)){
            JOptionPane.showMessageDialog(this, "please input a valid email address");
        }
        else {
            JOptionPane.showMessageDialog(this,"Account created!!");
        }

    }
/** here I am taking the set of values that would be used for log in from the registration window ( email and password ) and writing it into a file so the marker of this program can
     log in after creating account. I have done this instead of storing the login information in a database as that is a lot more work
    */
   File file = new File(("LoginDetails.txt")); // this file will be created in the marker's directory as well

    public void openFile(){
        try{
        FileWriter fileOutput = new FileWriter(file);
            Formatter fileFormat = new Formatter(fileOutput);
                fileFormat.format("%s,%s",emailTF.getText(), passwordTF.getText());
                fileFormat.close();
                fileOutput.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } // I saw on stackoverflow that I don't have to close file unless it's a stream

    public void next(){
        // just because I want a clean code is my reason for this method.
        // so I'm going to assign it to the object of this class when changing window
    }

}
