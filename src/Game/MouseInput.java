package Game;

import com.game.GUI.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * class which detect mouse input
 *
 */

public class MouseInput implements MouseListener {

    private GamePanel panel;
    public MouseInput(GamePanel panel){
        this.panel=panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override

    /**
     * function detecting a press on a button
     *
     */

    public void mousePressed(MouseEvent e) {
        int mx=e.getX();
        int my=e.getY();
    /*
     gtd.fillRect(panel.getWindow_width()/2-300,200,600,100);
        gtd.fillRect(panel.getWindow_width()/2-300,325,600,100);
        gtd.fillRect(panel.getWindow_width()/2-300,450,600,100);
        gtd.fillRect(panel.getWindow_width()/2-300,575,600,100);
     */
        if(mx >=(panel.getWindow_width()/2-300)*panel.getScalex() && mx <panel.getWindow_width()/2+300*panel.getScalex()){
            if(my >= 200*panel.getScaley() && my<300*panel.getScaley()){
                panel.setState(panel.getStateGame());
            }
            if(my >= 575*panel.getScaley() && my<675*panel.getScaley()){
                panel.setState(panel.getStateQuit());
            }
            if(my >= 450*panel.getScaley() && my<550*panel.getScaley()){
                panel.setState(panel.getStateOptions());
            }
            if(my >= 325*panel.getScaley() && my<425*panel.getScaley()){
                panel.setState(panel.getStateLeaderboard());
            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
