package trivial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Leaderboard extends VBox {

    private Player[] players;
    private double resfactor;

    public Leaderboard(Player[] player, double resfactor){
        this.resfactor=resfactor;
        this.players = new Player[player.length];
        System.arraycopy(player, 0, this.players, 0, player.length);
        
        Text text_text = new Text("Export as \n text file");
        Text image_text =  new Text("Export as \n image file");
        text_text.setStyle("-fx-font: 65px EraserDust;-fx-fill: white");
        image_text.setStyle("-fx-font: 65px EraserDust;-fx-fill: white");
        Rectangle exportTxt_rectangle = new Rectangle(443*resfactor,167*resfactor);
        Rectangle exportJpg_rectangle = new Rectangle(443*resfactor,167*resfactor);
        exportTxt_rectangle.setStroke(Color.BLACK);
        exportTxt_rectangle.setStrokeWidth(5*resfactor);
        exportJpg_rectangle.setStroke(Color.BLACK);
        exportJpg_rectangle.setStrokeWidth(5*resfactor);
        exportTxt_rectangle.setFill(Color.rgb(96, 139, 109));
        exportJpg_rectangle.setFill(Color.rgb(96, 139, 109));
        StackPane exportTxtStack = new StackPane();
        StackPane exportJpgStack = new StackPane();
        exportTxtStack.getChildren().addAll(exportTxt_rectangle,text_text);
        exportJpgStack.getChildren().addAll(exportJpg_rectangle,image_text);
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(80*resfactor);
        buttons.getChildren().addAll(exportTxtStack,exportJpgStack);
        
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
        VBox listingNames = new VBox();
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(920*resfactor, 650*resfactor);
        scroll.setMaxHeight(620*resfactor);
        scroll.setStyle("-fx-stroke: black;");
        scroll.setContent(listingNames);
        Text[] names = new Text[players.length];
        Text[] scores = new Text[players.length];
        HBox[] names_scores = new HBox[players.length];
        for(int i=0;i<players.length;i++){
            names[i] = new Text(players[i].getName());
            names[i].setStyle("-fx-font: "+45*resfactor+"px EraserDust;");
            scores[i] = new Text(Integer.toString(players[i].getScore()));
            scores[i].setStyle("-fx-font: "+45*resfactor+"px EraserDust;");
            names_scores[i] = new HBox();
            names_scores[i].getChildren().addAll(names[i],scores[i]);
            names_scores[i].setSpacing(100*resfactor);
            listingNames.getChildren().add(names_scores[i]);
        }
        
        
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
        try {
            File file=new File("Quiz_" + new java.util.Date().toString());
            PrintWriter pw = new PrintWriter(file);
        for (Player i : players) {
            pw.print(i.toString());
            pw.println();
            pw.println();
        }
        pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Leaderboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
