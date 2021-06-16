package Game;

import com.game.GUI.GamePanel;

import java.io.*;
import java.net.Socket;

public class Client {

    private int[] array;
    private int Level_Number;
    private int ballsize;
    private Socket s;
    public Client(GamePanel panel) throws IOException, ClassNotFoundException {
        s= new Socket("127.0.0.1", 4999);


        array=new int[30];
        ObjectInputStream in=new ObjectInputStream(s.getInputStream());
        array=(int[])in.readObject();

    }
    public void LevelNumberSender(GamePanel panel) throws IOException {
        Level_Number=panel.getLevel_Number();
        ObjectOutputStream op=new ObjectOutputStream((s.getOutputStream()));
        op.writeObject(Level_Number);
        op.writeObject(ballsize);
    }
    public void Reciever() throws IOException, ClassNotFoundException {
        array=new int[30];
        ObjectInputStream in=new ObjectInputStream(s.getInputStream());
        array=(int[])in.readObject();
    }

    public int[] getArray() {
        return array;
    }
}
