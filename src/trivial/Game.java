/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author guill
 */

public class Game extends StackPane{
    int resolution;
    double resfactor;
    double sound;
    Trivial main;
    private boolean playing;
    
    public Game(double sound, int resolution,double resfactor,Trivial main) throws FileNotFoundException{
        this.resolution=resolution;
        this.resfactor=resfactor;
        this.sound=sound;
        this.main=main;
        menu();
    }
    
        public void menu() throws FileNotFoundException{
        playing=false;
        getChildren().clear();
        Menu menu = new Menu(sound,resolution,resfactor,main);
        getChildren().add(menu);
        
    }
    
    public void startGame(boolean host,Player localPlayer){
        getChildren().clear();
        GameInterface gameInterface = new GameInterface(host,resfactor,localPlayer);
        getChildren().add(gameInterface);
        playing=true;
    }
    
    public void leaderboard(Player[] playerList){
        playing=false;
        getChildren().clear();
        Leaderboard leaderboard=new Leaderboard(playerList, resfactor);
        getChildren().add(leaderboard);
    }
    public boolean isPlaying(){
    return playing;
    }
    
    public void disconnected(Player player) throws FileNotFoundException{
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
        if(playing){
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
        
        
        if(playing){
        ((GameInterface)getChildren().get(0)).stop();
        back.setText("Go to leaderboard");
        }else{
        back.setText("Back to menu");
        }
        
        getChildren().addAll(textDisplay);
        textBox.setAlignment(Pos.CENTER);
    }
}
