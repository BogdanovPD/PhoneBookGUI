package ru.levelp.java.PhoneBookGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorWindow implements Window{

    String text;
    private JFrame frame = new JFrame("Error");
    private JLabel label= new JLabel();
    private JButton okButton = new JButton("OK");
    private JPanel panel = new JPanel();
    private JPanel panelButton = new JPanel();

    public ErrorWindow(String text){
        this.text = text;
    }

    public void build(){
        frame.setBounds(100, 100, 400, 100);
        label.setText(text);

        panel.add(label);
        panelButton.add(okButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(BorderLayout.CENTER, panel);
        frame.add(BorderLayout.SOUTH, panelButton);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
