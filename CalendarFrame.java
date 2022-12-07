package MiniProject.JavaMiniProject;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CalendarFrame implements Runnable {

    private JFrame  frame;

    @Override
    public void run() {
        // Month is zero based
        MonthPanel panel = new MonthPanel(5, 2013);

        frame = new JFrame();
        frame.setTitle("Calendar");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(panel);
        frame.pack();
        // frame.setBounds(100, 100, 400, 200);
        frame.setVisible(true);
    }

    public void exitProcedure() {
        frame.dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CalendarFrame());

    }

}
