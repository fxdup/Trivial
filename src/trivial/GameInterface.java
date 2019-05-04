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

    private StackPane questionPane;
    private AnswerPane answer1;
    private AnswerPane answer2;
    private AnswerPane answer3;
    private AnswerPane answer4;
    private StackPane skip;

    private Player localPlayer;
    private boolean host;
    private QuestionList questionList;
    private Question question;
    private boolean skipping;
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
    private Circle[] icons;
    private ImageView[] streakIcons;
    private ImageView crown;
    private double resfactor;
    private double HEIGHT;
    private double WIDTH;
    private boolean win = false;
    private boolean paused = false;

    int timerbar_red;
    int timerbar_green;
    Timeline countdown;
    Timeline startAnim;
    Timeline updateScoreAnimation;
    KeyFrame color;
    AudioClip click;
    int clickCount = 0;

    private ImageView separation;

    public GameInterface(Boolean host, double resfactor, Player localPlayer, double sound) {
        this.localPlayer = localPlayer;
        this.resfactor = resfactor;
        this.host = host;
        HEIGHT = 1080 * resfactor;
        WIDTH = 1920 * resfactor;
        skipping = true;

        click = new AudioClip(new File("src/Resources/Sounds/Click.wav").toURI().toString());
        click.setVolume(sound/100);

        try {
            questionList = new QuestionList();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

        answer1 = new AnswerPane(0, HEIGHT / 2 + 50);
        answer2 = new AnswerPane(WIDTH / 2, HEIGHT / 2 + 50);
        answer3 = new AnswerPane(0, HEIGHT / 2 + 50 + (HEIGHT / 4 - 50));
        answer4 = new AnswerPane(WIDTH / 2, HEIGHT / 2 + 50 + (HEIGHT / 4 - 50));

        leaderbar = new Rectangle(0, 25 * resfactor, WIDTH, 18 * resfactor);
        leaderbar.setFill(Color.WHITE);
        fillingbar = new Rectangle(0, 25 * resfactor, 0, 18 * resfactor);
        fillingbar.setFill(localPlayer.getColor());

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

        getChildren().addAll(background, answer1, answer2, answer3, answer4, separation, leaderbar, fillingbar, your_score, current_grade, first_place, questionPane, timerbar, skip);
        startAnimation();
    }

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
            }
        }));
        startAnim.setCycleCount(Animation.INDEFINITE);
        startAnim.play();
        timeAnimation(3);

    }

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

    public void timeAnimation(int time) {
        countdown = new Timeline();
        double millis = time * 2.0 / WIDTH;
        timerbar.setWidth(WIDTH);
        timerbar_red = 0;
        timerbar_green = 255;
        color = new KeyFrame(Duration.seconds(millis), e -> {
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
        });
        countdown.getKeyFrames().add(color);
        countdown.setCycleCount(Animation.INDEFINITE);
        countdown.play();
    }

    public void updateScore() {
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

    public void updateIcons() {

        for (int i = 0; i < icons.length; i++) {
            icons[i].setCenterX(WIDTH * localPlayer.getPlayers()[i].getScore() / 1000);
            if (localPlayer.getPlayers()[i].getGrade() == 6) {
                streakIcons[i].setX(icons[i].getCenterX() - icons[i].getRadius());
                streakIcons[i].setVisible(true);
            } else if (streakIcons[i].isVisible()) {
                streakIcons[i].setVisible(false);
            }
            if (localPlayer.getPlayers()[i].equals(getFirstPlace())) {
                crown.setX(icons[i].getCenterX() - crown.getFitWidth() / 2);
            }
        }
    }

    public void winner() {
        for (Player i : localPlayer.getPlayers()) {
            if (i.getScore() >= 20) {
                win = true;
                sendData();
                Platform.runLater(() -> {
                ((Game) (getParent())).leaderboard(localPlayer.getPlayers());
                });
            }
        }
    }

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

    public void clearQuestion() {
        text_question.setText("");
        answer1.setText("");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");
    }

    public Player getFirstPlace() {
        Player max = localPlayer;
        for (Player i : localPlayer.getPlayers()) {
            if (i.getScore() > max.getScore()) {
                max = i;
            }
        }
        return max;
    }

    public void goodAnswer() {
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

    public void badAnswer() {
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

            this.setOnMouseClicked(e -> {
                if (clickCount == 0) {
                    click.play();
                    clickCount++;
                }
                if (!skipping) {
                    if (answer_text.getText().equals(question.getAnswer())) { //find a way to read dAnswers when a double and iAnswer when an int
                        //if (answer_text.getText().equals(Double.toString(question.getAnswer())) ) --> this line is the line 388 before i modify it
                        goodAnswer();
                    } else {
                        badAnswer();
                        skipping = true;
                    }
                }
            });
        }

        public void setText(String text) {
            answer_text.setText(text);
        }

    }

    public void stop() {
        paused = true;
    }
}
