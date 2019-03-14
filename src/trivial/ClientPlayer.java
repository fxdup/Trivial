/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Félix Dupont
 */
public class ClientPlayer extends Player {

    private Socket connectedSocket;//sokect that is connected with the host
    private ObjectOutputStream output;//output to the host

    public ClientPlayer(String name) throws IOException {
        super(name);
    }

    //sends the player's information to the host
    public void sendData() throws IOException {
        output.writeObject(this);
    }

    //connects to the host with an IP and port
    public void connect(String ip, int port) throws IOException {
        connectedSocket = new Socket(ip, port);
        output = new ObjectOutputStream(connectedSocket.getOutputStream());
        new Thread(new DataReceiver(connectedSocket));
    }

    //manages the input from the host
    public class DataReceiver implements Runnable {

        Socket socket;
        ObjectInputStream input;

        DataReceiver(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                input = new ObjectInputStream(socket.getInputStream());
                setId(input.readInt());
                while (true) {

                    Player player = (Player) input.readObject();

                    //element.updatescore(player);
                    if (player.getScore() > 1000) {
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HostPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
