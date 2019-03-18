/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

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
    /* do we need this??*/private String[][] questions; //this 2 dimensional array stores all the questions for each grade. This array will then be shuffled.
    private String[] choices;
    private static int[] countKeeper = {0, 0, 0, 0, 0, 0}; //keeps the count of which question has been read from the file for each grade.
    private int iterator = 0;

    public Question(int grade) throws FileNotFoundException {
        this.grade = grade;

        /*
        This part of the code checks if all pre-made questions for all grades has been put into the array of questions.
        When all questions have been processed, it creates a math question
        At the end of the process, all the questions will be shuffled to make sure the quiz is not too easy.
         */
        if (grade == 1 && (iterator < countKeeper[grade - 1])) {
            File file = new File("grade1.txt");
            readFile(file);
        } else {
            math();
        }

        if (grade == 2 && (iterator< countKeeper[grade - 1])) {
            File file = new File("grade2.txt");
            readFile(file);
        } else {
            math();
        }

        if (grade == 3 && (iterator < countKeeper[grade - 1])) {
            File file = new File("grade3.txt");
            readFile(file);
        } else {
            math();
        }

        if (grade == 4 && (iterator < countKeeper[grade - 1])) {
            File file = new File("grade4.txt");
            readFile(file);
        } else {
            math();
        }

        if (grade == 5 && (iterator < countKeeper[grade - 1])) {
            File file = new File("grade5.txt");
            readFile(file);
        } else {
            math();
        }

        if (grade == 6 && (iterator < countKeeper[grade - 1])) {
            File file = new File("grade6.txt");
            readFile(file);
        } else {
            math();
        }
    }

    public void selectType(int type) {

//        switch (type) {
//
//            case 0:
//                math();
//            case 1:
//                other();
//        }
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

        int mathType = (int) (Math.random() * 6);

        switch (type) {

            case 0:
                arithmetic();
            case 1:
                geometry();
            case 3:
                fractions();
            case 4:
                shapeCalculation();
            case 5:
                priorityOperations();
        }

    }

    public void arithmetic() {

        if (this.grade == 1) {

            int x = (int) (Math.random() * 10);//variable 1 for calculations
            int y = (int) (Math.random() * 10); //variable 2 for calculations

            int operation = (int) (Math.random() * 2);

            switch (operation) {

                case 0:
                    String addition = x + " + " + y;
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    if (x >= y) { //to make sure we have a positive answer
                        subtraction = x + " - " + y;
                        answer = x - y + ""; //this is the true value of the operation
                    } else { //to make sure we have a positive answer
                        subtraction = y + " - " + x;
                        answer = y - x + ""; //this is the true value of the operation
                    }

                    question = subtraction; //puts the global variable "question" equal to the subtaction
            }
        }

        if (this.grade == 2) {

            int x = (int) (Math.random() * 100);//variable 1 for calculations. This variable will be 2 digits
            int y = (int) (Math.random() * 10); //variable 2 for calculations. This variable will be 1 digit

            int operation = (int) (Math.random() * 2);

            switch (operation) {

                case 0:
                    String addition = x + " + " + y;
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    if (x >= y && x < 10) { //to make sure it is a double digit subtracting a single digit resulting in a postive answer
                        x = x + 10;
                        subtraction = x + " - " + y;
                        question = subtraction; //puts the global variable "question" equal to the subtaction
                        answer = x - y + ""; //this is the true value of the operation
                    } else if (x < y && y < 10) { //to make sure it is a double digit subtracting a single digit resulting in a postive answer
                        y = y + 10;
                        subtraction = y + " - " + x;
                        question = subtraction; //puts the global variable "question" equal to the subtaction
                        answer = y - x + ""; //this is the true value of the operation
                    }
            }
        }

        if (this.grade == 3) {

            //These variables will be 2 digits these 2 variables will be used for addition and subtraction
            int x = (int) (Math.random() * 99);
            int y = (int) (Math.random() * 99);
            //This variable will be used for multiplication. 
            int z = (int) (Math.random() * 6);
            int w = (int) (Math.random() * 6);

            int operation = (int) (Math.random() * 3);

            switch (operation) {

                case 0:
                    String addition = x + " + " + y;
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    subtraction = x + " - " + y;
                    question = subtraction; //puts the global variable "question" equal to the subtaction
                    answer = x - y + ""; //this is the true value of the operation

                case 2:
                    String multiplication = z + " x " + w;
                    question = multiplication;  //puts the global variable "question" equal to the addition
                    answer = z * w + ""; //this is the true value of the operation
            }

        }

        if (this.grade == 4) {
            //These variables will have 1 decimal place and these 2 variables will be used for addition and subtraction
            int w = (int) (Math.random() * 1009);
            int x = (int) (Math.random() * 1009);
            //These 2 variables will be used for multiplication
            int y = (int) (Math.random() * 13);
            int z = (int) (Math.random() * 13);
            //These 2 variables will be used for division
            int u = (int) (Math.random() * 5);
            int v = ((int) ((Math.random() * 10) + 1)) * u;

            int operation = (int) (Math.random() * 4);

            switch (operation) {

                case 0:
                    String addition = (x / 10.0) + " + " + (y / 10.0);
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    subtraction = (x / 100.0) + " - " + (y / 100.0);
                    question = subtraction; //puts the global variable "question" equal to the subtaction
                    answer = x - y + ""; //this is the true value of the operation

                case 2:
                    String multiplication = y + " x " + z;
                    question = multiplication;
                    answer = y * z + "";

                case 3:
                    //looks for a division that won't have any reminders
                    while (u % v != 0) {
                        v = (int) (Math.random() * 13);
                    }

                    String division = v + " / " + u;
                    question = division;
                    answer = v / u + "";

            }
        }

        if (this.grade == 5) {

            //These variables will have 1 decimal place and these 2 variables will be used for addition and subtraction
            int w = (int) (Math.random() * 10009);
            int x = (int) (Math.random() * 10009);
            //These 2 variables will be used for multiplication
            int y = (int) (Math.random() * 100);
            int z = (int) (Math.random() * 13);
            //These 2 variables will be used for division
            int u = (int) (Math.random() * 12);
            int v = ((int) ((Math.random() * 12) + 1)) * u; //makes sure there is no division by 0 and that all answers are round

            int operation = (int) (Math.random() * 4);

            switch (operation) {

                case 0:
                    String addition = (x / 100.0) + " + " + (y / 100.0);
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    subtraction = (x / 100.0) + " - " + (y / 100.0);
                    question = subtraction; //puts the global variable "question" equal to the subtaction
                    answer = x - y + ""; //this is the true value of the operation

                case 2:
                    //if the first number is greater than 12, the second number has only 1 digit
                    if (y > 13) {
                        z -= 3;
                    }
                    String multiplication = y + " x " + z;
                    question = multiplication;
                    answer = y * z + "";

                case 3:
                    //looks for a division that won't have any reminders
                    while (u % v != 0) {
                        v = (int) (Math.random() * 13);
                    }

                    String division = v + " / " + u;
                    question = division;
                    answer = v / u + "";

            }

        }

        if (this.grade == 6) {

            //These variables will have 1 decimal place and these 2 variables will be used for addition and subtraction
            int w = (int) (Math.random() * 10099);
            int x = (int) (Math.random() * 10099);
            //These 2 variables will be used for multiplication
            int y = (int) (Math.random() * 1000);
            int z = (int) (Math.random() * 100);
            //These 2 variables will be used for division
            int u = (int) (Math.random() * 20);
            int v = ((int) ((Math.random() * 12) + 1)) * u;

            int operation = (int) (Math.random() * 4);

            switch (operation) {

                case 0:
                    String addition = (x / 100.0) + " + " + (y / 100.0);
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    subtraction = (x / 100.0) + " - " + (y / 100.0);
                    question = subtraction; //puts the global variable "question" equal to the subtaction
                    answer = x - y + ""; //this is the true value of the operation

                case 2:
                    //if the first number is greater than 12, the second number has only 1 digit
                    if (y > 13) {
                        z -= 3;
                    }
                    String multiplication = y + " x " + z;
                    question = multiplication;
                    answer = y * z + "";

                case 3:
                    String division = v + " / " + u;
                    question = division;
                    answer = v / u + "";

            }

        }
    }

    public void priorityOperations() {
        /*
        check in what grade they start doing fractions. If the player is 
        not in a grade where they do fractions, it will call the arithmetic()
        method
         */
    }

    public void geometry() {

    }

    public void fractions() {

        /*
        check in what grade they start doing fractions. If the player is 
        not in a grade where they do fractions, it will call the arithmetic()
        method
         */
        
        //I will do every operation on fractions here, adjust difficulty later
        
        //ADDITION
        int num1 = (int)(Math.random() * 100);
        int num2 = (int)(Math.random() * 100);
        int den1 = (int)(Math.random() * 100);
        int den2 = (int)(Math.random() * 100);
        
        question = num1 + "/" + den1 + " + " + num2 + "/" + den2;
        answer = (num1 / den1) + (num2 / den2) + "";
        
        //SUBTRACTION 
        
        question = num1 + "/" + den1 + " - " + num2 + "/" + den2;
        answer = (num1 / den1) - (num2 / den2) + "";
        
        //MULTIPLICATION
        question = num1 + "/" + den1 + " - " + num2 + "/" + den2;
        answer = (num1 / den1) * (num2 / den2) + "";
        
        //DIVISION
        question = num1 + "/" + den1 + " - " + num2 + "/" + den2;
        answer = (num1 / den1) / (num2 / den2) + "";
        
    }

    public void shapeCalculation() {

    }

    /*we need a metod to create answers for each category, to simulate to plausible answers. 
    for arithmetic, i will need to consider decimals
    for priority of operations, i will have to simulate mistakes if the way the player made a 
    mistake in the priorities
    */
    public void createArithmeticAnswers(double answer) {
        

        
    }
    /*
    do 1 file with all questions for each grade. So 1 file for 1st grade for science general and logic... 
    Then to make sure there is a good ratio for the number of math qusetions VS other questions generated,
    generate a number, if == 1 --> math question, if > 1 it will be a question of either science general 
    and logic
     */
    public String readFile(File file) throws FileNotFoundException {

        Scanner input = new Scanner(file);

        while (iterator < countKeeper[grade - 1]) {

            input.nextLine();
            iterator += 1;
        }
        
        String question = input.nextLine();
        countKeeper[grade - 1] += 1;
        
        return question;
    }

}
