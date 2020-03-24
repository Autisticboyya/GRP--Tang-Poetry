package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class gamePage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game,container,false);


        /**
         * goto apple_tree game
         */
        Button btn_apple_tree = view.findViewById(R.id.apple_tree);
        btn_apple_tree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AppleTree.class);
                startActivity(intent);
            }
        });

        /**
         * goto poem_escape game
         */
        Button btn_poem_escape = view.findViewById(R.id.game_story);
        btn_poem_escape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), gameStoryPage.class);
                startActivity(intent);
            }
        });

        /**
         * goto poem_river game
         */
        Button btn_poem_river = view.findViewById(R.id.exercise);
        btn_poem_river.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), exercisePage.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
