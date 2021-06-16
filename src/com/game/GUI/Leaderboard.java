package com.game.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * class creating window to leader board
 *
 */

public class Leaderboard extends JFrame {

    private Container container=getContentPane();
    private Container container2=getContentPane();

    /**
     *
     * constructor creating window to leader board
     * @param panel
     */
    public Leaderboard(GamePanel panel) {
    super("Score to leaderboard");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setBounds(10,10,370,200);
    setLocationRelativeTo(null);
    setLayout(new FlowLayout());
    try {
        JPanel jpanel = new LeaderboardBody(panel, this);
        add(jpanel);
    }catch(IOException e){
        System.out.println("Bład wczytywania");
    }
    setVisible(true);

}
    public Container getContainer() {
        return container;
    }

    public Container getContainer2() {
        return container2;
    }
}
