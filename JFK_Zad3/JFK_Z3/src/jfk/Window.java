package jfk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



class Window extends JPanel {

    public JLabel wynik;
    public JLabel dane;
    public JTextField textFieldArguments;
    private JButton przyciskOblicz;
    Input input= new Input();


    public Window() {
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth=1;
        c.gridheight=1;
        c.gridx = 1;
        c.gridy = 0;


        JButton przyciskOtworz;
        przyciskOtworz = new JButton(otworz);

        add(przyciskOtworz, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx=1;
        c.gridy = 1;
        c.gridwidth=2;
        c.gridheight = 3;
        add(input.Lista(), c);
        c.fill = GridBagConstraints.HORIZONTAL;
       c.weightx=0;
       c.gridheight=0;
       c.gridwidth=0;
       c.gridheight = 1;
        c.gridy = 6;
        dane = new JLabel();

        add(dane, c);

        c.gridy = 7;
        textFieldArguments = new JTextField();
        add(textFieldArguments, c);

        c.gridy = 8;
        przyciskOblicz = new JButton(wykonaj);
        przyciskOblicz.setEnabled(true);
        add(przyciskOblicz, c);

        c.gridy = 9;
        wynik = new JLabel("Wynik: ");
        add(wynik, c);
    }

    private AbstractAction otworz = new AbstractAction("Otwórz") {
        @Override
        public void actionPerformed(ActionEvent e) {

           input.plik();
        }

    };

    private AbstractAction wykonaj = new AbstractAction("Policz") {
        @Override
        public void actionPerformed(ActionEvent e) {

        }

    };




}
