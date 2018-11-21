package frame;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JDialog implements ActionListener {
    private JButton login,logout, set;
    private JComboBox shot;
    private JTextField username, email;
    private JPasswordField password;
    private int interval = 0;

    public Display(){

        configureView();
    }

    private void configureView() {
        JPanel panel = new JPanel();
        setVisible(false);
        username = new JTextField();
        password = new JPasswordField();
        login = new JButton("Login");

        setTitle("Military Logger");
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setType(Type.POPUP);
        setLayout(new GridLayout(1,1));

        GridLayout layout = new GridLayout(3,0);
        panel.setLayout(layout);

        panel.add(username);
        panel.add(password);
        panel.add(login);

        username.setText("root");
        password.setText("alpine");


        add(panel);
        setMinimumSize(new Dimension(200,200));

       // add(new JLabel("Set ScreenCapture Interval:"));
       // add(email = new JComboBox(intervals));
       // add(new JLabel("Set Email Interval:"));
       // add(shot = new JComboBox(intervals));
       // add(new JLabel("Email:"));
       // add(new JTextArea(2,20));


      //  p1.add(clear = new JButton("CLEAR"));
        pack();
        configureActions(login);

    }

    private void generateAccessPanel(){

        if(username.getText().compareTo("root")!=0 || password.getText().compareTo("alpine")!=0) return;

        getContentPane().removeAll();
        repaint();

        String[] intervals = {"5 secs","10 secs","30 secs"};
        JPanel panel = new JPanel();

        add(panel);

        GridLayout layout = new GridLayout(3,2);
        panel.setLayout(layout);

        panel.add(new JLabel("screen capture interval:"));
        shot = new JComboBox(intervals);
        panel.add(shot);

        panel.add(new JLabel("email:"));
        email = new JTextField();
        panel.add(email);

        set = new JButton("set");
        logout = new JButton("logout");
        panel.add(set);
        panel.add(logout);

        pack();

        configureActions(logout);
        configureActions(set);


    }

    private void configureActions(JButton btn) {
        btn.addActionListener(this);
    }

    private void doThings(){
        Controller con = new Controller();
        con.setDisplay(this);
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login){
            generateAccessPanel();
        }

        if(e.getSource() == logout){

        }

        if(e.getSource() == set){
            doThings();
        }
    }
}
