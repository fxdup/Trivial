package trivial;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.scene.layout.Pane;

public class Scoreboard extends Pane {

    private Player[] players;

    public Scoreboard(Player[] players) {
        players = new Player[players.length];
        System.arraycopy(players, 0, this.players, 0, players.length);
    }

    //creates a text file containing the scores and information of all the players in the game
    public void exportScore() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("Quiz_" + new java.util.Date().toString());
        for (Player i : players) {
            pw.print(i.toString());
            pw.println();
        }

    }
}
