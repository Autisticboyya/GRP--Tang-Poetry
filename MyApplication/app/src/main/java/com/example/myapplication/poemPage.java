package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greendao.Author;

import java.util.List;

import static com.example.myapplication.homePage.authorDao;

public class poemPage extends BaseActivity {
    private int theme = R.style.AppTheme;
    private  int nighttheme = R.style.NightAppTheme;
    private boolean isnight;
    private boolean iscolor;
    private int ThemeColor;
    public static long poem_index;
    public static String poemName_pp;
    public static String poemNameEnglish_pp;
    public static String authorName_pp;
    public static String authorNameEnglish_pp;
    public static String kindOfPoem_pp;
    public static String chineseVersion_pp;
    public static String EnglishVersion_pp;
    public static String webLink_pp;
    public static String background_pp;
    private TextView view_comment;

    private List<Author> Authors;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        isnight = isEnableNightMode();
        if (isnight == true){
            setTheme(nighttheme);
        }
        iscolor = isEnableColorMode();
        if (iscolor == true){
            ThemeColor = checkThemeColor();
            switch (ThemeColor){
                case 1:
                    theme = (theme == R.style.AppTheme) ? R.style.AppTheme_brown : R.style.AppTheme;
                    setTheme(theme);
                    break;
                case 2:
                    theme = (theme == R.style.AppTheme) ? R.style.AppTheme_purple: R.style.AppTheme;
                    setTheme(theme);
                    break;
                case 3:
                    theme = (theme == R.style.AppTheme) ? R.style.AppTheme_green: R.style.AppTheme;
                    setTheme(theme);
                    break;
                case 4:
                    theme = (theme == R.style.AppTheme) ? R.style.AppTheme_cyan : R.style.AppTheme;
                    setTheme(theme);
                    break;
            }
        }
        setContentView(R.layout.poem);
        view_comment = findViewById(R.id.view_comment);
        view_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(poemPage.this, commentPage.class);
                startActivity(intent);
            }
        });

        /**
         * 王的part
         */
        //读取所有作者的信息
        Authors = authorDao.loadAll();

        TextView poemName = (TextView)findViewById(R.id.poemName);
        poemName.setText(poemName_pp);

        TextView authorName = (TextView)findViewById(R.id.authorName);
        authorName.setText(authorName_pp);

        doWithChineseVersion();

        //根据诗写诗的背景
        TextView poemBackground = (TextView)findViewById(R.id.poemBackgrounds);
        if(background_pp.equals("")){
            poemBackground.setText("Sorry,No background yet~");
        }else{
            poemBackground.setText(background_pp);
        }

        //根据得到的作者英文名设置作者简介
        TextView author_introduction = (TextView)findViewById(R.id.introduction);
        for(Author author:Authors){
            if(author.getAuthorNameEnglish().equals(authorNameEnglish_pp)){
                author_introduction.setText(author.getAuthorBriefIntroduction());
                break;
            }
        }

        //根据得到的作者英文名设置作者照片
        ImageView Author_image = (ImageView)findViewById(R.id.Author_image);
        switch(authorNameEnglish_pp){
            case "Lee Bai":
                Author_image.setImageResource(R.drawable.li_bai);
                break;
            case "Meng Haoran":
                Author_image.setImageResource(R.drawable.meng_haoran);
                break;
            case "Lu Lun":
                Author_image.setImageResource(R.drawable.lu_lun);
                break;
            default:
                Author_image.setImageResource(R.drawable.emotionstore_progresscancelbtn);
                break;
        }


        //点击展示英语翻译
        Button btn_translate = (Button)findViewById(R.id.translate);
        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView authorNameEnglish = (TextView)findViewById(R.id.authorNameEnglish);
                authorNameEnglish.setText(authorNameEnglish_pp);

                TextView poemNameEnglish = (TextView)findViewById(R.id.poemNameEnglish);
                poemNameEnglish.setText(poemNameEnglish_pp);

                doWithEnglishVersion();
            }
        });

        //点击之后 如果有视频就跳转 没有就给toast
        Button btn_video = (Button)findViewById(R.id.show_video);
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webLink_pp.equals("")){
                    Toast.makeText(poemPage.this, "Sorry, this video isn't available yet~", Toast.LENGTH_LONG).show();
                }else{
                    String s = "https://www.bilibili.com/video/" + webLink_pp;
                    Uri uri = Uri.parse(s);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
            }
        });


    }

    private void doWithChineseVersion() {
        String[] strArr = chineseVersion_pp.split("，");

        TextView firstSentence = (TextView)findViewById(R.id.firstSentence);
        firstSentence.setText("  "+strArr[0]+"，");

        TextView secondSentence = (TextView)findViewById(R.id.secondSentence);
        secondSentence.setText("  "+strArr[1]+"。");

        TextView thirdSentence = (TextView)findViewById(R.id.thirdSentence);
        thirdSentence.setText("  "+strArr[2]+"，");

        TextView fourthSentence = (TextView)findViewById(R.id.fourthSentence);
        fourthSentence.setText("  "+strArr[3]);
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
