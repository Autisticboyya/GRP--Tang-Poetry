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


public class Question7 extends AppCompatActivity {

    private Button submit;
    private Button previous;
    private Button next;

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

    final private  String r1 = "寸";
    final private  String r2 = "报";
    final private  String r3 = "晖";

    private FrameLayout hole1;
    private FrameLayout hole2;
    private FrameLayout hole3;

    private static final String IMAGEVIEW_TAG1 = "寸";
    private static final String IMAGEVIEW_TAG2 = "报";
    private static final String IMAGEVIEW_TAG3 = "晖";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question7);

        imgApple1 = findViewById(R.id.apple1);
        imgApple2 = findViewById(R.id.apple2);
        imgApple3 = findViewById(R.id.apple3);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        word1 = findViewById(R.id.word1);
        word1.setTag(IMAGEVIEW_TAG1);
        word2 = findViewById(R.id.word2);
        word2.setTag(IMAGEVIEW_TAG2);
        word3 = findViewById(R.id.word3);
        word3.setTag(IMAGEVIEW_TAG3);

        question = findViewById(R.id.YouZiYin);

        submit = findViewById(R.id.submit);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);

        hole1 = findViewById(R.id.hole1);
        hole2 = findViewById(R.id.hole2);
        hole3 = findViewById(R.id.hole3);

        submit.setOnClickListener(new mySubmitListener());
        previous.setOnClickListener(new myPreviousListener());
        next.setOnClickListener(new myNextListener());

        word1.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {

                checkid = word1;

                v.getBackground().setAlpha(100);

                ClipData.Item item = new ClipData.Item(v.getTag().toString());


                ClipData dragData = new ClipData(v.getTag().toString(),new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0// flags (not currently used, set to 0)
                );

                return true;

            }
        });
        hole1.setOnDragListener(new myDragEventListener());

        word2.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {

                checkid = word2;

                v.getBackground().setAlpha(100);

                ClipData.Item item = new ClipData.Item(v.getTag().toString());


                ClipData dragData = new ClipData(v.getTag().toString(),new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0// flags (not currently used, set to 0)
                );

                return true;

            }
        });
        hole2.setOnDragListener(new myDragEventListener());

        word3.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {

                checkid = word3;

                v.getBackground().setAlpha(100);

                ClipData.Item item = new ClipData.Item(v.getTag().toString());


                ClipData dragData = new ClipData(v.getTag().toString(),new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0// flags (not currently used, set to 0)
                );

                return true;

            }
        });
        hole3.setOnDragListener(new myDragEventListener());


    }

    protected class mySubmitListener implements View.OnClickListener{
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v){
            if(s1.equals(r1)&&s2.equals(r2)&&s3.equals(r3)){
                Toast.makeText(Question7.this, "Correct!", Toast.LENGTH_LONG).show();

                tv1.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                tv2.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                tv3.setBackgroundColor(Color.parseColor("#00FFFFFF"));

                Animation scaleAnimation = new ScaleAnimation(0,2,0,2,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setDuration(2000);

                imgApple1.setBackground(word1.getBackground());
                imgApple1.startAnimation(scaleAnimation);
                imgApple2.setBackground(word1.getBackground());
                imgApple2.startAnimation(scaleAnimation);
                imgApple3.setBackground(word1.getBackground());
                imgApple3.startAnimation(scaleAnimation);

            } else {
                Toast.makeText(Question7.this, "Wrong answer, please try again.", Toast.LENGTH_LONG).show();
            }

        }

    }

    protected  class myPreviousListener implements  View.OnClickListener{
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Question7.this,Question6.class));
            finish();
        }
    }

    protected  class myNextListener implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            startActivity(new Intent(Question7.this,Question8.class));
            finish();
        }
    }

    protected class myDragEventListener implements View.OnDragListener {

        // This is the method that the system calls when it dispatches a drag event to the
        // listener.
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
                        question.setText("谁言"+s1+"草心，\n but how much love has the inch-long grass\n"+s2+"得三春"+s3+"\n for three spring months of the light of the sun? ");
                        System.out.println("s1 is "+s1);
                    } else if (v == hole2){
//                            ((ViewGroup)checkid.getParent()).removeView(checkid);
//                            ((FrameLayout)v).addView(checkid);
                        s2 = item.getText().toString();
                        System.out.println("s2 is "+s2);
                        question.setText("谁言"+s1+"草心，\n but how much love has the inch-long grass\n"+s2+"得三春"+s3+"\n for three spring months of the light of the sun? ");
                    } else if (v == hole3){
//                           ((ViewGroup)checkid.getParent()).removeView(checkid);
//                            ((FrameLayout)v).addView(checkid);
                        s3 = item.getText().toString();
                        System.out.println("s3 is "+s3);
                        question.setText("谁言"+s1+"草心，\n but how much love has the inch-long grass\n"+s2+"得三春"+s3+"\n for three spring months of the light of the sun? ");
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
                    word1.getBackground().setAlpha(255);
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

