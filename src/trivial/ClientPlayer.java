/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
        new Thread(new DataReceiver(connectedSocket)).start();
    }

    public void sendData() throws IOException {
        output.writeUTF(this.getName());
        output.writeInt(this.getScore());
        output.writeInt(this.getGrade());
    }

    public void connect(String ip, int port) throws IOException {
        connectedSocket = new Socket(ip, port);
        input = new DataInputStream(connectedSocket.getInputStream());
        
        output.writeUTF(this.getName());
    }

}
