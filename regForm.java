package MiniProject.JavaMiniProject;

import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.lang.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;

public class regForm extends JFrame{

    JPanel panel;
    JLabel fJLabel,lJLabel;
    JLabel userJLabel, passJLabel, topicsJLabel, errorLabel,lvlJLabel;
    JTextField userTextField, passTextField,fTextField,lTextField;
    JComboBox lvlField;
    JButton submitButton;

    public regForm()
    {
        panel=new JPanel();
        userJLabel=new JLabel("Username");
        passJLabel=new JLabel("Password");
        fJLabel=new JLabel("First Name");
        lJLabel=new JLabel("Last Name");
        topicsJLabel=new JLabel("Registration");
        errorLabel=new JLabel();
        lvlJLabel=new JLabel("Level of Access");
        userTextField=new JTextField(20);
        passTextField=new JPasswordField(20);
        fTextField=new JTextField(20);
        lTextField=new JTextField(20);
        String s[]={"1","2","3"};
        lvlField=new JComboBox(s);
        submitButton=new JButton("Register");


        topicsJLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        userJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        fJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        lJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        lvlJLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        userJLabel.setLabelFor(userTextField);
        passJLabel.setLabelFor(passTextField);
        fJLabel.setLabelFor(fTextField);
        lJLabel.setLabelFor(lTextField);
        lvlJLabel.setLabelFor(lvlField);


        panel.setLayout(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.red);
        panel.setBorder(blackline);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(topicsJLabel, c);

        c.gridwidth = 1;
        c.gridy = 1;
        panel.add(userJLabel, c);

        c.gridx = 1;
        panel.add(userTextField, c);

        c.gridy = 2;
        panel.add(passTextField, c);

        c.gridx = 0;
        panel.add(passJLabel, c);

        c.gridx=0;
        c.gridy=3;
        c.gridwidth=1;
        panel.add(fJLabel,c);

        c.gridx=1;
        panel.add(fTextField,c);

        c.gridy=4;
        panel.add(lTextField,c);

        c.gridx=0;
        panel.add(lJLabel,c);

        c.gridy=5;
        panel.add(lvlJLabel,c);

        c.gridx=1;
        panel.add(lvlField,c);

        c.gridx=0;
        c.gridy = 6;
        c.gridwidth = 2;
        panel.add(submitButton, c);

        c.gridx=0;
        c.gridy=7;
        c.gridwidth = 2;
        panel.add(errorLabel,c);



        begin(panel);
    }

    public void begin(JPanel p)
    {
        add(p,BorderLayout.CENTER);
        setTitle("Sasta Teams");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
