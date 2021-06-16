package Game;

import com.game.GUI.GamePanel;

import java.awt.*;

public class Bullet {
    private GamePanel panel;
    private double x;
    private double y;

    private int width;
    private int height;

    private double yspeed;

    Rectangle hitBox;

    /**
     * class making a bullet
     *
     */
    public Bullet(double x, double y, GamePanel panel){
        this.panel = panel;
        this.x=x;
        this.y=y;
        this.height= panel.getBullet_height();
        this.width=panel.getBullet_width();

        hitBox = new Rectangle((int)x,(int)y,width,height);

    }
    public void set(){
        yspeed = panel.getBullet_VelocityY();

        y +=yspeed;
      
        hitBox.y=(int)y;
        hitBox.x= (int)x;

    }

    /**
     * function giving color to our bullet
     *
     * @param gtd
     */

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.GREEN);
        gtd.fillRect((int)x,(int)y,width,height);
    }

    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }
}
