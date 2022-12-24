package dataGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static dataGenerator.createAccount.emailPattern;

public class Login extends JFrame { // these are the variables
    public JTextField passwordTF;
    public JButton loginButton;
    private JButton createAccountButton;
    private JButton forgotPasswordButton;
    private JPanel LoginPanel;
    public JTextField emailTF;
    public JButton nextButton;

    public Login() {
        setContentPane(LoginPanel);
        setTitle("Login");
        setMinimumSize(new Dimension(500, 550));
        setVisible(true); // passing true to ensure the frame is visible during run

        /*these are action listeners, so when you click them, they do what ever you have set as instruction
         */
        loginButton.addActionListener(e -> {// this one ensures correct inputs are gotten
            validateTfInputs();
            readFile();
        });
        createAccountButton.addActionListener(e -> {
            if (e.getSource() == createAccountButton) { // this one and the ones after it changes to the window specified
                createAccount registrationWindow = new createAccount();
                registrationWindow.next();
                dispose();
            }
        });
        forgotPasswordButton.addActionListener(e -> {
            if (e.getSource() == forgotPasswordButton) {
                forgotPassword passwordWindow = new forgotPassword();
                passwordWindow.next();
                dispose();
            }
        });
        nextButton.addActionListener(e -> {
                if (e.getSource() == nextButton) {
                    connectionToDataBase connectionWindow = new connectionToDataBase();
                    connectionWindow.next();
                    dispose();
            }
        });setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// you already remembered by now, To successful close the program
    }
// this method is to ensure data is inputted according to format
    public void validateTfInputs(){
        String emailAddy= emailTF.getText();
        String passWord = String.valueOf(passwordTF.getText());// shall convert the password from any type it's written in to a string

        if (emailAddy.isEmpty()|| passWord.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "please check all required field",
                    "try again", JOptionPane.ERROR_MESSAGE);
        }
        else if (!emailAddy.matches(emailPattern)){
            JOptionPane.showMessageDialog(this, "please input a valid email address");
        }
    }
    public void readFile() {// you can guess this method is to ensure that only registered users are able to log in
        File loginF = new File("LoginDetails.txt");
        String userEmail, userPassword;
        String str;

        String compareKey = String.valueOf(emailTF.getText());
        String passKey = String.valueOf(passwordTF.getText());
        try {
            FileReader fileReader = new FileReader(loginF);
            BufferedReader bufferedR = new BufferedReader(fileReader);
            while ((str = bufferedR.readLine()) != null) {
                /* splitting the lines in the text file to be able to easily compare them to user login input
                / getting the index of the user inputted password and email
                / it's just here that I am actually getting the split strings from the file
                 */
                String[] token = str.split(",");
                userEmail=token[0];// indexing each split
                userPassword=token[1];
                    if (userEmail.equals(compareKey) && userPassword.equals(passKey)) {
                        System.out.println("success");// This code is for developer benefit
                        JOptionPane.showMessageDialog(this, "Login successful");// this one is for user benefit
                        nextButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Login failed, please retry ");
                    }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void next(){
        /* just because I want a clean code is my reason for this method.
        / so I'm going to assign it to the object of this class when changing window
        / without it, I'd get an error (not a serious one though, just those yellow triangle ones that would be
        in the same spot as that green check sign at the top right )
         */
    }

}







































