package com.game.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
    private Timer gameTimer, pauseTimer;
    private DeathController deathcontroler;
    private ArrayList<Wall> walls = new ArrayList<>();
    private boolean running=true;
    private double scalex=1,scaley=1;
    private Config config;
    private Level level;
    private Menu menu;
    private LevelBuilder levelbuilder;
    private boolean pauseState=false;
    private TimerTask gameTimerTask,pauseTimerTask;
    private int Lives;
    private Font fnt0;
    private Client client;
    private boolean connected;
    private int score=0;

    private enum STATE{
        GAME,
        MENU,
        OPTIONS,
        QUIT,
        LEADERBOARD
    }
    private enum STATESERVER{
        LOCAL,
        SERVER
    }
    private STATE state=STATE.MENU;
    private STATESERVER stateserver=STATESERVER.LOCAL;
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
        try {
            client = new Client(this);
            connected=true;
        }catch(IOException e){
            System.out.println("Błąd odczytu 1");
            connected=false;
        }catch(ClassNotFoundException e2){
            System.out.println("Błąd odczytu 2");
        }
        if(stateserver==STATESERVER.LOCAL) {
             setVariables();
        }else if(stateserver==STATESERVER.SERVER){
            setVariablesfromSever();
        }
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        addKeyListener(new KeyChecker(this));
        addMouseListener(new MouseInput(this));
        menu =new Menu(this);
        player = new Player(Player_position_x, PLayer_position_y, this);
        controler =new BulletController(this);
        ballcontroler=new BallController(this,controler);
        deathcontroler=new DeathController(this,ballcontroler,player);
        levelbuilder=new LevelBuilder(this,walls);
        levelbuilder.Level(1);
        fnt0 = new Font("arial", Font.BOLD, 40);
        Lives=3;
       // makeWalls();
   //     JOptionPane.showMessageDialog(null,
     //           "Hello! A - go left, D - go right, W - shoot, space - jump ");


        gameTimer = new Timer();
        gameTimerTask=new TimerTask() {
            @Override
            public void run() {
                if(running) {
                    if(state==STATE.GAME) {
                        deathcontroler.set();
                        player.set();
                        ballcontroler.set();
                        controler.set();
                    }else if(state==STATE.QUIT){
                        running=false;
                        System.exit(0);
                    }else if(state==STATE.OPTIONS){
                        new RadioBox(getPanel());
                        running=false;
                    }else if(state==STATE.LEADERBOARD){
                        new Leaderboard2(getPanel());
                        running=false;
                    }
                    repaint();
                    calculatescale();
                }
            }
        };
        gameTimer.schedule(gameTimerTask, 0, 17);
        pauseTimer = new Timer();
        pauseTimerTask=new TimerTask() {
            @Override
            public void run() {
                if(running) {
                    calculatescale();
                }
            }
        };
        pauseTimer.schedule(pauseTimerTask, 0, 17);
    }

    /**
     * function drawing stuff on the screen
     *
     * @param g render context
     *
     */

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;
        gtd.scale(scalex,scaley);
        if(state==STATE.GAME) {
            player.draw(gtd);
            ballcontroler.draw(gtd);
            controler.draw(gtd);
            for (Wall wall : walls) wall.draw(gtd);
            gtd.setFont(fnt0);
            gtd.setColor(Color.BLACK);
            gtd.drawString("Lives: "+ Lives,50,700);
            gtd.drawString("Score: "+ score,250,700);
            gtd.drawString("ESC - pause ",950,700);
        }else if(state==STATE.MENU){
            gtd.setColor(Color.CYAN);
            gtd.fillRect(0,0,width,height);
            menu.draw(gtd);

        }else if(state==STATE.OPTIONS){
            gtd.setColor(Color.CYAN);
            gtd.fillRect(0,0,width,height);
            menu.draw(gtd);
        }else if(state==STATE.LEADERBOARD) {
            gtd.setColor(Color.CYAN);
            gtd.fillRect(0, 0, width, height);
            menu.draw(gtd);
        }
    }

    /**
     * function scanning for effective key events that is being send to process functions
     *
     * @param e key event
     */
    public void keyPressed(KeyEvent e) {
        if (state == STATE.GAME) {
            if (e.getKeyChar() == 'a') player.setKeyLeft(true);
            if (e.getKeyChar() == ' ') player.setKeyUp(true);
            if (e.getKeyChar() == 's') player.setKeyDown(true);
            if (e.getKeyChar() == 'd') player.setKeyRight(true);
            if (e.getKeyChar() == 'w') {
                controler.setKeySpace(true);
                controler.addBullet(new Bullet(player.getX(), player.getY(), this));
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                if (!pauseState) {
                    gameTimerTask.cancel();
                    pauseState = true;
                } else {
                    gameTimerTask=new TimerTask() {
                        @Override
                        public void run() {
                            if(running) {
                                if(state==STATE.GAME) {
                                    deathcontroler.set();
                                    player.set();
                                    ballcontroler.set();
                                    controler.set();
                                }else if(state==STATE.QUIT){
                                    running=false;
                                    System.exit(0);
                                }
                                repaint();
                            }
                        }
                    };
                    gameTimer.schedule(gameTimerTask, 0, 17);
                    pauseState = false;
                }
            }

        }
    }
    /**
     * function scanning for effective release key events that is being send to process functions
     *
     * @param e key event
     */
    public void keyReleased(KeyEvent e){
        if(state==STATE.GAME) {
            if (e.getKeyChar() == 'a') player.setKeyLeft(false);
            if (e.getKeyChar() == ' ') player.setKeyUp(false);
            if (e.getKeyChar() == 's') player.setKeyDown(false);
            if (e.getKeyChar() == 'd') player.setKeyRight(false);
            if (e.getKeyChar() == 'w') controler.setKeySpace(false);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /*public void makeWalls(){
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
    }*/

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     * calculates scale of window resize
     *
     */

    public void calculatescale(){
    scalex = this.getWidth()/(double)Window_width;
    scaley = this.getHeight()/(double)Window_height;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * populates data of this object with loaded configs
     *
     */

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
        Walls_X1 = level.getWalls_X1();
        Walls_X2 = level.getWalls_X2();
        Walls_Y = level.getWalls_Y();
        Walls_maxX = level.getWalls_maxX();
        Walls_maxY = level.getWalls_maxY();
        Ball_startX = level.getBall_startX();
        Ball_startY = level.getBall_startY();

    }
    public void setVariablesfromSever(){
        Window_width = client.getArray()[0];
        Window_height = client.getArray()[1];
        Block_size = client.getArray()[2];
        Block_width = client.getArray()[3];
        Block_height = client.getArray()[4];
        Player_position_x = client.getArray()[5];
        PLayer_position_y = client.getArray()[6];
        PLayer_width = client.getArray()[7];
        Player_height = client.getArray()[8];
        Bullet_width = client.getArray()[9];
        Bullet_height = client.getArray()[10];
        Wall_height = client.getArray()[11];
        Wall_width = client.getArray()[12];
        Max_velocity = client.getArray()[13];
        Level_Number = client.getArray()[14];
        Number_of_Balls = client.getArray()[15];
        Ball_VelocityX = client.getArray()[16];
        Ball_VelocityY = client.getArray()[17];
        Player_VelocityX = client.getArray()[18];
        Player_VelocityJump =client.getArray()[19];
        Bullet_VelocityY = client.getArray()[20];
        Ball_Width = client.getArray()[21];
        Ball_Height = client.getArray()[22];
        Walls_X1 = client.getArray()[23];
        Walls_X2 = client.getArray()[24];
        Walls_Y = client.getArray()[25];
        Walls_maxX = client.getArray()[26];
        Walls_maxY = client.getArray()[27];
        Ball_startX =client.getArray()[28];
        Ball_startY = client.getArray()[29];
    }

    /**
     * restarts the level to restart parameters
     *
     */

    private void ClearLevel(){
        walls.clear();
        ballcontroler.getBall().clear();
        controler.getBullet().clear();
    }

    /**
     * function switching the level
     *
     */

    public void SwitchLevel(){
        ClearLevel();
                if(stateserver==STATESERVER.LOCAL) {
                    try {
                        level = new Level(Level_Number);
                    } catch (FileNotFoundException e) {
                        System.out.println("Bład wczytywania");
                    }

                    setVariables();
                }else if(stateserver==STATESERVER.SERVER) {
                    try {
                        client.LevelNumberSender(this);
                        client.Reciever();
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Bład połączenia");
                    }
                    setVariablesfromSever();
                }
                player = new Player(Player_position_x, PLayer_position_y, this);
                controler =new BulletController(this);
                ballcontroler=new BallController(this,controler);
                deathcontroler=new DeathController(this,ballcontroler,player);
                levelbuilder=new LevelBuilder(this,walls);
                levelbuilder.Level(Level_Number);

    }

    /**
     * function restarting the level
     *
     */

    public void RestartLevel() {
        ClearLevel();
                if (stateserver == STATESERVER.LOCAL) {
                    try {
                        level = new Level(Level_Number);
                    } catch (FileNotFoundException e) {
                        System.out.println("Bład wczytywania");
                    }

                    setVariables();
                } else if (stateserver == STATESERVER.SERVER) {
                    try {
                        client.LevelNumberSender(this);
                        client.Reciever();
                        System.out.println(Number_of_Balls);
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Bład połączenia");
                    }
                    setVariablesfromSever();

                }
                player = new Player(Player_position_x, PLayer_position_y, this);
            controler = new BulletController(this);
            ballcontroler = new BallController(this, controler);
            deathcontroler = new DeathController(this, ballcontroler, player);
            levelbuilder = new LevelBuilder(this, walls);
            levelbuilder.Level(Level_Number);

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

    public void setState(STATE state) {
        this.state = state;
    }

    public STATE getStateGame() {
        return STATE.GAME;
    }
    public STATE getStateQuit() {
        return STATE.QUIT;
    }
    public STATE getStateMenu() {
        return STATE.MENU;
    }
    public STATE getStateOptions() {
        return STATE.OPTIONS;
    }

    public int getWalls_maxY() {
        return Walls_maxY;
    }
    public int getWalls_maxX() {
        return Walls_maxX;
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

    public void setLevel_Number(int level_Number) {
        Level_Number = level_Number;
    }

    public double getScaley() {
        return scaley;
    }

    public double getScalex() {
        return scalex;
    }

    public int getLives() {
        return Lives;
    }

    public void setLives(int lives) {
        Lives = lives;
    }

    public void setWalls_X2(int walls_X2) {
        Walls_X2 = walls_X2;
    }

    public BallController getBallcontroler() {
        return ballcontroler;
    }
    public STATESERVER getStateLocal() {
        return STATESERVER.LOCAL;
    }
    public STATESERVER getStateServer() {
        return STATESERVER.SERVER;
    }

    public STATESERVER getStateserver() {
        return stateserver;
    }

    public void setStateserver(STATESERVER stateserver) {
        this.stateserver = stateserver;
    }
    public GamePanel getPanel(){
        return this;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public STATE getStateLeaderboard() {
        return STATE.LEADERBOARD;
    }
    public boolean getConnected(){
        return connected;
    }

    public int getButton_width(){return config.getButton_width();}

    public int getButton_height(){return config.getButton_height();}

    public int getButton_separator(){return config.getButton_separator();}

    public int getStart_height(){return config.getStart_height();}

    public int getText_start_percents() { return config.getText_start_percents(); }
}
