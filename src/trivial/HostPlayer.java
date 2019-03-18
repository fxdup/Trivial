package trivial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HostPlayer extends Player {

    private ServerSocket serverSocket = new ServerSocket(0);
    private ArrayList<Socket> connectedSockets;//list of sockets that are connected with other players
    private ArrayList<ObjectOutputStream> outputs;//list of outputs related to the other players

    public HostPlayer(String name) throws IOException {
        super(name);
        connectedSockets = new ArrayList<Socket>();
        outputs = new ArrayList<ObjectOutputStream>();
        new Thread(new Connection()).start();

    }

    //sends the player's information to the other players connected
    public void sendData(Player player) throws IOException {
        for (int i = 0; i < outputs.size(); i++) {
            if (i + 1 != player.getId()) {
                outputs.get(i).writeObject(player);
            }
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

    //creates the link between the players connected and the host
    class Connection implements Runnable {

        @Override
        public void run() {
            try {
                
                System.out.println("IP:" + getIp() + "\nPort: " + getPort());

                //until there are 40 players connected or the game starts, waits for other players to connect
                while (connectedSockets.size() < 40) {

                    Socket socket = serverSocket.accept();
                    connectedSockets.add(socket);
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeInt(connectedSockets.size());
                    outputs.add(output);
                    new Thread(new DataReceiver(socket));
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    //manages the input from an other player
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

                while (true) {

                    Player player = (Player) input.readObject();
                    sendData(player);
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
