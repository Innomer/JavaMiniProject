package MiniProject.JavaMiniProject;

import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.lang.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.*;

public class loginForm {

    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];
    JFrame frame;
    JPanel panel;
    JLabel userJLabel, passJLabel, topicsJLabel, errorLabel;
    JTextField userField, passField;
    JButton submitButton;

    // Constructor
    loginForm() throws IOException {

        // Initializing Items
        frame = new JFrame();
        panel = new JPanel();
        userJLabel = new JLabel("Username");
        passJLabel = new JLabel("Password");
        userField = new JTextField("mann@gmail.com", 20);
        passField = new JPasswordField("123", 20);
        submitButton = new JButton("Submit");
        topicsJLabel = new JLabel("Login");
        errorLabel = new JLabel();

        // Setting Fonts
        topicsJLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        userJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passJLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        //Setting Colors
        panel.setBackground(Color.decode("#1177bb"));
        topicsJLabel.setForeground(Color.white);
        userJLabel.setForeground(Color.white);
        passJLabel.setForeground(Color.white);

        // Parenting Labels to Text Fields
        userJLabel.setLabelFor(userField);
        passJLabel.setLabelFor(passField);

        // Login Functionality
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
                        if (rs.getString("username").equalsIgnoreCase(username)
                                && rs.getString("password").equals(password)) {
                            errorLabel.setText("");
                            System.out.println("Success");
                            frame.setVisible(false);

                            if (rs.getString("level").equals("2")) {
                                adminDashboard admin = new adminDashboard();
                                admin.begin();

                            } else {
                                stuTeach sT = new stuTeach(rs.getString("Fname"));
                                sT.begin();
                            }

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

        // Arranging Items on Panel
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

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        panel.add(errorLabel, c);

        frame.add(panel, BorderLayout.CENTER);
    }

    public static void main(String args[]) throws IOException {

        begin();
    }

    public static void begin() throws IOException {
        loginForm lf = new loginForm();
        lf.frame.setTitle("Sasta Teams");
        lf.frame.setSize(1280, 720);
        lf.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        device.setFullScreenWindow(lf.frame);
        lf.frame.setVisible(true);

    }
}
