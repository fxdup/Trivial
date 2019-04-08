/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.FileNotFoundException;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 *
 * @author guill
 */

public class Game extends Pane{
    int resolution;
    double resfactor;
    public Game(double sound, int resolution,double resfactor) throws FileNotFoundException{
        this.resolution=resolution;
        this.resfactor=resfactor;
        Menu menu = new Menu(sound,resolution,resfactor);
        
        getChildren().add(menu);
    }
    
    public void startGame(boolean host,Player localPlayer){
        getChildren().clear();
        GameInterface gameInterface = new GameInterface(host,resfactor,localPlayer);
        getChildren().add(gameInterface);
    }
    
    public void leaderboard(Player[] playerList){
    getChildren().clear();
        Leaderboard leaderboard=new Leaderboard(playerList);
        getChildren().add(leaderboard);
    }
}
