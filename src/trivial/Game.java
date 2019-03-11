/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import javafx.scene.layout.Pane;

/**
 *
 * @author guill
 */
public class Game extends Pane{
    public Game(){
        Menu menu = new Menu();
        getChildren().add(menu);
    }
    
    public void startGame(){
        getChildren().clear();
        GameInterface gameInterface = new GameInterface();
        getChildren().add(gameInterface);
    }
}
