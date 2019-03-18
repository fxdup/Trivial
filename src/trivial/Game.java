/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 *
 * @author guill
 */
public class Game extends Pane{
    
    public Game(){
        Menu menu = new Menu();
        
        getChildren().add(menu);
    }
    
    public void startGame(boolean host){
        getChildren().clear();
        GameInterface gameInterface = new GameInterface(host);
        getChildren().add(gameInterface);
    }
}
