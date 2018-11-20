package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener {
    private JButton set,clear;
    private JComboBox email,shot;

    public Display(){

        configureView();
    }

    private void configureView() {
        JPanel p1;

        String[] intervals = {"10 secs","20 secs","30 secs","1 min","5 min","15 min","30 min"};
        setTitle("Military Logger");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,2));
       // add(new JLabel("Set ScreenCapture Interval:"));
       // add(email = new JComboBox(intervals));
       // add(new JLabel("Set Email Interval:"));
       // add(shot = new JComboBox(intervals));
       // add(new JLabel("Email:"));
       // add(new JTextArea(2,20));
        add(p1 = new JPanel(new GridLayout(0,2)));
        p1.add(set = new JButton("Ok"));
      //  p1.add(clear = new JButton("CLEAR"));
        pack();
        configureActions(set, clear);
    }

    private void configureActions(JButton set, JButton clear) {
        set.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == set){

        }

        if(e.getSource() == clear){

        }
    }
}
