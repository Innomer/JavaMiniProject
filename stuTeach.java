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
import java.time.chrono.JapaneseChronology;
import java.awt.*;

public class stuTeach {

    JFrame frame;
    JLabel topicJLabel;
    JPanel leftJPanel, rigthJPanel;
    JButton signoutButton;
    JButton grpJButton, chatJButton;

    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    public stuTeach(String chatName) {
        RunServerST rst=new RunServerST();
        Thread t3=new Thread(rst);
        t3.start();
        frame = new JFrame();
        topicJLabel = new JLabel("DashBoard");
        grpJButton = new JButton("Groups");
        chatJButton = new JButton("Q/A");
        signoutButton = new JButton("Sign Out");
        leftJPanel = new JPanel();
        rigthJPanel = new JPanel();

        topicJLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        grpJButton.setFont(new Font("Arial", Font.PLAIN, 20));
        chatJButton.setFont(new Font("Arial", Font.PLAIN, 20));
        signoutButton.setFont(new Font("Arial", Font.PLAIN, 20));

        topicJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        grpJButton.setHorizontalAlignment(SwingConstants.CENTER);
        chatJButton.setHorizontalAlignment(SwingConstants.CENTER);
        signoutButton.setHorizontalAlignment(SwingConstants.CENTER);

        signoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginForm lf;
                try {
                    lf = new loginForm();
                    rigthJPanel.removeAll();
                    frame.remove(rigthJPanel);
                    frame.setVisible(false);
                    lf.begin();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        leftJPanel.setLayout(new GridBagLayout());
        Border blackline = BorderFactory.createLineBorder(Color.red);
        leftJPanel.setBorder(blackline);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 1.0;
        c.weightx = 1.0;
        leftJPanel.add(grpJButton, c);

        c.gridy = 1;
        leftJPanel.add(chatJButton, c);

        frame.add(leftJPanel, BorderLayout.LINE_START);

        rigthJPanel.setLayout(new GridBagLayout());
        Border blueline = BorderFactory.createLineBorder(Color.blue);
        rigthJPanel.setBorder(blueline);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(5, 5, 5, 5);
        c2.anchor = GridBagConstraints.CENTER;
        c2.gridx = 0;
        c2.gridy = 0;
        c2.gridwidth = 2;
        rigthJPanel.add(topicJLabel, c2);

        c2.gridwidth = 1;
        c2.gridx = 2;
        c2.gridy = 0;
        c2.anchor=GridBagConstraints.FIRST_LINE_END;
        rigthJPanel.add(signoutButton, c2);

        chatJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.print(chatName);
                    Client cl=new Client(chatName);
                    c2.anchor=GridBagConstraints.CENTER;
                    c2.gridx = 0;
                    c2.gridy = 1;
                    c2.weightx = 1.0;
                    c2.weighty = 1.0;
                    c2.gridwidth=3;
                    c2.fill=GridBagConstraints.BOTH;
                    rigthJPanel.add(new JScrollPane(cl.messagearea),c2);
                    c2.gridy=2;
                    c2.fill=GridBagConstraints.HORIZONTAL;
                    c2.anchor=GridBagConstraints.PAGE_END;
                    rigthJPanel.add(cl.messageinput,c2);
                    
                    frame.invalidate();
                    frame.validate();
                    frame.repaint();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        frame.add(rigthJPanel);
    }

    public void begin() {
        frame.setTitle("Sasta Teams");
        frame.setSize(1280, 720);
        device.setFullScreenWindow(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class RunServerST implements Runnable {
    Server serv;

    @Override
    public void run() {
        try {
            if (Server.canRun == false) {
                serv = new Server();
                adminDashboard.serverPanel = new JScrollPane();
                adminDashboard.serverPanel.add(Server.messagearea);
                System.out.println(Server.messagearea);
                Server.canRun = true;
                serv.runningServer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
