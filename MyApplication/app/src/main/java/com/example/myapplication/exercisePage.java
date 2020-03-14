package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greendao.DaoSession;
import com.example.greendao.Question;
import com.example.greendao.QuestionDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Main exercise page;
 * Display exercise question and options.
 */
public class exercisePage extends AppCompatActivity {
    private QuestionDao questionDao;

    private int count; //the number of exercise questions
    private int current; //the question that is working on
    private int questionNum; //the serial number of question
    private static List<Question> list; //the question list
    private static Question q;

    private TextView questionNumber;
    private TextView question;
    private Button btn_hint;
    private RadioGroup radioGroup;
    private RadioButton[] radioButtons;
    private Button btn_previous;
    private Button btn_next;
    private ImageView image_hint; //the image of hint

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);

        /**
         * goto game page
         */
        Button btn_game = (Button)findViewById(R.id.game);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(exercisePage.this, gamePage.class);
                startActivity(intent);
            }
        });

        /**
         * goto finish page to display the result of exercise
         */
        Button btn_finish = (Button) findViewById(R.id.finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(exercisePage.this, exerciseResultPage.class);
                startActivity(intent);
            }
        });

        DaoSession daoSession = PoemList.getDaoSession();
        questionDao = daoSession.getQuestionDao();

        //initialize database
        initData();

        list = getQuestion(); //get the question list from database
        count = list.size();
        current = 0; //current exercise question ID
        questionNum = 1; //exercise question number

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
        btn_previous = findViewById(R.id.btn_previous);
        btn_next = findViewById(R.id.btn_next);
        image_hint = findViewById(R.id.img_hint);

        //set first question content
        q = list.get(0);
        setContent();

        //get hint information of current question
        btn_hint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String[] strArr = q.getHint().split("\\*");
                new AlertDialog.Builder(exercisePage.this)
                        .setTitle("Hint")
                        .setMessage(strArr[0]+"\n"+strArr[1]+"\n"+strArr[2])
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //do nothing
                            }
                        }).show();
                showPicture();

            }

        });

        //jump to next question
        btn_next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(current < count -1){
                    //set next question content
                    questionNum++;
                    current++;
                    q = list.get(current);
                    setContent();

                    //if the question was selected before, record the option
                    radioGroup.clearCheck();
                    if(q.getSelectedAnswer() != -1){
                        radioButtons[q.getSelectedAnswer()].setChecked(true);
                    }
                }
                else{
                    //there are no next question, set alert
                    new AlertDialog.Builder(exercisePage.this)
                            .setTitle("Reminder")
                            .setMessage("This is the last question! \nAre you sure you want to submit your answers?")
                            .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(exercisePage.this, exerciseResultPage.class);
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
                    //get previous question content
                    questionNum--;
                    current--;
                    q = list.get(current);
                    setContent();

                    //if the option has been chosen, record it
                    radioGroup.clearCheck();
                    if (q.getSelectedAnswer() != -1) {
                        radioButtons[q.getSelectedAnswer()].setChecked(true);
                    }
                }
                else{
                    //the question is the first question
                    new AlertDialog.Builder(exercisePage.this)
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

        //update the choice of users
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                for (int i = 0; i < 3; i++) {
                    if (radioButtons[i].isChecked() == true) {
                        list.get(current).setSelectedAnswer(i);
                        break;
                    }
                }

            }
        });

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
     * Set the content of question and options
     */
    private void setContent(){
        //assigns a value to the control
        questionNumber.setText(questionNum + "");
        String[] strArr = q.getQuestion().split("\\*");
        question.setText(strArr[0]+"\n"+strArr[1]);
        radioButtons[0].setText(q.getAnswerA());
        radioButtons[1].setText(q.getAnswerB());
        radioButtons[2].setText(q.getAnswerC());
        image_hint.setVisibility(View.INVISIBLE);
    }

    /**
     * Initialize database
     */
    protected void initData() {
        questionDao.deleteAll();
        readFromFile();
    }

    /**
     * Read exerciseQuestion.txt, and save all questions into database
     */
    private void readFromFile() {
        AssetManager assetManager = getAssets();
        int id = 0;
        try {
            InputStream inputStream = assetManager.open("exerciseQuestion.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                //read by line
                buffReader.readLine();
                while ((line = buffReader.readLine()) != null) {
                    String[] strArr = line.split("/");
                    Question exerciseQuestion = new Question(id,strArr[0],strArr[1],strArr[2],strArr[3],Integer.parseInt(strArr[4]),strArr[5],strArr[6],-1);
                    questionDao.insert(exerciseQuestion);
                    id++;
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get question list from database
     *
     * @return list - question list
     */
    public List<Question> getQuestion(){
        List<Question> lists = new ArrayList<>();
        //get the number of exercise question in database
        int totalCount = (int) questionDao.count();

        for(int i = 0; i < 10; i++){
            int ranNum = (int) (Math.random() * (totalCount-1));
            int j = 0;
            int size = lists.size();
            while(j < size){
                if(lists.get(j).getID() == ranNum){
                    ranNum = (int) (Math.random() * (totalCount-1));
                    j = 0;
                }else {
                    j++;
                }
            }
            QueryBuilder qb = questionDao.queryBuilder();
            qb.where(QuestionDao.Properties.ID.eq(ranNum));
            List<Question> ques = qb.list();
            //System.out.println("ques "+ ques.get(0).getQuestion() + "  "+ques.get(0).getID());
            if(ques.get(0) != null) {
                lists.add(ques.get(0));
            }
        }
        return lists;
    }

    /**
     * Check whether the answer is correct
     *
     * @param list
     * @return
     */
    public List<Integer> checkAnswer(List<Question> list){
        List<Integer> wrongList = new ArrayList<Integer>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getAnswer() != list.get(i).getSelectedAnswer()){
                wrongList.add(i);
            }
        }
        return wrongList;
    }

    /**
     * Get the list
     *
     * @return list - question list
     */
    public List<Question> getlist(){
        return list;
    }

}
