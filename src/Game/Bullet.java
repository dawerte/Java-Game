package Game;

import com.game.GUI.GamePanel;

import java.awt.*;

public class Bullet {
    private GamePanel panel;
    private int x;
    private int y;

    private int width;
    private int height;

    private double yspeed;

    Rectangle hitBox;


    public Bullet(int x, int y, GamePanel panel){
        this.panel = panel;
        this.x=x;
        this.y=y;
        this.height= panel.getBullet_height();
        this.width=panel.getBullet_width();

        hitBox = new Rectangle(x,y,width,height);

    }
    public void set(){
        yspeed = panel.getBullet_VelocityY();

        y +=yspeed;
      
        hitBox.y=y;

    }
    public void draw(Graphics2D gtd){
        gtd.setColor(Color.GREEN);
        gtd.fillRect(x,y,width,height);
    }

    public int getY() {
        return y;
    }
}
