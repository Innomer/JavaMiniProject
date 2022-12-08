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

public class regForm extends JFrame {

    JPanel panel, allUserPanel;
    JLabel fJLabel, lJLabel;
    JLabel userJLabel, passJLabel, topicsJLabel, errorLabel, lvlJLabel;
    JTextField userTextField, passTextField, fTextField, lTextField;
    JComboBox lvlField;
    JButton submitButton, backButton;

    JScrollPane sP;

    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    public regForm() {
        panel = new JPanel();
        userJLabel = new JLabel("Username");
        passJLabel = new JLabel("Password");
        fJLabel = new JLabel("First Name");
        lJLabel = new JLabel("Last Name");
        topicsJLabel = new JLabel("Registration");
        errorLabel = new JLabel();
        lvlJLabel = new JLabel("Level of Access");
        userTextField = new JTextField(20);
        passTextField = new JPasswordField(20);
        fTextField = new JTextField(20);
        lTextField = new JTextField(20);
        String s[] = { "Student", "Teacher", "Admin" };
        lvlField = new JComboBox(s);
        submitButton = new JButton("Register");
        backButton = new JButton("Back");

        topicsJLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        userJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        fJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        lJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        lvlJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        backButton.setFont(new Font("Arial", Font.PLAIN, 15));

        userJLabel.setLabelFor(userTextField);
        passJLabel.setLabelFor(passTextField);
        fJLabel.setLabelFor(fTextField);
        lJLabel.setLabelFor(lTextField);
        lvlJLabel.setLabelFor(lvlField);
        errorLabel.setForeground(Color.red);
        topicsJLabel.setForeground(Color.white);
        userJLabel.setForeground(Color.white);
        passJLabel.setForeground(Color.white);
        lvlJLabel.setForeground(Color.white);
        fJLabel.setForeground(Color.white);
        lJLabel.setForeground(Color.white);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 20));

        panel.setBackground(Color.gray);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Connection connection = null;
                Statement sta = null;
                int rs = 0;
                String username = userTextField.getText();
                String password = passTextField.getText();
                String fName = fTextField.getText();
                String lName = lTextField.getText();
                int lvl = lvlField.getSelectedIndex();
                try {
                    String myUrl = "jdbc:mysql://localhost:3306/sasta_teams?useSSL=false";
                    connection = DriverManager.getConnection(myUrl, "root", "root");
                    if (!((isEmptyString(username)) || isEmptyString(password) || isEmptyString(fName)
                            || isEmptyString(lName))) {
                        String query = "Insert into account values(\"" + fName + "\",\"" + lName + "\",\"" + username
                                + "\",\"" + password + "\"," + lvl + ");";
                        System.out.println(query);
                        sta = connection.createStatement();
                        rs = sta.executeUpdate(query);
                        if (rs == 0) {
                            errorLabel.setText("Wrong Data Entered!");
                            System.out.println("Wrong!");
                        } else {
                            errorLabel.setText("");
                            System.out.println("Success!");
                        }
                    }
                    else{
                        errorLabel.setText("Pls Enter Data!");
                    }
                } catch (Exception exception) {
                    errorLabel.setText("Wrong Data Entered!");
                } finally {
                    try {
                        if (sta != null) {
                            sta.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                        remove(sP);
                        showExisting();
                        add(sP);
                        invalidate();
                        revalidate();
                        repaint();

                    } catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                adminDashboard ad;
                try {
                    ad = new adminDashboard();
                    ad.begin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
            }
        });

        panel.setLayout(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel.setBorder(blackline);
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 0.5;
        c.weighty = 0.0;
        panel.add(topicsJLabel, c);

        c.gridwidth = 1;
        c.weighty = 1.0;
        c.weightx = 0.0;
        c.gridy = 1;
        panel.add(userJLabel, c);

        c.gridx = 1;
        panel.add(userTextField, c);

        c.gridy = 2;
        panel.add(passTextField, c);

        c.gridx = 0;
        panel.add(passJLabel, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        panel.add(fJLabel, c);

        c.gridx = 1;
        panel.add(fTextField, c);

        c.gridy = 4;
        panel.add(lTextField, c);

        c.gridx = 0;
        panel.add(lJLabel, c);

        c.gridy = 5;
        panel.add(lvlJLabel, c);

        c.gridx = 1;
        panel.add(lvlField, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        panel.add(submitButton, c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        panel.add(errorLabel, c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        panel.add(backButton, c);

        begin(panel);
    }

    public void showExisting() {

        allUserPanel = new JPanel();
        JLabel allUser = new JLabel("All Users");
        allUser.setFont(new Font("Arial", Font.PLAIN, 30));

        allUserPanel.setBackground(Color.darkGray);
        allUser.setBackground(Color.lightGray);
        allUser.setOpaque(true);

        JLabel f = new JLabel("First Name");
        JLabel l = new JLabel("Last Name");
        JLabel u = new JLabel("Username");
        JLabel s = new JLabel("Designation");
        JLabel x = new JLabel("Index");
        

        f.setFont(new Font("Arial", Font.PLAIN, 15));
        l.setFont(new Font("Arial", Font.PLAIN, 15));
        u.setFont(new Font("Arial", Font.PLAIN, 15));
        s.setFont(new Font("Arial", Font.PLAIN, 15));
        x.setFont(new Font("Arial", Font.PLAIN, 15));

        f.setBackground(Color.lightGray);
        f.setOpaque(true);
        l.setBackground(Color.lightGray);
        l.setOpaque(true);
        u.setBackground(Color.lightGray);
        u.setOpaque(true);
        s.setBackground(Color.lightGray);
        s.setOpaque(true);
        x.setBackground(Color.lightGray);
        x.setOpaque(true);
        
        allUser.setHorizontalAlignment(SwingConstants.CENTER);
        f.setHorizontalAlignment(SwingConstants.CENTER);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        u.setHorizontalAlignment(SwingConstants.CENTER);
        s.setHorizontalAlignment(SwingConstants.CENTER);
        x.setHorizontalAlignment(SwingConstants.CENTER);

        Vector<JLabel> temps = new Vector<JLabel>();
        Vector<String> fnames = new Vector<String>();
        Vector<String> lnames = new Vector<String>();
        Vector<String> usernames = new Vector<String>();
        Vector<String> stat = new Vector<String>();

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
                fnames.add(rs.getString("Fname"));
                lnames.add(rs.getString("Lname"));
                usernames.add(rs.getString("username"));
                if(rs.getString("level").equals("0"))
                stat.add("Student");
                else if(rs.getString("level").equals("1"))
                stat.add("Teacher");
                else
                stat.add("Admin");
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

        allUserPanel.setLayout(new GridBagLayout());
        Border blackline=BorderFactory.createLineBorder(Color.black);
        allUserPanel.setBorder(blackline);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(2, 2, 2, 2);
        c2.gridx = 0;
        c2.gridy = 0;
        c2.gridwidth = 5;
        c2.weightx = 0.5;
        c2.weighty=1.0;
        c2.anchor = GridBagConstraints.CENTER;
        c2.fill=GridBagConstraints.HORIZONTAL;
        allUserPanel.add(allUser, c2);


        c2.fill = GridBagConstraints.BOTH;
        c2.gridwidth = 1;
        c2.weightx = 0.5;
        c2.anchor = GridBagConstraints.CENTER;
        c2.gridy=1;
        allUserPanel.add(x,c2);

        c2.gridx = 1;
        allUserPanel.add(f, c2);

        c2.gridx = 2;
        allUserPanel.add(l, c2);

        c2.gridx = 3;
        allUserPanel.add(u, c2);

        c2.gridx = 4;
        allUserPanel.add(s, c2);

        for (int i = 0; i < fnames.size(); i++) {
            c2.gridy = 2 + i;
            for (int j = 0; j < 4; j++) {
                JLabel temp = new JLabel();
                temp.setFont(new Font("Arial", Font.PLAIN, 15));
                temp.setHorizontalAlignment(SwingConstants.CENTER);
                temp.setForeground(Color.black);
                temp.setBackground(Color.lightGray);
                temp.setOpaque(true);
                if (j == 0)
                    temp.setText(fnames.get(i));
                else if (j == 1)
                    temp.setText(lnames.get(i));
                else if (j == 2)
                    temp.setText(usernames.get(i));
                else if (j == 3)
                    temp.setText(stat.get(i));
                c2.gridx = j + 1;
                temps.add(temp);
                allUserPanel.add(temp, c2);
            }
            c2.gridx = 0;
            JLabel index = new JLabel();
            index.setFont(new Font("Arial", Font.PLAIN, 15));
            index.setHorizontalAlignment(SwingConstants.CENTER);
            index.setForeground(Color.black);
            index.setBackground(Color.lightGray);
            index.setOpaque(true);
            index.setText(Integer.toString(i + 1));
            temps.add(index);
            allUserPanel.add(index, c2);
        }

        sP=new JScrollPane(allUserPanel);
        
    }

    public void begin(JPanel p) {
        showExisting();
        add(sP);
        add(p, BorderLayout.LINE_END);
        setTitle("Sasta Teams");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }
}
