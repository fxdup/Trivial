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

/**
 *
 * @author Félix Dupont
 */
public class HostPlayer extends Player{
    private ServerSocket serverSocket;
    private ArrayList<Socket> connectedSockets;
    private ArrayList<DataOutputStream> output;
    private ArrayList<DataInputStream> input;

    public HostPlayer(String name) {
        super(name);
        //thread for connecting
    }

    
    
    @Override
    public void sendData(String name,int score,int grade) throws IOException {
        for(DataOutputStream i: output){
            i.writeUTF(name);
            i.writeInt(score);
            i.writeInt(grade);
        }
    }
    
    
    


    
}
