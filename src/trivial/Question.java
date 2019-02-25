/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

/**
 *
 * @author Félix Dupont
 */
public class Question {
    
    public final int MATH = 0;
    public final int SCIENCE = 1;
    public final int GENERAL = 2;
    public final int LOGIC = 3;

    private int type; //math(0), science(1), general(2), logic(3)
    private int grade;
    private String question;
    private String[] answers;

    public Question(int garde) {
        this.grade = grade;
    }

    public int getType() {
        return type;
    }

    public int getGrade() {
        return grade;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void math() {
        
        int type = (int)(Math.random() * 5);
        
        switch (type) {
            
            case 0: arithmetic();
            case 1: geometry();
            case 3: fractions();
            case 4: shapeCalculation();
        }
            
    }

    public void science() {
    }

    public void general() {
    }

    public void logic() {
    }
}
