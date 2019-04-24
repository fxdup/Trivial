/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Félix Dupont
 */
public class ClientPlayer extends Player {

    private transient Socket connectedSocket;//sokect that is connected with the host
    private transient ObjectOutputStream output;//output to the host
    private transient Game game;

    public ClientPlayer(String name, Game game) throws IOException {
        super(name);
        this.game=game;
    }

    //sends the player's information to the host
    public void sendData() throws IOException {
        output.writeUnshared(((Player)this));
    }

    //connects to the host with an IP and port
    public void connect(String ip, int port) throws IOException {
        connectedSocket = new Socket(ip, port);
        output = new ObjectOutputStream(connectedSocket.getOutputStream());
        new Thread(new DataReceiver(connectedSocket)).start();
    }
    
    public void addPlayer(Player p){
    super.addPlayer(p);
    }

    
    //manages the input from the host
    public class DataReceiver implements Runnable {

        Socket socket;
        ObjectInputStream input;
        private boolean read=true;

        DataReceiver(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                input = new ObjectInputStream(socket.getInputStream());
                setId(input.readInt());
                sendData();
                while (read) {
                    Object o = input.readObject();
                    if(o instanceof Player){
                    Player player = (Player) o;
                    addPlayer(player);
                    if(game.isPlaying())
                    ((GameInterface)game.getChildren().get(0)).updateScore();
                    }
                    else if(o instanceof String){Platform.runLater(()->{
                            game.startGame(false,getThis());
                            
                    });
                    }
                }
            }
                catch (java.net.SocketException | java.io.EOFException ex ) {
                
                        read=false;
                        Platform.runLater(()->{

                            try {
                                game.disconnected(getThis());
                            } catch (FileNotFoundException ex1) {
                                Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                    });
                
            } catch (IOException ex) {
                Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HostPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
