package ru.levelp.java.PhoneBookGUI;

import javax.swing.*;
import java.awt.*;

public class OtherActionsWindow {

    private GUI gui;
    private String actionType;

    public OtherActionsWindow(GUI gui, String actionType){
        this.gui = gui;
        this.actionType = actionType;
    }

    private JFrame frame = new JFrame();
    private JLabel label= new JLabel("Name");
    private JButton button = new JButton();
    private JTextField textField = new JTextField("Name");
    private JPanel panel = new JPanel();
    private JPanel panelButton = new JPanel();
    private MyMouseListener MML = new MyMouseListener();

    public void build(){
        frame.setBounds(100, 100, 250, 120);

        frame.setTitle(actionType);
        button.setText(actionType);
        textField.addMouseListener(MML);

        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.add(textField);
        panelButton.add(button);

        label.setFocusable(true);

        frame.add(BorderLayout.CENTER, panel);
        frame.add(BorderLayout.SOUTH, panelButton);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
