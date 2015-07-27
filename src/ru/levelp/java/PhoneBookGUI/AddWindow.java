package ru.levelp.java.PhoneBookGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindow {

    private GUI gui;

    public AddWindow(GUI gui){
        this.gui = gui;
    }

    private JFrame frame = new JFrame("New record");
    private JLabel labelName = new JLabel("Name");
    private JLabel labelPhone = new JLabel("Phone");
    private JLabel labelEmail= new JLabel("E-mail");
    private JTextField textFieldName = new JTextField("Name");
    private JTextField textFieldPhone = new JTextField("Phone");
    private JTextField textFieldEmail = new JTextField("E-mail");
    private JButton addButton = new JButton("Add");
    private MyMouseListener MML = new MyMouseListener();

    private JPanel panel = new JPanel();
    private JPanel panelButton = new JPanel();

    public void build(){
        frame.setBounds(100, 100, 400, 130);

        panel.setLayout(new GridLayout(2, 6));
        panel.add(labelName);
        panel.add(labelPhone);
        panel.add(labelEmail);
        panel.add(textFieldName);
        panel.add(textFieldPhone);
        panel.add(textFieldEmail);

        labelName.setFocusable(true);

        textFieldName.addMouseListener(MML);
        textFieldPhone.addMouseListener(MML);
        textFieldEmail.addMouseListener(MML);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldName.getText().length()>0 && textFieldPhone.getText().length()>0 && textFieldEmail.getText().length()>0)
                gui.getPhonebook().add(textFieldName.getText(), textFieldPhone.getText(), textFieldEmail.getText());
                else new ErrorWindow("There are empty fields!").build();
            }
        });

        panelButton.add(addButton);

        frame.add(BorderLayout.CENTER, panel);
        frame.add(BorderLayout.SOUTH, panelButton);

        frame.setResizable(false);
        frame.setVisible(true);
    }

}
