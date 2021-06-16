package com.game.GUI;

import java.awt.*;

/**
 * class makes menu (how it looks like)
 *
 */

public class Menu {

    private GamePanel panel;

    Menu(GamePanel panel) {
        this.panel = panel;
    }

    /**
     *
     * function which is drawing the menu
     * @param gtd
     */

    public void draw(Graphics2D gtd) {
        ButtonManager pos_manager = new ButtonManager(panel.getStart_height(), panel.getWindow_width()/2 - panel.getButton_width()/2, panel.getButton_height(), panel.getButton_width(), panel.getButton_separator());
        Font fnt0 = new Font("arial", Font.BOLD, 100);
        gtd.setFont(fnt0);
        gtd.setColor(Color.BLACK);
        gtd.drawString("PANG", panel.getWindow_width() / 2 - 130, 100);
        Font fnt1 = new Font("arial", Font.BOLD, 50);

        gtd.fillRect(pos_manager.getPosX(), pos_manager.getPosY(), pos_manager.getButton_width(), pos_manager.getButton_height());
        pos_manager.CalculateNextButtonPos();
        gtd.fillRect(pos_manager.getPosX(), pos_manager.getPosY(), pos_manager.getButton_width(), pos_manager.getButton_height());
        pos_manager.CalculateNextButtonPos();
        gtd.fillRect(pos_manager.getPosX(), pos_manager.getPosY(), pos_manager.getButton_width(), pos_manager.getButton_height());
        pos_manager.CalculateNextButtonPos();
        gtd.fillRect(pos_manager.getPosX(), pos_manager.getPosY(), pos_manager.getButton_width(), pos_manager.getButton_height());
        gtd.setFont(fnt1);
        gtd.drawString(""+panel.getStateserver(),1050,670);
        gtd.setColor(Color.WHITE);
        pos_manager.Restart();
        gtd.drawString("New Game", pos_manager.getPosX()+ (float) (panel.getText_start_percents())/100 * pos_manager.getButton_width(), pos_manager.getPosY()+70);
        pos_manager.CalculateNextButtonPos();
        gtd.drawString("Leaderboard",pos_manager.getPosX()+ (float) (panel.getText_start_percents())/100 * pos_manager.getButton_width(), pos_manager.getPosY()+70);
        pos_manager.CalculateNextButtonPos();
        gtd.drawString("Options", pos_manager.getPosX()+ (float) (panel.getText_start_percents())/100 * pos_manager.getButton_width(), pos_manager.getPosY()+70);
        pos_manager.CalculateNextButtonPos();
        gtd.drawString("Exit", pos_manager.getPosX()+ (float) (panel.getText_start_percents())/100 * pos_manager.getButton_width(), pos_manager.getPosY()+70);
    }
}
