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
        new Thread(new dataReceiver()).start();
    }

    @Override
    public void sendData(String name, int score, int grade) throws IOException {
        output.writeUTF(name);
        output.writeInt(score);
        output.writeInt(grade);
    }

    public void connect(String ip, int port) throws IOException {
        connectedSocket = new Socket(ip, port);
        input = new DataInputStream(connectedSocket.getInputStream());
        output = new DataOutputStream(connectedSocket.getOutputStream());
        output.writeUTF(this.getName());
    }

    class dataReceiver implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String name = input.readUTF();
                    int score = input.readInt();
                    int grade = input.readInt();
                    //element.updatescore(name,score,grade);
                    if (score > 1000) {
                        break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
}
