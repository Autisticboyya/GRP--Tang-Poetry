package com.example.myapplication;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greendao.DaoSession;
import com.example.greendao.Poem;
import com.example.greendao.PoemDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import poemListTools.SwipeCardLayout;

import static com.example.myapplication.poemPage.EnglishVersion_pp;
import static com.example.myapplication.poemPage.authorNameEnglish_pp;
import static com.example.myapplication.poemPage.authorName_pp;
import static com.example.myapplication.poemPage.chineseVersion_pp;
import static com.example.myapplication.poemPage.kindOfPoem_pp;
import static com.example.myapplication.poemPage.poemNameEnglish_pp;
import static com.example.myapplication.poemPage.poemName_pp;

public class dailyPoetryPage extends AppCompatActivity {
    private PoemDao poemDao;
    private SwipeCardLayout scl_layout;
    private ImageView back_button;
    public static List<Poem> Poems;
    private int[] poemIndexes = {0, 0 ,0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_poetry);

        //初始化诗歌列表
        DaoSession daoSession = PoemList.getDaoSession();
        poemDao = daoSession.getPoemDao();
        initData();
        Poems = poemDao.queryBuilder().orderAsc(PoemDao.Properties.PoemNameEnglish).build().list();

        //初始化卡片布局类
        scl_layout = findViewById(R.id.card);

        //初始化最初的五首随机推送的诗的index，并写入
        Random random = new Random();
        for (int i = 0; i < poemIndexes.length; i++){
            poemIndexes[i] = random.nextInt(Poems.size());
            while (i != 0 && poemIndexes[i] == poemIndexes[i - 1]){
                poemIndexes[i] = random.nextInt(Poems.size());
            }
        }

        Queue<CardEntity> data = new LinkedList<>();

        for (int i = 0; i < poemIndexes.length; i++){
            //若flag为0，取前两句，不然后两句
            System.out.println("----------------------------" + Poems.size() + "-----------" + poemIndexes[i]);
            int flag = random.nextInt(2);
            String[] strArr_chinese = Poems.get(poemIndexes[i]).getChineseVersion().split("，");
            String[] strArr_english = Poems.get(poemIndexes[i]).getEnglishVersion().split(";");
            String writer = Poems.get(poemIndexes[i]).getAuthorNameEnglish();
            String title = Poems.get(poemIndexes[i]).getPoemNameEnglish();
            System.out.println("----------------------------" + Poems.get(poemIndexes[i]).getChineseVersion());
            System.out.println("----------------------------" + strArr_english[0] + "-----------" + strArr_english[1]);
            if (flag == 0){
                dailyPoetryPage.CardEntity cardEntity = new dailyPoetryPage.CardEntity(poemIndexes[i], strArr_chinese[0], strArr_chinese[1], strArr_english[0], strArr_english[1], writer + ", 《" + title + "》");
                data.add(cardEntity);
            }else {
                dailyPoetryPage.CardEntity cardEntity = new dailyPoetryPage.CardEntity(poemIndexes[i], strArr_chinese[2], strArr_chinese[3], strArr_english[2], strArr_english[3], writer + ", 《" + title + "》");
                data.add(cardEntity);
            }

        }

        scl_layout.setAdapter(new SwipeCardLayout.CardAdapter<CardEntity>(data) {
            @Override
            public View bindLayout() {
                return LayoutInflater.from(dailyPoetryPage.this).inflate(R.layout.card_layout,null);
            }

            @Override
            public void bindData(dailyPoetryPage.CardEntity data, final View convertView) {
                Calendar timeGetter = getCalendar();

                TextView month = convertView.findViewById(R.id.month);
                TextView date = convertView.findViewById(R.id.date);
                TextView chinese_1 = convertView.findViewById(R.id.chinese_1);
                TextView chinese_2 = convertView.findViewById(R.id.chinese_2);
                TextView english_1 = convertView.findViewById(R.id.english_1);
                TextView english_2 = convertView.findViewById(R.id.english_2);
                TextView writer = convertView.findViewById(R.id.writer_info);
                TextView view_poem = convertView.findViewById(R.id.tv_view);

                //将诗歌的index传给诗歌列表
                final int index = data.index;

                view_poem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //传值到诗歌页面
                        poemName_pp = Poems.get(index).getPoemName();
                        poemNameEnglish_pp = Poems.get(index).getPoemNameEnglish();
                        authorName_pp = Poems.get(index).getAuthorName();
                        authorNameEnglish_pp = Poems.get(index).getAuthorNameEnglish();
                        kindOfPoem_pp = Poems.get(index).getKindOfPoem();
                        chineseVersion_pp = Poems.get(index).getChineseVersion();
                        EnglishVersion_pp = Poems.get(index).getEnglishVersion();

                        //跳转页面
                        Intent intent = new Intent(dailyPoetryPage.this, poemPage.class);
                        startActivity(intent);
                    }
                });

