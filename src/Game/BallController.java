package Game;

import com.game.GUI.GamePanel;

import java.awt.*;
import java.util.LinkedList;

public class BallController {
    private LinkedList<Ball> ball =new LinkedList<Ball>();
    private Ball tempball;
    private GamePanel panel;
    private BulletController removingbullet;

    public BallController(GamePanel panel, BulletController removingbullet){
        this.panel=panel;
        this.removingbullet=removingbullet;
        addBall(new Ball(100, 50,50,50, panel,4));
        addBall(new Ball(1000, 50,50,50, panel,4));
    }
    public void set(){
        for(int i=0; i<ball.size();i++){
            tempball = ball.get(i);
            if(removingbullet.getTempBullet()!=null ){
                if(tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight()==50 && tempball.getWidth()==50){
                    addBall(new Ball(tempball.getX(), tempball.getY(), 25,25, panel,4));
                    addBall(new Ball(tempball.getX(), tempball.getY(), 25,25, panel,-4));
                    removeBall(tempball);
                    removingbullet.removeBullet(removingbullet.getTempBullet());
                }
                else if(tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight()==25 && tempball.getWidth()==25){
                    removeBall(tempball);
                    removingbullet.removeBullet(removingbullet.getTempBullet());
                }
            }
            tempball.set();
        }
    }
    public void draw(Graphics2D g){
        for(int i=0; i<ball.size();i++){
            tempball=ball.get(i);

            tempball.draw(g);
        }
    }
    public void addBall(Ball block){
         ball.add(block);
    }
    public void removeBall(Ball block){

        ball.remove(block);
    }

    public Ball getTempball() {
        return tempball;
    }

    public LinkedList<Ball> getBall() {
        return ball;
    }
}
