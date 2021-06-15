package Game;

import com.game.GUI.GamePanel;
import com.game.GUI.Leaderboard;

import javax.swing.*;

public class DeathController {
    private GamePanel panel;
    private BallController ball;
    private Ball tempball;
    private Player player;

    public DeathController(GamePanel panel, BallController ball, Player player){
        this.ball=ball;
        this.panel=panel;
        this.player=player;
    }
    public void set(){
        for(int i=0; i<ball.getBall().size();i++) {
            tempball = ball.getBall().get(i);
           tempball.hitBox.x -= tempball.getXspeed();
          tempball.hitBox.y -= tempball.getYspeed();
            if(tempball.hitBox.intersects(player.hitBox)){
                tempball.hitBox.y-=tempball.getYspeed();
                player.hitBox.y -=player.getYspeed();
                while(!player.hitBox.intersects(tempball.hitBox)) tempball.hitBox.y += Math.signum(tempball.getYspeed());
                tempball.hitBox.y -= Math.signum(tempball.getYspeed());
                panel.setLives(panel.getLives()-1);
                panel.RestartLevel();
                if(panel.getLives()==0) {
                    tempball.setYspeed(0);
                    panel.setRunning(false);
                    new Leaderboard(panel);
                }
            }

//            if(tempball.hitBox.intersects(player.hitBox)){
//                tempball.hitBox.x-=tempball.getXspeed();
//                player.hitBox.x -= player.getXspeed();
//                while(!player.hitBox.intersects(tempball.hitBox)) tempball.hitBox.x += Math.signum(tempball.getXspeed());
//                tempball.hitBox.x -= Math.signum(tempball.getXspeed());
//                tempball.setXspeed(0);
//                panel.setRunning(false);
//                JOptionPane.showMessageDialog(null,
//                        "You are dead ");
//                System.exit(0);

            //if (player.hitBox.intersects(tempball.hitBox)) {
           //     player.hitBox.y-=player.getYspeed();
            //    player.hitBox.x= tempball.getX();
            //    player.hitBox.y= tempball.getY();
            //    JOptionPane.showMessageDialog(null,
             //           "You are dead ");
              //  panel.setRunning(false);
          //  }
        }
    }
}
