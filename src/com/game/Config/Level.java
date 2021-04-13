package com.game.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Class reads configuration files (level parameters)
 */
public class Level {
    private File levelFile;
    /**
     * Defines level number
     */
    private int levelNumber;
    /**
     * Defines ball velocity in axis X
     */
    private int ballVelocityX;
    /**
     * Defines ball velocity in axis Y
     */
    private int ballVelocityY;
    /**
     * Defines player Velocity
     */
    private int playerVelocity;
    /**
     * Defines bullet Velocity
     */
    private int bulletVelocity;
    /**
     * Defines size of the map
     */
    private int mapSize1D;
    private int mapSize2D;
    private short[] mapData;

    /**
     * Class constructor
     * @throws FileNotFoundException
     */
    public Level() throws FileNotFoundException{
        LocateFile(1);
        DataReader();
    }

    /**
     * Locate file's directory
     * @param i (is number of file)
     */
    private void LocateFile(int i){
        String path = new ReadingFiles("Config").MaKeFile("level"+i+".txt").toString();
        levelFile = new File(path);
    }

    /**
     * * Reads parameters from file and assign them to variables
     * @throws FileNotFoundException
     */
    private void DataReader() throws FileNotFoundException {
        Scanner scanner = new Scanner(levelFile);
        String temp;
        temp=scanner.next();
        levelNumber = scanner.nextInt();
        temp= scanner.next();
        ballVelocityX= scanner.nextInt();
        temp= scanner.next();
        ballVelocityY= scanner.nextInt();
        temp= scanner.next();
        playerVelocity= scanner.nextInt();
        temp= scanner.next();
        bulletVelocity= scanner.nextInt();
        temp= scanner.next();
        mapSize1D= scanner.nextInt();
        mapSize2D=(int) Math.pow(mapSize1D,2);
        mapData= new short[mapSize2D];
    }

    public File getLevelFile() {
        return levelFile;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getBallVelocityX() {
        return ballVelocityX;
    }

    public int getPlayerVelocity() {
        return playerVelocity;
    }

    public int getBulletVelocity() {
        return bulletVelocity;
    }

    public int getBallVelocityY() {
        return ballVelocityY;
    }
    public int getMapSize1D() {
        return mapSize1D;
    }
    public int getMapSize2D() {
        return mapSize2D;
    }

    public short[] getMapData() {
        return mapData;
    }
}


