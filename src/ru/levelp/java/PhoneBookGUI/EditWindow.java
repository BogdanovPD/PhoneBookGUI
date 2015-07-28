package ru.levelp.java.PhoneBookGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditWindow extends AddEditWindow {

    private String recordName;
    private String phoneNumber;
    private String email;


    public EditWindow(GUI gui, String recordName) {
        super(gui);
        this.recordName = recordName;
    }

    public void build(){
        super.build();

        frame.setTitle("Edit record");
        button.setText("Edit");
        textFieldPhone.setText(gui.getPhonebook().findByName(recordName).getPhone_number());
        phoneNumber = textFieldPhone.getText();
        textFieldEmail.setText(gui.getPhonebook().findByName(recordName).getEmail());
        email = textFieldEmail.getText();
        textFieldName.setText(recordName);
        textFieldName.setEnabled(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldPhone.getText().length() > 0 && textFieldEmail.getText().length() > 0) {
                    if (!phoneNumber.equals(textFieldPhone.getText()))
                        gui.getPhonebook().edit(textFieldName.getText(), "phone", textFieldPhone.getText());
                    if (!email.equals(textFieldEmail.getText()))
                        gui.getPhonebook().edit(textFieldName.getText(),"email", textFieldEmail.getText());
                } else new ErrorWindow("There are empty fields!").build();
            }
        });

        frame.setResizable(false);
        frame.setVisible(true);
    }


}
