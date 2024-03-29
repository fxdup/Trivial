/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author guill
 */
public class GameInterface extends Pane {

    private StackPane questionPane;//Pane containing the text of the question
    private AnswerPane answer1;//Pane of answer 1
    private AnswerPane answer2;//Pane of answer 2
    private AnswerPane answer3;//Pane of answer 3
    private AnswerPane answer4;//Pane of answer 4
    private StackPane skip;//Pane containing the skip button

    private Player localPlayer;
    private boolean host;//If the local player is a host
    private QuestionList questionList;//List of questions that will be played
    private Question question;//Current question
    private boolean skipping;//If the player is skipping the current question
    private Text first_place;//Text containing who is in first place
    private Text current_grade;//Current grade of the local player
    private Text your_score;//Current score of the local player
    private Text text_question;//Text of the current queston
    private Text skip_text;//Text in skip pane
    private Text startAnimTime;//Text of the countdown at the start of the game

    private ImageView background;//Background image
    private Rectangle leaderbar;//White bar at the top to show progression
    private Rectangle fillingbar;//Bar of the color of the player which will fill the leaderbar
    private Rectangle white_question;//Background of the questionPane
    private Rectangle timerbar;//Bar showing how much time is lefft to a question
    private Rectangle skip_button;//Background of the skip pane
    private Circle[] icons;//Array containing the icons of all players
    private ImageView[] streakIcons;//Array containing a star for each player for when they are in a streak of 6 and more
    private ImageView crown;//Crown which is diplayed on top of the leader
    private double resfactor;//The resolution factor which changes the window size
    private double HEIGHT;//Height of the window
    private double WIDTH;//Width of the window
    private boolean win = false;//If someone has won
    private boolean paused = false;//If the game is paused

    private int timerbar_red;//Quantity of red in the timebar
    private int timerbar_green;//Quantity of green in the timebar
    private Timeline countdown;//Timeline of the line countdown during 
    private Timeline startAnim;//Timeline of the starting countdown
    private Timeline updateScore;//Timeline that updates the score and the icon placement
    private KeyFrame color;//Keyframe of the color and the size of the countdown bar;
    private int clickCount = 0;//Used for the sound. So that it does not make the sound when spamming an answer

    private ImageView separation;//Image of the chalk between the answers 
    
    private AudioClip correctAnswer;//Audio of good answer
    private AudioClip wrongAnswer;//Audio of wrong anser

