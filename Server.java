import java.net.Socket;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;

public class Server extends JFrame{
    
    ServerSocket server;
    Socket socket;
    BufferedReader br;    //for reading
    PrintWriter out;      //writing

    //delcaring gui components
    private JLabel heading=new JLabel("Server");
    private JTextArea messagearea=new JTextArea();
    private JTextField messageinput=new JTextField();
    JButton sendbutton=new JButton("Send");
    private Font font=new Font("Roboto",Font.PLAIN,20);

    //constructor
    public Server(){
        try{
        server=new ServerSocket(7777);

        System.out.println("server is ready to accept");
        System.out.println("waiting...");
        
        socket=server.accept();

        //socket.getinputstream() gets data in bytes
        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));      //new will change the data into character and bufferreader will convert into buffer
        
        out=new PrintWriter(socket.getOutputStream()); 

        creategui();
        handleEvents();
        startReading();
        //startwriting();
    
    }
        catch(Exception e){
            e.printStackTrace();
        }
   
    }

    private void handleEvents(){

        messageinput.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
               
                //getkeycode gives code of key pressed 
                //10 is keycode for enter
                if(e.getKeyCode()==10){
                    System.out.println("you pressed enter");
                    //getting what u typed
                    String contenttosend=messageinput.getText();
                    if(contenttosend.length()>=1){
                    messagearea.append("Me: "+contenttosend+"\n");
                    out.println(contenttosend);
                    out.flush();
                    }
                    //clearing the window for new text
                    messageinput.setText("");
                    

                }
            }

        });

        sendbutton.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
    
            @Override
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub
                
                
            }
    
            @Override
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
    
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                String content=messageinput.getText();
                messagearea.append("Me: "+content+"\n");
                out.println(content);
                out.flush();
                messageinput.setText("");
            }
    
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
    }

    private void creategui(){
        
        this.setTitle("Server messager[end]");
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //coding component
        heading.setFont(font);
        messagearea.setFont(font);
        messageinput.setFont(font);
        

        //heading.setIcon(new ImageIcon("icon.png"));

        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        messagearea.setEditable(false);



        //  setting frame's layout
        this.setLayout(new BorderLayout());
        this.add(heading,BorderLayout.NORTH);
        JScrollPane jscrollpane=new JScrollPane(messagearea);
        this.add(jscrollpane,BorderLayout.CENTER);
        this.add(messageinput,BorderLayout.SOUTH);

       sendbutton.setHorizontalAlignment(SwingConstants.CENTER);
       sendbutton.setBounds(500,0,100,32);
       messageinput.add(sendbutton);
       
       

        
        this.setVisible(true);
    }

    //reading bufferdata
    //use multithreading because we have to read and write multiple data
    public void startReading(){
        //thread will read the data continuously

        Runnable r1=()->{

            System.out.println("reader started");


            try {
            while(true){
                String msg;
                
                    msg = br.readLine();

                    if(msg.equals("exit")){
                        System.out.println("terminating");
                        JOptionPane.showMessageDialog(this,"server terminated the chat");
                        messageinput.setEnabled(false);;
                        socket.close();
                        break;
                    }

                    messagearea.append("Client: "+msg+"\n");
                }

                } catch (Exception e) { 
                   // e.printStackTrace();
                   System.out.println("connection closed");
                }
                
                
            
        };

        new Thread(r1).start();
       
    }

    
    //writing
    public void startwriting(){

        //sending the msg 

        Runnable r2=()->{
            System.out.println("writer started");

            try {
            while(!socket.isClosed()){
                

                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in)); //taking data from user so that we should send it to client

                    String content=br1.readLine();

                    out.println(content);
                    out.flush();

                    if(content.equals("exit")){
                        socket.close();
                        break;
                    }

            }
                }catch (Exception e) { 
                   // e.printStackTrace();
                   System.out.println("connection closed");
                }
            
        };

        new Thread(r2).start();

    }
    public static void main(String[] args) {
        System.out.println("this is going to start server");
        Server sc=new Server();
    }

}
