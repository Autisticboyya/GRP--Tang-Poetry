package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BackgroundSetting extends BaseActivity {
    private int theme = R.style.AppTheme;
    private  int nighttheme = R.style.NightAppTheme;
    private boolean isnight;
    private  int ThemeColor;
    private boolean iscolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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





        setContentView(R.layout.activity_setting_background);

        ImageButton btn_brown = (ImageButton) findViewById(R.id.Brown);
        btn_brown.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.AppTheme_brown : R.style.AppTheme;
                changeColorMode(1);
                BackgroundSetting.this.recreate();
            }
        });

        ImageButton btn_purple = (ImageButton) findViewById(R.id.Purple);
        btn_purple.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.AppTheme_purple : R.style.AppTheme;
                changeColorMode(2);
                BackgroundSetting.this.recreate();
            }
        });

        ImageButton btn_green = (ImageButton) findViewById(R.id.Green);
        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.AppTheme_green : R.style.AppTheme;
                changeColorMode(3);
                BackgroundSetting.this.recreate();
            }
        });

        ImageButton btn_cyan = (ImageButton) findViewById(R.id.Cyan);
        btn_cyan.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.AppTheme_cyan : R.style.AppTheme;
                changeColorMode(4);
                BackgroundSetting.this.recreate();
            }
        });

        TextView recover = (TextView) findViewById(R.id.confirm);
        recover.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                changeColorMode(0);

                BackgroundSetting.this.recreate();
            }
        });

        ImageButton btn_back = (ImageButton)findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(BackgroundSetting.this , settingPage.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

}
