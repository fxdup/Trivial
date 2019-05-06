package trivial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javafx.application.Platform;

public class HostPlayer extends Player implements Serializable {

    private transient ServerSocket serverSocket = new ServerSocket(0);//The server socket to create the link between the host and the client
    private transient ArrayList<Socket> connectedSockets;//list of sockets that are connected with other players
    private transient ArrayList<ObjectOutputStream> outputs;//list of outputs related to the other players
    private boolean connecting = true;//If players are still able to connect to the host
    private transient Game game;//Instance of the game(control between menu, game interface and leaderboard) to be able to use some of its commands
    boolean reading;//If the host is still reading the incomming data from the clients

    //Creates a host player. the host player always have th Id 0
    public HostPlayer(String name, Game game) throws IOException {
        super(name, 0);
        this.game = game;
        connectedSockets = new ArrayList<>();
        outputs = new ArrayList<>();
        new Connection().start();//Players are now able to connect to the host
    }

    //Sends the player list of the players in the game and sends a message to start the game to the clients 
    public synchronized void sendStart() {
        try {
            sendPlayerList();
            for (int i = 0; i < outputs.size(); i++) {
                outputs.get(i).writeObject("start");
                outputs.get(i).reset();
            }

        } catch (IOException ex) {
            System.out.println("Invalid output");
        }

    }

    //sends the given player's information to the other players connected
    public synchronized void sendData(Player player) throws IOException {
        for (int i = 0; i < outputs.size(); i++) {
            if (i + 1 != player.getId()) {
                outputs.get(i).writeObject(((Player) player));
                outputs.get(i).reset();
            }
        }
    }

    //sends the list of players to the players connected
    public synchronized void sendPlayerList() throws IOException {
        for (Player p : this.getPlayers()) {
            sendData(p);
        }
    }

    //returns the local ip address of the host
    public String getIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    //returns the port of the server socket
    public int getPort() {
        return serverSocket.getLocalPort();
    }

    //Stops players from being able to connect to the host
    public void stopConnecting() {
        connecting = false;
    }

    @Override//At the connection stage, the player list is based of the connected sockets since it the clients don't have an id yet
    public int getPlayerSize() {
        return connectedSockets.size()+1;
    }

    //Terminates the inputs coming from the clients
    public void stopInputs() throws IOException {
        stopConnecting();
        for (Socket socket : connectedSockets) {
            socket.close();
        }
        outputs.clear();
        connectedSockets.clear();
        reading = false;
    }

    //Enables the player to connect to the host and creates the link between them
    class Connection extends Thread {

        @Override
        public void run() {
            try {
                //until there are 40 players connected or the game starts, waits for other players to connect
                while (connectedSockets.size() < 40 && connecting) {

                    Socket socket = serverSocket.accept();
                    connectedSockets.add(socket);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    outputs.add(output);
                    DataReceiver communicationThread= new DataReceiver(socket, output);
                    communicationThread.start();
                }
                serverSocket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    //manages the input from an other player
    public class DataReceiver extends Thread {

        Socket socket;//Socket connected to a client
        ObjectInputStream input;//Input stream connected to a client
        ObjectOutputStream output;//Onput stream connected to a client
        Object o;//Object received

        DataReceiver(Socket socket, ObjectOutputStream output) {
                this.socket = socket;
                this.output = output;
                reading = true;
                
        }

        @Override
        public void run() {
            try {
                input = new ObjectInputStream(socket.getInputStream());
                output.writeInt(outputs.indexOf(output) + 1);//sends the id to the player
                while (reading) {
                    
                    o = input.readObject();
                    
                    if (o instanceof Player) {
                        Player player = ((Player) o).clone();
                        updatePlayer(player);sendData(player);//After receiving the player, it is sent to all the other clients
                        if (game.getState().equals("playing") && player.getId() != -1) {
                            sendData(player);//After receiving the player, it is sent to all the other clients
                            try{
                            Platform.runLater(() -> {
                            ((GameInterface) game.getChildren().get(0)).updateScore();//Updates the score in the GUI});
                            });
                            }catch(ClassCastException ex){
                            reading=false;
                            }
                        }
                    }
                }

                input.close();
                output.close();
                outputs.remove(output);
            
                
            } //if the connection between the host and all the clients is lost, sends the host to either the main menu or the leaderboard.
            catch (java.net.SocketException ex) {
                outputs.remove(output);
                connectedSockets.remove(socket);
                if (game.getState().equals("playing") || game.getState().equals("menu")) {
                    if (connectedSockets.size() < 1) {
                        Platform.runLater(() -> {
                            game.disconnected(getThis());
                        });
                    }
                }
                    }catch (IOException ex) {
                System.out.println("invalid input stream");
            } catch (ClassNotFoundException ex) {
                System.out.println("Invalid class");
            }
        }
    }
}
