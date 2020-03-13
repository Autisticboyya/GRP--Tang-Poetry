package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.myapplication.poemsPage.difficulty;

public class chooseDifficulty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_difficulty);

        /**
         * 4个按钮分别对应 简单模式--1 中等模式--2 困难模式--3 所有诗--4
         */
        Button btn_easy_mode = (Button)findViewById(R.id.easy_mode);
        btn_easy_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = "1";
                Intent intent = new Intent(chooseDifficulty.this,poemsPage.class);
                startActivity(intent);
            }
        });

        Button btn_middle_mode = (Button)findViewById(R.id.middle_mode);
        btn_middle_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = "2";
                Intent intent = new Intent(chooseDifficulty.this,poemsPage.class);
                startActivity(intent);
            }
        });

        Button btn_hell_mode = (Button)findViewById(R.id.hell_mode);
        btn_hell_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = "3";
                Intent intent = new Intent(chooseDifficulty.this,poemsPage.class);
                startActivity(intent);
            }
        });

        Button btn_showAllPoems = (Button)findViewById(R.id.show_all_poem);
        btn_showAllPoems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = "0";
                Intent intent = new Intent(chooseDifficulty.this,poemsPage.class);
                startActivity(intent);
            }
        });

    }

    public void back_to_main(View view) {
        chooseDifficulty.this.finish();
    }
}
