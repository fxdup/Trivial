/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
/**
 *
 * @author guill
 */

public class GameInterface extends Pane{
    private Player me;
    private String firstPlace;
    private String currentGrade;
    private String question;
    private int score;
    private final int red = (int)(Math.random()*200+50);
    private final int green = (int)(Math.random()*200+50);
    private final int blue = (int)(Math.random()*200+50);
    private boolean paused;
    private final int HEIGHT=1080;
    private final int WIDTH=1920;
    
    public GameInterface(){
        
        Rectangle answer1_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50);
        Text answer1_text = new Text("answer1");
        StackPane answer1 = new StackPane();
        answer1.getChildren().addAll(answer1_rectangle,answer1_text);
        answer1.setLayoutX(0);
        answer1.setLayoutY(HEIGHT/2+50);
        
        Rectangle answer2_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50);
        Text answer2_text = new Text("answer2");
        StackPane answer2 = new StackPane();
        answer2.getChildren().addAll(answer2_rectangle,answer2_text);
        
        Rectangle answer3_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50);
        Text answer3_text = new Text("answer3");
        StackPane answer3 = new StackPane();
        answer3.getChildren().addAll(answer3_rectangle,answer3_text);
        
        Rectangle answer4_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50);
        Text answer4_text = new Text("answer4");
        StackPane answer4 = new StackPane();
        answer4.getChildren().addAll(answer4_rectangle,answer4_text);
        
        answer1_rectangle.setFill(Color.rgb(96, 139, 109));
        answer2_rectangle.setFill(Color.rgb(96, 139, 109));
        answer3_rectangle.setFill(Color.rgb(96, 139, 109));
        answer4_rectangle.setFill(Color.rgb(96, 139, 109));
        
        Rectangle leaderbar = new Rectangle(0,25,WIDTH,18);
        leaderbar.setFill(Color.WHITE);
        Rectangle fillingbar = new Rectangle(0,25,WIDTH/2,18);
        fillingbar.setFill(Color.rgb(red,green,blue));
        
        Text first_place = new Text("First Place: "+firstPlace);
        first_place.setStyle("-fx-font: 30px EraserDust;-fx-stroke: white;-fx-fill: white;");
        Text current_grade = new Text("Grade "+currentGrade);
        current_grade.setStyle("-fx-font: 30px EraserDust;-fx-stroke: white;-fx-fill: white;");
        Text your_score = new Text("Your Score: "+score);
        your_score.setStyle("-fx-font: 30px EraserDust;-fx-stroke: white;-fx-fill: white;");
        
        first_place.setY(72);
        first_place.setX(WIDTH-360);
        current_grade.setY(72);
        current_grade.setX(WIDTH/2-50);
        your_score.setY(72);
        your_score.setX(5);
        
        Rectangle black = new Rectangle(0,0,WIDTH,HEIGHT);
        black.setFill(Color.BLACK);
        
        ImageView separation = new ImageView(new Image("file:ChalkLine.png"));
        separation.setFitWidth(WIDTH);
        separation.setFitHeight(HEIGHT/2-100);
        separation.setX(0);
        separation.setY(HEIGHT/2+50);
        
        Rectangle white_question = new Rectangle(50,250,WIDTH-100,200);
        white_question.setFill(Color.WHITE);
        
        Rectangle timerbar = new Rectangle(0,HEIGHT/2+25,WIDTH,30);
        timerbar.setFill(Color.WHITE);
        
        Rectangle skip_button = new Rectangle();
        skip_button.setHeight(40);
        skip_button.setWidth(100);
        skip_button.setFill(Color.WHITE);
        skip_button.setStroke(Color.RED);
        skip_button.setStrokeWidth(5);
        
        Text skip_text = new Text("Skip");
        StackPane skip = new StackPane();
        skip.setLayoutX(WIDTH/2-50);
        skip.setLayoutY(HEIGHT/2+18);
        skip.getChildren().addAll(skip_button,skip_text);
        getChildren().addAll(black,answer1,separation,leaderbar,fillingbar,your_score,current_grade,first_place,white_question,timerbar,skip);
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void updateScore() {
        
    }

    public void answer() {
    }

    public void generateQuestion() {
    }
}