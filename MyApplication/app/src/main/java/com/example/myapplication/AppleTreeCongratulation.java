package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AppleTreeCongratulation extends BaseActivity {
    private int theme = R.style.AppTheme;
    private  int nighttheme = R.style.NightAppTheme;
    private boolean isnight;
    private boolean iscolor;
    private int ThemeColor;
    private Button homepage;

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
        setContentView(R.layout.congratulation);

        /**
         * goto game interface
         */
        Button btn_game = (Button)findViewById(R.id.gamepage);
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppleTreeCongratulation.this, MainActivity.class);
                intent.putExtra("fragFlag","2");
                startActivity(intent);
                finish();
            }
        });
    }

}
