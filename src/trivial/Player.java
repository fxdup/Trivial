/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.IOException;

/**
 *
 * @author Félix Dupont
 */
public abstract class Player {
    private String name;
    private int score;
    private int grade;
    
    public Player(String name){
    this.name=name;
    score=0;
    grade=0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
    
    public abstract void sendData(String name,int score,int grade) throws IOException;
}
