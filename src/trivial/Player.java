/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Félix Dupont
 */
public class Player implements Serializable{

    private final String NAME; //Name of the player
    private int id; //Identifiant of the player
    private int score; //Current score of the player
    private int grade; //Current grade of the player
    private int highestGrade; //Highest grade of the player in the current game
    private int streak; //How many questions in a row the player has answered without missing. Skipping not included
    private int highestStreak; //Highest streak of the player in the current game

    public Player(String name) {
        this.NAME = name;
        score = 0;
        grade = 0;
    }
    public Player(String name,int id){
        this.NAME = name;
        score = 0;
        grade = 0;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public int getHighestGrade() {
        return highestGrade;
    }

    public void setHighestGrade(int highestGrade) {
        this.highestGrade = highestGrade;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getHighestStreak() {
        return highestStreak;
    }

    public void setHighestStreak(int highestStreak) {
        this.highestStreak = highestStreak;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return NAME;
    }
    public String toString(){
    
        return "Name: "+getName()+"\n Score: "+getScore()+"\n Grade: "+getGrade()+"\n Highest Grade: "+getHighestGrade()+"\n Streak: "+getStreak()+"\n Highest Streak: "+getHighestStreak();
    
    }


    public boolean equals(Object o){
        if(o instanceof Player){
            Player p= (Player)o;
            if(p.getId()==this.getId())
                return true;

        }
        return false;
    }
}
