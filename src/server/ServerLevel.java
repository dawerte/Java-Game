package server;

import com.game.Config.ReadingFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ServerLevel {
    private File levelFile;

    private int Level_Number;
    private int Number_of_Balls;
    private int Ball_VelocityX;
    private int Ball_VelocityY;
    private int Player_VelocityX;
    private int Player_VelocityJump;
    private int Bullet_VelocityY;
    private int Ball_Width;
    private int Ball_Height;
    private int Walls_X1;
    private int Walls_X2;
    private int Walls_Y;
    private int Walls_maxX;
    private int Walls_maxY;
    private int Ball_startX;
    private int Ball_startY;

    /**
     * Class constructor
     * @throws FileNotFoundException
     */
    public ServerLevel() throws FileNotFoundException{
        LocateFile(1);
        DataReader();
    }
    public ServerLevel(int i) throws FileNotFoundException{
        LocateFile(i);
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
        Level_Number = scanner.nextInt();
        temp= scanner.next();
        Number_of_Balls= scanner.nextInt();
        temp= scanner.next();
        Ball_VelocityX= scanner.nextInt();
        temp= scanner.next();
        Ball_VelocityY= scanner.nextInt();
        temp= scanner.next();
        Player_VelocityX= scanner.nextInt();
        temp= scanner.next();
        Player_VelocityJump= scanner.nextInt();
        temp= scanner.next();
        Bullet_VelocityY= scanner.nextInt();
        temp= scanner.next();
        Ball_Width= scanner.nextInt();
        temp= scanner.next();
        Ball_Height= scanner.nextInt();
        temp= scanner.next();
        Walls_X1= scanner.nextInt();
        temp= scanner.next();
        Walls_X2= scanner.nextInt();
        temp= scanner.next();
        Walls_Y= scanner.nextInt();
        temp= scanner.next();
        Walls_maxX= scanner.nextInt();
        temp= scanner.next();
        Walls_maxY= scanner.nextInt();
        temp= scanner.next();
        Ball_startX= scanner.nextInt();
        temp= scanner.next();
        Ball_startY= scanner.nextInt();
    }

    public File getLevelFile() {
        return levelFile;
    }

    public int getBall_VelocityX() {
        return Ball_VelocityX;
    }

    public int getBall_VelocityY() {
        return Ball_VelocityY;
    }

    public int getBullet_VelocityY() {
        return Bullet_VelocityY;
    }

    public int getLevel_Number() {
        return Level_Number;
    }

    public int getNumber_of_Balls() {
        return Number_of_Balls;
    }

    public int getPlayer_VelocityJump() {
        return Player_VelocityJump;
    }

    public int getPlayer_VelocityX() {
        return Player_VelocityX;
    }

    public int getBall_Height() {
        return Ball_Height;
    }

    public int getBall_Width() {
        return Ball_Width;
    }

    public int getWalls_Y() {
        return Walls_Y;
    }

    public int getWalls_X1() {
        return Walls_X1;
    }

    public int getWalls_X2() {
        return Walls_X2;
    }

    public int getWalls_maxX() {
        return Walls_maxX;
    }

    public int getWalls_maxY() {
        return Walls_maxY;
    }

    public int getBall_startX() {
        return Ball_startX;
    }

    public int getBall_startY() {
        return Ball_startY;
    }
}
