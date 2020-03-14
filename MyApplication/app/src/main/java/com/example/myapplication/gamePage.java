package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class gamePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        /**
         * goto home page
         */
        Button btn_home = (Button)findViewById(R.id.home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gamePage.this,homePage.class);
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
                Intent intent = new Intent(gamePage.this,personalCenterPage.class);
                startActivity(intent);
            }
        });

        /**
         * goto apple_tree game
         */
        Button btn_apple_tree = (Button)findViewById(R.id.apple_tree);
        btn_apple_tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gamePage.this,appleTree.class);
                startActivity(intent);
            }
        });

        /**
         * goto poem_escape game
         */
        Button btn_poem_escape = (Button)findViewById(R.id.apple_tree);
        btn_poem_escape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gamePage.this,poemEscape.class);
                startActivity(intent);
            }
        });

        /**
         * goto poem_river game
         */
        Button btn_poem_river = (Button)findViewById(R.id.game_story);
        btn_poem_river.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gamePage.this,gameStoryPage.class);
                startActivity(intent);
            }
        });

        /**
         * goto exercise game
         */
        Button btn_exercise = (Button)findViewById(R.id.exercise);
        btn_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gamePage.this,exercisePage.class);
                startActivity(intent);
            }
        });
    }
}
