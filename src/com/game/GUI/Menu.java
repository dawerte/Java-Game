package com.game.GUI;

import java.awt.*;

public class Menu {

    private GamePanel panel;

    Menu(GamePanel panel) {
        this.panel = panel;
    }

    public void draw(Graphics2D gtd) {
        Font fnt0 = new Font("arial", Font.BOLD, 100);
        gtd.setFont(fnt0);
        gtd.setColor(Color.BLACK);
        gtd.drawString("PANG", panel.getWindow_width() / 2 - 130, 100);
        Font fnt1 = new Font("arial", Font.BOLD, 50);
        gtd.fillRect(panel.getWindow_width() / 2 - 300, 200, 600, 100);
        gtd.fillRect(panel.getWindow_width() / 2 - 300, 325, 600, 100);
        gtd.fillRect(panel.getWindow_width() / 2 - 300, 450, 600, 100);
        gtd.fillRect(panel.getWindow_width() / 2 - 300, 575, 600, 100);
        gtd.setFont(fnt1);
        gtd.drawString(""+panel.getStateserver(),1050,670);
        gtd.setColor(Color.WHITE);
        gtd.drawString("New Game", panel.getWindow_width() / 2 - 130, 270);
        gtd.drawString("Leaderboard", panel.getWindow_width() / 2 - 130, 390);
        gtd.drawString("Options", panel.getWindow_width() / 2 - 130, 515);
        gtd.drawString("Exit", panel.getWindow_width() / 2 - 130, 645);
    }
}
