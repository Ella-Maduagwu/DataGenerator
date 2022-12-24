package dataGenerator;

import javax.swing.*;
import java.awt.*;
public class salutation extends JFrame {
    private JPanel mainPanel;
    private JButton exitButton;
    public salutation() {
        // setting the layout of the JFrame
        setContentPane(mainPanel);
        setTitle("farewell");
        setMinimumSize(new Dimension(500, 474)); // I'm sure you guessed this is the size of the window, you're right!

        setVisible(true);// makes the frame visible

        exitButton.addActionListener(e -> System.exit(0));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// This code ensure that the program stops running when you click the 'x' at the topmost right
    }
    public void next(){
        // just because I want a clean code is my reason for this method.
        // so I'm going to assign it to the object of this class
    }

}

