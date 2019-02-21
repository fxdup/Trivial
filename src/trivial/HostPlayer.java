/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Félix Dupont
 */
public class HostPlayer extends Player {

    private ServerSocket serverSocket;
    private ArrayList<Socket> connectedSockets;
    private ArrayList<DataOutputStream> output;
    private ArrayList<DataInputStream> input;

    public HostPlayer(String name) throws IOException {
        super(name);
        ServerSocket serverSocket=new ServerSocket(0);
    }

    @Override
    public void sendData(String name, int score, int grade) throws IOException {
        for (DataOutputStream i : output) {
            i.writeUTF(name);
            i.writeInt(score);
            i.writeInt(grade);
        }
    }
    
    public String getIp(){
    return serverSocket.getInetAddress().getHostAddress();
    }
    public int getPort(){
    return serverSocket.getLocalPort();
    }
    
    class Connection implements Runnable{
        
        @Override
        public void run() {
            while(connectedSockets.size()<40){
                try {
                    connectedSockets.add(serverSocket.accept());
                } catch (IOException ex) {
                    Logger.getLogger(HostPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
        }
    }

}
