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
    private Text startAnimTime;
    
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
    private final int HEIGHT=1080;
    private final int WIDTH=1920;
    int timerbar_red;
    int timerbar_green;
    Timeline countdown;
    Timeline startAnim;
    KeyFrame color;
    private ImageView separation;

    public GameInterface(Boolean host) throws FileNotFoundException{
        this.host=host;
        questionList=new QuestionList();
        
        answer1= new AnswerPane(0,HEIGHT/2+50);
        answer2= new AnswerPane(WIDTH/2,HEIGHT/2+50);
        answer3= new AnswerPane(0,HEIGHT/2+50+(HEIGHT/4-50));
        answer4= new AnswerPane(WIDTH/2,HEIGHT/2+50+(HEIGHT/4-50));
        
        
        
        
        leaderbar = new Rectangle(0,25,WIDTH,18);
        leaderbar.setFill(Color.WHITE);
        fillingbar = new Rectangle(0,25,0,18);
        fillingbar.setFill(Color.rgb(red,green,blue));
        
        first_place = new Text("First Place: "+firstPlace);
        first_place.getStyleClass().add("inGameGUI");
        current_grade = new Text("Grade "+currentGrade);
        current_grade.getStyleClass().add("inGameGUI");
        your_score = new Text("Your Score: "+score);
        your_score.getStyleClass().add("inGameGUI");
        
        first_place.setY(72);
        first_place.setX(WIDTH-360);
        current_grade.setY(72);
        current_grade.setX(WIDTH/2-50);
        your_score.setY(72);
        your_score.setX(5);
        
        background = new ImageView(new Image("/Resources/background_image.jpg"));
        background.setFitWidth(1920);
        background.setFitHeight(1080);
        
        
        separation = new ImageView(new Image("file:ChalkLine.png"));
        separation.setFitWidth(WIDTH);
        separation.setFitHeight(HEIGHT/2-100);
        separation.setX(0);
        separation.setY(HEIGHT/2+50);
        
        questionPane = new StackPane();
        text_question = new Text("");
        white_question = new Rectangle(50,250,WIDTH-100,200);
        white_question.setArcHeight(30);
        white_question.setArcWidth(30);
        questionPane.getChildren().addAll(white_question,text_question);
        questionPane.setLayoutX(50);
        questionPane.setLayoutY(250);
        white_question.setFill(Color.WHITE);
        
        
        
        
        skip_button = new Rectangle();
        skip_button.setArcHeight(5);
        skip_button.setArcWidth(5);
        skip_button.setHeight(40);
        skip_button.setWidth(100);
        skip_button.setFill(Color.WHITE);
        skip_button.setStroke(Color.RED);
        skip_button.setStrokeWidth(5);
        timerbar = new Rectangle(0,HEIGHT/2+25,WIDTH,30);
        timerbar.setStroke(Color.rgb(timerbar_red, timerbar_green, 0));
        timerbar.setFill(Color.rgb(timerbar_red, timerbar_green, 0));
        skip_text = new Text("Skip");
        skip = new StackPane();
        skip.setLayoutX(WIDTH/2-50);
        skip.setLayoutY(HEIGHT/2+18);
        skip.getChildren().addAll(skip_button,skip_text);
        
        getChildren().addAll(background,answer1,answer2,answer3,answer4,separation,leaderbar,fillingbar,your_score,current_grade,first_place,questionPane,timerbar,skip);
        startAnimation();
    }
    
    public void startAnimation(){
        startAnimTime = new Text(WIDTH/2,HEIGHT/2,"3");
        startAnimTime.getStyleClass().add("inGameGUI");
        startAnim=new Timeline();
        getChildren().add(startAnimTime);
        startAnim.getKeyFrames().addAll(new KeyFrame(Duration.seconds(1), e->{
        if(Integer.parseInt(startAnimTime.getText())-1>0){
        startAnimTime.setText(Integer.parseInt(startAnimTime.getText())-1+"");
        }else {
            getChildren().remove(startAnimTime);
            nextQuestion();
            startAnim.stop();}
        }));
        startAnim.setCycleCount(Animation.INDEFINITE);
        startAnim.play();
        timeAnimation(3);
    }

    public void timeAnimation(int time){
        countdown=new Timeline();
        double millis=time*2.0/WIDTH;
        timerbar.setWidth(WIDTH);
        timerbar_red=0;
        timerbar_green=255;
        color = new KeyFrame(Duration.seconds(millis),e->{
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
            if(timerbar.getWidth()>=0){
                timerbar.setWidth(timerbar.getWidth()-2);
            }else{countdown.getKeyFrames().clear();}
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
        fillingbar.setWidth(WIDTH*score/1000);
    }

    public void nextQuestion() {
        question=questionList.getQuestion(1);
        text_question.setText(question.getQuestion());
        answer1.setText(question.getChoices()[0]);
        answer2.setText(question.getChoices()[1]);
        answer3.setText(question.getChoices()[2]);
        answer4.setText(question.getChoices()[3]);
        timeAnimation(question.getTime());
    }
    
    
    class AnswerPane extends StackPane{
        private Rectangle answer_rectangle;
        private Text answer_text;
        
        AnswerPane(int layoutX,int LayoutY){
        answer_rectangle = new Rectangle(WIDTH/2,HEIGHT/4-50);
        answer_rectangle.setFill(Color.rgb(96, 139, 109));
        answer_text = new Text("");
        answer_text.getStyleClass().add("inGameGUI");
        this.getChildren().addAll(answer_rectangle,answer_text);
        this.setLayoutX(layoutX);
        this.setLayoutY(LayoutY);
        
        answer_rectangle.setOnMouseClicked(e->{
        if(answer_text.getText().equals(question.getAnswer()))
            score+=question.getScore();
        updateScore();
        });
        
        }

        public void setText(String text){
        answer_text.setText(text);
        }
        
}
}