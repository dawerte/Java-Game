package com.game.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Game.*;
import com.game.Config.Config;
import com.game.Config.Level;


/**
 * Class controls game's running and multi-thread
 */
public class GamePanel extends JPanel implements ActionListener {
    public static int width;
    public static int height;

    public static int Window_width;
    public static int Window_height;
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

    private Ball ball;
    private Player player;
    private BulletController controler;
    private BallController ballcontroler;
    private Timer gameTimer;
    private DeathController deathcontroler;
    private ArrayList<Wall> walls = new ArrayList<>();
    private boolean running=true;
    double scalex=1,scaley=1;
    private Config config;
    private Level level;



    /**
     * Class constructor, selects preferred size of widndow
     *
     * @param width
     * @param height
     */
    public GamePanel(int width, int height) {

        try {
            config = new Config();
        }
        catch (IOException e){
            System.out.println("Błąd odczytu danych");
        }
        try {
            level = new Level();
        }
        catch (IOException e){
            System.out.println("Błąd odczytu danych");
        }
        setVariables();
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyChecker(this));

        player = new Player(Player_position_x, PLayer_position_y, this);
        controler =new BulletController(this);
        ballcontroler=new BallController(this,controler);
        deathcontroler=new DeathController(this,ballcontroler,player);
        makeWalls();
        JOptionPane.showMessageDialog(null,
                "Hello! A - go left, D - go right, W - shoot, space - jump ");
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(running) {
                    deathcontroler.set();
                    player.set();
                    ballcontroler.set();
                    controler.set();
                    repaint();
                    calculatescale();
                }
            }
        }, 0, 17);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        gtd.scale(scalex,scaley);
        player.draw(gtd);
        ballcontroler.draw(gtd);
        controler.draw(gtd);
        for (Wall wall : walls) wall.draw(gtd);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a') player.setKeyLeft(true);
        if (e.getKeyChar() == ' ') player.setKeyUp(true);
        if (e.getKeyChar() == 's') player.setKeyDown(true);
        if (e.getKeyChar() == 'd') player.setKeyRight(true);
        if (e.getKeyChar() == 'w') {
            controler.setKeySpace(true);
            controler.addBullet(new Bullet(player.getX(), player.getY(), this));
        }

    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyChar()=='a') player.setKeyLeft(false);
        if(e.getKeyChar()==' ') player.setKeyUp(false);
        if(e.getKeyChar()=='s') player.setKeyDown(false);
        if(e.getKeyChar()=='d') player.setKeyRight(false);
        if(e.getKeyChar()=='w') controler.setKeySpace(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void makeWalls(){
        if(Level_Number==1) {
            for (int i = Wall_width; i < Walls_maxX; i += Wall_width) {
                walls.add(new Wall(i, Walls_Y, Wall_width, Wall_height));
            }
            for (int i = Wall_width; i < Walls_maxY; i += Wall_width) {
                walls.add(new Wall(Walls_X1, i, Wall_width, Wall_height));
            }
            for (int i = Wall_width; i < Walls_maxY; i += Wall_width) {
                walls.add(new Wall(Walls_X2, i, Wall_width, Wall_height));
            }
        }
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public void calculatescale(){
    scalex = this.getWidth()/(double)Window_width;
    scaley = this.getHeight()/(double)Window_height;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    public void setVariables(){
        Window_width = config.getWindow_width();
        Window_height = config.getWindow_height();
        Block_size = config.getBlock_size();
        Block_width = config.getBlock_width();
        Block_height = config.getBlock_height();
        Player_position_x = config.getPlayer_position_x();
        PLayer_position_y = config.getPLayer_position_y();
        PLayer_width = config.getPLayer_width();
        Player_height = config.getPlayer_height();
        Bullet_width = config.getBullet_width();
        Bullet_height = config.getBullet_height();
        Wall_height = config.getWall_height();
        Wall_width = config.getWall_width();
        Max_velocity = config.getMax_velocity();
        Level_Number = level.getLevel_Number();
        Number_of_Balls = level.getNumber_of_Balls();
        Ball_VelocityX = level.getBall_VelocityX();
        Ball_VelocityY = level.getBall_VelocityY();
        Player_VelocityX = level.getPlayer_VelocityX();
        Player_VelocityJump = level.getPlayer_VelocityJump();
        Bullet_VelocityY = level.getBullet_VelocityY();
        Ball_Width = level.getBall_Width();
        Ball_Height = level.getBall_Height();
        Level_Number = level.getLevel_Number();
        Walls_X1 = level.getWalls_X1();
        Walls_X2 = level.getWalls_X2();
        Walls_Y = level.getWalls_Y();
        Walls_maxX = level.getWalls_maxX();
        Walls_maxY = level.getWalls_maxY();
        Ball_startX = level.getBall_startX();
        Ball_startY = level.getBall_startY();

    }

    public int getPlayer_VelocityX() {
        return Player_VelocityX;
    }

    public int getPlayer_VelocityJump() {
        return Player_VelocityJump;
    }

    public int getNumber_of_Balls() {
        return Number_of_Balls;
    }

    public int getLevel_Number() {
        return Level_Number;
    }

    public int getBullet_VelocityY() {
        return Bullet_VelocityY;
    }

    public int getBall_VelocityY() {
        return Ball_VelocityY;
    }

    public int getBall_VelocityX() {
        return Ball_VelocityX;
    }

    public static int getWindow_width() {
        return Window_width;
    }

    public static int getWindow_height() {
        return Window_height;
    }

    public int getWall_width() {
        return Wall_width;
    }

    public int getWall_height() {
        return Wall_height;
    }

    public int getPLayer_width() {
        return PLayer_width;
    }

    public int getPlayer_position_x() {
        return Player_position_x;
    }

    public int getPLayer_position_y() {
        return PLayer_position_y;
    }

    public int getPlayer_height() {
        return Player_height;
    }

    public int getMax_velocity() {
        return Max_velocity;
    }

    public int getBullet_width() {
        return Bullet_width;
    }

    public int getBullet_height() {
        return Bullet_height;
    }

    public int getBlock_size() {
        return Block_size;
    }

    public int getBlock_width() {
        return Block_width;
    }

    public int getBlock_height() {
        return Block_height;
    }

    public int getBall_Width() {
        return Ball_Width;
    }

    public int getBall_Height() {
        return Ball_Height;
    }

    public int getBall_startY() {
        return Ball_startY;
    }

    public int getBall_startX() {
        return Ball_startX;
    }
}
