package MiniProject.JavaMiniProject;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client {
    final static int ServerPort = 1234;
    static Vector<String> listofnames = new Vector<>();

    // delcaring gui components

    JTextArea messagearea = new JTextArea();
    JTextField messageinput = new JTextField();
    JButton sendbutton = new JButton("Send");
    private Font font = new Font("Roboto", Font.PLAIN, 20);
    private JLabel heading = new JLabel("name");

    public Client(String name) throws IOException {
        listofnames.add(name);
        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection
        Socket s = new Socket(ip, ServerPort);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        creategui(heading);
        // handleEvents(s,dis,dos,name);
        // startReading(s,dis,dos,name);
        // startwriting(s,dis,dos,name);
        dos.writeUTF(name);
        // sendMessage thread
        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {

                messageinput.addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                        // getkeycode gives code of key pressed
                        // 10 is keycode for enter
                        if (e.getKeyCode() == 10) {
                            System.out.println("you pressed enter");
                            // getting what u typed
                            String contenttosend = messageinput.getText();
                            if (contenttosend.length() >= 1) {

                                // messagearea.append(name+": "+contenttosend+"\n");

                                try {
                                    dos.writeUTF(contenttosend);

                                } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                            // clearing the window for new text
                            messageinput.setText("");

                        }
                    }

                });

                sendbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String content = messageinput.getText();
                        // messagearea.append(name+": "+content+"\n");
                        if (content.length() > 0) {
                            try {
                                dos.writeUTF(content);
                            } catch (IOException e1) {

                                e1.printStackTrace();
                            }
                        }
                        messageinput.setText("");
                    }
                    // clearing the window for new text

                });
            }
        });

        // readMessage thread
        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {

                        // read the message sent to this client
                        String msg = dis.readUTF();
                        if (msg.equals("logout")) {
                            // this.isloggedin=false;
                            s.close();

                            break;
                        }
                        // System.out.println(msg);
                        messagearea.append(msg + "\n");
                    }
                } catch (IOException e) {

                    System.out.println("connection closed");

                }
            }
        });

        sendMessage.start();
        readMessage.start();

    }

    private void creategui(JLabel heading) {

        // this.setTitle("client messager[end]");
        // this.setSize(600,600);
        // this.setLocationRelativeTo(null);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // coding component
        heading.setFont(font);
        messagearea.setFont(font);
        messageinput.setFont(font);

        // heading.setIcon(new ImageIcon("icon.png"));

        heading.setHorizontalAlignment(SwingConstants.CENTER);
        // heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        messagearea.setEditable(false);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        messagearea.setBorder(blackline);
        messageinput.setBorder(blackline);
        

        // setting frame's layout
        sendbutton.setHorizontalAlignment(SwingConstants.CENTER);
        sendbutton.setFont(new Font("Arial", Font.PLAIN, 20));
        sendbutton.setBounds(1475, 0, 100, 32);
        messageinput.add(sendbutton);

        // this.setVisible(true);
    }

    // public static void main(String args[]) throws UnknownHostException, IOException {

    //     Client cp = new Client();

    // }
}