package jfk;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JFK_ZAD#");
        frame.setSize(400,500);
        frame.setLocationRelativeTo(null);


        Window panel=new Window();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
