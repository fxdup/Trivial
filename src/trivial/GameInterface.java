/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;
import javafx.scene.layout.Pane;

/**
 *
 * @author Félix Dupont
 */
public class GameInterface extends Pane {
    
    private boolean paused;
    private int ownScore;
    private int grade;

    public GameInterface() {
        paused = false;
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
