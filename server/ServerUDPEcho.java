package serverudpchat;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerUDPEcho {


    public static void main(String[] args) throws IOException, InterruptedException {
        int c;
        Thread thread;
        try {
            UDPEcho echoServer= new UDPEcho(1077);
            thread= new Thread(echoServer);
            thread.start();
            c=System.in.read();
            thread.interrupt();
            thread.join();
            System.out.println("Server");
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDPEcho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

   
