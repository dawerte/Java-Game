package Game;

import com.game.GUI.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BallController {
    private LinkedList<Ball> ball = new LinkedList<Ball>();
    private Ball tempball;
    private GamePanel panel;
    private BulletController removingbullet;
    private static int count = 1;

    public BallController(GamePanel panel, BulletController removingbullet){
        this.panel=panel;
        this.removingbullet=removingbullet;

        if(panel.getNumber_of_Balls()==1) {
            addBall(new Ball(panel.getBall_startX(), panel.getBall_startY(), panel.getBall_Width(), panel.getBall_Height(), panel, panel.getBall_VelocityX()));
        }
        if(panel.getNumber_of_Balls()==2) {
            addBall(new Ball(panel.getBall_startX(), panel.getBall_startY(), panel.getBall_Width(), panel.getBall_Height(), panel, panel.getBall_VelocityX()));
            addBall(new Ball(panel.getBall_startX() * 10, panel.getBall_startY(), panel.getBall_Width(), panel.getBall_Height(), panel, panel.getBall_VelocityX()));
        }
        if(panel.getNumber_of_Balls()==3) {
            addBall(new Ball(panel.getBall_startX(), panel.getBall_startY(), panel.getBall_Width(), panel.getBall_Height(), panel, panel.getBall_VelocityX()));
            addBall(new Ball(panel.getBall_startX() * 10, panel.getBall_startY(), panel.getBall_Width(), panel.getBall_Height(), panel, panel.getBall_VelocityX()));
            addBall(new Ball(panel.getBall_startX() * 4, panel.getBall_startY(), panel.getBall_Width(), panel.getBall_Height(), panel, panel.getBall_VelocityX()));
        }
    }
    public void set(){
        for(int i=0; i<ball.size();i++){
            tempball = ball.get(i);
            if(removingbullet.getTempBullet()!=null ) {
                if (panel.getLevel_Number() == 1) {
                    if (tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight() == panel.getBall_Height() && tempball.getWidth() == panel.getBall_Width()) {
                        removingbullet.getTempBullet().hitBox.x=10000;
                        removingbullet.getTempBullet().hitBox.y=10000;
                        removingbullet.getBullet().clear();
                        removeBall(tempball);
                        panel.setScore(panel.getScore()+200);
                        addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width() / 2, panel.getBall_Height() / 2, panel, panel.getBall_VelocityX()));
                        addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width() / 2, panel.getBall_Height() / 2, panel, panel.getBall_VelocityX() * -1));


                    } else if (tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight() == panel.getBall_Height() / 2 && tempball.getWidth() == panel.getBall_Width() / 2) {
                        removeBall(tempball);
                        removingbullet.getTempBullet().hitBox.x=10000;
                        removingbullet.getTempBullet().hitBox.y=10000;
                        removingbullet.getBullet().clear();
                        panel.setScore(panel.getScore()+400);


                    }
                }
                if (panel.getLevel_Number() != 1) {
                    if (tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight() == panel.getBall_Height() && tempball.getWidth() == panel.getBall_Width()) {
                        removingbullet.getTempBullet().hitBox.x=10000;
                        removingbullet.getTempBullet().hitBox.y=10000;
                        removingbullet.getBullet().clear();
                        removeBall(tempball);
                        panel.setScore(panel.getScore()+200);
                        addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width() / 2, panel.getBall_Height() / 2, panel, panel.getBall_VelocityX()));
                        addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width() / 2, panel.getBall_Height() / 2, panel, panel.getBall_VelocityX() * -1));
                    } else if (tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight() == panel.getBall_Height() / 2 && tempball.getWidth() == panel.getBall_Width() / 2) {
                        removingbullet.getTempBullet().hitBox.x=10000;
                        removingbullet.getTempBullet().hitBox.y=10000;
                        removingbullet.getBullet().clear();
                        removeBall(tempball);
                        panel.setScore(panel.getScore()+400);
                        addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width() / 4, panel.getBall_Height() / 4, panel, panel.getBall_VelocityX()));
                        addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width() / 4, panel.getBall_Height() / 4, panel, panel.getBall_VelocityX() * -1));
                    }else if (tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight() == panel.getBall_Height() / 4 && tempball.getWidth() == panel.getBall_Width() / 4) {
                        removingbullet.getTempBullet().hitBox.x=10000;
                        removingbullet.getTempBullet().hitBox.y=10000;
                        removingbullet.getBullet().clear();
                        removeBall(tempball);
                        panel.setScore(panel.getScore()+600);
                        addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width() / 6, panel.getBall_Height() / 6, panel, panel.getBall_VelocityX()));
                        addBall(new Ball(tempball.getX(), tempball.getY(), panel.getBall_Width() / 6, panel.getBall_Height() / 6, panel, panel.getBall_VelocityX() * -1));
                    }else if (tempball.hitBox.intersects(removingbullet.getTempBullet().hitBox) && tempball.getHeight() == panel.getBall_Height() / 6 && tempball.getWidth() == panel.getBall_Width() / 6) {
                        removingbullet.getTempBullet().hitBox.x=10000;
                        removingbullet.getTempBullet().hitBox.y=10000;
                        panel.setScore(panel.getScore()+800);
                        removeBall(tempball);
                        removingbullet.getBullet().clear();
                    }
                }
            }
            tempball.set();
            if(ball.size()==0){
                count++;
                panel.setLevel_Number(count);
                panel.SwitchLevel();

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
