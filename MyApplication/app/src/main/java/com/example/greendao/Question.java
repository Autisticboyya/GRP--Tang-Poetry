package com.example.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Exercise question entity
 */
@Entity
public class Question {
    //@Id
    private int ID;
    @Property(nameInDb = "Question")
    private String question;
    @Property(nameInDb = "AnswerA")
    private String answerA;
    @Property(nameInDb = "AnswerB")
    private String answerB;
    @Property(nameInDb = "AnswerC")
    private String answerC;
    @Property(nameInDb = "Answer")
    private int answer;
    @Property(nameInDb = "Explanation")
    private String explanation;
    @Property(nameInDb = "Hint")
    private String hint;
    //user select answer
    private int selectedAnswer;
    @Generated(hash = 739850926)
    public Question(int ID, String question, String answerA, String answerB,
            String answerC, int answer, String explanation, String hint,
            int selectedAnswer) {
        this.ID = ID;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answer = answer;
        this.explanation = explanation;
        this.hint = hint;
        this.selectedAnswer = selectedAnswer;
    }
    @Generated(hash = 1868476517)
    public Question() {
    }
    public String getQuestion() {
        return this.question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswerA() {
        return this.answerA;
    }
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }
    public String getAnswerB() {
        return this.answerB;
    }
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
    public String getAnswerC() {
        return this.answerC;
    }
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }
    public int getAnswer() {
        return this.answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    public String getExplanation() {
        return this.explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    public int getID() {
        return this.ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getSelectedAnswer() {
        return this.selectedAnswer;
    }
    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
    public String getHint() {
        return this.hint;
    }
    public void setHint(String hint) {
        this.hint = hint;
    }

}
