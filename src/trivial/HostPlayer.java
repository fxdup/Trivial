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
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Félix Dupont
 */
public class HostPlayer extends Player {

    private ServerSocket serverSocket;
    private ArrayList<Socket> connectedSockets;
    private ArrayList<ObjectOutputStream> outputs;

    public HostPlayer(String name) throws IOException {
        super(name);
        connectedSockets=new ArrayList<Socket>();
        outputs=new ArrayList<ObjectOutputStream>();
        new Thread(new Connection()).start();
        
    }

    public void sendData(Player player) throws IOException {
        for (int i=0;i<outputs.size();i++) {
            if(i+1!=player.getId())
            outputs.get(i).writeObject(player);
        }
    }

    public String getIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    
    class Connection implements Runnable {
        
        @Override
        public void run() {
            try {
        ServerSocket serverSocket = new ServerSocket(0);
                System.out.println("IP:" + getIp() + "\nPort: " + serverSocket.getLocalPort());
            while (connectedSockets.size() < 40) {
                    
                    Socket socket = serverSocket.accept();
                    connectedSockets.add(socket);
                    ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
                    outputs.add(output);
                    System.out.println("Connected");
                    new Thread(new DataReceiver(socket));
                }} catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
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
                    sendData(player);
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




