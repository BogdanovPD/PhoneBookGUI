package ru.levelp.java.PhoneBookGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI {

    private JFrame frame = new JFrame("Phone book");
    private JList list;
    private JPanel centerPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JButton showAllButton = new JButton("Show all");
    private JButton showByNameButton = new JButton("Show by name");
    private JButton deleteButton = new JButton("Delete");
    private GUI gui = this;
    private PhoneBook phonebook;

    JScrollPane scroll;

    public void build(){

        setPhonebook(new PhoneBook());
        try{
            getPhonebook().readFromFile();}
        catch (IOException e){
        }

        String[] data = new String[getPhonebook().size];
        for (int i = 0; i < getPhonebook().size; i++) {
            data[i] = getPhonebook().phone_book.get(i).getName();
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 300, 180);
        list = new JList(data);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(0);
        scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(100, 100));

        centerPanel.add(scroll);

        addButton.setPreferredSize(showAllButton.getSize());

        rightPanel.setLayout(new GridLayout(5, 1));
        rightPanel.add(addButton);
        rightPanel.add(editButton);
        rightPanel.add(showAllButton);
        rightPanel.add(showByNameButton);
        rightPanel.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddWindow(gui).build();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OtherActionsWindow(gui,"Edit").build();
            }
        });

        showByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OtherActionsWindow(gui,"Show").build();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OtherActionsWindow(gui,"Delete").build();
            }
        });

        frame.add(BorderLayout.CENTER, centerPanel);
        frame.add(BorderLayout.EAST, rightPanel);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public PhoneBook getPhonebook() {
        return phonebook;
    }

    public void setPhonebook(PhoneBook phonebook) {
        this.phonebook = phonebook;
    }
}
