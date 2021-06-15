package com.game.GUI;
import Game.KeyChecker;

import javax.swing.JFrame;

/**
 * Creates GUI of the game
 */
public class Window extends JFrame {
    /**
     * Class constructor
     */
    public Window(){
        setTitle("Pang");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280,720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