    public GameInterface(Boolean host, double resfactor, Player localPlayer, double sound) {
        this.localPlayer = localPlayer;
        this.resfactor = resfactor;
        this.host = host;
        HEIGHT = 1080 * resfactor;
        WIDTH = 1920 * resfactor;
        skipping = true;
        
        correctAnswer = new AudioClip(new File("src/Resources/Sounds/Correct.wav").toURI().toString());
        correctAnswer.setVolume(sound / 100);
        wrongAnswer = new AudioClip(new File("src/Resources/Sounds/Incorrect.mp3").toURI().toString());
        wrongAnswer.setVolume(sound / 100);

        try {
            questionList = new QuestionList();//Creating the list of questions for the game
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creating the answer panes
        answer1 = new AnswerPane(0, HEIGHT / 2 + 50);
        answer2 = new AnswerPane(WIDTH / 2, HEIGHT / 2 + 50);
        answer3 = new AnswerPane(0, HEIGHT / 2 + 50 + (HEIGHT / 4 - 50));
        answer4 = new AnswerPane(WIDTH / 2, HEIGHT / 2 + 50 + (HEIGHT / 4 - 50));

        //Bar of progression of the local player
        leaderbar = new Rectangle(0, 25 * resfactor, WIDTH, 18 * resfactor);
        leaderbar.setFill(Color.WHITE);
        fillingbar = new Rectangle(0, 25 * resfactor, 0, 18 * resfactor);
        fillingbar.setFill(localPlayer.getColor());

        //Text of the the first player, grade and score
        first_place = new Text();
        first_place.getStyleClass().add("inGameGUI");
        first_place.setStyle("-fx-font: " + 30 * resfactor + "px EraserDust;");
        current_grade = new Text();
        current_grade.getStyleClass().add("inGameGUI");
        current_grade.setStyle("-fx-font: " + 30 * resfactor + "px EraserDust;");
        your_score = new Text();
        your_score.getStyleClass().add("inGameGUI");
        your_score.setStyle("-fx-font: " + 30 * resfactor + " EraserDust;");

        first_place.setY(72 * resfactor);
        first_place.setX(WIDTH - 360 * resfactor);
        current_grade.setY(72 * resfactor);
        current_grade.setX(WIDTH / 2 - 50 * resfactor);
        your_score.setY(72 * resfactor);
        your_score.setX(5 * resfactor);

        background = new ImageView(new Image("/Resources/Images/background_image.jpg"));
        background.setFitWidth(1920 * resfactor);
        background.setFitHeight(1080 * resfactor);

        separation = new ImageView(new Image("/Resources/Images/ChalkLine.png"));
        separation.setFitWidth(WIDTH);
        separation.setFitHeight(HEIGHT / 2 - 100 * resfactor);
        separation.setX(0);
        separation.setY(HEIGHT / 2 + 50 * resfactor);

        questionPane = new StackPane();
        text_question = new Text("");
        white_question = new Rectangle(50 * resfactor, 250 * resfactor, WIDTH - 100 * resfactor, 200 * resfactor);
        white_question.setArcHeight(30 * resfactor);
        white_question.setArcWidth(30 * resfactor);
        questionPane.getChildren().addAll(white_question, text_question);
        questionPane.setLayoutX(50 * resfactor);
        questionPane.setLayoutY(250 * resfactor);
        white_question.setFill(Color.WHITE);
        text_question.setStyle("-fx-font: " + 30 * resfactor + "px EraserDust;");
        
        //skip button and timebar creation
        skip_button = new Rectangle();
        skip_button.setArcHeight(5 * resfactor);
        skip_button.setArcWidth(5 * resfactor);
        skip_button.setHeight(40 * resfactor);
        skip_button.setWidth(100 * resfactor);
        skip_button.setFill(Color.WHITE);
        skip_button.setStroke(Color.RED);
        skip_button.setStrokeWidth(2);

        timerbar = new Rectangle(0, HEIGHT / 2 + 55 * resfactor - skip_button.getHeight() / 2, WIDTH, 30 * resfactor);
        timerbar.setStroke(Color.rgb(timerbar_red, timerbar_green, 0));
        timerbar.setFill(Color.rgb(timerbar_red, timerbar_green, 0));
        skip_text = new Text("Skip");
        skip = new StackPane();
        skip.setLayoutX(WIDTH / 2 - skip_button.getWidth() / 2);
        skip.setLayoutY(HEIGHT / 2 + 50 * resfactor - skip_button.getHeight() / 2);
        skip.getChildren().addAll(skip_button, skip_text);
        skip.setOnMouseClicked(e -> {
            if (!skipping) {
                skip_button.setFill(Color.ORANGE);
                skipping = true;
                clearQuestion();
                countdown.stop();
                timeAnimation(question.getTime());
            }
        });
        updateScore = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            updateScore();
        }));
        updateScore.setCycleCount(Animation.INDEFINITE);
        getChildren().addAll(background, answer1, answer2, answer3, answer4, separation, leaderbar, fillingbar, your_score, current_grade, first_place, questionPane, timerbar, skip);
        startAnimation();

    }

    //Animation of the start countdown
    public void startAnimation() {
        startAnimTime = new Text(WIDTH / 2, HEIGHT / 3, "3");
        startAnimTime.setStroke(Color.BLACK);
        startAnimTime.setStyle("-fx-font: " + 30 * resfactor + "px EraserDust;");
        startAnim = new Timeline();
        getChildren().add(startAnimTime);
        startAnim.getKeyFrames().addAll(new KeyFrame(Duration.seconds(1), e -> {
            if (Integer.parseInt(startAnimTime.getText()) - 1 > 0) {
                startAnimTime.setText(Integer.parseInt(startAnimTime.getText()) - 1 + "");
            } else {
                drawCircles();
                getChildren().remove(startAnimTime);
                startAnim.stop();
                updateScore.play();
            }
        }));
        startAnim.setCycleCount(Animation.INDEFINITE);
        startAnim.play();
        timeAnimation(3);

    }

    //Draws the icons of the players
    public void drawCircles() {
        crown = new ImageView(new Image("/Resources/Images/crown.png"));
        icons = new Circle[localPlayer.getPlayers().length];
        streakIcons = new ImageView[localPlayer.getPlayers().length];
        for (int i = 0; i < icons.length; i++) {
            icons[i] = new Circle(fillingbar.getX() + fillingbar.getWidth() / 2, fillingbar.getY() + fillingbar.getHeight() / 2, fillingbar.getHeight() * 2 / 3, localPlayer.getPlayers()[i].getColor());
            streakIcons[i] = new ImageView(new Image("/Resources/Images/Streak_star.png"));
            streakIcons[i].setFitHeight(24 * resfactor);
            streakIcons[i].setFitWidth(24 * resfactor);
            streakIcons[i].setX(icons[i].getCenterX() - icons[i].getRadius());
            streakIcons[i].setY(icons[i].getCenterY() - icons[i].getRadius());
            streakIcons[i].setVisible(false);
            getChildren().addAll(icons[i], streakIcons[i]);
        }
        crown.setFitWidth(43 * resfactor);
        crown.setFitHeight(33 * resfactor);
        crown.setY(fillingbar.getY() - crown.getFitHeight() * 19 / 20);
        crown.setX(icons[0].getCenterX() - crown.getFitWidth() / 2);
        getChildren().add(crown);
    }

    //Method to start the cooldown of the timebar
    public void timeAnimation(int time) {
        countdown = new Timeline();
        double millis = time * 2.0 / WIDTH;
        timerbar.setWidth(WIDTH);
        timerbar_red = 0;
        timerbar_green = 255;
        color = new KeyFrame(Duration.seconds(millis), e -> {
            if(!win){
                if (paused) {
                    countdown.stop();
                }
                if (timerbar_red < 254) {
                    timerbar_red += 1;
                    timerbar.setStroke(Color.rgb(timerbar_red, timerbar_green, 0));
                    timerbar.setFill(Color.rgb(timerbar_red, timerbar_green, 0));
                } else if (timerbar.getWidth() < WIDTH / 2 && timerbar_green > 1) {
                    timerbar_green -= 1;
                    timerbar.setStroke(Color.rgb(timerbar_red, timerbar_green, 0));
                    timerbar.setFill(Color.rgb(timerbar_red, timerbar_green, 0));
                }
                if (timerbar.getWidth() >= 0) {
                    timerbar.setWidth(timerbar.getWidth() - 2);
                } else {
                    if (skipping) {
                        skipping = false;
                        countdown.stop();
                        skip_button.setFill(Color.WHITE);
                    } else {
                        countdown.stop();
                        badAnswer();
                    }
                    nextQuestion();
                }
            }
        });
        countdown.getKeyFrames().add(color);
        countdown.setCycleCount(Animation.INDEFINITE);
        countdown.play();
    }

    //updates the scores and places
    public synchronized void updateScore() {
        if (icons != null) {
            first_place.setText("First Place: " + getFirstPlace().getName());
            your_score.setText("Your Score: " + localPlayer.getScore());
            current_grade.setText("Grade " + localPlayer.getGrade());
            fillingbar.setWidth(WIDTH * localPlayer.getScore() / 1000);
            updateIcons();
            if (!win) {
                winner();
            }
        }
    }

    //updates the icons on the bar
    public void updateIcons() {
        for (int i = 0; i < icons.length; i++) {
            icons[i].setCenterX(WIDTH * localPlayer.getPlayers()[i].getScore() / 1000);
            icons[i].toFront();
            if (localPlayer.getPlayers()[i].getGrade() == 6) {
                streakIcons[i].setX(icons[i].getCenterX() - icons[i].getRadius());
                streakIcons[i].setVisible(true);
                streakIcons[i].toFront();
            } else if (streakIcons[i].isVisible()) {
                streakIcons[i].setVisible(false);
            }
            if (localPlayer.getPlayers()[i].equals(getFirstPlace())) {
                crown.setX(icons[i].getCenterX() - crown.getFitWidth() / 2);
                crown.toFront();
            }
        }
    }

    //check if their is a winner
    public void winner() {
        for (Player i : localPlayer.getPlayers()) {
            if (i.getScore() >= 1000) {
                win = true;
                i.setScore(1000);
                sendData();
                Platform.runLater(() -> {
                    ((Game) (getParent())).leaderboard(localPlayer.getPlayers());
                });
            }
        }
    }

    //Goes to teh next question
    public void nextQuestion() {
        clickCount = 0;
        updateScore();
        sendData();
        countdown.stop();
        question = questionList.getQuestion(localPlayer.getGrade());
        text_question.setText(question.getQuestion());
        answer1.setText(question.getChoices()[0]);
        answer2.setText(question.getChoices()[1]);
        answer3.setText(question.getChoices()[2]);
        answer4.setText(question.getChoices()[3]);
        timeAnimation(question.getTime());
    }

    //send data to the other players
    public void sendData() {
        try {
            if (host) {
                ((HostPlayer) localPlayer).sendData(localPlayer);
            } else {
                ((ClientPlayer) localPlayer).sendData();
            }
        } catch (IOException ex) {
            Logger.getLogger(GameInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //clears de question pane and the choices
    public void clearQuestion() {
        text_question.setText("");
        answer1.setText("");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");
    }

    //Returns the player first place
    public Player getFirstPlace() {
        Player max = localPlayer;
        for (Player i : localPlayer.getPlayers()) {
            if (i.getScore() > max.getScore()) {
                max = i;
            }
        }
        return max;
    }

    //Animation for a good answer
    public void goodAnswer() {
        correctAnswer.play();
        FillTransition anstran1 = new FillTransition(Duration.seconds(0.3), (Rectangle) answer1.getChildren().get(0), Color.GREEN, Color.rgb(96, 139, 109));
        FillTransition anstran2 = new FillTransition(Duration.seconds(0.3), (Rectangle) answer2.getChildren().get(0), Color.GREEN, Color.rgb(96, 139, 109));
        FillTransition anstran3 = new FillTransition(Duration.seconds(0.3), (Rectangle) answer3.getChildren().get(0), Color.GREEN, Color.rgb(96, 139, 109));
        FillTransition anstran4 = new FillTransition(Duration.seconds(0.3), (Rectangle) answer4.getChildren().get(0), Color.GREEN, Color.rgb(96, 139, 109));
        anstran1.play();
        anstran2.play();
        anstran3.play();
        anstran4.play();
        localPlayer.addScore(question.getScore());
        localPlayer.graduate();
        if (localPlayer.getGrade() > localPlayer.getHighestGrade()) {
            localPlayer.setHighestGrade(localPlayer.getGrade());
        }

        localPlayer.setStreak(localPlayer.getStreak() + 1);
        if (localPlayer.getHighestStreak() < localPlayer.getStreak()) {
            localPlayer.setHighestStreak(localPlayer.getStreak());
        }
        sendData();
        updateScore();
        nextQuestion();
    }

    //Animation for a bad answer
    public void badAnswer() {
        wrongAnswer.play();
        FillTransition anstran1 = new FillTransition(Duration.seconds(0.3), (Rectangle) answer1.getChildren().get(0), Color.RED, Color.rgb(96, 139, 109));
        FillTransition anstran2 = new FillTransition(Duration.seconds(0.3), (Rectangle) answer2.getChildren().get(0), Color.RED, Color.rgb(96, 139, 109));
        FillTransition anstran3 = new FillTransition(Duration.seconds(0.3), (Rectangle) answer3.getChildren().get(0), Color.RED, Color.rgb(96, 139, 109));
        FillTransition anstran4 = new FillTransition(Duration.seconds(0.3), (Rectangle) answer4.getChildren().get(0), Color.RED, Color.rgb(96, 139, 109));
        anstran1.play();
        anstran2.play();
        anstran3.play();
        anstran4.play();
        localPlayer.resetGrade();
        localPlayer.setStreak(0);
        sendData();
        updateScore();
    }

    //Pane that contains the choice
    class AnswerPane extends StackPane {

        private Rectangle answer_rectangle;
        private Text answer_text;

        AnswerPane(double layoutX, double LayoutY) {
            answer_rectangle = new Rectangle(WIDTH / 2, HEIGHT / 4 - 50 * resfactor, Color.rgb(96, 139, 109));
            answer_text = new Text();
            answer_text.getStyleClass().add("inGameGUI");
            answer_text.setStyle("-fx-font: " + 40 * resfactor + "px EraserDust;");
            this.getChildren().addAll(answer_rectangle, answer_text);
            this.setLayoutX(layoutX);
            this.setLayoutY(LayoutY);

            //Checks if the answer is the good one
            this.setOnMouseClicked(e -> {
                if (clickCount == 0) {
                    clickCount++;
                }
                if (!skipping) {
                    if (answer_text.getText().equals(question.getAnswer())) { //find a way to read Answers when a double and iAnswer when an int
                        goodAnswer();
                    } else {
                        badAnswer();
                        skipping = true;
                    }
                }
            });
        }

        //Sets the text in the pane
        public void setText(String text) {
            answer_text.setText(text);
        }

    }

    //Stops the game
    public void stop() {
        paused = true;
    }
}
