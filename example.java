package MiniProject.JavaMiniProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.PanelUI;

public class example implements ActionListener {
    static JButton button;

    public static void main(String[] args) {

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.red);
        panel1.setBounds(50, 200, 1000, 830);

        // JPanel panel2= new JPanel();
        // panel2.setBackground(Color.blue);
        // panel2.setBounds(1150,200,720,480);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Color.green);
        panel3.setBounds(1150, 730, 720, 300);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Color.black);
        panel4.setBounds(50, 50, 1820, 100);

        button = new JButton("Members");
        // button.addActionListener(this);
        button.setBounds(700, 50, 100, 100);
        panel4.add(button);

        MonthPanel mp=new MonthPanel(5, 2013);
        mp.setBounds(1150,200,720,480);

        JFrame frame = new JFrame();
        frame.setTitle("Group A");
        frame.setVisible(true);
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(panel1);
        frame.add(mp);
        frame.add(panel3);
        frame.add(panel4);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("Members List");
        }
    }
}