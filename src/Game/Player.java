package Game;

import com.game.GUI.GamePanel;

import java.awt.*;

public class Player {
    private GamePanel panel;
    private int x;
    private int y;

    private int width;
    private int height;

    private double xspeed;
    private double yspeed;

    Rectangle hitBox;

    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyUp;
    private boolean keyDown;
    private int max_speed;

    public Player(int x,int y, GamePanel panel){
        this.panel = panel;
        this.x=x;
        this.y=y;
        max_speed=panel.getMax_velocity();
        width=panel.getPLayer_width();
        height=panel.getPlayer_height();
        hitBox = new Rectangle(x,y,width,height);


    }
    public void set(){
        if(keyLeft && keyRight || !keyLeft && !keyRight) xspeed *=0.8;
        else if(keyLeft && !keyRight) xspeed--;
        else if(keyRight && !keyLeft) xspeed++;

        if(xspeed>0 && xspeed<0.75) xspeed=0;
        if(xspeed<0 && xspeed>-0.75) xspeed=0;

        if(xspeed>max_speed) xspeed =max_speed;
        if(xspeed<max_speed*-1) xspeed=max_speed*-1;

        if(keyUp) {
            hitBox.y ++;
            for(Wall wall: panel.getWalls()){
                if(wall.hitBox.intersects(hitBox)) yspeed =-6;
            }
            hitBox.y--;
        }
        yspeed+=0.3;
        //Horizontal Collision with walls
        hitBox.x += xspeed;
        for(Wall wall: panel.getWalls()){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.x-=xspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                hitBox.x -= Math.signum(xspeed);
                xspeed=0;
                x=hitBox.x;
            }
        }
        //Vertical Collision with walls
        hitBox.y += yspeed;
        for(Wall wall: panel.getWalls()){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.y-=yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                hitBox.y -= Math.signum(yspeed);
                yspeed=0;
                y=hitBox.y;
            }
        }
        x +=xspeed;
        y +=yspeed;

        hitBox.x=x;
        hitBox.y=y;

    }
    public void draw(Graphics2D gtd){
        gtd.setColor(Color.RED);
        gtd.fillRect(x,y,width,height);
    }

    public void setKeyLeft(boolean keyLeft) {
        this.keyLeft = keyLeft;
    }

    public void setKeyRight(boolean keyRight) {
        this.keyRight = keyRight;
    }

    public void setKeyDown(boolean keyDown) {
        this.keyDown = keyDown;
    }

    public void setKeyUp(boolean keyUp) {
        this.keyUp = keyUp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getYspeed() {
        return yspeed;
    }

    public double getXspeed() {
        return xspeed;
    }
}
