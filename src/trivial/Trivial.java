/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    double sound=100;
    double resfactor=1;
    int resolution=1;
    Game game;
    Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        restart();
    }
    
    public void restart() throws FileNotFoundException{
        if(stage!=null)
        stage.close();
        stage=new Stage();
        try{
            File opt = new File("src/Resources/opt.txt");
            Scanner input = new Scanner(opt);
            sound=Double.parseDouble(input.next());
            resolution=input.nextInt();
            input.close();
            switch(resolution){
            case 1: this.resfactor=1;break;
            case 2: this.resfactor=(0.75);break;
            case 3: this.resfactor=(0.6666666);break;
            case 4: this.resfactor=(0.5);break;
            }
        
        }
        catch(FileNotFoundException e){
            PrintWriter writer = new PrintWriter("src/Resources/opt.txt");
            writer.println(sound);
            writer.println(resolution);
            writer.close();
        }
        
        game = new Game(sound,resolution,resfactor,this);
        
        Scene scene = new Scene(game,1920*resfactor,1080*resfactor);
        Font.loadFont(getClass().getResourceAsStream("/Resources/EraserDust.ttf"), 14);
        scene.getStylesheets().add(getClass().getResource("/Resources/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Trivial");
        stage.show();
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent t) {
        Platform.exit();
        System.exit(0);
    }
});
    }
}
