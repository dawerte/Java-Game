package com.game.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Class reads configuration files (global parameters)
 */
public class Config {
    //test
    private File file;

    private int Window_width;
    private int Window_height;
    private int Block_size;
    private int Block_width;
    private int Block_height;
    private int Player_position_x;
    private int PLayer_position_y;
    private int PLayer_width;
    private int Player_height;
    private int Bullet_width;
    private int Bullet_height;
    private int Wall_height;
    private int Wall_width;
    private int Max_velocity;
    private int Menu_width;
    private int Menu_height;
    private int Button_width;
    private int Button_height;
    private int Button_separator;
    private int Start_height;
    private int Text_start_percents;


    /**
     * Class Constructor
     *
     * @throws FileNotFoundException
     */
    public Config() throws FileNotFoundException {   //
        LocateFile();
        DataReader();
    }

    /**
     * Locates file's directory
     */
    private void LocateFile() {
        String path = new ReadingFiles("Config").MaKeFile("config.txt").toString();
        file = new File(path);
    }

    /**
     * Reads parameters from file and assign them to variables
     *
     * @throws FileNotFoundException
     */
    private void DataReader() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String temp;
        temp = scanner.next();
        Window_width = scanner.nextInt();
        temp = scanner.next();
        Window_height = scanner.nextInt();
        temp = scanner.next();
        Block_size = scanner.nextInt();
        temp = scanner.next();
        Block_width = scanner.nextInt();
        temp = scanner.next();
        Block_height = scanner.nextInt();
        temp = scanner.next();
        Player_position_x = scanner.nextInt();
        temp = scanner.next();
        PLayer_position_y = scanner.nextInt();
        temp = scanner.next();
        PLayer_width = scanner.nextInt();
        temp = scanner.next();
        Player_height = scanner.nextInt();
        temp = scanner.next();
        Bullet_width = scanner.nextInt();
        temp = scanner.next();
        Bullet_height = scanner.nextInt();
        temp = scanner.next();
        Wall_height = scanner.nextInt();
        temp = scanner.next();
        Wall_width = scanner.nextInt();
        temp = scanner.next();
        Max_velocity = scanner.nextInt();
        temp = scanner.next();
        Menu_width = scanner.nextInt();
        temp = scanner.next();
        Menu_height = scanner.nextInt();

        temp = scanner.next();
        Button_width = scanner.nextInt();
        temp = scanner.next();
        Button_height = scanner.nextInt();
        temp = scanner.next();
        Button_separator = scanner.nextInt();
        temp = scanner.next();
        Start_height = scanner.nextInt();
        temp = scanner.next();
        Text_start_percents = scanner.nextInt();

    }

    public int getBlock_height() {
        return Block_height;
    }

    public int getBlock_size() {
        return Block_size;
    }

    public int getBlock_width() {
        return Block_width;
    }

    public int getBullet_height() {
        return Bullet_height;
    }

    public int getBullet_width() {
        return Bullet_width;
    }

    public int getMax_velocity() {
        return Max_velocity;
    }

    public int getPlayer_height() {
        return Player_height;
    }

    public int getPlayer_position_x() {
        return Player_position_x;
    }

    public int getPLayer_position_y() {
        return PLayer_position_y;
    }

    public int getPLayer_width() {
        return PLayer_width;
    }

    public int getWall_height() {
        return Wall_height;
    }

    public int getWall_width() {
        return Wall_width;
    }

    public int getWindow_height() {
        return Window_height;
    }

    public int getWindow_width() { return Window_width; }

    public int getMenu_width() {
        return Menu_width;
    }
    public int getMenu_height() {
        return Menu_height;
    }

    public int getButton_width() {
        return Button_width;
    }

    public int getButton_height() { return Button_height; }

    public int getButton_separator() {
        return Button_separator;
    }

    public int getStart_height() { return Start_height; }

    public int getText_start_percents() { return Text_start_percents; }


}

