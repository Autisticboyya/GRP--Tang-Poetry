package com.example.myapplication;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class blankPage extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gh);
        getWindow().setExitTransition(new Fade().setDuration(2000));
        ani();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void ani(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(new Intent(this, gameStoryPage.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }
}
