/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Félix Dupont
 */
public class Question {

    //USED FOR FRACTION ANSWERS
    public final int ADDITION = 0;
    public final int SUBTRACTION = 1;
    public final int MULTIPLICATION = 2;
    public final int DIVISION = 3;

    private int type; //math(0), science(1), general(2), logic(3)
    private int grade;
    private String question;
    private String answer;
    private String[] choices;
    private static int[] countKeeper = {0, 0, 0, 0, 0, 0}; //keeps the count of which question has been read from the file for each grade.
    private int iterator = 0;
    private static int[] numberOfQuestions = {-1, -1, -1, -1, -1, -1};

    public Question(int grade) throws FileNotFoundException {
        this.grade = grade;
        choices = new String[4];
        /*
        This part of the code checks if all pre-made questions for all grades has been put into the array of questions.
        When all questions have been processed, it creates a math question
        At the end of the process, all the questions will be shuffled to make sure the quiz is not too easy.
         */
        if (numberOfQuestions[grade - 1] == -1) {
            File file = null;

            if (grade == 1) {
                numberOfQuestions[grade - 1]++; //bring the value to 0
                file = new File("grade1.txt");
            }
            if (grade == 2) {
                numberOfQuestions[grade - 1]++; //bring the value to 0
                file = new File("grade2.txt");
            }
            if (grade == 3) {
                numberOfQuestions[grade - 1]++; //bring the value to 0
                file = new File("grade3.txt");
            }
            if (grade == 4) {
                numberOfQuestions[grade - 1]++; //bring the value to 0
                file = new File("grade4.txt");
            }
            if (grade == 5) {
                numberOfQuestions[grade - 1]++; //bring the value to 0
                file = new File("grade5.txt");
            }
            if (grade == 6) {
                numberOfQuestions[grade - 1]++; //bring the value to 0
                file = new File("grade6.txt");
            }

            Scanner input = new Scanner(file);

//            while (input.hasNext()) {
//
//                input.hasNext();
//                input.hasNext();
//                input.hasNext();
//                input.hasNext();
//                input.hasNext();
//
//                numberOfQuestions[grade - 1]++;
//            }
        }
        if (grade == 1) {
            if (iterator < countKeeper[grade - 1]) {
                File file = new File("grade1.txt");
                readFile(file);
            } else {
                math();
            }
        }

        if (grade == 2) {
            if (iterator < countKeeper[grade - 1]) {
                File file = new File("grade2.txt");
                readFile(file);
            } else {
                math();
            }
        }

        if (grade == 3) {
            if (iterator < countKeeper[grade - 1]) {
                File file = new File("grade3.txt");
                readFile(file);
            } else {
                math();
            }
        }

        if (grade == 4) {
            if (iterator < countKeeper[grade - 1]) {
                File file = new File("grade4.txt");
                readFile(file);
            } else {
                math();
            }
        }

        if (grade == 5) {
            if (iterator < countKeeper[grade - 1]) {
                File file = new File("grade5.txt");
                readFile(file);
            } else {
                math();
            }
        }

        if (grade == 6) {
            if (iterator < countKeeper[grade - 1]) {
                File file = new File("grade6.txt");
                readFile(file);
            } else {
                math();
            }
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

    public static void setNumberOfQuestions(int[] numberOfQuestions) {

    }

    public void math() {

        int mathType = (int) (Math.random() * 6);
        switch (type) {

            case 0:
                arithmetic();
                break;
            case 1:
                geometry();
                break;
            case 3:
                fractions();
                break;
            case 4:
                shapeCalculation();
                break;
            case 5:
                priorityOperations();
                break;
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
                    arithmeticIntegerAnswers(answer);
                    break;

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
                    arithmeticIntegerAnswers(answer);
                    break;
            }
        }

        if (this.grade == 2) { //MISTAKE HERE

            int x = (int) (Math.random() * 100);//variable 1 for calculations. This variable will be 2 digits
            int y = (int) (Math.random() * 10); //variable 2 for calculations. This variable will be 1 digit

            int operation = (int) (Math.random() * 2);

            switch (operation) {

                case 0:
                    String addition = x + " + " + y;
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation
                    arithmeticIntegerAnswers(answer);
                    break;

                case 1:
                    String subtraction;
                    if (x >= y) { //to make sure it is a double digit subtracting a single digit resulting in a postive answer
                        x = x + 10;
                        subtraction = x + " - " + y;
                        question = subtraction; //puts the global variable "question" equal to the subtaction
                        answer = x - y + ""; //this is the true value of the operation
                        arithmeticIntegerAnswers(answer);
                        break;
                    } else if (x < y) { //to make sure it is a double digit subtracting a single digit resulting in a postive answer
                        y = y + 10;
                        subtraction = y + " - " + x;
                        question = subtraction; //puts the global variable "question" equal to the subtaction
                        answer = y - x + ""; //this is the true value of the operation
                        arithmeticIntegerAnswers(answer);
                        break;
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
                    arithmeticIntegerAnswers(answer);
                    break;

                case 1:
                    String subtraction;
                    subtraction = x + " - " + y;
                    question = subtraction; //puts the global variable "question" equal to the subtaction
                    answer = x - y + ""; //this is the true value of the operation
                    arithmeticIntegerAnswers(answer);
                    break;

                case 2:
                    String multiplication = z + " x " + w;
                    question = multiplication;  //puts the global variable "question" equal to the addition
                    answer = z * w + ""; //this is the true value of the operation
                    arithmeticIntegerAnswers(answer);
                    break;
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
            int v = ((int) ((Math.random() * 10) + 1));

            int operation = (int) (Math.random() * 4);

            switch (operation) {

                case 0:
                    String addition = (x / 10.0) + " + " + (y / 10.0);
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation
                    arithmeticDoubleAnswers(answer);
                    break;

                case 1:
                    String subtraction;
                    subtraction = (x / 100.0) + " - " + (y / 100.0);
                    question = subtraction; //puts the global variable "question" equal to the subtaction
                    answer = x - y + ""; //this is the true value of the operation
                    arithmeticDoubleAnswers(answer);
                    break;

                case 2:
                    String multiplication = y + " x " + z;
                    question = multiplication;
                    answer = y * z + "";
                    arithmeticIntegerAnswers(answer);
                    break;

                case 3:
                    //looks for a division that won't have any reminders
                    while (u % v != 0) {
                        v = (int) (Math.random() * 10) + 1;
                    }

                    String division = u + " / " + v;
                    question = division;
                    answer = u / v + "";
                    arithmeticIntegerAnswers(answer);
                    break;

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
            int v = ((int) (Math.random() * 12)) + 1; //makes sure there is no division by 0 and that all answers are round

            int operation = (int) (Math.random() * 4);

            switch (operation) {

                case 0:
                    String addition = (x / 10.0) + " + " + (y / 10.0);
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation
                    arithmeticDoubleAnswers(answer);
                    break;

                case 1:
                    String subtraction;
                    subtraction = (x / 10.0) + " - " + (y / 10.0);
                    question = subtraction; //puts the global variable "question" equal to the subtaction
                    answer = x - y + ""; //this is the true value of the operation
                    arithmeticDoubleAnswers(answer);
                    break;

                case 2:
                    //if the first number is greater than 12, the second number has only 1 digit
                    if (y > 13) {
                        z -= 3;
                    }
                    String multiplication = y + " x " + z;
                    question = multiplication;
                    answer = y * z + "";
                    arithmeticIntegerAnswers(answer);
                    break;

                case 3:
                    //looks for a division that won't have any reminders
                    while (u % v != 0) {
                        v = (int) (Math.random() * 12) + 1;
                    }

                    String division = u + " / " + v;
                    question = division;
                    answer = u / v + "";
                    arithmeticIntegerAnswers(answer);
                    break;

            }

        }

        if (this.grade == 6) {

            //These variables will have 1 decimal place and these 2 variables will be used for addition and subtraction
            int w = (int) (Math.random() * 10000);
            int x = (int) (Math.random() * 10000);
            //These 2 variables will be used for multiplication
            int y = (int) (Math.random() * 1000);
            int z = (int) (Math.random() * 100);
            //These 2 variables will be used for division
            int u = (int) (Math.random() * 20);
            int v = ((int) ((Math.random() * 12)) + 1);

            int operation = (int) (Math.random() * 4);

            switch (operation) {

                case 0:
                    String addition = (x / 100.0) + " + " + (y / 100.0);
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation
                    arithmeticDoubleAnswers(answer);
                    break;

                case 1:
                    String subtraction;
                    subtraction = (x / 100.0) + " - " + (y / 100.0);
                    question = subtraction; //puts the global variable "question" equal to the subtaction
                    answer = x - y + ""; //this is the true value of the operation
                    arithmeticDoubleAnswers(answer);
                    break;

                case 2:
                    //if the first number is greater than 12, the second number has only 1 digit
                    if (y > 13) {
                        z -= 3;
                    }
                    String multiplication = y + " x " + z;
                    question = multiplication;
                    answer = y * z + "";
                    arithmeticIntegerAnswers(answer);
                    break;

                case 3:
                    while (u % v != 0) {
                        v = (int) (Math.random() * 12) + 1;
                    }
                    String division = u + " / " + v;
                    question = division;
                    answer = u / v + "";
                    arithmeticIntegerAnswers(answer);
                    break;

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

        if (grade == 1) {

            math();
        }

        if (grade == 2) {
            /*
            Addition = 0
            Subtaction = 1
            Multiplication = 2
            Division = 3
             */
            int num1 = (int) (Math.random() * 6);
            int num2 = (int) (Math.random() * 6);
            int den1 = (int) (Math.random() * 5) + 1;
            int den2 = den1;

            int operation = (int) (Math.random() * 2);

            switch (operation) {
                //ADDITION
                case 0:
                    question = num1 + "/" + den1 + " + " + num2 + "/" + den2;
                    answer = (num1 / den1) + "+" + (num2 / den2);
                    fractionAnswers(answer, 0, num1, num2, den1, den2);
                    break;

                //SUBTRACTION 
                case 1:
                    if ((num1 / den1) > (num2 / den1)) {
                        question = num1 + "/" + den1 + " - " + num2 + "/" + den1;
                        answer = (num1 / den1) + "-" + (num2 / den2);
                    } else {
                        question = num2 + "/" + den1 + " - " + num1 + "/" + den1;
                        answer = (num2 / den1) + "-" + (num1 / den2);
                    }
                    fractionAnswers(answer, 1, num1, num2, den1, den2);
                    break;
            }

        }

        if (grade == 3) {

            int num1 = (int) (Math.random() * 6);
            int num2 = (int) (Math.random() * 6);
            int den1 = (int) (Math.random() * 5) + 1; //to avoid division by 0
            int den2 = (int) (Math.random() * 5) + 1; //to avoid division by 0

            int operation = (int) (Math.random() * 3);

            switch (operation) {
                //ADDITION  
                case 0:
                    question = num1 + "/" + den1 + " + " + num2 + "/" + den1;
                    answer = (num1 / den1) + "+" + (num2 / den1);
                    fractionAnswers(answer, 0, num1, num2, den1, den2);
                    break;

                //SUBTRACTION 
                case 1:
                    if ((num1 / den1) > (num2 / den1)) {
                        question = num1 + "/" + den1 + " - " + num2 + "/" + den1;
                        answer = (num1 / den1) + "-" + (num2 / den1);
                    } else {
                        question = num2 + "/" + den1 + " - " + num1 + "/" + den1;
                        answer = (num2 / den1) + "-" + (num1 / den1);
                    }
                    fractionAnswers(answer, 1, num1, num2, den1, den2);
                    break;

                //MULTIPLICATION
                case 2:
                    question = num1 + "/" + den1 + " x " + num2 + "/" + den2;
                    answer = (num1 / den1) + "*" + (num2 / den2);
                    fractionAnswers(answer, 2, num1, num2, den1, den2);
                    break;

            }

        }

        if (grade == 4) {

            int num1 = (int) (Math.random() * 6);
            int num2 = (int) (Math.random() * 6);
            int den1 = (int) (Math.random() * 5) + 1; //to avoid division by 0
            int den2 = (int) (Math.random() * 5) + 1; //to avoid division by 0

            int operation = (int) (Math.random() * 3);

            switch (operation) {
                //ADDITION
                case 0:
                    question = num1 + "/" + den1 + " + " + num2 + "/" + den1;
                    answer = (num1 / den1) + "+" + (num2 / den2);
                    fractionAnswers(answer, 0, num1, num2, den1, den2);
                    break;

                //SUBTRACTION 
                case 1:
                    question = num1 + "/" + den1 + " - " + num2 + "/" + den1;
                    answer = (num1 / den1) + "-" + (num2 / den2);
                    fractionAnswers(answer, 1, num1, num2, den1, den2);
                    break;

                //MULTIPLICATION
                case 2:
                    question = num1 + "/" + den1 + " x " + num2 + "/" + den2;
                    answer = (num1 / den1) + "*" + (num2 / den2);
                    fractionAnswers(answer, 2, num1, num2, den1, den2);
                    break;

            }
        }

        if (grade == 5) {
            int num1 = (int) (Math.random() * 11);
            int num2 = (int) (Math.random() * 10) + 1; //to avoid division by 0
            int den1 = (int) (Math.random() * 10) + 1; //to avoid division by 0
            int den2 = (int) (Math.random() * 10) + 1; //to avoid division by 0

            int operation = (int) (Math.random() * 4);

            switch (operation) {
                //ADDITION
                case 0:
                    question = num1 + "/" + den1 + " + " + num2 + "/" + den1;
                    answer = (num1 / den1) + "+" + (num2 / den2);
                    fractionAnswers(answer, 0, num1, num2, den1, den2);
                    break;

                //SUBTRACTION 
                case 1:
                    question = num1 + "/" + den1 + " - " + num2 + "/" + den1;
                    answer = (num1 / den1) + "-" + (num2 / den2);
                    fractionAnswers(answer, 1, num1, num2, den1, den2);
                    break;

                //MULTIPLICATION
                case 2:
                    question = num1 + "/" + den1 + " x " + num2 + "/" + den2;
                    answer = (num1 / den1) + "*" + (num2 / den2);
                    fractionAnswers(answer, 2, num1, num2, den1, den2);
                    break;

                //DIVISION
                case 3:
                    question = num1 + "/" + den1 + " / " + num2 + "/" + den2;
                    answer = (num1 / den1) + "/" + (num2 / den2);
                    fractionAnswers(answer, 3, num1, num2, den1, den2);
                    break;

            }
        }

        if (grade == 6) {
            int num1 = (int) (Math.random() * 16);
            int num2 = (int) (Math.random() * 15) + 1; //to avoid division by 0
            int den1 = (int) (Math.random() * 15) + 1; //to avoid division by 0
            int den2 = (int) (Math.random() * 15) + 1; //to avoid division by 0

            int operation = (int) (Math.random() * 4);

            switch (operation) {
                //ADDITION
                case 0:
                    question = num1 + "/" + den1 + " + " + num2 + "/" + den1;
                    answer = (num1 / den1) + "+" + (num2 / den2);
                    fractionAnswers(answer, 0, num1, num2, den1, den2);
                    break;

                //SUBTRACTION 
                case 1:
                    question = num1 + "/" + den1 + " - " + num2 + "/" + den1;
                    answer = (num1 / den1) + "-" + (num2 / den2);
                    fractionAnswers(answer, 1, num1, num2, den1, den2);
                    break;

                //MULTIPLICATION
                case 2:
                    question = num1 + "/" + den1 + " x " + num2 + "/" + den2;
                    answer = (num1 / den1) + "*" + (num2 / den2);
                    fractionAnswers(answer, 2, num1, num2, den1, den2);
                    break;

                //DIVISION
                case 3:
                    question = num1 + "/" + den1 + " / " + num2 + "/" + den2;
                    answer = (num1 / den1) + "/" + (num2 / den2);
                    fractionAnswers(answer, 3, num1, num2, den1, den2);
                    break;

            }
        }

    }

    public void fractionAnswers(String answer, int type, int num1, int num2, int den1, int den2) {

        if (grade == 2) {

            if (type == 0) {
                String c1 = (num1 + num2) + "/" + (den1 + den2);
                String c2 = (num1 + num2 + 1) + "/" + den1;
                String c3 = (Math.max(num1, num2) - Math.min(num1, num2)) + "/" + den1;

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            }

            if (type == 1) {
                String c1 = (num1 - num2) + "/" + (den1 + den2);
                String c2 = (num1 - num2 + 1) + "/" + den1;
                String c3 = (num1 + num2) + "/" + den1;

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            }
        }
        //creating wrong answers for addition questions for grades 3 and over
        if (grade >= 3 && type == 0) {

            String c1 = (num1 + num2) + "/" + (den1 + den2);
            String c2 = (num1 + num2) + "/" + (den1 * den2);
            String c3 = ((num1 * den1) + (num2 * den2)) + "/" + (den1 * den2);

            choices[0] = answer;
            choices[1] = c1;
            choices[2] = c2;
            choices[3] = c3;

        }
        //creating wrong answers for subtraction of fractions for grades 3 and over
        if (grade >= 3 && type == 1) {

            String c1 = (num1 - num2) + "/" + (den1 * den2);
            String c2 = (num1 - num2) + "/" + (den1 - den2);
            String c3 = ((num1 * den1) - (num2 * den2)) + "/" + (den1 * den2);

            choices[0] = answer;
            choices[1] = c1;
            choices[2] = c2;
            choices[3] = c3;
        }
        //creating wrong answers for multiplication of fractions for grades 3 and over
        if (grade >= 3 && type == 2) {

            String c1 = (num1 * num2) + "/" + (den1 + den2);
            String c2 = (num1 + num2) + "/" + (den1 + den2);
            String c3 = ((num1 * den2) + (num2 * den1)) + "/" + (den1 * den2);

            choices[0] = answer;
            choices[1] = c1;
            choices[2] = c2;
            choices[3] = c3;
        }

        if (grade >= 3 && type == 3) {

            String c1 = (num1 * num2) + "/" + (den1 * den2);
            String c2 = (num1 * den1) + "/" + (num2 * den2);
            String c3 = (den1 * den2) + "/" + (num1 * num2);
            
            choices[0] = answer;
            choices[1] = c1;
            choices[2] = c2;
            choices[3] = c3;
        }
        //shuffles the order of the answers 
        shuffle();
    }

    public void shapeCalculation() {

    }

    /*we need a metod to create answers for each category, to simulate to plausible answers. 
    for arithmetic, i will need to consider decimals
    for priority of operations, i will have to simulate mistakes if the way the player made a 
    mistake in the priorities
     */
    public void arithmeticDoubleAnswers(String answer) {

        double numericalAnswer = Double.parseDouble(this.answer);

        if (grade == 4) {

            if (numericalAnswer < 10.0 && numericalAnswer > 2.0) {

                String c1 = String.format("%.3s", numericalAnswer - 2 + "");
                String c2 = String.format("%.3s", numericalAnswer - 1 + "");
                String c3 = String.format("%.3s", numericalAnswer + 1 + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            } else if (numericalAnswer < 3.0) {

                String c1 = String.format("%.3s", numericalAnswer + 1 + "");
                String c2 = String.format("%.3s", numericalAnswer + 1 + "");
                String c3 = String.format("%.3s", numericalAnswer + 1 + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            } else if (numericalAnswer > 9.0) {

                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);

                String c1 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            }
        }

        if (this.grade == 5) { //MISTAKE HERE

            if (numericalAnswer < 10.0 && numericalAnswer > 2.0) {

                String c1 = String.format("%.3s", numericalAnswer - 2 + "");
                String c2 = String.format("%.3s", numericalAnswer - 1 + "");
                String c3 = String.format("%.3s", numericalAnswer + 1 + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;

                //System.out.println("mistake A:");
            } else if (numericalAnswer <= 2.0) {

                String c1 = String.format("%.3s", numericalAnswer + 1 + "");
                String c2 = String.format("%.3s", numericalAnswer + 1 + "");
                String c3 = String.format("%.3s", numericalAnswer + 1 + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;

                //System.out.println("mistake B:");
            } else if (numericalAnswer > 9.9 && numericalAnswer < 100.0) {

                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);

                String c1 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;

                //System.out.println("mistake C:");
            } else if (numericalAnswer > 99.9) {

                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);

                String c1 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;

                //System.out.println("mistake D:");
            }
        }

        if (grade == 6) { //MISTAKE HERE
            if (numericalAnswer < 10.00 && numericalAnswer > 2.00) {
                String c1 = String.format("%.4s", numericalAnswer - 2 + "");
                String c2 = String.format("%.4s", numericalAnswer - 1 + "");
                String c3 = String.format("%.4s", numericalAnswer + 1 + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            } else if (numericalAnswer < 3.0) {
                System.out.println("b");
                String c1 = String.format("%.4s", numericalAnswer + 1 + "");
                String c2 = String.format("%.4s", numericalAnswer + 1 + "");
                String c3 = String.format("%.4s", numericalAnswer + 1 + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            } else if (numericalAnswer > 9.00 && numericalAnswer < 100.00) {
                System.out.println("c");
                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);
                
                String c1 = String.format("%.5s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.5s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.5s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            } else if (numericalAnswer > 99.99) {

                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);

                String c1 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = answer;
                choices[1] = c1;
                choices[2] = c2;
                choices[3] = c3;
            }

        }

        shuffle(); //shuffles the order of the answers so that the answer is not always at the same place

    }

    public void arithmeticIntegerAnswers(String answer) {

        int numericalAnswer = Integer.parseInt(this.answer);

        if (numericalAnswer < 10 && numericalAnswer > 2) {

            String c1 = numericalAnswer - 2 + "";
            String c2 = numericalAnswer - 1 + "";
            String c3 = numericalAnswer + 1 + "";

            choices[0] = answer;
            choices[1] = c1;
            choices[2] = c2;
            choices[3] = c3;
        } else if (numericalAnswer < 3) {

            String c1 = numericalAnswer + 1 + "";
            String c2 = numericalAnswer + 2 + "";
            String c3 = numericalAnswer + 3 + "";

            choices[0] = answer;
            choices[1] = c1;
            choices[2] = c2;
            choices[3] = c3;
        } else if (numericalAnswer > 9) {

            int range = (int) (0.2 * numericalAnswer);
            int minRange = numericalAnswer - range;
            int maxRange = numericalAnswer + range;

            String c1 = ((int) (Math.random() * maxRange)) - ((int) (Math.random() * minRange)) + "";
            String c2 = ((int) (Math.random() * maxRange)) - ((int) (Math.random() * minRange)) + "";
            String c3 = ((int) (Math.random() * maxRange)) - ((int) (Math.random() * minRange)) + "";

            choices[0] = answer;
            choices[1] = c1;
            choices[2] = c2;
            choices[3] = c3;

        }

        shuffle(); //shuffles the order of the answers so that the answer is not always at the same place

    }

    /*
    do 1 file with all questions for each grade. So 1 file for 1st grade for science general and logic... 
    Then to make sure there is a good ratio for the number of math qusetions VS other questions generated,
    generate a number, if == 1 --> math question, if > 1 it will be a question of either science general 
    and logic
     */
    public void readFile(File file) throws FileNotFoundException {

        Scanner input = new Scanner(file);

        while (iterator < countKeeper[grade - 1]) {

            input.nextLine(); //question
            input.nextLine(); //answer 1
            input.nextLine();//answer 2
            input.nextLine();//answer 3
            input.nextLine();//answer 4
            iterator += 1;

        }

        question = input.nextLine();
        String answer1 = input.nextLine();
        String answer2 = input.nextLine();
        String answer3 = input.nextLine();
        String answer4 = input.nextLine();

        choices[0] = answer1; //correct answer
        answer = answer1;
        choices[1] = answer2;
        choices[2] = answer3;
        choices[3] = answer4;

        shuffle(); //shuffles the order of the answers so that the answer is not always at the same place


        /*
        if file has a next line, it will increase countkeeper. This ensures that
        if the file is over, it will go to the math questions.
         */
        if (input.hasNextLine()) {
            countKeeper[grade - 1] += 1;
        }

    }

    public int getTime() {
        switch (grade) {
            case 1:
                return 7;
            case 2:
            case 3:
                return 8;
            case 4:
            case 5:
                return 9;
            case 6:
                return 10;
            default:
                return -1;
        }
    }

    public void shuffle() {

        ArrayList<String> unshuffled = new ArrayList<String>(Arrays.asList(choices));
        Collections.shuffle(unshuffled);
        choices = unshuffled.toArray(new String[unshuffled.size()]);
    }

    public int getScore() {
        switch (grade) {
            case 1:
                return 10;
            case 2:
                return 15;
            case 3:
                return 30;
            case 4:
                return 35;
            case 5:
                return 40;
            case 6:
                return 50;
            default:
                return -1;
        }
    }

}
