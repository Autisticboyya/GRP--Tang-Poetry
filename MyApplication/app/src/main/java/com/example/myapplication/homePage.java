package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.greendao.Author;
import com.example.greendao.AuthorDao;
import com.example.greendao.DaoSession;
import com.example.greendao.Poem;
import com.example.greendao.PoemDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class homePage extends AppCompatActivity {
    public static PoemDao poemDao;
    public static AuthorDao authorDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        DaoSession daoSession = PoemList.getDaoSession();
        poemDao = daoSession.getPoemDao();
        authorDao = daoSession.getAuthorDao();

        initData();


        /**
         * goto game interface
         */
        Button btn_game = (Button)findViewById(R.id.game);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this,gamePage.class);
                startActivity(intent);
            }
        });

        /**
         * goto me interface
         */
        Button btn_me = (Button)findViewById(R.id.me);
        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this,personalCenterPage.class);
                startActivity(intent);
            }
        });

        /**
         * goto poems list interface
         */
        Button btn_poems_list = (Button)findViewById(R.id.viewing_poems);
        btn_poems_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this,chooseDifficulty.class);
                startActivity(intent);
            }
        });

        /**
         * goto daily poetry interface
         */
        Button btn_daily_poetry = (Button)findViewById(R.id.daily_poetry);
        btn_daily_poetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this,dailyPoetryPage.class);
                startActivity(intent);
            }
        });

        /**
         * goto user guide interface
         */
        Button btn_user_guide = (Button)findViewById(R.id.user_guide);
        btn_user_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this,userGuidePage.class);
                startActivity(intent);
            }
        });
    }
    protected void initData() {
        poemDao.deleteAll();
        readPoemsFromFile();
        readAuthorFromFile();
    }

    private void readAuthorFromFile() {
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("author_introduction.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                //分行读取
                buffReader.readLine();
                while ((line = buffReader.readLine()) != null) {
                    String[] strArr = line.split(":");
                    Author author = new Author(strArr[0],strArr[1]);
                    authorDao.insert(author);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readPoemsFromFile() {
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
                    Poem poem = new Poem(null,strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5],strArr[6],strArr[8],strArr[9],strArr[7],null,null);
                    poemDao.insert(poem);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
