package trivial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;

public class Leaderboard extends Pane {

    private Player[] players;

    public Leaderboard(Player[] players){
        players = new Player[players.length];
        System.arraycopy(players, 0, this.players, 0, players.length);
        try {
            exportScore();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Leaderboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //creates a text file containing the scores and information of all the players in the game
    public void exportScore() throws FileNotFoundException {
        File file=new File("Quiz_" + new java.util.Date().toString());
        PrintWriter pw = new PrintWriter(file);
        for (Player i : players) {
            pw.print(i.toString());
            pw.println();
            pw.println();
        }
        pw.close();
    }
}
