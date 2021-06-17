package Game;

import com.game.GUI.GamePanel;

import java.util.ArrayList;

/**
 * class which makes walls (and extra walls) in each level
 *
 */

public class LevelBuilder {

    private GamePanel panel;
    private ArrayList<Wall> walls;

    public LevelBuilder(GamePanel panel,ArrayList walls){
        this.panel=panel;
        this.walls=walls;
    }

    /**
     * function making walls
     *
     * @param j
     */
    public void Level(int j){

        if(j==1) {
            for (int i = panel.getWall_width(); i < panel.getWalls_maxX(); i += panel.getWall_width()) {
                walls.add(new Wall(i, panel.getWalls_Y(), panel.getWall_width(), panel.getWall_height()));
            }
            for (int i = 0; i < panel.getWalls_maxY(); i += panel.getWall_width()) {
                walls.add(new Wall(panel.getWalls_X1(), i, panel.getWall_width(), panel.getWall_height()));
            }
            for (int i = 0; i < panel.getWalls_maxY(); i += panel.getWall_width()) {
                walls.add(new Wall(panel.getWalls_X2(), i, panel.getWall_width(), panel.getWall_height()));
            }
        }
        if(j==2) {
            for (int i = panel.getWall_width(); i < panel.getWalls_maxX(); i += panel.getWall_width()) {
                walls.add(new Wall(i, panel.getWalls_Y(), panel.getWall_width(), panel.getWall_height()));
            }
            for (int i = 0; i < panel.getWalls_maxY(); i += panel.getWall_width()) {
                walls.add(new Wall(panel.getWalls_X1(), i, panel.getWall_width(), panel.getWall_height()));
            }
            for (int i = 0; i < panel.getWalls_maxY(); i += panel.getWall_width()) {
                walls.add(new Wall(panel.getWalls_X2(), i, panel.getWall_width(), panel.getWall_height()));
            }
            walls.add(new Wall( panel.getWall_width()+250,panel.getWalls_Y()-200,panel.getWall_width(), panel.getWall_height()));
            walls.add(new Wall(panel.getWall_width()+650,panel.getWalls_Y()-300,panel.getWall_width(), panel.getWall_height()));
            walls.add(new Wall(panel.getWall_width()+650,panel.getWalls_Y()-350,panel.getWall_width(), panel.getWall_height()));
        }
        if(j==3) {
            for (int i = panel.getWall_width(); i < panel.getWalls_maxX(); i += panel.getWall_width()) {
                walls.add(new Wall(i, panel.getWalls_Y(), panel.getWall_width(), panel.getWall_height()));
            }
            for (int i = 0; i < panel.getWalls_maxY(); i += panel.getWall_width()) {
                walls.add(new Wall(panel.getWalls_X1(), i, panel.getWall_width(), panel.getWall_height()));
            }
            for (int i = 0; i < panel.getWalls_maxY(); i += panel.getWall_width()) {
                walls.add(new Wall(panel.getWalls_X2(), i, panel.getWall_width(), panel.getWall_height()));
            }
            walls.add(new Wall( panel.getWall_width()+250,panel.getWalls_Y()-200,panel.getWall_width(), panel.getWall_height()));
            walls.add(new Wall(panel.getWall_width()+750,panel.getWalls_Y()-200,panel.getWall_width(), panel.getWall_height()));
        }
    }
}
