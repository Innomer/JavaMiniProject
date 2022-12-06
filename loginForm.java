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

public class loginForm {

    JFrame frame;
    JPanel panel;
    JLabel userJLabel, passJLabel, topicsJLabel, errorLabel;
    JTextField userField, passField;
    JButton submitButton;

    //Constructor
    loginForm() {
        //Initializing Items
        frame = new JFrame();
        panel = new JPanel();
        userJLabel = new JLabel("Username");
        passJLabel = new JLabel("Password");
        userField = new JTextField("Enter username", 20);
        passField = new JPasswordField("Enter password", 20);
        submitButton = new JButton("Submit");
        topicsJLabel = new JLabel("Login");
        errorLabel = new JLabel();

        //Setting Fonts
        topicsJLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        userJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        // topicsJLabel.setBounds(400, 20, 100, 30);
        // userJLabel.setBounds(200, 60, 100, 20);
        // userField.setBounds(350, 60, 300, 20);
        // passJLabel.setBounds(200, 110, 100, 20);
        // passField.setBounds(350, 110, 300, 20);
        // submitButton.setBounds(400, 150, 100, 30);

        //Parenting Labels to Text Fields
        userJLabel.setLabelFor(userField);
        passJLabel.setLabelFor(passField);

        // userJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        // passJLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        // passField.setHorizontalAlignment(SwingConstants.CENTER);
        // userField.setHorizontalAlignment(SwingConstants.CENTER);

        //Login Functionality
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = passField.getText();
                Connection connection = null;
                Statement sta = null;
                ResultSet rs = null;
                try {
                    String myUrl = "jdbc:mysql://localhost:3306/sasta_teams?useSSL=false";
                    connection = DriverManager.getConnection(myUrl, "root", "root");
                    String query = "Select * from account";
                    sta = connection.createStatement();
                    rs = sta.executeQuery(query);
                    while (rs.next()) {
                        //System.out.println(rs.getString("username")+username+rs.getString("password"));
                        if (rs.getString("username").equalsIgnoreCase(username)
                                && rs.getString("password").equals(password)) {
                            errorLabel.setText("");
                            System.out.println("Success");
                            frame.setVisible(false);
                            adminDashboard admin=new adminDashboard();
                            admin.begin();

                        } else {
                            errorLabel.setText("Wrong Credentials. Please Check your Username and Password Again");
                            errorLabel.setForeground(Color.red);
                            errorLabel.setFont(new Font("Arial", Font.BOLD, 20));
                            errorLabel.setBounds(200, 200, 500, 50);
                        }
                    }
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (sta != null) {
                            sta.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }

                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        });
        // panel.setLayout(new BorderLayout());

        //Arranging Items on Panel
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
        panel.add(userField, c);

        c.gridy = 2;
        panel.add(passField, c);

        c.gridx = 0;
        panel.add(passJLabel, c);

        c.gridy = 3;
        c.gridwidth = 2;
        panel.add(submitButton, c);

        c.gridx=0;
        c.gridy=4;
        c.gridwidth = 2;
        panel.add(errorLabel,c);

        // panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        /*
         * panel.add(userJLabel);
         * panel.add(passJLabel);
         * panel.add(userField);
         * panel.add(passField);
         * panel.add(submitButton);
         */
        // panel.add(topicsJLabel);
        // panel.add(userJLabel);
        // panel.add(passJLabel);
        // panel.add(userField);
        // panel.add(passField);
        // panel.add(submitButton);
        // panel.add(errorLabel);
        frame.add(panel,BorderLayout.CENTER);
        // frame.add(panel);
        // frame.add(topicsJLabel);
        // frame.add(userJLabel);
        // frame.add(passJLabel);
        // frame.add(userField);
        // frame.add(passField);
        // frame.add(submitButton);
        // frame.add(errorLabel);

        // frame.add(panel);
    }

    public static void main(String args[]) {
        begin();
    }
    public static void begin()
    {
        loginForm lf = new loginForm();
        lf.frame.setTitle("Sasta Teams");
        lf.frame.setSize(1280, 720);
        lf.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // lf.frame.setLayout(null);
        lf.frame.setVisible(true);
    }
}
