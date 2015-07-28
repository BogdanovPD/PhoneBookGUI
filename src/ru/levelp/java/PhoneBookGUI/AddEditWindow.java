package ru.levelp.java.PhoneBookGUI;

import javax.swing.*;
import java.awt.*;

public class AddEditWindow implements  Window {

    protected GUI gui;

    public AddEditWindow(GUI gui){
        this.gui = gui;
    }

    protected JFrame frame = new JFrame();
    protected JLabel labelName = new JLabel("Name");
    protected JLabel labelPhone = new JLabel("Phone");
    protected JLabel labelEmail= new JLabel("E-mail");
    protected JTextField textFieldName = new JTextField("Name");
    protected JTextField textFieldPhone = new JTextField("Phone");
    protected JTextField textFieldEmail = new JTextField("E-mail");
    protected JButton button = new JButton();
    protected MyMouseListener MML = new MyMouseListener();

    protected JPanel panel = new JPanel();
    protected JPanel panelButton = new JPanel();

    @Override
    public void build() {
        frame.setBounds(100, 100, 400, 130);

        panel.setLayout(new GridLayout(2, 6));
        panel.add(labelName);
        panel.add(labelPhone);
        panel.add(labelEmail);
        panel.add(textFieldName);
        panel.add(textFieldPhone);
        panel.add(textFieldEmail);

        labelName.setFocusable(true);

        panelButton.add(button);

        frame.add(BorderLayout.CENTER, panel);
        frame.add(BorderLayout.SOUTH, panelButton);

    }
}
