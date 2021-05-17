package Game;

import com.game.GUI.GamePanel;

import javax.swing.*;
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
        addBall(new Ball(100, 50,panel.getBall_Width(), panel.getBall_Height(), panel,panel.getBall_VelocityX()));
        addBall(new Ball(1000, 50,panel.getBall_Width(), panel.getBall_Height(), panel,panel.getBall_VelocityX()));
    }
    public void set(){
        for(int i=0; i<ball.size();i++){
            tempball = ball.get(i);
            if(removingbullet.getTempBullet()!=null ){
                if(tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight()==panel.getBall_Height() && tempball.getWidth()==panel.getBall_Width()){
                    addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width()/2, panel.getBall_Height()/2, panel,panel.getBall_VelocityX()));
                    addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width()/2, panel.getBall_Height()/2, panel,panel.getBall_VelocityX()*-1));
                    removeBall(tempball);
                    removingbullet.removeBullet(removingbullet.getTempBullet());
                }
                else if(tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight()==panel.getBall_Height()/2 && tempball.getWidth()==panel.getBall_Width()/2){
                    removeBall(tempball);
                    removingbullet.removeBullet(removingbullet.getTempBullet());
                }
            }
            tempball.set();
            if(ball.size()==0){
                JOptionPane.showMessageDialog(null,
                        "Congratulations! You have just finished the exemplary level of our game. It is not over yet... Making game in progress, we are doing our best");
                System.exit(0);
            }
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
