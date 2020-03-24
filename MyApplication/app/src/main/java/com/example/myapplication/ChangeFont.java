package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class ChangeFont extends BaseActivity {
    private TextView tvSizeScale;
    private int theme = R.style.AppTheme;
    private  int nighttheme = R.style.NightAppTheme;
    private boolean isnight;
    private boolean iscolor;
    private int ThemeColor;
    private float Fontsize;

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

        setContentView(R.layout.activity_change_font);

        tvSizeScale = findViewById(R.id.tv_size_scale);
        SeekBar seekBar = findViewById(R.id.seek_bar);
        tvSizeScale.setText("Px："+ getFontSize());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float value = 1f+progress*0.1f;
                tvSizeScale.setText("Px："+value);
                Fontsize = value;
                setFontSize(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        TextView tv_sure =  findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                finish();
                startActivity(new Intent(ChangeFont.this, ChangeFont.class));
            }
        });

        ImageButton btn_back = (ImageButton)findViewById(R.id.back2);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(ChangeFont.this , settingPage.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat("seekbar", Fontsize);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Fontsize = savedInstanceState.getFloat("seekbar");
    }



}
