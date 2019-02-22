/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Félix Dupont
 */
public class Player implements Serializable{

    private final String NAME;
    private int id;
    private int score;
    private int grade;
    

    public Player(String name) {
        this.NAME = name;
        score = 0;
        grade = 0;
    }
    public Player(String name,int id){
        this.NAME = name;
        score = 0;
        grade = 0;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return NAME;
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
