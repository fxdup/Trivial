/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author guill
 */

public class Game extends StackPane{
    int resolution;//stores an integer value (1,2,3,4) each associated with a different resolution
    double resfactor;//a double value that represent a certain fraction (1/2,2/3,3/4)
    double sound;//stores a double value from 0 to 100 for the sound of the game
    Trivial main;//an instance of the Trivial class
    private String state;//a string that describes the state of the game
    
    //constructor of a Game instance
    public Game(double sound, int resolution,double resfactor,Trivial main) throws FileNotFoundException{
        this.resolution=resolution;
        this.resfactor=resfactor;
        this.sound=sound;
        this.main=main;
        menu();
    }
    
    //method that generates the menu of the game
    public void menu() throws FileNotFoundException{
    state="menu";
    getChildren().clear();
    Menu menu = new Menu(sound,resolution,resfactor,main);
    getChildren().add(menu);
        
    }
    
    //method that brings the player back to the menu
    public void backToMenu() throws FileNotFoundException{
        getChildren().clear();
        Menu menu = new Menu(sound,resolution,resfactor,main);
        getChildren().add(menu);
    }
    
    //method that starts the game
    public void startGame(boolean host,Player localPlayer){
        getChildren().clear();
        GameInterface gameInterface = new GameInterface(host,resfactor,localPlayer,sound);
        getChildren().add(gameInterface);
        state="playing";
    }
    
    //method that generates the leaderboard of the game
    public void leaderboard(Player[] playerList){
        state="leaderboard";
        getChildren().clear();
        Leaderboard leaderboard=new Leaderboard(playerList, resfactor,sound);
        getChildren().add(leaderboard);
    }
    public String getState(){
    return state;
    }
    
    //method called in case the host disconnects
    public void disconnected(Player player){
        VBox textBox = new VBox();
        StackPane textDisplay=new StackPane();
        Rectangle background =new Rectangle(1920*resfactor,1080*resfactor,Color.BLACK);
        background.setOpacity(0.7);
        Text message =new Text("Connection Lost");
        Text back = new Text();
        message.getStyleClass().addAll("menu");
        message.setStyle("-fx-font: "+120*resfactor+"px EraserDust;");
        back.getStyleClass().addAll("menu","redHover");
        back.setStyle("-fx-font: "+120*resfactor+"px EraserDust;");
        back.setOnMouseClicked(e->{
        getChildren().clear();
        if(state.equals("playing")){
        leaderboard(player.getPlayers());
        }else{
            try {
                menu();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        textBox.getChildren().addAll(message,back);
        textDisplay.getChildren().addAll(background,textBox);
        
        
        if(state.equals("playing")){
        ((GameInterface)getChildren().get(0)).stop();
        back.setText("Go to leaderboard");
        }else{
        back.setText("Back to menu");
        }
        
        getChildren().addAll(textDisplay);
        textBox.setAlignment(Pos.CENTER);
    }
}
