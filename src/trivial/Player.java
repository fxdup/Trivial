package trivial;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Player implements Serializable,Cloneable {

    private final String NAME; //Name of the player
    private int id; //Identifiant of the player
    private int score; //Current score of the player
    private int grade; //Current grade of the player
    private int highestGrade; //Highest grade of the player in the current game
    private int streak; //How many questions in a row the player has answered without missing. Skipping not included
    private int highestStreak; //Highest streak of the player in the current game
    private ArrayList<Player> players;
    private int red =(int) (Math.random() * 200 + 50);
    private int green =(int) (Math.random() * 200 + 50);
    private int blue =(int) (Math.random() * 200 + 50);        

    public Player(String name) {
        this.NAME = name;
        id=-1;
        players=new ArrayList<>();
        score = 0;
        grade = 1;
    }

    public Player(String name, int id) {
        this(name);
        this.id = id;
    }

    //returns the id of the player
    public int getId() {
        return id;
    }

    //returns the highest grade the player has reached during the game 
    public int getHighestGrade() {
        return highestGrade;
    }

    //sets the highest grade the player has reached during the game 
    public void setHighestGrade(int highestGrade) {
        this.highestGrade = highestGrade;
    }

    //returns the number of questions the player has answered correctly in a row
    public int getStreak() {
        return streak;
    }

    //sets the current number of questions the player has answered correctly in a row
    public void setStreak(int streak) {
        this.streak = streak;
    }

    //returns the highest number of questions the player has answered correctly in a row during the game 
    public int getHighestStreak() {
        return highestStreak;
    }

    //sets the highest number of questions the player has answered correctly in a row during the game 
    public void setHighestStreak(int highestStreak) {
        this.highestStreak = highestStreak;
    }

    //sets the id of the player
    public void setId(int id) {
        this.id = id;
    }

    //returns the grade the player is currently in
    public int getGrade() {
        return grade;
    }

    //sets the grade the player
    public void setGrade(int grade) {
        this.grade = grade;
    }

    //returns the current score of the player
    public int getScore() {
        return score;
    }

    //sets the score of the player
    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score){
        this.score+=score;
    }
    //returns the name of the player
    public String getName() {
        return NAME;
    }

    public void graduate() {
        if (grade < 6) {
            grade++;
        }
    }

    public void resetGrade() {
        grade = 1;
    }
    
    public Color getColor(){
    return Color.rgb(red, green, blue);
    }
    
    public void addPlayer(Player player){
    if(!players.contains(player))
        players.add(player);
    else players.set(players.indexOf(player),player);
    }
    
    public Player[] getPlayers(){
    Player[] p=new Player[players.size()];
        for(int i=0;i<p.length;i++)
            p[i]=players.get(i);
        return p;
    }
    
    public void clearPlayers(){
    players.clear();
    }
    
    //returns a string containing the informations of the player
    @Override
    public String toString() {

        return "Name: " + getId() + "\n Score: " + getScore() + "\n Grade: " + getGrade() + "\n Highest Grade: " + getHighestGrade() + "\n Streak: " + getStreak() + "\n Highest Streak: " + getHighestStreak()+"\n RGB"+red+" "+blue+" "+green;

    }

    //returns true if the to players are equal
    @Override
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player p = (Player) o;
            if (p.getId() == this.getId()) {
                return true;
            }

        }
        return false;
    }
}
