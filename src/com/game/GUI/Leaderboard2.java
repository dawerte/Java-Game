package com.game.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * class creating window (leader board)
 *
 */

public class Leaderboard2 extends JFrame {

    private Container container=getContentPane();

    Leaderboard2(GamePanel panel) {
        setTitle("Leaderboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(10,10,370,600);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        try {
            JPanel jpanel = new LeaderboardArray(panel,this);
            add(jpanel);
        }catch(IOException e){
            System.out.println("Bład wczytywania");
        }
        setVisible(true);

    }
    public Container getContainer() {
        return container;
    }
}