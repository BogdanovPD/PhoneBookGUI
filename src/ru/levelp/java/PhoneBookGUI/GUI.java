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
    private JButton editButton = new JButton("Show\\Edit");
    private JButton showAllButton = new JButton("Show all");
    private JButton showByNameButton = new JButton("Select by name");
    private JButton deleteButton = new JButton("Delete");
    private GUI gui = this;
    private PhoneBook phonebook;
    private DefaultListModel listModel = new DefaultListModel();
    private MyMouseListener MML = new MyMouseListener(gui);

    JScrollPane scroll;

    public void build(){

        setPhonebook(new PhoneBook());
        try{
            getPhonebook().readFromFile();}
        catch (IOException e){
        }

        showAll();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 300, 180);
        setList(new JList(getListModel()));
        list.addMouseListener(MML);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        getList().setLayoutOrientation(JList.VERTICAL);
        getList().setVisibleRowCount(0);
        scroll = new JScrollPane(getList());
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
                if (list.getSelectedValue()!=null)
                    new EditWindow(gui, (String)list.getSelectedValue()).build();
                else
                    new ErrorWindow("Select record!").build();

            }
        });

        showByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedValue()!=null)
                    new OtherActionsWindow(gui,"Show", (String)(list.getSelectedValue())).build();
                else
                    new OtherActionsWindow(gui,"Show").build();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedValue()!=null)
                    new OtherActionsWindow(gui,"Delete", (String)(list.getSelectedValue())).build();
                else
                    new OtherActionsWindow(gui,"Delete").build();
            }
        });

        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
                showAll();
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

    public JList getList() {
        return list;
    }

    public void setList(JList list) {
        this.list = list;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public void setListModel(DefaultListModel listModel) {
        this.listModel = listModel;
    }

    public void showAll(){
        for (int i = 0; i < getPhonebook().size; i++) {
            getListModel().addElement(getPhonebook().phone_book.get(i).getName());
        }
    }

}
