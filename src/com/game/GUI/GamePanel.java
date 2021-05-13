package com.game.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Game.*;


/**
 * Class controls game's running and multi-thread
 */
public class GamePanel extends JPanel implements ActionListener {
    public static int width;
    public static int height;

    private Ball ball;
    private Player player;
    private BulletController controler;
    private BallController ballcontroler;
    private Timer gameTimer;
    private DeathController deathcontroler;
    private ArrayList<Wall> walls = new ArrayList<>();
    private boolean running=true;
    double scalex=1,scaley=1;

    /**
     * Class constructor, selects preferred size of widndow
     *
     * @param width
     * @param height
     */
    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyChecker(this));

        player = new Player(400, 400, this);
        //ball = new Ball(100, 50, this);
        //ball2 = new Ball(1000, 50, this);
        controler =new BulletController(this);
        ballcontroler=new BallController(this,controler);
        deathcontroler=new DeathController(this,ballcontroler,player);
        makeWalls();
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
            controler.addBullet(new Bullet(player.getX(), player.getY(), 10,20,this));
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
        for(int i=50; i<1250;i+=50){
            walls.add(new Wall(i,550,50,50));
        }
        for(int i=50; i<600;i+=50){
            walls.add(new Wall(0,i,50,50));
        }
        for(int i=50; i<600;i+=50){
            walls.add(new Wall(1200,i,50,50));
        }
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public void calculatescale(){
    scalex = this.getWidth()/(double)1280;
    scaley = this.getHeight()/(double)720;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
