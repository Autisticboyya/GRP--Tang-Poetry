package com.example.myapplication;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class AppleTreeQuestion extends AppCompatActivity {

    private Button submit;
    private Button previous;
    private Button next;

    private TextView questionNum;

    private ImageView imgApple1;
    private ImageView imgApple2;
    private ImageView imgApple3;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    private TextView word1;
    private TextView word2;
    private TextView word3;
    private TextView question;
    private static TextView checkid;
    private String s1 = "_1 _";
    private String s2 = "_2 _";
    private String s3 = "_3 _";

    private  String r1;
    private  String r2;
    private  String r3;

    private FrameLayout hole1;
    private FrameLayout hole2;
    private FrameLayout hole3;

    private static String IMAGEVIEW_TAG1;
    private static String IMAGEVIEW_TAG2;
    private static String IMAGEVIEW_TAG3;

    private static int numOfQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appletree_question);

        questionNum = findViewById(R.id.question_num);

        imgApple1 = findViewById(R.id.apple1);
        imgApple2 = findViewById(R.id.apple2);
        imgApple3 = findViewById(R.id.apple3);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        word1 = findViewById(R.id.word1);
        word2 = findViewById(R.id.word2);
        word3 = findViewById(R.id.word3);

        question = findViewById(R.id.question);

        submit = findViewById(R.id.submit);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);

        hole1 = findViewById(R.id.hole1);
        hole2 = findViewById(R.id.hole2);
        hole3 = findViewById(R.id.hole3);

        numOfQues = 1;
        setContent();

        submit.setOnClickListener(new mySubmitListener());
        previous.setOnClickListener(new myPreviousListener());
        next.setOnClickListener(new myNextListener());

        word1.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                checkid = word1;
                //v.getBackground().setAlpha(100);
                dragSetting(v);
                return true;
            }
        });
        hole1.setOnDragListener(new myDragEventListener());

        word2.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                checkid = word2;
                //v.getBackground().setAlpha(100);
                dragSetting(v);
                return true;
            }
        });
        hole2.setOnDragListener(new myDragEventListener());

        word3.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                checkid = word3;
                //v.getBackground().setAlpha(100);
                dragSetting(v);
                return true;
            }
        });
        hole3.setOnDragListener(new myDragEventListener());

        /**
         * goto game page
         */
        Button btn_game = (Button)findViewById(R.id.back);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppleTreeQuestion.this,MainActivity.class);
                intent.putExtra("fragFlag","2");
                startActivity(intent);
                finish();
            }
        });
    }

    private void dragSetting(View v){
        ClipData.Item item = new ClipData.Item(v.getTag().toString());
        ClipData dragData = new ClipData(v.getTag().toString(),new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
        v.startDrag(dragData,  // the data to be dragged
                myShadow,  // the drag shadow builder
                null,      // no need to use local data
                0// flags (not currently used, set to 0)
        );
    }

    private void reset(){
        imgApple1.setBackgroundColor(Color.parseColor("#80FFFFFF"));
        imgApple2.setBackgroundColor(Color.parseColor("#80FFFFFF"));
        imgApple3.setBackgroundColor(Color.parseColor("#80FFFFFF"));

        setContent();

        s1 = "_1 _";
        s2 = "_2 _";
        s3 = "_3 _";

    }

    private void setContent(){
        System.out.println("num of question"+ numOfQues);
        questionNum.setText("Question "+ numOfQues);
        setQuestion(numOfQues);
        setWords(numOfQues);
    }

    /**
     * Set question content
     * @param num - the number of question
     */
    private void setQuestion(int num){
        switch(num){
            case 1:
                question.setText(getString(R.string.question1));
                break;
            case 2:
                question.setText(getString(R.string.question2));
                break;
            case 3:
                question.setText(getString(R.string.question3));
                break;
            case 4:
                question.setText(getString(R.string.question4));
                break;
            case 5:
                question.setText(getString(R.string.question5));
                break;
            case 6:
                question.setText(getString(R.string.question6));
                break;
            case 7:
                question.setText(getString(R.string.question7));
                break;
            case 8:
                question.setText(getString(R.string.question8));
                break;
            case 9:
                question.setText(getString(R.string.question9));
                break;
        }
    }

    private void setWords(int num){
        switch(num){
            case 1:
                r1 = "明";
                r2 = "月";
                r3 = "霜";
                IMAGEVIEW_TAG1 = "明";
                IMAGEVIEW_TAG2 = "月";
                IMAGEVIEW_TAG3 = "霜";
                break;
            case 2:
                r1 = "景";
                r2 = "照";
                r3 = "苔";
                IMAGEVIEW_TAG1 = "景";
                IMAGEVIEW_TAG2 = "照";
                IMAGEVIEW_TAG3 = "苔";
                break;
            case 3:
                r1 = "君";
                r2 = "归";
                r3 = "时";
                IMAGEVIEW_TAG1 = "君";
                IMAGEVIEW_TAG2 = "归";
                IMAGEVIEW_TAG3 = "时";
                break;
            case 4:
                r1 = "会";
                r2 = "顶";
                r3 = "小";
                IMAGEVIEW_TAG1 = "会";
                IMAGEVIEW_TAG2 = "顶";
                IMAGEVIEW_TAG3 = "小";
                break;
            case 5:
                r1 = "莫";
                r2 = "云";
                r3 = "尽";
                IMAGEVIEW_TAG1 = "莫";
                IMAGEVIEW_TAG2 = "云";
                IMAGEVIEW_TAG3 = "尽";
                break;
            case 6:
                r1 = "举";
                r2 = "邀";
                r3 = "影";
                IMAGEVIEW_TAG1 = "举";
                IMAGEVIEW_TAG2 = "邀";
                IMAGEVIEW_TAG3 = "影";
                break;
            case 7:
                r1 = "寸";
                r2 = "报";
                r3 = "晖";
                IMAGEVIEW_TAG1 = "寸";
                IMAGEVIEW_TAG2 = "报";
                IMAGEVIEW_TAG3 = "晖";
                break;
            case 8:
                r1 = "月";
                r2 = "山";
                r3 = "云";
                IMAGEVIEW_TAG1 = "月";
                IMAGEVIEW_TAG2 = "山";
                IMAGEVIEW_TAG3 = "云";
                break;
            case 9:
                r1 = "逢";
                r2 = "歌";
                r3 = "碧";
                IMAGEVIEW_TAG1 = "逢";
                IMAGEVIEW_TAG2 = "歌";
                IMAGEVIEW_TAG3 = "碧";
                break;
        }
        word1.setText(r1);
        word2.setText(r2);
        word3.setText(r3);
        word1.setTag(IMAGEVIEW_TAG1);
        word2.setTag(IMAGEVIEW_TAG2);
        word3.setTag(IMAGEVIEW_TAG3);
    }

    /**
     * Display question content
     * @param num - the number of question
     */
    private void setQuestionSelect(int num){
        switch(num){
            case 1:
                question.setText("床前"+s1+ s2+"光，\nThe bright moonlight in front of the bed \n疑似地上"+s3+"。\nis suspected to be frost on the ground.");
                break;
            case 2:
                question.setText("返"+s1+"入深林，\nWhere sunlight, entering a grove,\n复"+s2+"青"+s3+"上。\n Shines back to me from the green moss.");
                break;
            case 3:
                question.setText("当"+s1+"怀"+s2+"日，\n And at last you think of returning home,\n  是妾断肠"+s3+"。\n now when my heart is almost broken.");
                break;
            case 4:
                question.setText(s1+"当凌绝"+s2+"，\n when shall i reach the top and hold  \n 一览众山"+s3+" 。\n all mountains in a single glance");
                break;
            case 5:
                question.setText("但去"+s1+"复闻，\n so give me leave and ask me no questions. \n 白"+s2+"无"+s3+"时。\n white clouds pass there without end.");
                break;
            case 6:
                question.setText(s1+"杯"+s2+"明月，\n till, raising my cup, i asked the bright moon \n 对"+s3+"成三人。\n to bring me my shadow and make us three.");
                break;
            case 7:
                question.setText("谁言"+s1+"草心，\n but how much love has the inch-long grass\n"+s2+"得三春"+s3+"\n for three spring months of the light of the sun? ");
                break;
            case 8:
                question.setText("明"+s1+"出天"+s2+" ，\n the bright moon lifts from the mountain of heaven \n  苍茫"+s3+"海间。\n in an infinite haze of cloud and sea.");
                break;
            case 9:
                question.setText("来往不"+s1+"人，\n back and forth i go, scarcely meeting anyone, \n 长"+s2+"楚天"+s3+"。\n and sing a long poem and gaze at the blue sky.");
                break;
        }
    }

    protected class mySubmitListener implements View.OnClickListener{
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v){
            if(s1.equals(r1)&&s2.equals(r2)&&s3.equals(r3)){
                Toast.makeText(AppleTreeQuestion.this, "Correct!", Toast.LENGTH_LONG).show();

                tv1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                tv2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                tv3.setBackgroundColor(Color.parseColor("#00FFFFFF"));

                Animation scaleAnimation = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setDuration(2000);

                imgApple1.setBackground(word1.getBackground());
                imgApple1.startAnimation(scaleAnimation);
                imgApple2.setBackground(word1.getBackground());
                imgApple2.startAnimation(scaleAnimation);
                imgApple3.setBackground(word1.getBackground());
                imgApple3.startAnimation(scaleAnimation);

            } else {
                Toast.makeText(AppleTreeQuestion.this, "Wrong answer, please try again.", Toast.LENGTH_LONG).show();
                reset();
            }

        }

    }

    protected  class myPreviousListener implements  View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(numOfQues>1){
                numOfQues--;
                reset();
            }
