package Game;

import com.game.GUI.GamePanel;

import java.awt.*;

public class Ball {
    private GamePanel panel;
    private int x;
    private int y;

    private int width;
    private int height;

    private double xspeed=4;
    private double yspeed;

    Rectangle hitBox;

    public Ball(int x,int y, int width,int height, GamePanel panel,int xspeed) {
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.xspeed=xspeed;
        hitBox = new Rectangle(x, y, width, height);

    }
    public void set(){

        yspeed+=0.3;
        if(width<=25 &&height<=25) yspeed+=0.15;
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

        hitBox.x=x;
        hitBox.y=y;
    }
    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.fillRect(x,y,width,height);
    }

    public int getY() {
        return y;
    }

    public int getX() {
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
}

