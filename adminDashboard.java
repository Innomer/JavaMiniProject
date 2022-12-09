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
    JPanel leftJPanel, rightJPanel;

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
        signoutButton = new JButton("Sign Out");
        // signoutButton.setIcon(new ImageIcon("signout.png"));
        addUserButton = new JButton("Add User");
        viewQnAButton = new JButton("View Q/A Chat");
        topicJLabel = new JLabel("Admin Dashboard");
        leftJPanel=new JPanel();
        rightJPanel=new JPanel();

        topicJLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        viewQnAButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addUserButton.setFont(new Font("Arial", Font.PLAIN, 20));
        signoutButton.setFont(new Font("Arial", Font.PLAIN, 20));

        topicJLabel.setForeground(Color.white);

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

        leftJPanel.setLayout(new GridBagLayout());
        rightJPanel.setLayout(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        leftJPanel.setBorder(blackline);
        rightJPanel.setBorder(blackline);
        leftJPanel.setBackground(Color.decode("#1177bb"));
        rightJPanel.setBackground(Color.lightGray);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weighty = 1.0;
        c.weightx = 1.5;
        leftJPanel.add(topicJLabel, c);

        // c.gridx = 2;
        // c.gridy = 0;
        // c.weighty = 1.0;
        // c.weightx = 0.0;
        // leftJPanel.add(signoutButton, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
        leftJPanel.add(addUserButton, c);

        c.gridy = 2;
        leftJPanel.add(viewQnAButton, c);
        
        c.gridy=3;
        leftJPanel.add(signoutButton,c);

        frame.add(leftJPanel, BorderLayout.LINE_START);

        viewQnAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints c2 = new GridBagConstraints();
                c2.insets = new Insets(5, 5, 5, 5);
                c2.gridy = 0;
                c2.gridx=0;
                c2.gridwidth = 1;
                c2.anchor = GridBagConstraints.CENTER;
                c2.fill = GridBagConstraints.BOTH;
                c2.weightx = 1.0;
                c2.weighty = 1.0;
                // serverPanel = new JScrollPane();
                // serverPanel.add(Server.messagearea);
                rightJPanel.add(Server.messagearea, c2);
                frame.invalidate();
                frame.revalidate();
                frame.repaint();
            }
        });
        frame.add(rightJPanel,BorderLayout.CENTER);

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
