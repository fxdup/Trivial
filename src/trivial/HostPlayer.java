package trivial;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class HostPlayer extends Player implements Serializable {

    private transient ServerSocket serverSocket = new ServerSocket(0);
    private transient ArrayList<Socket> connectedSockets;//list of sockets that are connected with other players
    private transient ArrayList<ObjectOutputStream> outputs;//list of outputs related to the other players
    private boolean connecting = true;
    private transient Game game;

    public HostPlayer(String name,Game game) throws IOException {
        super(name, 0);
        this.game=game;
        super.addPlayer(this);
        connectedSockets = new ArrayList<>();
        outputs = new ArrayList<>();
        new Thread(new Connection()).start();
    }

    public void sendStart() {
        try {
            for (int i = 0; i < outputs.size(); i++) {
                outputs.get(i).writeUnshared("start");
            }
            sendPlayerList();
        } catch (IOException ex) {
            Logger.getLogger(HostPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //sends the player's information to the other players connected
    public void sendData(Player player) throws IOException {
        for (int i = 0; i < outputs.size(); i++) {
            if (i + 1 != player.getId()) {
                outputs.get(i).writeUnshared(player);
            }
        }
    }

    public void sendPlayerList() throws IOException {
        sendData(this);
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

    public void stopConnecting() {
        connecting = false;
    }

    @Override
    public int getPlayerSize() {
        return connectedSockets.size();
    }

    //creates the link between the players connected and the host
    class Connection implements Runnable {

        @Override
        public void run() {
            try {

                System.out.println("IP:" + getIp() + "\nPort: " + getPort());

                //until there are 40 players connected or the game starts, waits for other players to connect
                while (connectedSockets.size() < 40 && connecting) {

                    Socket socket = serverSocket.accept();
                    connectedSockets.add(socket);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    outputs.add(output);
                    new Thread(new DataReceiver(socket, output)).start();

                }
                serverSocket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    //manages the input from an other player
    public class DataReceiver implements Runnable {

        Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        Player player;
        boolean read=true;

        DataReceiver(Socket socket, ObjectOutputStream output) {
            this.socket = socket;
            this.output = output;
        }

        @Override
        public void run() {
            try {
                input = new ObjectInputStream(socket.getInputStream());
                output.writeInt(outputs.indexOf(output) + 1);
                while (read) {
                    Object o = input.readObject();
                    if (o instanceof Player) {
                        player= (Player) o;
                        addPlayer(player);
                        sendData(player);
                        if(game.isPlaying())
                            ((GameInterface)game.getChildren().get(0)).updateScore();
                    }
                }
            } catch (java.net.SocketException ex) {
                read=false;
                outputs.remove(outputs.indexOf(output));
                connectedSockets.remove(socket);
                if(game.isPlaying()){
                if(connectedSockets.size()<1){
                    Platform.runLater(()->{

                            try {
                                game.disconnected(getThis());
                            } catch (FileNotFoundException ex1) {
                                Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                    });
                }
                }
                
            }catch (IOException ex) {
                Logger.getLogger(ClientPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (ClassNotFoundException ex) {
                Logger.getLogger(HostPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
