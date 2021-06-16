package Game;

import com.game.GUI.GamePanel;

import java.awt.*;
import java.util.LinkedList;

/**
 * class which controls the bullet
 *
 */

public class BulletController {
    private LinkedList<Bullet> bullet =new LinkedList<Bullet>();
    private Bullet TempBullet;
    private GamePanel panel;

    private boolean KeySpace;

    public BulletController(GamePanel panel){
        this.panel=panel;
    }

    /**
     * function which sets bullet on board and determines when it will end
     *
     */

    public void set(){
        for(int i=0; i<bullet.size();i++){
            TempBullet = bullet.get(i);
            TempBullet.set();
            if(TempBullet.getY()<-2000){
                removeBullet(TempBullet);
            }
        }
    }

    /**
     * function which draws bullet on board
     * @param g
     */

    public void draw(Graphics2D g){
        for(int i=0; i<bullet.size();i++){
            TempBullet=bullet.get(i);

            TempBullet.draw(g);
        }
    }
    public void addBullet(Bullet block){
        if(KeySpace) bullet.add(block);
    }
    public void removeBullet(Bullet block){
         bullet.remove(block);
    }

    public void setKeySpace(boolean keySpace) {
        KeySpace = keySpace;
    }

    public Bullet getTempBullet() {
        return TempBullet;
    }

    public LinkedList<Bullet> getBullet() {
        return bullet;
    }
}
