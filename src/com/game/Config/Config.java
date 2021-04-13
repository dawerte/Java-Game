package com.game.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Class reads configuration files (global parameters)
 */
public class Config {

    private File file;
    /**
     * Defines size of one block on the map
     */
    private int BLOCK_SIZE;
    /**
     * Defines amount of ball on the map
     */
    private int NUMBER_OF_BALLS;
    /**
     * Defines player's movement speed
     */
    private int PLAYER_SPEED;
    /**
     * Defines maximum speed that player and ball can reach
     */
    private int MAX_VELOCITY;
    /**
     * Parameter use to help animations
     */
    private int DELAY;

    /**
     * Class Constructor
     * @throws FileNotFoundException
     */
    public Config() throws FileNotFoundException{   //
        LocateFile();
        DataReader();
    }

    /**
     * Locates file's directory
     */
    private void LocateFile(){
        String path = new ReadingFiles("Config").MaKeFile("config.txt").toString();
        file = new File(path);
    }

    /**
     * Reads parameters from file and assign them to variables
     * @throws FileNotFoundException
     */
    private void DataReader() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String temp;
        temp=scanner.next();
        BLOCK_SIZE = scanner.nextInt();
        temp=scanner.next();
        NUMBER_OF_BALLS= scanner.nextInt();
        temp=scanner.next();
        PLAYER_SPEED= scanner.nextInt();
        temp=scanner.next();
        MAX_VELOCITY= scanner.nextInt();
        temp=scanner.next();
        DELAY= scanner.nextInt();
    }

    public int getBLOCK_SIZE() {
        return BLOCK_SIZE;
    }

    public int getNUMBER_OF_BALLS() {
        return NUMBER_OF_BALLS;
    }

    public int getPLAYER_SPEED() {
        return PLAYER_SPEED;
    }

    public int getMAX_VELOCITY() {
        return MAX_VELOCITY;
    }

    public int getDELAY() {
        return DELAY;
    }
}

