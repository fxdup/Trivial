package trivial;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Player implements Cloneable, Serializable {

    private final String NAME; //Name of the player
    private int id; //Identifiant of the player
    private int score; //Current score of the player
    private int grade; //Current grade of the player
    private int highestGrade; //Highest grade of the player in the current game
    private int streak; //How many questions in a row the player has answered without missing. Skipping not included
    private int highestStreak; //Highest streak of the player in the current game
    private ArrayList<Player> players; //List of players in the game

    //The colors need to be integers to be able to send the player as an object and keeping it
    private int red = (int) (Math.random() * 200 + 50);//red color in rgb of the player
    private int green = (int) (Math.random() * 200 + 50);//green color in rgb of the player
    private int blue = (int) (Math.random() * 200 + 50);//blue color in rgb of the player

    //Creates a player without an Id gets an Id of -1
    public Player(String name) {
        this(name, -1);
    }

    //Creates a player with an Id
    public Player(String name, int id) {
        this.NAME = name;
        players = new ArrayList<>();
        updatePlayer(this);//add the player to the player list
        grade = 1;
        score = 0;
        this.id = id;
    }
    public Player(String name,int id,int r,int g,int b){
    this(name,id);
    red=r;
    green=g;
    blue=b;
    }

    //returns the player itself (used in the inner threads where it is not possible to use this and to reffer to the instance)
    public Player getThis() {
        return this;
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
        players.remove(this);//need to remove the player from the list and add him back since it changes Id and it is what identifies him
        this.id = id;
        players.add(this);
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

    //adds the given score to the current score
    public void addScore(int score) {
        this.score += score;
    }

    //returns the name of the player
    public String getName() {
        return NAME;
    }

    //returns the number of players in the game
    public synchronized int getPlayerSize() {
        return players.size();
    }

    //Graduates the player to the next grade
    public void graduate() {
        if (grade < 6) {
            grade++;
        }
    }

    //resets the grade of the player to one
    public void resetGrade() {
        grade = 1;
    }

    //returns the Color of the player
    public Color getColor() {
        return Color.rgb(red, green, blue);
    }

    //updates the given player in the player list if it is already in it. If not it adds the player to the list
    public synchronized void updatePlayer(Player player) {
        if (player.getId() == -1) //if the players id is not set yet it needs not to be added to the player list
        {
            return;
        }
        if (!players.contains(player)) {
            players.add(player);
        } else {
            players.set(players.indexOf(player), player);
        }
    }

    //removes a player from the player list
    public void removePlayer(Player player) {
        players.remove(player);
    }

    //returns the list of player in the game
    public synchronized Player[] getPlayers() {
        Player[] p = new Player[players.size()];
        for (int i = 0; i < p.length; i++) {
            p[i] = players.get(i);
        }
        return p;
    }

    //clears the player list
    public void clearPlayers() {
        players.clear();
    }

    //returns a string containing the informations of the player
    @Override
    public String toString() {
        return "Name: " + getName() + "\n Score: " + getScore() + "\n Grade: " + getGrade() + "\n Highest Grade: " + getHighestGrade() + "\n Streak: " + getStreak() + "\n Highest Streak: " + getHighestStreak();
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

    public Player clone(){
    Player p=new Player(this.getName(),this.getId(),this.red,this.green,this.blue);    
    p.setGrade(grade);
    p.setHighestGrade(highestGrade);
    p.setHighestStreak(highestStreak);
    p.setScore(score);
    p.setStreak(streak);
    return p;
    }
    
}
