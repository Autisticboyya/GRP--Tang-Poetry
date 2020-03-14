package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class homePage extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

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
                Intent intent = new Intent(homePage.this,poemsPage.class);
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

}
