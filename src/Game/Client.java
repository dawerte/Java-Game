package Game;

import com.game.GUI.GamePanel;
import com.game.GUI.LeaderboardArray;
import com.game.GUI.LeaderboardBody;

import java.io.*;
import java.net.Socket;

/**
 * class making client
 *
 */

public class Client {

    private int[] array;
    private int Level_Number;
    private int ballsize;
    private Socket s;
    public Client() throws IOException, ClassNotFoundException {
        s= new Socket("127.0.0.1", 4999);


        array=new int[30];
        ObjectInputStream in=new ObjectInputStream(s.getInputStream());
        array=(int[])in.readObject();

    }

    /**
     * function which sends a level number to server
     *
     * @param panel
     * @throws IOException
     */

    public void LevelNumberSender(GamePanel panel) throws IOException {
        Level_Number=panel.getLevel_Number();
        ballsize=panel.getBallcontroler().getBall().size();
        System.out.println(ballsize);
        System.out.println(Level_Number);
        ObjectOutputStream op=new ObjectOutputStream((s.getOutputStream()));
        op.writeObject(Level_Number);
        op.writeObject(ballsize);
        op.flush();

    }

    /**
     * function which receives a level number
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void Reciever() throws IOException, ClassNotFoundException {
        array=new int[30];
        ObjectInputStream in=new ObjectInputStream(s.getInputStream());
        array=(int[])in.readObject();
    }

    public void LeaderboardSender(LeaderboardBody leaderboard) throws IOException {
        ObjectOutputStream op=new ObjectOutputStream((s.getOutputStream()));
        op.writeObject(leaderboard.getNicknames());
        op.writeObject(leaderboard.getScores());
        op.flush();
    }
    public int[] getArray() {
        return array;
    }
}
