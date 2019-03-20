/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Félix Dupont
 */
public class Trivial extends Application{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Game game = new Game();
        
        Scene scene = new Scene(game,1920,1080);
        Font.loadFont(getClass().getResourceAsStream("/Resources/EraserDust.ttf"), 14);
        scene.getStylesheets().add(getClass().getResource("/Resources/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Elementary Quiz");
        stage.show();
    }

}
