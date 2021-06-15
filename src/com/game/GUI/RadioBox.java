package com.game.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class RadioBox extends JFrame{
    public RadioBox(GamePanel panel){
        super("Choice variables");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400,300);

        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JPanel jpanel=new RadioBoxBody(panel,this);
        add(jpanel);

        setVisible(true);

    }

}
