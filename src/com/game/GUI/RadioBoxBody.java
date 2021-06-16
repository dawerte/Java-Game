package com.game.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class RadioBoxBody extends JPanel implements ActionListener {
   private int width;
   private int height;
   private JRadioButton serverButton;
   private JRadioButton localButton;
   private JButton b;
   private GamePanel panel;
   private RadioBox radiobox;

    public RadioBoxBody(GamePanel panel,RadioBox radiobox) {
        this.panel=panel;
        this.radiobox=radiobox;
        serverButton = new JRadioButton("Server");
        serverButton.setActionCommand("Server");
        serverButton.setSelected(false);

        localButton = new JRadioButton("Local");
        localButton.setActionCommand("Local");
        localButton.setSelected(true);
        b=new JButton("Apply");


        ButtonGroup group = new ButtonGroup();
        group.add(serverButton);
        group.add(localButton);

        serverButton.addActionListener(this);
        localButton.addActionListener(this);
        b.addActionListener(this);
        add(serverButton); // added this line
        add(localButton); // added this line
        add(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(serverButton.isSelected()){
            if(panel.getConnected()) {
                panel.setStateserver(panel.getStateServer());
            }else{
                localButton.setSelected(true);
                JOptionPane.showMessageDialog(this, "Server is down, please turn on your server in order to change that");
            }

        }else if(localButton.isSelected()){
            panel.setStateserver(panel.getStateLocal());
        }
        if(e.getActionCommand().equals(b.getText())){
            radiobox.setVisible(false);
            panel.setState(panel.getStateMenu());
            panel.setRunning(true);
        }
    }
}
