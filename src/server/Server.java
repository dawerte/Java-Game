package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * class making server
 *
 */

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss= new ServerSocket(4999);
        while(true) {
            Socket s = ss.accept();
            (new Thread(new ServerBody(s))).start();

        }
    }

}

