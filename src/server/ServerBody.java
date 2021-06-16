package server;

import com.game.Config.Level;
import com.game.GUI.GamePanel;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;

/**
 * class server body, determining all important informations
 *
 */

public class ServerBody extends Thread {

    private ServerConfig config;
    private ServerLevel level;

    public static int Window_width;
    public static int Window_height;
    private int Block_size;
    private int Block_width;
    private int Block_height;
    private int Player_position_x;
    private int PLayer_position_y;
    private int PLayer_width;
    private int Player_height;
    private int Bullet_width;
    private int Bullet_height;
    private int Wall_height;
    private int Wall_width;
    private int Max_velocity;


    private int Level_Number;
    private int Number_of_Balls;
    private int Ball_VelocityX;
    private int Ball_VelocityY;
    private int Player_VelocityX;
    private int Player_VelocityJump;
    private int Bullet_VelocityY;
    private int Ball_Width;
    private int Ball_Height;
    private int Walls_X1;
    private int Walls_X2;
    private int Walls_Y;
    private int Walls_maxX;
    private int Walls_maxY;
    private int Ball_startX;
    private int Ball_startY;

    private int Ballsize;
    private int Lives;

    private Socket socket;

    private int[] array;
    private int[] makeLives;
     ServerBody(Socket socket) {
         this.socket = socket;
     }
     @Override
     public void run(){
         try {
             config = new ServerConfig();
         } catch (
                 IOException e) {
             System.out.println("Błąd odczytu danych");
         }
         try {
             level = new ServerLevel();
         } catch (IOException e) {
             System.out.println("Błąd odczytu danych");
         }
         setVariables();
         try{
             Sending();
         }catch(IOException e){
             System.out.println("Brak połączenia");
         }
         SwapLevels();
     }
    private void setVariables(){
        Window_width = config.getWindow_width();
        Window_height = config.getWindow_height();
        Block_size = config.getBlock_size();
        Block_width = config.getBlock_width();
        Block_height = config.getBlock_height();
        Player_position_x = config.getPlayer_position_x();
        PLayer_position_y = config.getPLayer_position_y();
        PLayer_width = config.getPLayer_width();
        Player_height = config.getPlayer_height();
        Bullet_width = config.getBullet_width();
        Bullet_height = config.getBullet_height();
        Wall_height = config.getWall_height();
        Wall_width = config.getWall_width();
        Max_velocity = config.getMax_velocity();
        Level_Number = level.getLevel_Number();
        Number_of_Balls = level.getNumber_of_Balls();
        Ball_VelocityX = level.getBall_VelocityX();
        Ball_VelocityY = level.getBall_VelocityY();
        Player_VelocityX = level.getPlayer_VelocityX();
        Player_VelocityJump = level.getPlayer_VelocityJump();
        Bullet_VelocityY = level.getBullet_VelocityY();
        Ball_Width = level.getBall_Width();
        Ball_Height = level.getBall_Height();
        Walls_X1 = level.getWalls_X1();
        Walls_X2 = level.getWalls_X2();
        Walls_Y = level.getWalls_Y();
        Walls_maxX = level.getWalls_maxX();
        Walls_maxY = level.getWalls_maxY();
        Ball_startX = level.getBall_startX();
        Ball_startY = level.getBall_startY();
    }
    public int[] makeArray(){

         array= new int[30];
         array[0]= Window_width;
         array[1]= Window_height;
         array[2]= Block_size ;
        array[3]= Block_width;
        array[4]= Block_height;
        array[5]= Player_position_x;
        array[6]= PLayer_position_y;
        array[7]= PLayer_width;
        array[8]= Player_height;
        array[9]= Bullet_width;
        array[10]= Bullet_height;
        array[11]= Wall_height;
        array[12]= Wall_width;
        array[13]= Max_velocity;
        array[14]= Level_Number;
        array[15]= Number_of_Balls;
        array[16]= Ball_VelocityX;
        array[17]= Ball_VelocityY;
        array[18]= Player_VelocityX;
        array[19]= Player_VelocityJump;
        array[20]= Bullet_VelocityY;
        array[21]= Ball_Width;
        array[22]= Ball_Height;
        array[23]= Walls_X1;
        array[24]= Walls_X2;
        array[25]= Walls_Y;
        array[26]= Walls_maxX;
        array[27]= Walls_maxY;
        array[28]= Ball_startX;
        array[29]= Ball_startY;
        return array;


    }

    /**
     *
     * function sending array of variables
     * @throws IOException
     */

    public void Sending() throws IOException {
        System.out.println("Client connected");

        ObjectOutputStream op = new ObjectOutputStream(socket.getOutputStream());
        op.writeObject(makeArray());
        op.flush();
    }

    /**
     * function receiving level number
     * @param socket
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public void RecivingtoSwap(Socket socket) throws IOException, ClassNotFoundException {

        ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
        Level_Number=(int)in.readObject();
        Ballsize=(int)in.readObject();
    }

    /**
     * function switches levels
     *
     */

    public void SwapLevels() {
        try{
            RecivingtoSwap(this.socket);
        }catch(IOException e){
            System.out.println("Brak połączenia");
        }catch(ClassNotFoundException e) {
            System.out.println("Brak klasy");
        }
        if(Ballsize==0) {
                    try {
                        level = new ServerLevel(Level_Number);
                        System.out.println(Level_Number);
                        setVariables();
                        System.out.println(Level_Number);
                        Sending();
                    } catch (FileNotFoundException e) {
                        System.out.println("Bład wczytywania");
                    } catch (IOException e) {
                        System.out.println("Bład połączenia");
                    }
        }
    }
    public void RestartLevel(){
        try {
            level = new ServerLevel(Level_Number);
            setVariables();
            ObjectOutputStream op = new ObjectOutputStream(socket.getOutputStream());
            op.writeObject(makeArray());
            op.flush();
        } catch (FileNotFoundException e) {
            System.out.println("Bład wczytywania");
        } catch (IOException e) {
            System.out.println("Bład połączenia");
        }
    }

    public int getWalls_X2() {
        return Walls_X2;
    }
}