                month.setText(getMonth(timeGetter.get(Calendar.MONTH)));
                date.setText(getWeekDay(timeGetter.get(Calendar.DAY_OF_WEEK))+ " " + timeGetter.get(Calendar.DAY_OF_MONTH));
                chinese_1.setText(data.chinese_1);
                chinese_2.setText(data.chinese_2);
                english_1.setText(data.english_1);
                english_2.setText(data.english_2);
                writer.setText(data.writer);
            }
        });
        scl_layout.setOnSwipeListener(new SwipeCardLayout.OnSwipeListener() {
            @Override
            public void onSwipe(int type) {
                switch (type) {
                    case SwipeCardLayout.TYPE_RIGHT:
                        //右滑代表不喜欢
                        //////这里写需要的代码
                        Toast.makeText(dailyPoetryPage.this, "Like it!", Toast.LENGTH_SHORT).show();
                        break;
                    case SwipeCardLayout.TYPE_LEFT:
                        //左滑代表喜欢
                        //////这里写需要的代码
                        Toast.makeText(dailyPoetryPage.this, "No, don't like it", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

//        /**
//         * goto home page
//         */
//        Button btn_home = (Button)findViewById(R.id.home);
//        btn_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(dailyPoetryPage.this,homePage.class);
//                startActivity(intent);
//            }
//        });
//
//        /**
//         * goto game interface
//         */
//        Button btn_game = (Button)findViewById(R.id.game);
//        btn_game.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(dailyPoetryPage.this,gamePage.class);
//                startActivity(intent);
//            }
//        });
//
//        /**
//         * goto me interface
//         */
//        Button btn_me = (Button)findViewById(R.id.me);
//        btn_me.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(dailyPoetryPage.this,personalCenterPage.class);
//                startActivity(intent);
//            }
//        });

        back_button = findViewById(R.id.back_home);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dailyPoetryPage.this, homePage.class);
                startActivity(intent);
            }
        });


    }

    class CardEntity {

        public CardEntity(int index, String chinese_1, String chinese_2, String english_1, String english_2, String writer) {
            this.index = index;
            this.chinese_1 = chinese_1;
            this.chinese_2 = chinese_2;
            this.english_1 = english_1;
            this.english_2 = english_2;
            this.writer = writer;
        }

        public int index;
        public String chinese_1;
        public String chinese_2;
        public String english_1;
        public String english_2;
        public String writer;
    }

    public Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        calendar.setTime(date);

        return calendar;
    }

    public String getMonth(int index){

        switch (index){
            case 0:
                return "JAN";

            case 1:
                return "FEB";

            case 2:
                return "MAR";

            case 3:
                return "APR";

            case 4:
                return "MAY";

            case 5:
                return "JUN";

            case 6:
                return "JULY";

            case 7:
                return "AUG";

            case 8:
                return "SEPT";

            case 9:
                return "OCT";

            case 10:
                return "NOV";

            case 11:
                return "DEC";

        }
        return "";
    }

    public String getWeekDay(int index){
        switch (index){
            case 1:
                return "SUN";

            case 2:
                return "MON";

            case 3:
                return "TUE";

            case 4:
                return "WED";

            case 5:
                return "THUR";

            case 6:
                return "FRI";

            case 7:
                return "SAT";

        }
        return "";
    }

    protected void initData() {
        poemDao.deleteAll();
        readFromFile();
    }

    private void readFromFile() {
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("a.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                //分行读取
                buffReader.readLine();
                while ((line = buffReader.readLine()) != null) {
                    String[] strArr = line.split(":");
                    Poem poem = new Poem(null,null,strArr[0],null,strArr[1],strArr[2],strArr[3],strArr[4],strArr[5],strArr[6],null,null,null);
                    poemDao.insert(poem);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


