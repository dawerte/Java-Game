package com.game.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import com.game.Config.*;


/**
 * Class controls game's running and multi-thread
 */
public class GamePanel extends JPanel implements Runnable{
    public static int width;
    public static int height;

    private Thread thread;
    private boolean running;

    private BufferedImage img;

    private Graphics2D g;

    /**
     * Class constructor, selects preferred size of widndow
     * @param width
     * @param height
     */
    public GamePanel(int width, int height){
        this.width=width;
        this.height=height;
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }

    /**
     * Starts and creates thread
     */
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread= new Thread (this, "GameThread");
            thread.start();
        }
    }

    /**
     * Initializes game
     */
    public void init(){
        running=true;

        img=new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
        g=(Graphics2D) img.getGraphics();
    }

    /**
     * Runs game after initializing
     */
    public void run(){
        init();
        while(running){
            update();
            render();
            draw();
        }
    }

    /**
     * Updates things per frame
     */
    public void update(){

    }

    /**
     * Renders
     */
    public void render(){
        if(g!=null){
            g.setColor(new Color(164, 178, 228));
            g.fillRect(0,0,width,height-60);
        }
    }

    /**
     * Draws in program's window
     */
    public void draw(){
        Graphics g2=(Graphics) this.getGraphics();
        g2.drawImage(img,0,0,width,height,null);
        g2.dispose();
       /*try {                               //engineering debuging :)
            Config config = new Config();
            Level level = new Level();
            System.out.println(config.getDELAY());
            System.out.println(level.getLevelNumber());
        }
        catch(FileNotFoundException e){
            System.out.println("Blad");
        }*/
    }
}
