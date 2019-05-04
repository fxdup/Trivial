package trivial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Leaderboard extends VBox {

    private Player[] players;
    private double resfactor;
    Text exportText;
    private boolean exported = false;
    AudioClip click;
            
    public Leaderboard(Player[] player, double resfactor,double sound){
        
        click = new AudioClip(new File("src/Resources/Sounds/Click.wav").toURI().toString());
        click.setVolume(sound/100);
        this.resfactor=resfactor;
        this.players = new Player[player.length];
        System.arraycopy(player, 0, this.players, 0, player.length);
        
        exportText = new Text("Export as \n text file");
        exportText.setStyle("-fx-font: "+65*resfactor+"px EraserDust;-fx-fill: white");
        Text mainMenuText = new Text("Main \n menu");
        mainMenuText.setStyle("-fx-font: "+65*resfactor+"px EraserDust;-fx-fill: white");
        Rectangle exportTxt_rectangle = new Rectangle(443*resfactor,167*resfactor);
        Rectangle mainMenu_rectangle = new Rectangle(443*resfactor,167*resfactor);
        exportTxt_rectangle.setStroke(Color.BLACK);
        exportTxt_rectangle.setStrokeWidth(5*resfactor);
        exportTxt_rectangle.setFill(Color.rgb(96, 139, 109));
        mainMenu_rectangle.setStroke(Color.BLACK);
        mainMenu_rectangle.setStrokeWidth(5*resfactor);
        mainMenu_rectangle.setFill(Color.rgb(96, 139, 109));
        StackPane exportTxtStack = new StackPane();
        StackPane mainMenuStack = new StackPane();
        exportTxtStack.getChildren().addAll(exportTxt_rectangle,exportText);
        mainMenuStack.getChildren().addAll(mainMenu_rectangle,mainMenuText);
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(80*resfactor);
        buttons.getChildren().addAll(exportTxtStack,mainMenuStack);
        
        Pane podium = new Pane();
        switch(players.length){
            case 1:{
                Rectangle first_rectangle = new Rectangle(170*resfactor,650*resfactor);
                first_rectangle.setFill(players[0].getColor());
                podium.getChildren().addAll(first_rectangle);
                Text first_text = new Text(first_rectangle.getX(),first_rectangle.getY()-10,players[0].getName());
                first_text.setStyle("-fx-font: "+30*resfactor+"px EraserDust;");
                podium.getChildren().addAll(first_text);
                
                break; 
            }
            case 2:{
                Rectangle first_rectangle = new Rectangle(170*resfactor,650*resfactor);
                first_rectangle.setFill(players[0].getColor());
                Rectangle second_rectangle = new Rectangle(170*resfactor,0,170*resfactor,650*resfactor/1000*players[1].getScore());
                second_rectangle.setFill(players[1].getColor());
                second_rectangle.setY(second_rectangle.getY()+650*resfactor-second_rectangle.getHeight());
                podium.getChildren().addAll(second_rectangle,first_rectangle);
                Text first_text = new Text(first_rectangle.getX(),first_rectangle.getY()-10*resfactor,players[0].getName());
                Text second_text = new Text(second_rectangle.getX(),second_rectangle.getY()-10*resfactor,players[1].getName());
                first_text.setStyle("-fx-font: "+30*resfactor+"px EraserDust;");
                second_text.setStyle("-fx-font: "+30*resfactor+"px EraserDust;");
                podium.getChildren().addAll(first_text,second_text);
                break;
            }
            default:{
                Rectangle first_rectangle = new Rectangle(170*resfactor,650*resfactor);
                first_rectangle.setFill(players[0].getColor());
                Rectangle second_rectangle = new Rectangle(170*resfactor,0,170*resfactor,650*resfactor/1000*players[1].getScore());
                second_rectangle.setFill(players[1].getColor());
                second_rectangle.setY(second_rectangle.getY()+650*resfactor-second_rectangle.getHeight());
                Rectangle third_rectangle = new Rectangle(170*2*resfactor,0,170*resfactor,650*resfactor/1000*players[2].getScore());
                third_rectangle.setFill(players[2].getColor());
                third_rectangle.setY(third_rectangle.getY()+650*resfactor-third_rectangle.getHeight());
                podium.getChildren().addAll(second_rectangle,first_rectangle,third_rectangle);
                Text first_text = new Text(first_rectangle.getX(),first_rectangle.getY()-10*resfactor,players[0].getName());
                Text second_text = new Text(second_rectangle.getX(),second_rectangle.getY()-10*resfactor,players[1].getName());
                Text third_text = new Text(third_rectangle.getX(),third_rectangle.getY()-10*resfactor,players[2].getName());
                first_text.setStyle("-fx-font: "+30*resfactor+"px EraserDust;");
                second_text.setStyle("-fx-font: "+30*resfactor+"px EraserDust;");
                third_text.setStyle("-fx-font: "+30*resfactor+"px EraserDust;");
                podium.getChildren().addAll(first_text,second_text,third_text);
            }
        }
        GridPane listingNames = new GridPane();
        listingNames.setHgap(600*resfactor);
        listingNames.setVgap(10*resfactor);
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(920*resfactor, 650*resfactor);
        scroll.setMaxHeight(620*resfactor);
        scroll.setStyle("-fx-stroke: black;");
        scroll.setContent(listingNames);
        Text[] names = new Text[players.length];
        Text[] scores = new Text[players.length];
        
        for(int i=0;i<players.length;i++){
            names[i] = new Text(players[i].getName());
            names[i].setStyle("-fx-font: "+45*resfactor+"px EraserDust;");
            scores[i] = new Text(Integer.toString(players[i].getScore()));
            scores[i].setStyle("-fx-font: "+45*resfactor+"px EraserDust;");
            listingNames.addRow(i, names[i],scores[i]);
        }
        
        exportTxtStack.setOnMouseClicked(eh->{try {
            click.play();
            exportScore();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Leaderboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        mainMenuStack.setOnMouseClicked(eh->{
            click.play();
            try {
                ((Game)(getParent())).backToMenu();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Leaderboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        HBox leaderBoard = new HBox();
        leaderBoard.setSpacing(200*resfactor);
        leaderBoard.getChildren().addAll(podium,scroll);
        leaderBoard.setAlignment(Pos.CENTER);
        this.setSpacing(85*resfactor);
        this.getChildren().addAll(leaderBoard,buttons);
        this.setAlignment(Pos.CENTER);
    }

    //creates a text file containing the scores and information of all the players in the game
    public void exportScore() throws FileNotFoundException {
        if(!exported){
            Date date = new Date();
            String filename = "Quiz_"+date.getDate()+"∕"+(date.getMonth()+1)+"∕"+(date.getYear()+1900)+"∕"+date.getHours()+"꞉"+date.getMinutes()+"꞉"+date.getSeconds()+".txt";
            try {
                PrintWriter writer = new PrintWriter("src/Leaderboards/"+filename);
                for (Player i : players) {
                    writer.print(i.toString());
                    writer.println();
                    writer.println();
                }
                writer.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Leaderboard.class.getName()).log(Level.SEVERE, null, ex);
            }
            exportText.setText("Done");
            exported=true;
        }
    }
}
