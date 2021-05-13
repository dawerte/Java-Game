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


    public Bullet(int x, int y, int width, int height, GamePanel panel){
        this.panel = panel;
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;

        hitBox = new Rectangle(x,y,width,height);

    }
    public void set(){
        yspeed =-10;

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
