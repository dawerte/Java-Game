package Game;

import java.awt.*;

/**
 * class making wall
 *
 */

public class Wall {

    private int x;
    private int y;
    private int width;
    private int height;

    Rectangle hitBox;

    public Wall(int x, int y,int width, int height){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;

        hitBox=new Rectangle(x,y,width,height);

    }

    /**
     *
     * function drawing wall (setting color etc.)
     * @param gtd
     */
    public void draw(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.drawRect(x,y,width,height);
        gtd.setColor(Color.CYAN);
        gtd.fillRect(x+1,y+1,width-2,height-2);
    }

}
