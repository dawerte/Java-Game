package com.game.GUI;

public class ButtonManager {
    private int Start_width;
    private int Start_height;
    private int Button_width;
    private int Button_height;
    private int Button_separator;
    private int Current_posX;
    private int Current_posY;

    private ButtonManager (){}
    public ButtonManager (int startHeight, int startWidth, int buttonHeight, int buttonWidth, int separator)
    {
        this.Start_width = startWidth;
        this.Start_height = startHeight;
        this.Button_width = buttonWidth;
        this.Button_height = buttonHeight;
        this.Button_separator = separator;
        this.Current_posX = startWidth;
        this.Current_posY = startHeight;
    }
    public void CalculateNextButtonPos()
    {
        Current_posY += Button_height + Button_separator;

    }

    public int getPosX() {
        return Start_width;
    }
    public int getPosY() {
        return Current_posY;
    }
    public int getButton_width() {
        return Button_width;
    }
    public int getButton_height() {
        return Button_height;
    }

    public void Restart(){
        Current_posX = Start_width;
        Current_posY = Start_height;
    }

}
