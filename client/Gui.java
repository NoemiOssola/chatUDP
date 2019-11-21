/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client1;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author pc15
 */
public class Gui extends JFrame implements ActionListener {
    JPanel p;
    JLabel i;
    JTextField input;
    JTextArea output;
    JScrollPane sjp;
    JButton invia;
    JButton inizia;

    public Gui() {
        this.setBounds(500,200,200,500);
        this.setTitle("CHAT");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        this.i = new JLabel("Inserisci messaggio");
        i.setEnabled(false);
        this.input = new JTextField();
        this.output = new JTextArea();
        this.sjp = new JScrollPane(output);
        this.invia = new JButton("invia");
        invia.addActionListener(this);
        this.inizia = new JButton("inizia");
        inizia.addActionListener(this);
        
        this.add(p);
        p.add(i);
        p.add(input);
        p.add(inizia);
        p.add(invia);
        p.add(sjp);
        
        
        this.pack();
        this.setVisible(true);
    }
    
     String username;

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("inizia")){
             username = JOptionPane.showInputDialog(p,
            "Inserisci user name", "Settaggio...", JOptionPane.ERROR_MESSAGE);
         output.append("User: "  + username + "\n");
        }
        if(ae.getActionCommand().equals("invia")){
            try {
                String i = input.getText();
                output.setText(username + "> " + i);
                try {
                    this.Invia(i);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.Ricezione();
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    


public void Invia(String messaggio) throws SocketException, UnknownHostException, IOException{
    DatagramSocket socket = new DatagramSocket();
    InetAddress ind = InetAddress.getByName("localhost");
    int UDP_port = 1088;
    byte[] buffer = new byte[100];
    buffer = messaggio.getBytes();
    DatagramPacket userDatagram = new DatagramPacket(buffer, buffer.length, ind, UDP_port);
    socket.send(userDatagram);  
    JOptionPane.showMessageDialog(p, "Ho inviato il messaggio", "Send", JOptionPane.ERROR_MESSAGE);
}

public void Ricezione() throws SocketException, IOException{
    DatagramSocket socket = new DatagramSocket(1088);
    String utente = username;
    byte[] buffer = new byte[100];
    String received;
    DatagramPacket serverDatagram = new DatagramPacket(buffer, buffer.length);
            serverDatagram = new DatagramPacket(buffer, buffer.length);
            while (!Thread.interrupted()){
                socket.receive(serverDatagram);
                received = new String(serverDatagram.getData(), 0, serverDatagram.getLength(), "ISO-8859-1");
                output.setText(username + "> " + received);     
}
}

}