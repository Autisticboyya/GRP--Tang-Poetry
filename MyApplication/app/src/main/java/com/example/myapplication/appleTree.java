package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AppleTree extends AppCompatActivity {

    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.appletree);

        start = findViewById(R.id.start);
        start.setOnClickListener(new MyClickListener());
    }

    protected  class MyClickListener implements  View.OnClickListener{
        @Override
        public void onClick(View v){
            startActivity(new Intent(AppleTree.this,Question1.class));
            finish();
        }
    }
}

