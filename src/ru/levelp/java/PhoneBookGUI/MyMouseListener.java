package ru.levelp.java.PhoneBookGUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {

    private GUI gui;

    public MyMouseListener(){

    }

    public MyMouseListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gui == null)
        ((JTextField)e.getSource()).setText("");
        if (e.getClickCount()==2){
                new EditWindow(gui, (String)gui.getList().getSelectedValue()).build();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}