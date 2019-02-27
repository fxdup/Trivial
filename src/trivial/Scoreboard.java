/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javafx.scene.layout.Pane;

/**
 *
 * @author Félix Dupont
 */
public class Scoreboard extends Pane {

    private Player[] players;

    public Scoreboard(Player[] players) {
        players=new Player[players.length];
        for(int i=0;i<players.length;i++){
        this.players[i]=players[i];
        }
    }

    public void exportScore() throws FileNotFoundException{
    PrintWriter pw=new PrintWriter("Quiz_"+new java.util.Date().toString());
        for(Player i: players){
            pw.print(i.toString());
            pw.println();
    }
    
    }
}
