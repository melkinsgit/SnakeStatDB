package com.margaret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class SnakeInfoGUI extends JFrame implements WindowListener {
    private JPanel rootPanel;
    private JTable snakeTable;
    private JButton quitButton;
    protected SnakeInfoGUI(SnakeModel sdm) {
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(this);
        setVisible(true);
        setSize(400, 200);
//Create a data model and tell our table to use it
// SnakeDataModel snakeModel = new SnakeDataModel();
        snakeTable.setModel(sdm);
//Grid color default is white, change it so we can see it
        snakeTable.setGridColor(Color.BLACK);
//Also make the columns wider
        snakeTable.getColumnModel().getColumn(0).setWidth(400);
//Quit application when user closes window, otherwise app keeps running
//Sometimes this is what you want, sometimes it isn't.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Close button code.
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//Call Main's shutdown method - one class is the entry and exit point of the progam
                Main.shutdown();
            }
        });
    }
    //Method provided by WindowListener interface
//Called when user clicks button to close application
    public void windowClosing(WindowEvent e) {
        System.out.println("Window closing");
        Main.shutdown();
    }
    //A WindowListener is required to provide these methods
//We can choose what they do - in this case, nothing
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
