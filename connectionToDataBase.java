package dataGenerator;

import com.github.javafaker.Faker;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Locale;


public class connectionToDataBase extends JFrame {
    // initialising and assigning all variables
    public JTextField connectionStringTf;
    public JTextField userNameTf;
    public JTextField passWordTf;
    public JTextField limitTf;
    private JButton nextButton;
    private JButton connectButton;
    private JPanel connectionPagePanel;
    public JRadioButton studentRadioButton;
    public JRadioButton employeeRadioButton;
    public JRadioButton customerButton;
    int row = 0;// this is the only int in my program haha, it's empty now, so I'll parse a string later

    public connectionToDataBase() {
        // setting the layout of the JFrame
        setContentPane(connectionPagePanel);
        setTitle("connect to database");
        setMinimumSize(new Dimension(550, 600));

        setVisible(true);// makes the frame visible
        connectButton.addActionListener(e -> {
            validateFormInputs();
            connection2Database();
        });
        // action listener for the next button
        nextButton.addActionListener(e -> {
            if ( e.getSource() == nextButton){
                salutation byePage = new salutation(); // same ol' changes window to specified one
                byePage.next();
                dispose();// close the frame, not the entire program lol
            }
        });

        studentRadioButton.addActionListener(e -> {
            createStudentTable();
            for (int i = 0; i < row; i++) {
                populateStudentTable();// the method that does the populating
            }
            if (studentRadioButton != null){
                nextButton.setEnabled(true); // this one makes the next button clickable
            }
        });
        employeeRadioButton.addActionListener(e -> {
            createEmployeeTable();
            for (int i = 0; i < row; i++) {
                populateEmployeeTable();
            }
            if (employeeRadioButton != null){
                nextButton.setEnabled(true); // this enables the disabled button that will enable the user proceed
            }
        });
        customerButton.addActionListener(e -> {
            createCustomerTable();
            for (int i = 0; i < row; i++) {
                populateCustomerTable();
            }
            if (customerButton != null){
                nextButton.setEnabled(true); // this enables the disabled button that will enable the user proceed
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void validateFormInputs(){
        // this method is used to format the keyboard input from the user
        String userName= userNameTf.getText();
        String jdbcUrl = String.valueOf(connectionStringTf.getText());// shall convert the password from any type it's written in to a string
        String passWord= passWordTf.getText();
        String rows = limitTf.getText();
        try {
            if (!(!userName.isEmpty() && !passWord.isEmpty() && !jdbcUrl.isEmpty() && !rows.isEmpty())){
                JOptionPane.showMessageDialog(this,
                        "please check all required field",
                        "try again", JOptionPane.ERROR_MESSAGE);
            }
            row = Integer.parseInt(rows); // remember that only Int?, I have parsed it now. It is with this integer that my program will know how many rows to fill in the database
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

    }

    /* the string method below are the java Faker class library being implemented
      we have used it to generate names, addresses, email e.t.c*/
    String getNames() {
            Faker data = new Faker(new Locale("en-GB"));
            return data.name().fullName();
    }
    String getAddress() {
        Faker data = new Faker(new Locale("en-GB"));
        return data.address().streetAddress();
    }
    String getEmail () {
        Faker data = new Faker(new Locale("en-GB"));
        return data.bothify("??????#@gmail.com");
    }
    String getPhoneNumber () {
        Faker data = new Faker(new Locale("en-GB"));
        return data.phoneNumber().cellPhone();
    }
    String getIdNumber(){
            Faker data = new Faker(new Locale("en-GB"));
            return data.idNumber().valid();
    }
    // this is the method that connects to the database
    // the method below tests the connection to the database first before anything else , this is more like me formatting the connection details before eventually doing the deed
    public void connection2Database(){
        String userName = userNameTf.getText();
        String jdbcUrl = String.valueOf(connectionStringTf.getText());
        String passWord = passWordTf.getText();
        try {
           Connection connect = DriverManager.getConnection(jdbcUrl, userName, passWord);
            if (connect != null) {
                JOptionPane.showMessageDialog(this, "connection successful!");
            } else {
                JOptionPane.showMessageDialog(this, "connection failed, check parameters");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*so because I don't know which template the user will pick, and I don't know the names of the tables in their database,
     *  I am creating my own table, one for each template*/
    public void createCustomerTable(){
        // this one creates the table for customer template
        String userName = userNameTf.getText();
        String jdbcUrl = String.valueOf(connectionStringTf.getText());
        String passWord = passWordTf.getText();
        try {
           Connection connect = DriverManager.getConnection(jdbcUrl, userName, passWord); // this is the guy that makes the connection possible a.k.a. decision maker!
            if (connect != null) {
                JOptionPane.showMessageDialog(this, "generating data! please click 'next' ");
                String query1= "CREATE TABLE IF NOT EXISTS customers(full_name VARCHAR(45) NOT NULL,email_address VARCHAR(45) NOT NULL,phone_number VARCHAR(45) NOT NULL,primary key (full_name))";// this is what we are asking sql to do, remember, it's to create a table
                Statement queryStatement = connect.createStatement(); // this is the messenger that delivers Sql query
                queryStatement.executeUpdate(query1);
            } else {
                JOptionPane.showMessageDialog(this, "connection failed, check parameters");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createEmployeeTable(){
        // this one creates the table in the database for employee template
        String userName = userNameTf.getText();
        String jdbcUrl = String.valueOf(connectionStringTf.getText());
        String passWord = passWordTf.getText();
        try {
           Connection connect = DriverManager.getConnection(jdbcUrl, userName, passWord);// remember decision maker?, look at it here again!
            if (connect != null) {
                JOptionPane.showMessageDialog(this, "generating data! please click 'next' ");
                String query1= "CREATE TABLE IF NOT EXISTS employees(employee_id VARCHAR(45) NOT NULL,full_name VARCHAR(45) NOT NULL,email_address VARCHAR(45) NOT NULL,phone_number VARCHAR(45) NOT NULL,address VARCHAR(255) NOT NULL,primary key (employee_id))";
                Statement queryStatement = connect.createStatement(); // same as before
                queryStatement.executeUpdate(query1);
            } else {
                JOptionPane.showMessageDialog(this, "connection failed, check parameters");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createStudentTable(){
        // this one creates the table in the database for student template
        String userName = userNameTf.getText();
        String jdbcUrl = String.valueOf(connectionStringTf.getText());
        String passWord = passWordTf.getText();
        try {
            Connection connect = DriverManager.getConnection(jdbcUrl, userName, passWord);
            if (connect != null) {
                JOptionPane.showMessageDialog(this, "generating data please click 'next' ");
                String query1= "CREATE TABLE IF NOT EXISTS students(student_id VARCHAR(45) NOT NULL,full_name VARCHAR(45) NOT NULL,email_address VARCHAR(45) NOT NULL,phone_number VARCHAR(45) NOT NULL,primary key (student_id))";
                Statement queryStatement = connect.createStatement(); // same as other table methods
                queryStatement.executeUpdate(query1);
            } else {
                JOptionPane.showMessageDialog(this, "connection failed, check parameters");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   //
    private void populateCustomerTable(){
        /* this is another SQL query to actually take the data that JavaFaker will be generating and put it inside the database for every template the user may choose
         * so obviously, we should have a 'populate' method for each template*/
        // this one is for the customer template
            String userName = userNameTf.getText();
            String jdbcUrl = String.valueOf(connectionStringTf.getText());
            String passWord = passWordTf.getText();
            try {
               Connection connect = DriverManager.getConnection(jdbcUrl, userName, passWord);
            String query = "insert into customers(full_name, email_address, phone_number)values(?,?,?)";
            PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, getNames());
                    statement.setString(2, getEmail());
                    statement.setString(3, getPhoneNumber());
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void populateStudentTable(){ // this one is for the student template
        String userName = userNameTf.getText();
        String jdbcUrl = String.valueOf(connectionStringTf.getText());
        String passWord = passWordTf.getText();
        try {
           Connection connect = DriverManager.getConnection(jdbcUrl, userName, passWord);
            String query = "insert into students(student_id,full_name, email_address, phone_number)values(?,?,?,?)";
            PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, getIdNumber());
                statement.setString(2, getNames());
                statement.setString(3, getEmail());
                statement.setString(4, getPhoneNumber());
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void populateEmployeeTable(){// and this is for the employee template
            String userName = userNameTf.getText();
            String jdbcUrl = String.valueOf(connectionStringTf.getText());
            String passWord = passWordTf.getText();
            try {
               Connection connect = DriverManager.getConnection(jdbcUrl, userName, passWord);
            String query = "insert into employees(employee_id,full_name, email_address, phone_number,address)values(?,?,?,?,?)";// the query statement
            PreparedStatement statement = connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, getIdNumber());
                statement.setString(2, getNames());
                statement.setString(3, getEmail());
                statement.setString(4, getPhoneNumber());
                statement.setString(5, getAddress());
            statement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void next(){
        // just because I want a clean code is my reason for this method.
        // so I'm going to assign it to the object of this class
    }



}
