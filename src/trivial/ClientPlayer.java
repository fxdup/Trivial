/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Félix Dupont
 */
public class ClientPlayer extends Player {

    private Socket connectedSocket;
    private DataInputStream input;
    private DataOutputStream output;

    public ClientPlayer(String name, String ip, int port) throws IOException {
        super(name);
        connect(ip, port);
        
    }

    public void sendData() throws IOException {
        output.writeUTF(this.getName());
        output.writeInt(this.getScore());
        output.writeInt(this.getGrade());
    }

    public void connect(String ip, int port) throws IOException {
        connectedSocket = new Socket(ip, port);
        input = new DataInputStream(connectedSocket.getInputStream());
        new Thread(new DataReceiver(connectedSocket));
    }
    
    
public class DataReceiver implements Runnable {
        Socket socket;
        ObjectInputStream input;
        DataReceiver(Socket socket){
        this.socket=socket;
        }
        
        @Override
        public void run() {
            try {
            input=new ObjectInputStream(socket.getInputStream());
            
            while (true) {
                
                    Player player=(Player)input.readObject();
                    
                    //element.updatescore(player);
                    
                    if (player.getScore() > 1000) {
                        break;
                    }
                }} catch (IOException ex) {
                    Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(HostPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
    }
}
