package com.example.myapplication;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greendao.DaoSession;
import com.example.greendao.Script;
import com.example.greendao.ScriptDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Main game story page;
 * Display scripts and options.
 */
public class gameStoryPage extends AppCompatActivity {
    private ScriptDao scriptDao;
    private static List<Script> scriptList = new ArrayList<>();
    private static Script script;
    private static int index;
    private String[] strArr;

    private LinearLayout layout;
    private TextView content;
    private ImageView rolePic;
    private TextView roleName;
    private Button btn_pervious;
    private Button btn_next;
    private ImageView img_action1;
    private ImageView img_action2;
    private Button btn_action1;
    private Button btn_action2;
    private Resources resources;
    private Drawable btnDrawable;
    private ImageView light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_story);
        //getWindow().setEnterTransition(new Fade().setDuration(2000));

        /**
         * goto game page
         */
        Button btn_game = (Button)findViewById(R.id.back);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameStoryPage.this,gamePage.class);
                startActivity(intent);
            }
        });

        //initialize database
        DaoSession daoSession = PoemList.getDaoSession();
        scriptDao = daoSession.getScriptDao();
        initData();

        FrameLayout fgl = findViewById(R.id.contentbg);
        layout = findViewById(R.id.overall);
        content = findViewById(R.id.question);
        roleName = findViewById(R.id.rolename);
        rolePic = findViewById(R.id.rolePic);
        btn_pervious = findViewById(R.id.btn_per);
        btn_next = findViewById(R.id.btn_next);
        img_action1 = findViewById(R.id.img_action1);
        img_action2 = findViewById(R.id.img_action2);
        btn_action1 = findViewById(R.id.action1);
        btn_action2 = findViewById(R.id.action2);

        light = findViewById(R.id.light);
        light.setVisibility(View.INVISIBLE);

        //content.setBackgroundColor(Color.argb(200, 192, 192, 192));  //background of content
        content.setTextColor(Color.argb(255, 0, 0, 0));  //text of content
        fgl.getBackground().mutate().setAlpha(200); //set transparency of content background

        //set first script content
        script = scriptList.get(0);
        setContent();

        //first sentence of script
        index = 0;
        //get next sentence of script
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index < strArr.length){
                    content.setText(strArr[index]);
                }else{
                    if(script.getAction1().equalsIgnoreCase("null")){
                        //get next script content
                        if(!script.getResult1().equalsIgnoreCase(("null"))){
                            script = getNewScript(Integer.parseInt(script.getResult1()));
                            setContent();
                        }else{
                            script = getNewScript(script.getID()+1);
                            if (script != null) {
                                if(script.getLight() == "1"){
                                    lightEffect();
                                }else{
                                    setContent();
                                }
                            }
                        }
                    }else{ //display actions
                        img_action1.setVisibility(View.VISIBLE);
                        img_action2.setVisibility(View.VISIBLE);
                        btn_action1.setVisibility(View.VISIBLE);
                        btn_action2.setVisibility(View.VISIBLE);
                        btn_action1.setText(script.getAction1());
                        btn_action2.setText(script.getAction2());
                        btn_next.setVisibility(View.INVISIBLE);
                    }
                    index = 0;
                }
            }
        });

        //get previous sentence of script
        btn_pervious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index > 0){
                    index--;
                    content.setText(strArr[index]);
                    btn_next.setVisibility(View.VISIBLE);
                }
            }
        });

        //choose action1 and jump to next script
        btn_action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!script.getResult1().equalsIgnoreCase("null")) {
                    setAction1Script();
                    btn_next.setVisibility(View.VISIBLE);
                }
            }
        });

        //choose action2 and jump to next script
        btn_action2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!script.getResult2().equals("null")){
                    setAction2Script();
                    btn_next.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * Get new script
     *
     * @param result - ending
     * @return scr.get(0) - new script; null - cannot get next script
     */
    private Script getendScript(String result){
        QueryBuilder qb = scriptDao.queryBuilder();
        qb.where(ScriptDao.Properties.Role.eq(result));
        List<Script> scr = qb.list();
        if(qb.list().size() > 0){
            return scr.get(0);
        }
        return null;
    }

    /**
     * Set the action1 script
     */
    private void setAction1Script(){
        switch(script.getResult1()) {
            case "good ending1":
                script = getendScript("good ending1");
                Intent intent = new Intent(gameStoryPage.this,gameStoryResultPage.class);
                startActivity(intent);
                break;
            case "good ending2":
                script = getendScript("good ending2");
                Intent intent1 = new Intent(gameStoryPage.this,gameStoryResultPage.class);
                startActivity(intent1);
                break;
            case "bad ending3":
                script = getendScript("bad ending3");
                Intent intent2 = new Intent(gameStoryPage.this,gameStoryResultPage.class);
                startActivity(intent2);
                break;
            default:
                script = getNewScript(Integer.parseInt(script.getResult1()));
                if (script != null) {
                    if(script.getLight().equalsIgnoreCase("1")){
                        lightEffect();
                    }else{
                        setContent();
                    }
                }
                break;
        }
    }

    /**
     * Set the action2 script
     */
    private void setAction2Script(){
        switch(script.getResult2()) {
            case "bad ending1":
                script = getendScript("bad ending1");
                Intent intent = new Intent(gameStoryPage.this,gameStoryResultPage.class);
                startActivity(intent);
                break;
            case "bad ending2":
                script = getendScript("bad ending2");
                Intent intent1 = new Intent(gameStoryPage.this,gameStoryResultPage.class);
                startActivity(intent1);
                break;
            case "good ending3":
                script = getendScript("good ending3");
                Intent intent2 = new Intent(gameStoryPage.this,gameStoryResultPage.class);
                startActivity(intent2);
                break;
            default:
                script = getNewScript(Integer.parseInt(script.getResult2()));
                if (script != null) {
                    if(script.getLight().equalsIgnoreCase("1")){
                        lightEffect();
                    }else{
                        setContent();
                    }
                }
                break;
        }
    }


    /**
     * Add flicker effects
     */
    private void lightEffect(){
        light.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation1.setDuration(2000);
        //alphaAnimation1.setRepeatCount(Animation.INFINITE);
        //alphaAnimation1.setRepeatMode(Animation.RESTART);
        alphaAnimation1.setRepeatCount(0);
        light.setAnimation(alphaAnimation1);
        alphaAnimation1.start();
        light.setVisibility(View.INVISIBLE);

        //wait 2 second and show next page
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContent();
            }
        }, 2000);//after 2 seconds to run run()
    }

    /**
     * Get new script
     *
     * @param ID
     * @return scr.get(0) - new script; null - cannot get next script
     */
    private Script getNewScript(int ID){
        QueryBuilder qb = scriptDao.queryBuilder();
        qb.where(ScriptDao.Properties.ID.eq(ID));
        List<Script> scr = qb.list();
        if(qb.list().size() > 0){
            return scr.get(0);
        }
        return null;
    }

    /**
     * Set the content of control
     */
    private void setContent(){
        setBackground(script.getID());
        setRole();
        img_action1.setVisibility(View.INVISIBLE);
        img_action2.setVisibility(View.INVISIBLE);
        btn_action1.setVisibility(View.INVISIBLE);
        btn_action2.setVisibility(View.INVISIBLE);
        String cont = script.getContent();
        strArr = cont.split("\\*");
        content.setText(strArr[0]);
    }

    /**
     * Set background picture
     */
    private void setBackground(int ID){
        resources = layout.getContext().getResources();
        switch(ID) {
            case 0:
                btnDrawable = resources.getDrawable(R.drawable.storybglibrary);
                break;
            case 1:
                btnDrawable = resources.getDrawable(R.drawable.storybg3);
                break;
            case 7:
                btnDrawable = resources.getDrawable(R.drawable.storybg4);
                break;
            case 10:
                btnDrawable = resources.getDrawable(R.drawable.storybg5);
                break;
            case 14:
                btnDrawable = resources.getDrawable(R.drawable.storybg6);
                break;
            case 16:
                btnDrawable = resources.getDrawable(R.drawable.storybg1);
                break;
            case 21:
                btnDrawable = resources.getDrawable(R.drawable.storybg8);
                break;
            case 28:
                btnDrawable = resources.getDrawable(R.drawable.storybg4);
                break;
            case 39:
                btnDrawable = resources.getDrawable(R.drawable.storybg1);
                break;
            case 41:
                btnDrawable = resources.getDrawable(R.drawable.storybg2);
                break;
            case 44:
                btnDrawable = resources.getDrawable(R.drawable.storybg7);
                break;
        }
        layout.setBackgroundDrawable(btnDrawable);
        layout.getBackground().mutate().setAlpha(153);
    }

    /**
     * Display role name and picture
     */
    private void setRole(){
        switch(script.getRole()){
            case "Voiceover":
                rolePic.setVisibility(View.INVISIBLE);
                roleName.setVisibility(View.INVISIBLE);
                break;
            case "Du Fu":
                rolePic.setVisibility(View.VISIBLE);
                roleName.setVisibility(View.VISIBLE);
                rolePic.setImageResource(R.drawable.story_dufu);
                roleName.setText(script.getRole());
                break;
            case "Li Bai":
                rolePic.setVisibility(View.VISIBLE);
                roleName.setVisibility(View.VISIBLE);
                rolePic.setImageResource(R.drawable.story_libai);
                roleName.setText(script.getRole());
                break;
            case "John":
                rolePic.setVisibility(View.VISIBLE);
                roleName.setVisibility(View.VISIBLE);
                rolePic.setImageResource(R.drawable.story_role);
                roleName.setText(script.getRole());
                break;
            case "Old woman":
                rolePic.setVisibility(View.VISIBLE);
                roleName.setVisibility(View.VISIBLE);
                rolePic.setImageResource(R.drawable.story_oldwoman);
                roleName.setText(script.getRole());
                break;
        }
    }

    /**
     * Initialize database
     */
    protected void initData() {
        scriptDao.deleteAll();
        readFromFile();
        scriptList = scriptDao.loadAll();
    }

    /**
     * Read exerciseQuestion.txt, and save all questions into database
     */
    private void readFromFile() {
        AssetManager assetManager = getAssets();
        int id = 0;
        try {
            InputStream inputStream = assetManager.open("script.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                //read by line
                buffReader.readLine();
                while ((line = buffReader.readLine()) != null) {
                    String[] strArr = line.split("/");
                    Script script = new Script(id,strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5],strArr[6]);
                    scriptDao.insert(script);
                    id++;
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get current script
     * @return script - current script
     */
    public static Script getScript() {
        return script;
    }
}
