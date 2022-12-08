package MiniProject.JavaMiniProject;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.*;
import java.awt.Font;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Server class
public class Server {

    // Vector to store active clients
    static Vector<ClientHandler> ar = new Vector<>();
    static String name;
    // counter for clients

    static Vector<Integer> portnumber = new Vector<>();
    static Vector<String> listofnames = new Vector<>();

    // delcaring gui components
    // private JLabel heading = new JLabel("Server");
    public static JTextArea messagearea = new JTextArea();
    // private JTextField messageinput=new JTextField();
    JButton sendbutton = new JButton("Send");
    private Font font = new Font("Roboto", Font.PLAIN, 20);
    static boolean canRun = false;
    Socket s;

    public Server() throws IOException {

        // server is listening on port 1234
        System.out.print("FK2");
        runningServer();
       
    }

    public void runningServer() throws IOException
    {
        if (canRun) {
            System.out.print("FK");
            ServerSocket ss = new ServerSocket(1234);

            Socket s;

            creategui();

            // running infinite loop for getting
            // client request
            while (true) {
                // Accept the incoming request

                s = ss.accept();

                int port = s.getPort();
                portnumber.add(port);

                System.out.println("New client request received : " + s);

                // obtain input and output streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                name = dis.readUTF();
                System.out.println("Creating a new handler for this client... " + name);
                listofnames.add(name);
                // Create a new handler object for handling this request.
                ClientHandler mtch = new ClientHandler(s, Server.name, dis, dos);

                // Create a new Thread with this object.
                Thread t = new Thread(mtch);

                System.out.println("Adding this client to active client list");
                for (ClientHandler mc : ar)
                    mc.dos.writeUTF(name + " is ready to chat ");
                messagearea.append(name + " is ready to chat \n");

                // add this client to active clients list
                Server.ar.add(mtch);

                // start the thread.
                t.start();

                // increment i for new client.
                // i is used for naming only, and can be replaced
                // by any naming scheme
                // i++;
            }
        }

    }

    private void creategui() {

        // this.setTitle("Server[end]");
        // this.setSize(600,600);
        // this.setLocationRelativeTo(null);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // coding component
        // heading.setFont(font);
        messagearea.setFont(font);

        // heading.setIcon(new ImageIcon("icon.png"));

        // heading.setHorizontalAlignment(SwingConstants.CENTER);
        // heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        messagearea.setEditable(false);

        // setting frame's layout
        // this.setLayout(new BorderLayout());
        // this.add(heading, BorderLayout.NORTH);
        // JScrollPane jscrollpane = new JScrollPane(messagearea);
        // this.add(jscrollpane, BorderLayout.CENTER);

        // this.setVisible(true);
    }

    // public static void main(String[] args) throws IOException
    // {

    // Server sc=new Server();
    // //Creatinggui cig=new Creatinggui();
    // }
}

// ClientHandler class
class ClientHandler implements Runnable {
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;

    // constructor
    public ClientHandler(Socket s, String name,
            DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin = true;

    }

    @Override
    public void run() {

        String received;
        try {
            while (true) {

                int port = s.getPort();
                // receive the string
                received = dis.readUTF();

                for (int i = 0; i < Server.listofnames.size(); i++) {
                    if (Server.portnumber.get(i) == s.getPort()) {
                        System.out.println(Server.listofnames.get(i) + ": " + received);
                        Server.messagearea.append(Server.listofnames.get(i) + ": " + received + "\n");
                    }

                }
                if (received.equals("logout")) {
                    this.isloggedin = false;
                    this.s.close();
                    break;
                }

                for (ClientHandler mc : Server.ar) {
                    if (received.equals("logout")) {
                        this.isloggedin = false;
                        this.s.close();

                        break;
                    }
                    // if the recipient is found, write on its
                    // output stream
                    if (mc.isloggedin == true) {
                        if (Server.listofnames.size() == 1) {
                            mc.dos.writeUTF(Server.listofnames.get(0) + " : " + received);
                        } else {
                            for (int i = 0; i < Server.listofnames.size(); i++) {
                                if (Server.portnumber.get(i) == port)
                                    mc.dos.writeUTF(Server.listofnames.get(i) + " : " + received);
                            }

                        }

                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
