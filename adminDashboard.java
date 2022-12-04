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

public class adminDashboard {
    JFrame frame;
    JPanel dashboardJPanel,regFormJPanel,chatJPanel;

    //Dashboard Panel Items
    JButton signoutButton,addUserButton,viewQnAButton;
    JLabel topicJLabel;

    adminDashboard()
    {
        frame=new JFrame();
        dashboardJPanel=new JPanel();
        signoutButton=new JButton("");
        signoutButton.setIcon(new ImageIcon("MiniProject\signout.png"));
        addUserButton=new JButton("Add User");
        viewQnAButton=new JButton("View Q/A Chat");

        dashboardJPanel.setLayout(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.red);
        dashboardJPanel.setBorder(blackline);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        
        c.anchor=GridBagConstraints.FIRST_LINE_END;
        dashboardJPanel.add(signoutButton,c);

        frame.add(dashboardJPanel);

    }
    public static void main(String args[]) {
        adminDashboard ad = new adminDashboard();
        ad.frame.setTitle("Sasta Teams");
        ad.frame.setSize(1280, 720);
        ad.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // lf.frame.setLayout(null);
        ad.frame.setVisible(true);
    }
}
