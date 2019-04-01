package trivial;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class QuestionList {

    private ArrayList<Question>[] gradeQuestions; //List of questions from Grade 1 to Grade 6

    private Iterator<Question>[] gradeIterators;//List iterator for all grades

    public QuestionList() throws FileNotFoundException {

        //Initializing the array
        gradeQuestions = new ArrayList[6];
        for (int i = 0; i < 6; i++) {
            gradeQuestions[i] = new ArrayList();
        }

        //Creating the questions in the different ArrayLists
        for (int i = 0; i < 25; i++) {
            gradeQuestions[0].add(new Question(1));
//            gradeQuestions[5].add(new Question(6));
//            if (i < 50) {
//                gradeQuestions[1].add(new Question(2));
//            }
//            if (i < 45) {
//                gradeQuestions[2].add(new Question(3));
//            }
//            if (i < 40) {
//                gradeQuestions[3].add(new Question(4));
//            }
//            if (i < 35) {
//                gradeQuestions[4].add(new Question(5));
//            }
        }

        //Shuffuling the questions
        shuffleQuestions();

        //Assigning each iterators to its list
        gradeIterators = new Iterator[6];
        for (int i = 0; i < gradeIterators.length; i++) {
            gradeIterators[i] = gradeQuestions[i].iterator();
        }

    }

    //Method that shuffles the arrays of questions
    private void shuffleQuestions() {
        Collections.shuffle(gradeQuestions[0]);
        Collections.shuffle(gradeQuestions[1]);
        Collections.shuffle(gradeQuestions[2]);
        Collections.shuffle(gradeQuestions[3]);
        Collections.shuffle(gradeQuestions[4]);
        Collections.shuffle(gradeQuestions[5]);

    }

    //returns the next question of the specified grade
    public Question getQuestion(int grade) {
        //if it reaches the end of the list, it will start from the start with questions the player has already answered
        if (!gradeIterators[grade - 1].hasNext()) {
            gradeIterators[grade - 1] = gradeQuestions[grade - 1].iterator();
        }

        return gradeIterators[grade - 1].next();
    }

}
