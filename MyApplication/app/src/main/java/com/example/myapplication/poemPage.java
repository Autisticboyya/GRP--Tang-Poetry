package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class poemPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem);

        TextView authorName = (TextView)findViewById(R.id.authorName);
        authorName.setText("李白");
    }

    public void back_to_main(View view) {
        poemPage.this.finish();
    }
}
