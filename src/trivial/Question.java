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
    private String answer;
    private String[] choices;

    public Question(int grade) {
        this.grade = grade;
        int questionType = (int)(Math.random() * 4);
        selectType(questionType);
    }
    
    public void selectType(int type) {
        
        switch(type) {
            
            case 0: math();
            case 1: science(); 
            case 2: general();
            case 3: logic();
        }
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

    public String[] getChoices() {
        return choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void math() {

        int mathType = (int) (Math.random() * 5);

        switch (type) {

            case 0:
                arithmetic();
            case 1:
                geometry();
            case 3:
                fractions();
            case 4:
                shapeCalculation();
        }

    }

    public void arithmetic() {

        if (this.grade == 1) {

            int x = (int) (Math.random() * 9);//variable 1 for calculations
            int y = (int) (Math.random() * 9); //variable 2 for calculations

            int operation = (int) (Math.random() * 2);

            switch (operation) {

                case 0:
                    String addition = x + y + "";
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    if (x >= y) { //to make sure we have a positive answer
                        subtraction = x - y + "";
                        answer = x - y + ""; //this is the true value of the operation
                    } else {
                        subtraction = y - x + "";
                        answer = y - x + ""; //this is the true value of the operation
                    }

                    question = subtraction; //puts the global variable "question" equal to the subtaction
            }
        }

        if (this.grade == 2) {

            int x = (int) (Math.random() * 99);//variable 1 for calculations
            int y = (int) (Math.random() * 9); //variable 2 for calculations

            int operation = (int) (Math.random() * 2);

            switch (operation) {

                case 0:
                    String addition = x + y + "";
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    if (x >= y && x < 10) {
                        x = x + 10;
                        subtraction = x - y + "";
                        question = subtraction; //puts the global variable "question" equal to the subtaction
                        answer = x - y + ""; //this is the true value of the operation
                    } else if (x < y && y < 10) {
                        y = y + 10;
                        subtraction = y - x + "";
                        question = subtraction; //puts the global variable "question" equal to the subtaction
                        answer = y - x + ""; //this is the true value of the operation
                    }
            }

            if (this.grade == 3) {

            }
        }

    }

    public void geometry() {

    }

    public void fractions() {

    }

    public void shapeCalculation() {

    }

    /*
    
    do 1 file with all questions for each grade. So 1 file for 1st grade for science general and logic... 
    Then to make sure there is a good ratio for the number of math qusetions VS other questions generated,
    generate a number, if == 1 --> math question, if > 1 it will be a question of either science general 
    and logic
    */
    public void science() {
    }

    public void general() {
    }

    public void logic() {
    }
}
