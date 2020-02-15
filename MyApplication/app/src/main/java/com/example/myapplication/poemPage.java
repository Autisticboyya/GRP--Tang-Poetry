package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.myapplication.poemsPage.Poems;

public class poemPage extends AppCompatActivity {

    public static String authorName_pp;
    public static String poemName_pp;
    public static String chineseVersion_pp;
    public static String EnglishVersion_pp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem);

        TextView authorName = (TextView)findViewById(R.id.authorName);
        authorName.setText(authorName_pp);

        TextView poemName = (TextView)findViewById(R.id.poemName);
        poemName.setText(poemName_pp);

        doWithChineseVersion();
        doWithEnglishVersion();
    }

    private void doWithChineseVersion() {
        String[] strArr = chineseVersion_pp.split("，");

        TextView firstSentence = (TextView)findViewById(R.id.firstSentence);
        firstSentence.setText(strArr[0]+"，");

        TextView secondSentence = (TextView)findViewById(R.id.secondSentence);
        secondSentence.setText(strArr[1]+"。");

        TextView thirdSentence = (TextView)findViewById(R.id.thirdSentence);
        thirdSentence.setText(strArr[2]+"，");

        TextView fourthSentence = (TextView)findViewById(R.id.fourthSentence);
        fourthSentence.setText(strArr[3]);
    }

    private void doWithEnglishVersion() {
        String[] strArr = EnglishVersion_pp.split(";");

        TextView firstSentenceEnglish = (TextView)findViewById(R.id.firstSentenceEnglish);
        firstSentenceEnglish.setText(strArr[0]+".");

        TextView secondSentenceEnglish = (TextView)findViewById(R.id.secondSentenceEnglish);
        secondSentenceEnglish.setText(strArr[1]+".");

        TextView thirdSentenceEnglish = (TextView)findViewById(R.id.thirdSentenceEnglish);
        thirdSentenceEnglish.setText(strArr[2]+".");

        TextView fourthSentenceEnglish = (TextView)findViewById(R.id.fourthSentenceEnglish);
        fourthSentenceEnglish.setText(strArr[3]);
    }

    public void back_to_main(View view) {
        poemPage.this.finish();
    }
}
