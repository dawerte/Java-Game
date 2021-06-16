package Game;

import com.game.GUI.GamePanel;

import java.awt.*;

/**
 * class which creates ball and set all they parameters
 *
 */

public class Ball{
    private GamePanel panel;
    private double x;
    private double y;

    private int width;
    private int height;

    private double xspeed;
    private double yspeed;

    Rectangle hitBox;

    /**
     * class constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param panel
     * @param xspeed
     */

    public Ball(double x,double y, int width,int height, GamePanel panel,int xspeed) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.xspeed=xspeed;
        hitBox = new Rectangle((int)x, (int)y, width, height);
        yspeed=panel.getBall_VelocityY();

    }

    /**
     * function determining the position of ball etc.
     *
     */
    public void set(){

        yspeed+=0.3;
        if(width<= 25 &&height<=25) yspeed+=0.15;
        //Horizontal Collision
        hitBox.x += xspeed;
        for(Wall wall: panel.getWalls()){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.x-=xspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                hitBox.x -= Math.signum(xspeed);
                if(xspeed>0) xspeed=-4;
                else if(xspeed<0) xspeed=4;
                x=hitBox.x;
            }
        }
        //Vertical Collision
        hitBox.y += yspeed;
        for(Wall wall: panel.getWalls()){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.y-=yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                hitBox.y -= Math.signum(yspeed);
                yspeed=-14;
                y=hitBox.y;
            }
        }
        x +=xspeed;
        y +=yspeed;

        hitBox.x=(int)x;
        hitBox.y=(int)y;
    }

    /**
     *
     * function which gives ball a color
     * @param gtd
     */
    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.fillRect((int)x,(int)y,width,height);
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getXspeed() {
        return xspeed;
    }

    public double getYspeed() {
        return yspeed;
    }

    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    public void setYspeed(double yspeed) {
        this.yspeed = yspeed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

