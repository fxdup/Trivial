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

/**
 *
 * @author Félix Dupont
 */
public class ClientPlayer extends Player{
    private Socket connectedSocket;
    private DataInputStream input;
    private DataOutputStream output;

    public ClientPlayer(String name) {
        super(name);
        //thread
    }

    @Override
    public void sendData(String name, int score, int grade) throws IOException {
        output.writeUTF(name);
        output.writeInt(score);
        output.writeInt(grade);
    }
    
}
