
package client1;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReceiveFromServerAndPrint implements Runnable {
    DatagramSocket socket;
    String utente;

    ReceiveFromServerAndPrint (DatagramSocket socket, String u) {
        this.socket = socket;
        this.utente = u;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[100];
        String received;
        DatagramPacket serverDatagram;

 
        try {
            // Costruisco il datagram per ricevere i pacchetti inviati dal server
            serverDatagram = new DatagramPacket(buffer, buffer.length);
            // fino a quando il main non interrompe il thread rimango in ascolto 
            while (!Thread.interrupted()){
                socket.receive(serverDatagram);  //attendo il prossimo pacchetto da server
                //converto in string il messaggio contenuto nel buffer
                received = new String(serverDatagram.getData(), 0, serverDatagram.getLength(), "ISO-8859-1");
                //e quindi scrivo su schermo il messaggio appena ricevuto
                System.out.println(utente + "> server: " + received);
                //scrivo anche il prompt nel caso utente voglia digitare un altro messaggio da inviare
                System.out.print("> ");
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ReceiveFromServerAndPrint.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReceiveFromServerAndPrint.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
