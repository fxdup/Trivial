/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
/**
 *
 * @author guill
 */

public class GameInterface extends Pane{
    private StackPane questionPane;
    private StackPane answer1;
    private StackPane answer2;
    private StackPane answer3;
    private StackPane answer4;
    private StackPane skip;
    
    private Player me;
    private boolean host;
    private String firstPlace;
    private String currentGrade;
    private String question;
    private int score;
    private QuestionList questionList;
    
    private Text first_place;
    private Text current_grade;
    private Text your_score;
    private Text answer1_text;
    private Text answer2_text;
    private Text answer3_text;
    private Text answer4_text;
    private Text text_question;
    private Text skip_text;
    
    private ImageView background;
    private Rectangle leaderbar;
    private Rectangle fillingbar;
    private Rectangle answer1_rectangle;
    private Rectangle answer2_rectangle;
    private Rectangle answer3_rectangle;
    private Rectangle answer4_rectangle;
    private Rectangle white_question;
    private Rectangle timerbar;
    private Rectangle skip_button;
    
    private final int red = (int)(Math.random()*200+50);
    private final int green = (int)(Math.random()*200+50);
    private final int blue = (int)(Math.random()*200+50);
    private boolean paused;
    private double resfactor;
    private double HEIGHT;
    private double WIDTH;
    int timerbar_red=0;
    int timerbar_green=255;
    private ImageView separation;

    public GameInterface(Boolean host,double resfactor){
        this.resfactor=resfactor;
        HEIGHT=1080*resfactor;
        WIDTH=1920*resfactor;
        System.out.println(HEIGHT);
        System.out.println(WIDTH);
        
        this.host=host;
        
        answer1_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50*resfactor);
        answer1_text = new Text("answer1");
        answer1_text.getStyleClass().add("inGameGUI");
        answer1 = new StackPane();
        answer1.getChildren().addAll(answer1_rectangle,answer1_text);
        answer1.setLayoutX(0);
        answer1.setLayoutY(HEIGHT/2+50*resfactor);
        
        answer2_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50*resfactor);
        answer2_text = new Text("answer2");
        answer2_text.getStyleClass().add("inGameGUI");
        answer2 = new StackPane();
        answer2.getChildren().addAll(answer2_rectangle,answer2_text);
        answer2.setLayoutX(WIDTH/2);
        answer2.setLayoutY(HEIGHT/2+50*resfactor);
        
        answer3_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50*resfactor);
        answer3_text = new Text("answer3");
        answer3_text.getStyleClass().add("inGameGUI");
        answer3 = new StackPane();
        answer3.getChildren().addAll(answer3_rectangle,answer3_text);
        answer3.setLayoutX(0);
        answer3.setLayoutY(HEIGHT/2+50*resfactor+(HEIGHT/4-50*resfactor));
        
        answer4_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50*resfactor);
        answer4_text = new Text("answer4");
        answer4_text.getStyleClass().add("inGameGUI");
        answer4 = new StackPane();
        answer4.getChildren().addAll(answer4_rectangle,answer4_text);
        answer4.setLayoutX(WIDTH/2);
        answer4.setLayoutY(HEIGHT/2+50*resfactor+(HEIGHT/4-50*resfactor));
        
        answer1_rectangle.setFill(Color.rgb(96, 139, 109));
        answer2_rectangle.setFill(Color.rgb(96, 139, 109));
        answer3_rectangle.setFill(Color.rgb(96, 139, 109));
        answer4_rectangle.setFill(Color.rgb(96, 139, 109));
        
        leaderbar = new Rectangle(0,25*resfactor,WIDTH,18*resfactor);
        leaderbar.setFill(Color.WHITE);
        fillingbar = new Rectangle(0,25*resfactor,WIDTH/2,18*resfactor);
        fillingbar.setFill(Color.rgb(red,green,blue));
        
        first_place = new Text("First Place: "+firstPlace);
        first_place.getStyleClass().add("inGameGUI");
        current_grade = new Text("Grade "+currentGrade);
        current_grade.getStyleClass().add("inGameGUI");
        your_score = new Text("Your Score: "+score);
        your_score.getStyleClass().add("inGameGUI");
        
        first_place.setY(72*resfactor);
        first_place.setX(WIDTH-360*resfactor);
        current_grade.setY(72*resfactor);
        current_grade.setX(WIDTH/2-50*resfactor);
        your_score.setY(72*resfactor);
        your_score.setX(5*resfactor);
        
        background = new ImageView(new Image("/Resources/background_image.jpg"));
        background.setFitWidth(1920*resfactor);
        background.setFitHeight(1080*resfactor);
        
        
        separation = new ImageView(new Image("file:ChalkLine.png"));
        separation.setFitWidth(WIDTH);
        separation.setFitHeight(HEIGHT/2-100*resfactor);
        separation.setX(0);
        separation.setY(HEIGHT/2+50*resfactor);
        
        questionPane = new StackPane();
        text_question = new Text("Question");
        white_question = new Rectangle(50*resfactor,250*resfactor,WIDTH-100*resfactor,200*resfactor);
        white_question.setArcHeight(30*resfactor);
        white_question.setArcWidth(30*resfactor);
        questionPane.getChildren().addAll(white_question,text_question);
        questionPane.setLayoutX(50*resfactor);
        questionPane.setLayoutY(250*resfactor);
        white_question.setFill(Color.WHITE);
        
        timerbar = new Rectangle(0,HEIGHT/2+25*resfactor,WIDTH,30*resfactor);
        Timeline countdown = new Timeline();
        KeyFrame length = new KeyFrame(Duration.millis(7),e->{
            if(timerbar.getWidth()>0){
                timerbar.setWidth(timerbar.getWidth()-2);
            }});
        KeyFrame color = new KeyFrame(Duration.millis(7),e->{
            if(timerbar_red<254){
                timerbar_red+=1;
                timerbar.setStroke(Color.rgb(timerbar_red, timerbar_green, 0));
                timerbar.setFill(Color.rgb(timerbar_red, timerbar_green, 0));
            }
            else if(timerbar.getWidth()<WIDTH/2 && timerbar_green>1){
                timerbar_green-=1;
                timerbar.setStroke(Color.rgb(timerbar_red, timerbar_green, 0));
                timerbar.setFill(Color.rgb(timerbar_red, timerbar_green, 0));
            }
        });
        countdown.getKeyFrames().addAll(length,color);
        countdown.setCycleCount(Animation.INDEFINITE);
        countdown.play();
        
        skip_button = new Rectangle();
        skip_button.setArcHeight(5*resfactor);
        skip_button.setArcWidth(5*resfactor);
        skip_button.setHeight(40*resfactor);
        skip_button.setWidth(100*resfactor);
        skip_button.setFill(Color.WHITE);
        skip_button.setStroke(Color.RED);
        skip_button.setStrokeWidth(5);
        
        skip_text = new Text("Skip");
        skip = new StackPane();
        skip.setLayoutX(WIDTH/2-50*resfactor);
        skip.setLayoutY(HEIGHT/2+18*resfactor);
        skip.getChildren().addAll(skip_button,skip_text);
        getChildren().addAll(background,answer1,answer2,answer3,answer4,separation,leaderbar,fillingbar,your_score,current_grade,first_place,questionPane,timerbar,skip);
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void updateScore() {
        first_place.setText("First Place: "+firstPlace);
    }

    public void answer() {
    }

    public void generateQuestion() {
    }
}