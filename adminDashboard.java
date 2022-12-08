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

public class adminDashboard {
    JFrame frame;
    JPanel dashboardJPanel;

    // Dashboard Panel Items
    JButton signoutButton, addUserButton, viewQnAButton;
    JLabel topicJLabel;

    JScrollPane serverPanel;
    boolean serverShowing = false;

    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    adminDashboard() throws IOException {
        RunServer rServer = new RunServer();
        Thread t1 = new Thread(rServer);
        t1.start();
        frame = new JFrame();
        dashboardJPanel = new JPanel();
        signoutButton = new JButton("Sign Out");
        // signoutButton.setIcon(new ImageIcon("signout.png"));
        addUserButton = new JButton("Add User");
        viewQnAButton = new JButton("View Q/A Chat");
        topicJLabel = new JLabel("Admin Dashboard");

        topicJLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        viewQnAButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addUserButton.setFont(new Font("Arial", Font.PLAIN, 20));
        signoutButton.setFont(new Font("Arial", Font.PLAIN, 20));

        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regForm regf = new regForm();
                frame.setVisible(false);
                device.setFullScreenWindow(regf);
                regf.setVisible(true);
            }
        });

        signoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginForm lf;
                try {
                    lf = new loginForm();
                    frame.setVisible(false);
                    lf.begin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        dashboardJPanel.setLayout(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.red);
        dashboardJPanel.setBorder(blackline);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 0, 0);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weighty = 0.0;
        c.weightx = 0.5;
        dashboardJPanel.add(topicJLabel, c);

        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 0;
        c.weighty = 1.0;
        c.weightx = 0.0;
        dashboardJPanel.add(signoutButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        dashboardJPanel.add(addUserButton, c);

        c.gridy = 2;
        dashboardJPanel.add(viewQnAButton, c);

        viewQnAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.gridy = 3;
                c.gridwidth = 3;
                c.anchor = GridBagConstraints.CENTER;
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1.0;
                c.weighty = 1.0;
                // serverPanel = new JScrollPane();
                // serverPanel.add(Server.messagearea);
                dashboardJPanel.add(Server.messagearea, c);
                frame.invalidate();
                frame.revalidate();
                frame.repaint();
            }
        });

        frame.add(dashboardJPanel);

    }

    public void begin() throws IOException {
        frame.setTitle("Sasta Teams");
        frame.setSize(1280, 720);
        device.setFullScreenWindow(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class RunServer implements Runnable {
    Server serv;

    @Override
    public void run() {
        try {
            if (Server.canRun == false) {
                serv = new Server();
                System.out.println(Server.messagearea);
                Server.canRun = true;
            }
            serv.runningServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
