package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import poemListTools.FavouriteAdapter;
import poemListTools.SortModel;

public class favouritePage extends AppCompatActivity {
    private ListView favouriteList;
    ArrayList<SortModel> favouriteItemList = new ArrayList<>();
    FavouriteAdapter favouriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite);
        //testing items
        favouriteItemList.add(new SortModel("JingYeSi", "LiBai", "J"));
        favouriteItemList.add(new SortModel("JueJu_01", "DuFu", "J"));
        favouriteItemList.add(new SortModel("JueJu_02", "DuFu", "J"));
        favouriteItemList.add(new SortModel("JueJu_03", "DuFu", "J"));
        favouriteItemList.add(new SortModel("JueJu_04", "DuFu", "J"));
        favouriteItemList.add(new SortModel("JueJu_05", "DuFu", "J"));
        favouriteItemList.add(new SortModel("JueJu_06", "DuFu", "J"));
        favouriteItemList.add(new SortModel("JueJu_07", "DuFu", "J"));

        initList();

        /**
         * goto home page
         */
        Button btn_home = (Button)findViewById(R.id.home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(favouritePage.this, homePage.class);
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
                Intent intent = new Intent(favouritePage.this, gamePage.class);
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
                Intent intent = new Intent(favouritePage.this, personalCenterPage.class);
                startActivity(intent);
            }
        });


    }

    public void initList(){
        this.favouriteList = findViewById(R.id.favourite_list_view);
        this.favouriteAdapter = new FavouriteAdapter(this, favouriteItemList);
        favouriteList.setAdapter(favouriteAdapter);
    }

}
