package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greendao.Question;

import java.util.List;

/**
 * Display review question model.
 */
public class exerciseWrongPage extends AppCompatActivity {

    private int count; //the number of exercise questions
    private int current; //the question that is working on
    private int questionNum; //the serial number of question
    private static List<Question> list; //the question list
    private static List<Integer> wrongList;
    private Question q;
    private exercisePage exercisepage;

    private Button btn_changeModel;
    private TextView questionNumber;
    private TextView question;
    private Button btn_hint;
    private RadioGroup radioGroup;
    private RadioButton[] radioButtons;
    private TextView explaination;
    private Button btn_previous;
    private Button btn_next;
    private ImageView image_hint; //the image of hint

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_wrong);

        /**
         * goto game page
         */
        Button btn_game = (Button) findViewById(R.id.game);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(exerciseWrongPage.this,MainActivity.class);
                intent.putExtra("fragFlag","2");
                startActivity(intent);
                finish();
            }
        });

        /**
         * Change the model of review questions
         * 0 represents view all questions; 1 represents just view wrong questions
         */
        btn_changeModel = (Button) findViewById(R.id.changeModel);
        btn_changeModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wrongList.size()==0){
                    new AlertDialog.Builder(exerciseWrongPage.this)
                            .setTitle("Reminder")
                            .setMessage("There is no wrong question.")
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //do nothing
                                }
                            }).show();
                }else {
                    if (exerciseResultPage.reviewMode == 0) {
                        exerciseResultPage.reviewMode = 1;
                        btn_changeModel.setText("REVIEW ALL QUESTIONS");
                    } else {
                        exerciseResultPage.reviewMode = 0;
                        btn_changeModel.setText("REVIEW WRONG QUESTIONS");
                    }
                    Intent intent = new Intent(exerciseWrongPage.this, exerciseWrongPage.class);
                    startActivity(intent);
                }
            }
        });

        //get the whole question list and wrong question list
        exercisepage = new exercisePage();
        list = exercisepage.getlist(); //get the question list
        wrongList = exercisepage.checkAnswer(list);
        if(exerciseResultPage.reviewMode == 0){
            btn_changeModel.setText("REVIEW ALL QUESTIONS");
            count = wrongList.size();
        }else{
            btn_changeModel.setText("REVIEW WRONG QUESTIONS");
            count = list.size();
        }

        current = 0; //represents current question ID
        questionNum = 1; //represents question number

        /**
         * Initialize controls
         */
        questionNumber = findViewById(R.id.noQuestion);
        btn_hint = findViewById(R.id.btn_hint);
        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtons = new RadioButton[3];
        radioButtons[0] = findViewById(R.id.answerA);
        radioButtons[1] = findViewById(R.id.answerB);
        radioButtons[2] = findViewById(R.id.answerC);
        explaination = findViewById(R.id.explanation);
        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        image_hint = findViewById(R.id.image_hint);

        //set the first question content
        setContent();
        //display option state
        checkOption();

        //jump to next question
        btn_next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(current < count -1){
                    //set next question content
                    questionNum++;
                    current++;
                    setContent();
                    checkOption();
                }
                else{
                    new AlertDialog.Builder(exerciseWrongPage.this)
                            .setTitle("Reminder")
                            .setMessage("This is the last question! \nAre you sure you want to quit?")
                            .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(exerciseWrongPage.this, exerciseResultPage.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel",null)
                            .show();
                }
            }

        });

        //jump tp previous question
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if the question is not the first question, go to previous one; otherwise, do not change
                if (current > 0)
                {
                    //set previous question content
                    questionNum--;
                    current--;
                    setContent();
                    checkOption();
                }
                else{
                    new AlertDialog.Builder(exerciseWrongPage.this)
                            .setTitle("Reminder")
                            .setMessage("This is the first question.")
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //do nothing
                                }
                            }).show();
                }

            }
        });

    }

    /**
     * If the option has been chosen, record it
     */
    private void checkOption(){
        radioGroup.clearCheck();
        if(q.getSelectedAnswer() != -1){
            radioButtons[q.getSelectedAnswer()].setChecked(true);
        }
        getColor(radioButtons);
    }


    /**
     * If the model is review wrong questions, only wrong questions are shown;
     * If the model is review all the questions, all the questions are shown.
     *
     * @return q - current question
     */
    public Question getQuestion(){
        Question q;
        if(exerciseResultPage.reviewMode == 0){
            q = list.get(wrongList.get(current));
        }else{
            q = list.get(current);
        }
        return q;
    }

    /**
     * Set the content of question and options
     */
    private void setContent(){
        q = getQuestion();
        //assigns a value to the control
        questionNumber.setText(questionNum + "");
        String[] strArrQ = q.getQuestion().split("\\*");
        question.setText(strArrQ[0] + "\n" + strArrQ[1]);
        radioButtons[0].setText(q.getAnswerA());
        radioButtons[1].setText(q.getAnswerB());
        radioButtons[2].setText(q.getAnswerC());
        String[] strArr = q.getExplanation().split("\\*");
        explaination.setText("\nExplaination:\n" + strArr[0] + "\n" + strArr[1] + "\n\n" + strArr[2] + "\n" + strArr[3] + "\n" + strArr[4] + "\n\n" + strArr[5] + "\n" + strArr[6] + "\n" + strArr[7] + "\n" + strArr[8] + "\n");
        image_hint.setVisibility(View.INVISIBLE);
        showPicture();
    }

    /**
     * Display hint picture.
     */
    public void showPicture(){
        switch (q.getID()){
            case 20:
                image_hint.setImageResource(R.drawable.exercise_pic20);
                image_hint.setVisibility(View.VISIBLE);
                break;
            case 21:
                image_hint.setImageResource(R.drawable.exercise_pic21);
                image_hint.setVisibility(View.VISIBLE);
                break;
            case 22:
                image_hint.setImageResource(R.drawable.exercise_pic22);
                image_hint.setVisibility(View.VISIBLE);
                break;
            case 24:
                image_hint.setImageResource(R.drawable.exercise_pic24);
                image_hint.setVisibility(View.VISIBLE);
                break;
            case 27:
                image_hint.setImageResource(R.drawable.exercise_pic27);
                image_hint.setVisibility(View.VISIBLE);
                break;
            case 30:
                image_hint.setImageResource(R.drawable.exercise_pic30);
                image_hint.setVisibility(View.VISIBLE);
                break;
            case 31:
                image_hint.setImageResource(R.drawable.exercise_pic30);
                image_hint.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * Set correct answer is green; and set the wrong answer that was selected is red
     *
     * @param radioButtons
     */
    public void getColor(RadioButton[] radioButtons){
        for(int i = 0; i < 3; i++){
            radioButtons[i].setTextColor(Color.BLACK);
        }
        if(q.getAnswer() != q.getSelectedAnswer() && q.getSelectedAnswer() != -1){
            radioButtons[q.getSelectedAnswer()].setTextColor(android.graphics.Color.RED);
            radioButtons[q.getAnswer()].setTextColor(android.graphics.Color.GREEN);
        }else{
            radioButtons[q.getAnswer()].setTextColor(android.graphics.Color.GREEN);
        }
    }

}
