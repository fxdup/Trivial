/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.FileNotFoundException;
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
    private AnswerPane answer1;
    private AnswerPane answer2;
    private AnswerPane answer3;
    private AnswerPane answer4;
    private StackPane skip;
    
    private Player me;
    private Player[] players;
    private boolean host;
    private String firstPlace;
    private String currentGrade;
    private int score;
    private QuestionList questionList;
    private Question question;
    
    private Text first_place;
    private Text current_grade;
    private Text your_score;
    private Text text_question;
    private Text skip_text;
    
    private ImageView background;
    private Rectangle leaderbar;
    private Rectangle fillingbar;
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

    public GameInterface(Boolean host,double resfactor) throws FileNotFoundException{
        this.resfactor=resfactor;
        HEIGHT=1080*resfactor;
        WIDTH=1920*resfactor;
        System.out.println(HEIGHT);
        System.out.println(WIDTH);
        this.host=host;
        question=new Question(1);
        
        answer1= new AnswerPane(0,HEIGHT/2+50);
        answer2= new AnswerPane(WIDTH/2,HEIGHT/2+50);
        answer3= new AnswerPane(0,HEIGHT/2+50+(HEIGHT/4-50));
        answer4= new AnswerPane(WIDTH/2,HEIGHT/2+50+(HEIGHT/4-50));
        
        
        

        
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
        text_question = new Text("");
        white_question = new Rectangle(50*resfactor,250*resfactor,WIDTH-100*resfactor,200*resfactor);
        white_question.setArcHeight(30*resfactor);
        white_question.setArcWidth(30*resfactor);
        questionPane.getChildren().addAll(white_question,text_question);
        questionPane.setLayoutX(50*resfactor);
        questionPane.setLayoutY(250*resfactor);
        white_question.setFill(Color.WHITE);
        
        skip_button = new Rectangle();
        skip_button.setArcHeight(5*resfactor);
        skip_button.setArcWidth(5*resfactor);
        skip_button.setHeight(40*resfactor);
        skip_button.setWidth(100*resfactor);
        skip_button.setFill(Color.WHITE);
        skip_button.setStroke(Color.RED);
        skip_button.setStrokeWidth(5);
        timerbar = new Rectangle(0,HEIGHT/2+25,WIDTH,30);
        timerbar.setStroke(Color.rgb(timerbar_red, timerbar_green, 0));
        timerbar.setFill(Color.rgb(timerbar_red, timerbar_green, 0));
        skip_text = new Text("Skip");
        skip = new StackPane();
        skip.setLayoutX(WIDTH/2-50*resfactor);
        skip.setLayoutY(HEIGHT/2+18*resfactor);
        skip.getChildren().addAll(skip_button,skip_text);
        getChildren().addAll(background,answer1,answer2,answer3,answer4,separation,leaderbar,fillingbar,your_score,current_grade,first_place,questionPane,timerbar,skip);
        
        
        
        nextQuestion();
        
    }

    public void timeAnimation(int time){
        Timeline countdown = new Timeline();
        timerbar = new Rectangle(0,HEIGHT/2+25,WIDTH,30);
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
            if(timerbar.getWidth()>0){
                timerbar.setWidth(timerbar.getWidth()-2);
            }
        });
        countdown.getKeyFrames().add(color);
        countdown.setCycleCount(Animation.INDEFINITE);
        countdown.play();
    }
    
    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void updateScore() {
        first_place.setText("First Place: "+firstPlace);
        your_score.setText("Your Score: "+score);
    }

    public void nextQuestion() {
        text_question.setText(question.getQuestion());
        answer1.setText(question.getChoices()[0]);
        answer2.setText(question.getChoices()[1]);
        answer3.setText(question.getChoices()[2]);
        answer4.setText(question.getChoices()[3]);
    }
    
    
    class AnswerPane extends StackPane{
        private Rectangle answer_rectangle;
        private Text answer_text;
        
        AnswerPane(double layoutX,double LayoutY){
        answer_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50);
        answer_rectangle.setFill(Color.rgb(96, 139, 109));
        answer_text = new Text("");
        answer_text.getStyleClass().add("inGameGUI");
        this.getChildren().addAll(answer_rectangle,answer_text);
        this.setLayoutX(layoutX);
        this.setLayoutY(LayoutY);
        
        answer_rectangle.setOnMouseClicked(e->{
        if(answer_text.getText().equals(question.getAnswer()))
            score++;
        updateScore();
        });
        
        }

        public void setText(String text){
        answer_text.setText(text);
        }
        
}
}