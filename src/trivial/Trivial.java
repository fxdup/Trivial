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
    double sound;
    double resfactor;
    int resolution;
    Game game;
    @Override
    public void start(Stage stage) throws Exception {
        try{
            File opt = new File("opt.txt");
            Scanner input = new Scanner(opt);
            sound=Double.parseDouble(input.next());
            resolution=input.nextInt();
            switch(resolution){
            case 1: this.resfactor=1;break;
            case 2: this.resfactor=(0.75);break;
            case 3: this.resfactor=(0.6666666);break;
            case 4: this.resfactor=(0.5);break;
        }
        
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
            PrintWriter writer = new PrintWriter("opt.txt");
            writer.println(sound);
            writer.println(resolution);
            writer.close();
        }
        
        game = new Game(sound,resolution,resfactor);
        
        Scene scene = new Scene(game,1920*resfactor,1080*resfactor);
        Font.loadFont(getClass().getResourceAsStream("/Resources/EraserDust.ttf"), 14);
        scene.getStylesheets().add(getClass().getResource("/Resources/Style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Elementary Quiz");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent t) {
        Platform.exit();
        System.exit(0);
    }
});
    }
    public static void restart() throws FileNotFoundException{
        System.exit(0);
    }
}
