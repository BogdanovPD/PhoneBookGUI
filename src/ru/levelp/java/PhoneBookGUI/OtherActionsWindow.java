package ru.levelp.java.PhoneBookGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherActionsWindow implements Window {

    private GUI gui;
    private String actionType;
    private String selectedElement="";

    public OtherActionsWindow(GUI gui, String actionType){
        this.gui = gui;
        this.actionType = actionType;
    }

    public OtherActionsWindow(GUI gui, String actionType, String selectedElement){
        this.gui = gui;
        this.actionType = actionType;
        this.selectedElement = selectedElement;
    }

    private JFrame frame = new JFrame();
    private JLabel label= new JLabel("Name");
    private JButton button = new JButton();
    private JTextField textField = new JTextField("Name");
    private JPanel panel = new JPanel();
    private JPanel panelButton = new JPanel();
    private MyMouseListener MML = new MyMouseListener();

    public void build(){

        if (!selectedElement.equals(""))
            textField.setText(selectedElement);

        frame.setBounds(100, 100, 250, 120);

        frame.setTitle(actionType);
        button.setText(actionType);
        textField.addMouseListener(MML);

        panel.setLayout(new GridLayout(2, 1));
        panel.add(label);
        panel.add(textField);
        panelButton.add(button);

        label.setFocusable(true);


        if (actionType.equals("Delete")){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ind = gui.getPhonebook().getIndex(textField.getText());
                    if (ind!=-1){
                    gui.getPhonebook().delete(textField.getText());
                    gui.getListModel().remove(ind);}
                    else
                        new ErrorWindow("Can't find record").build();
                }
            });

        }

        if (actionType.equals("Show")){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int ind = gui.getPhonebook().getIndex(textField.getText());
                    for (int i = 0; i < ind; i++) {
                        gui.getListModel().remove(i);
                        ind--;
                        i--;
                    }

                        for (int i = ind + 1; i < gui.getListModel().size(); i++) {
                            gui.getListModel().remove(i);
                            i--;
                        }

                }
            });
        }

        frame.add(BorderLayout.CENTER, panel);
        frame.add(BorderLayout.SOUTH, panelButton);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
