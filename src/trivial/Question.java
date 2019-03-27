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
    /* do we need this??*/
    private String[][] questions; //this 2 dimensional array stores all the questions for each grade. This array will then be shuffled.
    private String[] choices;
    private static int[] countKeeper = {0, 0, 0, 0, 0, 0}; //keeps the count of which question has been read from the file for each grade.
    private int iterator = 0;

    public Question(int grade) throws FileNotFoundException {
        this.grade = grade;
        choices=new String[4];
        /*
        This part of the code checks if all pre-made questions for all grades has been put into the array of questions.
        When all questions have been processed, it creates a math question
        At the end of the process, all the questions will be shuffled to make sure the quiz is not too easy.
         */
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
                    String addition = (x / 10.0) + " + " + (y / 10.0);
                    question = addition; //puts the global variable "question" equal to the addition
                    answer = x + y + ""; //this is the true value of the operation

                case 1:
                    String subtraction;
                    subtraction = (x / 10.0) + " - " + (y / 10.0);
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
            int w = (int) (Math.random() * 10000);
            int x = (int) (Math.random() * 10000);
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
        int num1 = (int) (Math.random() * 100);
        int num2 = (int) (Math.random() * 100);
        int den1 = (int) (Math.random() * 100);
        int den2 = (int) (Math.random() * 100);
       
        question = num1 + "/" + den1 + " + " + num2 + "/" + den2;
        answer = (num1 / den1) + (num2 / den2) + "";

        //SUBTRACTION 
        question = num1 + "/" + den1 + " - " + num2 + "/" + den2;
        answer = (num1 / den1) - (num2 / den2) + "";

        //MULTIPLICATION
        question = num1 + "/" + den1 + " - " + num2 + "/" + den2;
        answer = (num1 / den1) * (num2 / den2) + "";

        //DIVISION
        System.out.println(num1+" "+num2+" "+den1+" "+den2);
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
    public void arithmeticDoubleAnswers(String answer) {

        double numericalAnswer = Double.parseDouble(this.answer);

        if (grade == 4) {

            if (numericalAnswer < 10.0 && numericalAnswer > 2.0) {

                String c1 = String.format("%.3s", numericalAnswer - 2 + "");
                String c2 = String.format("%.3s", numericalAnswer - 1 + "");
                String c3 = String.format("%.3s", numericalAnswer + 1 + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            } else if (numericalAnswer < 3.0) {

                String c1 = String.format("%.3s", numericalAnswer + 1 + "");
                String c2 = String.format("%.3s", numericalAnswer + 1 + "");
                String c3 = String.format("%.3s", numericalAnswer + 1 + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            } else if (numericalAnswer > 9.0) {

                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);

                String c1 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            }
        }

        if (this.grade == 5) {

            if (numericalAnswer < 10.0 && numericalAnswer > 2.0) {

                String c1 = String.format("%.3s", numericalAnswer - 2 + "");
                String c2 = String.format("%.3s", numericalAnswer - 1 + "");
                String c3 = String.format("%.3s", numericalAnswer + 1 + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            } else if (numericalAnswer < 3.0) {

                String c1 = String.format("%.3s", numericalAnswer + 1 + "");
                String c2 = String.format("%.3s", numericalAnswer + 1 + "");
                String c3 = String.format("%.3s", numericalAnswer + 1 + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            } else if (numericalAnswer > 9.0 && numericalAnswer < 100.0) {

                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);

                String c1 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.4s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            } else if (numericalAnswer > 99.9 && numericalAnswer < 1001.0) {

                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);

                String c1 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.6s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            }

        }

        if (grade == 6) {
            if (numericalAnswer < 10.00 && numericalAnswer > 2.00) {

                String c1 = String.format("%.4s", numericalAnswer - 2 + "");
                String c2 = String.format("%.4s", numericalAnswer - 1 + "");
                String c3 = String.format("%.4s", numericalAnswer + 1 + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            } else if (numericalAnswer < 3.0) {

                String c1 = String.format("%.4s", numericalAnswer + 1 + "");
                String c2 = String.format("%.4s", numericalAnswer + 1 + "");
                String c3 = String.format("%.4s", numericalAnswer + 1 + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            } else if (numericalAnswer > 9.00 && numericalAnswer < 100.00) {

                double minRange = numericalAnswer - (0.2 * numericalAnswer);
                double maxRange = numericalAnswer + (0.2 * numericalAnswer);

                String c1 = String.format("%.5s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c2 = String.format("%.5s", (Math.random() * maxRange) - (Math.random() * minRange) + "");
                String c3 = String.format("%.5s", (Math.random() * maxRange) - (Math.random() * minRange) + "");

                choices[0] = c1;
                choices[1] = c2;
                choices[2] = c3;
            }

        }

    }

    public void arithmeticIntegerAnswers(String answer) {

        int numericalAnswer = Integer.parseInt(this.answer);

        if (numericalAnswer < 10 && numericalAnswer > 2) {

            String c1 = numericalAnswer - 2 + "";
            String c2 = numericalAnswer - 1 + "";
            String c3 = numericalAnswer + 1 + "";

            choices[0] = c1;
            choices[1] = c2;
            choices[2] = c3;
        } else if (numericalAnswer < 3) {

            String c1 = numericalAnswer + 1 + "";
            String c2 = numericalAnswer + 2 + "";
            String c3 = numericalAnswer + 3 + "";

            choices[0] = c1;
            choices[1] = c2;
            choices[2] = c3;
        } else if (numericalAnswer > 9) {

            int range = (int) (0.2 * numericalAnswer);
            int minRange = numericalAnswer - range;
            int maxRange = numericalAnswer + range;

            String c1 = ((int) (Math.random() * maxRange)) - ((int) (Math.random() * minRange)) + "";
            String c2 = ((int) (Math.random() * maxRange)) - ((int) (Math.random() * minRange)) + "";
            String c3 = ((int) (Math.random() * maxRange)) - ((int) (Math.random() * minRange)) + "";

            choices[0] = c1;
            choices[1] = c2;
            choices[2] = c3;
        }

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
            iterator += 1;
        }

        question = input.nextLine();
        String answer1 = input.nextLine();
        String answer2 = input.nextLine();
        String answer3 = input.nextLine();
        String answer4 = input.nextLine();
        
        choices[0] = answer1; //correct answer
        choices[1] = answer2;
        choices[2] = answer3;
        choices[3] = answer4;
        
        ArrayList<String> unshuffled = new ArrayList<String>(Arrays.asList(choices));
        Collections.shuffle(unshuffled);
        choices = unshuffled.toArray(new String[unshuffled.size()]);

        /*
        if file has a next line, it will increase countkeeper. This ensures that
        if the file is over, it will go to the math questions.
        */
        if (input.hasNextLine()) { 
        countKeeper[grade - 1] += 1;
        }

        System.out.println("Question: " + question);
        System.out.println("Answer1: " + answer1);
        System.out.println("Answer2: " + answer2);
        System.out.println("Answer3: " + answer3);
        

    }

}
