package dataGenerator;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Formatter;

import static dataGenerator.createAccount.emailPattern;

public class forgotPassword extends JFrame {
    private JButton nextButton;
    public JTextField emailTF;
    public JTextField confirmPasswordTf;
    private JButton finishButton;
    private JTextField newPasswordTf;
    private JPanel forgotPasswordPanel;
    private JButton logInButton;

    public forgotPassword() {
        setContentPane(forgotPasswordPanel);
        setTitle("reset password");
        setMinimumSize(new Dimension(600, 474));
        setVisible(true);

        nextButton.addActionListener(e -> {
            validateEmail();
            readFile();
        });
        logInButton.addActionListener(e -> {
            Login loginWindow = new Login();
            loginWindow.next();
            dispose();
        });
        finishButton.addActionListener(e -> {
            if (!confirmPasswordTf.getText().isEmpty()){
                validateForm();
                writeFile();
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
        public void readFile () {//initializing variables
            File loginFile = new File("LoginDetails.txt");
            String userEmail;
            String str;

            String compareKey = String.valueOf(emailTF.getText());
            try {
                FileReader fileReader = new FileReader(loginFile);
                BufferedReader bufferedR = new BufferedReader(fileReader);
                while ((str = bufferedR.readLine()) != null) {
                    String[] token = str.split(",");
                    userEmail = token[0];
                    if (compareKey.matches(emailPattern) && !compareKey.equals(userEmail)){
                        JOptionPane.showMessageDialog(this, "account not found but an account can be assigned to this email address, create a password");
                        finishButton.setEnabled(true);
                    }
                    if (userEmail.equals(compareKey)) {
                        System.out.println("success");
                        JOptionPane.showMessageDialog(this, "account found");
                        finishButton.setEnabled(true);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void validateEmail () {
            String email = emailTF.getText();
            //this is me validating the text fields
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "please check all required field",
                        "try again", JOptionPane.ERROR_MESSAGE);
            }
            else if (!email.matches(emailPattern)){
                JOptionPane.showMessageDialog(this, "please enter a valid email address");
            }
            else {
                System.out.println("email matches");
            }
        }
        public void validateForm () {
            String passWord = String.valueOf(newPasswordTf.getText());// shall convert the password form any type it's written in to a string
            String confirmPassWord = String.valueOf(confirmPasswordTf.getText());//same as above
            if (passWord.isEmpty() || confirmPassWord.isEmpty()) {
                JOptionPane.showMessageDialog(this, "please fill all required fields");
            } else if (!passWord.equals(confirmPassWord)) {
                JOptionPane.showMessageDialog(this,
                        "passwords do not match");
            } else {
                JOptionPane.showMessageDialog(this, "password changed, proceed to login!");
            }
        }
        File file = new File("LoginDetails.txt");
        public void writeFile () {
            try {
                FileWriter fileOutput = new FileWriter(file);
                Formatter fileFormat = new Formatter(fileOutput);
                fileFormat.format("%s,%s", emailTF.getText(), confirmPasswordTf.getText());
                fileFormat.close();
                fileOutput.close();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    public void next(){
        // just because I want a clean code is my reason for this method.
        // so I'm going to assign it to the object of this class
    }

}
