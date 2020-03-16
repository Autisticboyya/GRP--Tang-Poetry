package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class favouritePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite);

        /**
         * goto home page
         */
        Button btn_home = (Button)findViewById(R.id.home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(favouritePage.this,MainActivity.class);
                intent.putExtra("fragFlag","1");
                startActivity(intent);
                finish();
            }
        });

        /**
         * goto game interface
         */
        Button btn_game = (Button)findViewById(R.id.game);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(favouritePage.this,MainActivity.class);
                intent.putExtra("fragFlag","2");
                startActivity(intent);
                finish();
            }
        });

        /**
         * goto me interface
         */
        Button btn_me = (Button)findViewById(R.id.me);
        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(favouritePage.this,MainActivity.class);
                intent.putExtra("fragFlag","3");
                startActivity(intent);
                finish();
            }
        });


    }
}