//            startActivity(new Intent(AppleTreeQuestion.this, Question1.class));
//            finish();
        }
    }

    protected  class myNextListener implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            if(numOfQues == 9){
                startActivity(new Intent(AppleTreeQuestion.this, AppleTreeCongratulation.class));
                finish();
            }else{
                numOfQues++;
                reset();
            }
//            startActivity(new Intent(AppleTreeQuestion.this,Question3.class));
//            finish();
        }
    }

    protected class myDragEventListener implements View.OnDragListener {
        // This is the method that the system calls when it dispatches a drag event to the listener.
        public boolean onDrag(View v, DragEvent event) {

            // Defines a variable to store the action type for the incoming event
            final int action = event.getAction();

            // Handles each of the expected events
            switch(action) {

                case DragEvent.ACTION_DRAG_STARTED:
                    // Determines if this View can accept the dragged data
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        v.invalidate();
                        // returns true to indicate that the View can accept the dragged data.
                        return true;

                    }
                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:
                    // Applies a green tint to the View. Return true; the return value is ignored

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Re-sets the color tint to blue. Returns true; the return value is ignored.

                    // Invalidate the view to force a redraw in the new tint
                    //v.invalidate();

                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    String dragData = item.getText().toString();

                    // Displays a message containing the dragged data.
                    //Toast.makeText(Question1.this, "Dragged data is " + dragData, Toast.LENGTH_LONG).show();

                    // Turns off any color tints
                    if(v == hole1){
                        s1 = item.getText().toString();
                        setQuestionSelect(numOfQues);
                        System.out.println("s1 is "+s1);
                    } else if (v == hole2){
                        s2 = item.getText().toString();
                        System.out.println("s2 is "+s2);
                        setQuestionSelect(numOfQues);
                    } else if (v == hole3){
//                           ((ViewGroup)checkid.getParent()).removeView(checkid);
//                            ((FrameLayout)v).addView(checkid);
                        s3 = item.getText().toString();
                        System.out.println("s3 is "+s3);
                        setQuestionSelect(numOfQues);
                    }
                    // Invalidates the view to force a redraw
                    // v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:

                    // Turns off any color tinting

                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
//                    if (event.getResult()) {
//                        Toast.makeText(Question1.this, "The drop was handled.", Toast.LENGTH_LONG).show();
//
//                    } else {
//                        Toast.makeText(Question1.this, "The drop didn't work.", Toast.LENGTH_LONG).show();
//
//                    }
                  word1.getBackground().setAlpha(225);
                    // returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }
    }
}

