/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trivial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Félix Dupont
 */
public class QuestionList {

    private ArrayList<Question>[] gradeQuestions; //List of questions from Grade 1 to Grade 6
    
    private Iterator<Question>[] gradeIterators;//List iterator for all grades
    
    

    public QuestionList() {
        gradeQuestions= new ArrayList[6];
        for(int i=0;i<6;i++){
        gradeQuestions[i]=new ArrayList();
        }
        for (int i = 0; i < 60; i++) {
            gradeQuestions[0].add(new Question(1));
            gradeQuestions[5].add(new Question(6));
            if (i < 50) {
                gradeQuestions[1].add(new Question(2));
            }
            if (i < 45) {
                gradeQuestions[2].add(new Question(3));
            }
            if (i < 40) {
                gradeQuestions[3].add(new Question(4));
            }
            if (i < 35) {
                gradeQuestions[4].add(new Question(5));
            }
        }
        shuffleQuestions();   
        gradeIterators= new Iterator[6];
        gradeIterators[0]= gradeQuestions[0].iterator();
        gradeIterators[1]= gradeQuestions[1].iterator();
        gradeIterators[2]= gradeQuestions[2].iterator();
        gradeIterators[3]= gradeQuestions[3].iterator();
        gradeIterators[4]= gradeQuestions[4].iterator();
        gradeIterators[5]= gradeQuestions[5].iterator();

    }
    
    
    private void shuffleQuestions(){
    Collections.shuffle(gradeQuestions[0]);
    Collections.shuffle(gradeQuestions[1]);
    Collections.shuffle(gradeQuestions[2]);
    Collections.shuffle(gradeQuestions[3]);
    Collections.shuffle(gradeQuestions[4]);
    Collections.shuffle(gradeQuestions[5]);
    
    }

    public Question getQuestion(int grade){
        if(!gradeIterators[grade-1].hasNext())
            gradeIterators[grade-1]=gradeQuestions[grade-1].iterator();
            
            return gradeIterators[grade-1].next();
    }
    
}
