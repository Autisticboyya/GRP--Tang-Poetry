package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class homePage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home,container,false);


        /**
         * goto poems list interface
         */
        Button btn_poems_list = view.findViewById(R.id.viewing_poems);
        btn_poems_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),poemsPage.class);
                startActivity(intent);
            }
        });

        /**
         * goto daily poetry interface
         */
        Button btn_daily_poetry = view.findViewById(R.id.daily_poetry);
        btn_daily_poetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),dailyPoetryPage.class);
                startActivity(intent);
            }
        });

        /**
         * goto user guide interface
         */
        Button btn_user_guide = view.findViewById(R.id.user_guide);
        btn_user_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),userGuidePage.class);
                startActivity(intent);
            }
        });
        return view;
    }



}
