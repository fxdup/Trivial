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
import java.net.ServerSocket;
import java.net.Socket;
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

    public String getIp() {
        return serverSocket.getInetAddress().getHostAddress();
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    

    

    class Connection implements Runnable {
        
        @Override
        public void run() {
            try {
        ServerSocket serverSocket = new ServerSocket(7000);
            while (connectedSockets.size() < 40) {
                    
                    Socket socket = serverSocket.accept();
                    connectedSockets.add(socket);
                    ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
                    outputs.add(output);
                    new Thread(new DataReceiver(socket));
                }} catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        
    }



