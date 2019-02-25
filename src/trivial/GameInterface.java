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

/**
 *
 * @author guill
 */
public class GameInterface extends Pane{
    private Player me;
    private String firstPlace;
    private String currentGrade;
    private boolean paused;
    private final int HEIGHT=1080;
    private final int WIDTH=1920;
    
    public GameInterface(){
        
        Rectangle answer1 = new Rectangle(0,HEIGHT/2,WIDTH/2,HEIGHT/4);
        Rectangle answer2 = new Rectangle(0,HEIGHT/2+answer1.getHeight(),answer1.getWidth(),answer1.getHeight());
        Rectangle answer3 = new Rectangle(WIDTH/2,HEIGHT/2,WIDTH/2,HEIGHT/4);
        Rectangle answer4 = new Rectangle(WIDTH/2,HEIGHT/2+answer1.getHeight(),answer1.getWidth(),answer1.getHeight());
        answer1.setFill(Color.rgb(96, 139, 109));
        answer2.setFill(Color.rgb(96, 139, 109));
        answer3.setFill(Color.rgb(96, 139, 109));
        answer4.setFill(Color.rgb(96, 139, 109));
        
        Rectangle black = new Rectangle(0,0,WIDTH,HEIGHT);
        black.setFill(Color.BLACK);
        
        ImageView separation = new ImageView(new Image("file:ChalkLine.png"));
        separation.setFitWidth(WIDTH);
        separation.setFitHeight(HEIGHT/2);
        separation.setX(0);
        separation.setY(HEIGHT/2);
        
        getChildren().addAll(black,answer1,answer2,answer3,answer4,separation);
    }
}