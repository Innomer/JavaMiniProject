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
    JPanel dashboardJPanel;

    //Dashboard Panel Items
    JButton signoutButton,addUserButton,viewQnAButton;
    JLabel topicJLabel;

    static GraphicsDevice device = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getScreenDevices()[0];

    adminDashboard()
    {
        frame=new JFrame();
        dashboardJPanel=new JPanel();
        signoutButton=new JButton("Sign Out");
        // signoutButton.setIcon(new ImageIcon("signout.png"));
        addUserButton=new JButton("Add User");
        viewQnAButton=new JButton("View Q/A Chat");


        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                regForm regf=new regForm();
                frame.setVisible(false);
                device.setFullScreenWindow(regf);
                regf.setVisible(true);
            } 
        });

        viewQnAButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //Server serv=new Server();
                client cli=new client();
            }
        });
        
        signoutButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e)
           {
                loginForm lf=new loginForm();
                frame.setVisible(false);
                lf.begin();
           } 
        });



        dashboardJPanel.setLayout(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.red);
        dashboardJPanel.setBorder(blackline);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        
        c.anchor=GridBagConstraints.CENTER;
        c.gridx = 10;
        c.gridy = 0;
        dashboardJPanel.add(signoutButton,c);
        
        c.gridx=0;
        c.gridy=1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        dashboardJPanel.add(addUserButton, c);

        c.gridy=2;
        dashboardJPanel.add(viewQnAButton,c);


        frame.add(dashboardJPanel);

    }
    public static void begin(){
        adminDashboard ad = new adminDashboard();
        ad.frame.setTitle("Sasta Teams");
        ad.frame.setSize(1280, 720);
        // ad.frame.setUndecorated(true);
        device.setFullScreenWindow(ad.frame);
        ad.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // lf.frame.setLayout(null);
        ad.frame.setVisible(true);
    }
}
