package ru.levelp.java.PhoneBookGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWindow extends AddEditWindow {

    public AddWindow(GUI gui){
        super(gui);
    }

    public void build(){
        super.build();

        frame.setTitle("New record");
        button.setText("Add");

        textFieldName.addMouseListener(MML);
        textFieldPhone.addMouseListener(MML);
        textFieldEmail.addMouseListener(MML);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldName.getText().length() > 0 && textFieldPhone.getText().length() > 0 && textFieldEmail.getText().length() > 0) {
                    gui.getPhonebook().add(textFieldName.getText(), textFieldPhone.getText(), textFieldEmail.getText());
                    gui.getListModel().addElement(textFieldName.getText());
                } else new ErrorWindow("There are empty fields!").build();
            }
        });

        frame.setResizable(false);
        frame.setVisible(true);
    }

}
