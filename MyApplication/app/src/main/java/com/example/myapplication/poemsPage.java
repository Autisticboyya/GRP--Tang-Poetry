package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class poemsPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poems);

        /**
         * goto home page
         */
        Button btn_home = (Button)findViewById(R.id.home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(poemsPage.this,homePage.class);
                startActivity(intent);
            }
        });

        /**
         * goto game interface
         */
        Button btn_game = (Button)findViewById(R.id.game);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(poemsPage.this,gamePage.class);
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
                Intent intent = new Intent(poemsPage.this,personalCenterPage.class);
                startActivity(intent);
            }
        });

    }

    /**
     * back_to_previous to previous interface
     */
    public void back_to_main(View view) {
        poemsPage.this.finish();
    }
}
