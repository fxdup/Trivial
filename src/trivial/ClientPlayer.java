package trivial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class ClientPlayer extends Player implements Serializable {

    private transient Socket connectedSocket;//socket that is connected with the host
    private transient ObjectOutputStream output;//output to the host
    private transient Game game;//Instance of the game(control between menu, game interface and leaderboard) to be able to use some of its commands

    //Creates a client player. Client players have an Id of -1 at the begining
    public ClientPlayer(String name, Game game) throws IOException {
        super(name);
        this.game = game;
    }

    //sends the player's information to the host
    public synchronized void sendData() throws IOException {
        try{
        output.writeObject(((Player) this));
        output.reset();
        }catch(java.net.SocketException ex){
        }
        }

    //connects to the host with an IP and port and creates the connection points
    public void connect(String ip, int port) throws IOException {
        connectedSocket = new Socket(ip, port);
        output = new ObjectOutputStream(connectedSocket.getOutputStream());
        DataReceiver communicationThread = new DataReceiver(connectedSocket);
        communicationThread.start();
    }

    //manages the information received from the host
    public class DataReceiver extends Thread {

        Socket socket;//The socket connected to the host
        ObjectInputStream input;//The input coming from the host
        Object o;//Object received

        DataReceiver(Socket socket) {
            
                this.socket = socket;
                
        }

        @Override
        public void run() {
            try {
                input = new ObjectInputStream(socket.getInputStream());
                setId(input.readInt()); //First information received from the host is the Id of the player
                sendData(); //Sends itself to the host with the new Id
            while (true) {
                    o = input.readObject();
                 
                if (o instanceof Player) {
                    Player player = ((Player) o).clone();
                    updatePlayer(player);
                    if (game.getState().equals("playing")&&player.getId()!=-1) {
                        if(game.getChildren().get(0) instanceof GameInterface)
                        Platform.runLater(() -> {
                        ((GameInterface) game.getChildren().get(0)).updateScore();//Updates the score in the GUI
                        });
                    }
                } //only string received from the host is to start the game
                else if (o instanceof String) {
                    System.out.println(getPlayerSize());
                    Platform.runLater(() -> {
                        game.startGame(false, getThis());
                    });
                }
                
            }
                } //if the connection between the host and the client is lost, sends the player to either the main menu or the leaderboard
                catch (java.net.SocketException | java.io.EOFException ex) {
                try {
                    output.close();
                } catch (IOException ex1) {
                    Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex1);
                }
                    if (game.getState().equals("playing") || game.getState().equals("menu")) {
                        Platform.runLater(() -> {
                            game.disconnected(getThis());
                        });
                    }

                }
             catch (IOException ex) {
                Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
