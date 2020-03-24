package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;


public class settingPage extends BaseActivity {

    ImageView iv_main;
    private boolean isnight;
    private boolean iscolor;
    private boolean eyemode;
    private int theme = R.style.AppTheme;
    private  int nighttheme = R.style.NightAppTheme;
    private int ThemeColor;

    private int blue=100;
    private int red=100;
    private int green=100;
    private int ld=0;
    private int alapha=100;



    SharedPreferences preferences;
    final boolean falg = false;
    private Switch mSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * get night state
         */
        isnight = isEnableNightMode();
        if (isnight == true){
            setTheme(nighttheme);
        }

        /**
         * get color state
         */
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
        setContentView(R.layout.setting);
        initData();
        //permission();

        /**
         * background page
         */
        Button back = (Button)findViewById(R.id.Background);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(settingPage.this , BackgroundSetting.class);
                startActivity(i);
            }
        });
        /**
         * goto home page
         */
        ImageButton btn_home = (ImageButton)findViewById(R.id.back3);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * nightmode
         */
        Button btn_theme = (Button) findViewById(R.id.night);
        btn_theme.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme: R.style.AppTheme;
                changeNightMode();
                settingPage.this.recreate();
            }
        });

        /**
         * fontsize
         */
        Button btn_font = (Button) findViewById(R.id.fontsize);
        btn_font.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                finish();
                Intent i = new Intent(settingPage.this , ChangeFont.class);
                startActivity(i);
            }
        });

        mSwitch = (Switch) findViewById(R.id.eye);

        eyemode = isEnableEyeMode();
        if (isEnableEyeMode() == true){
            mSwitch.setChecked(true);
        }

        // listening
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", true);
                    editor.commit();
                    changeEyeProtectMode();
                    openAleterWindow();
                }else {
                    SharedPreferences myPreference=getSharedPreferences("myPreference", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = myPreference.edit();
                    editor.putBoolean("flag", false);
                    editor.commit();
                    changeEyeProtectMode();
                    if(ld>=0&&ld<=100&&red>=0&&red<=100&&blue>=0&blue<=100&&green>=0&&green<=100) {
                        editor.putInt("ld", ld);
                        editor.putInt("red", red);
                        editor.putInt("blue", blue);
                        editor.putInt("green", green);
                        editor.commit();
                    }
                    Log.e("this","data"+ld+red+blue+green);
                    Dialog d =  EyeDialog.getInstance2();
                    d.dismiss();
                }
            }
        });
    }

    public  void initData(){
        SharedPreferences myPreference=getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPreference.edit();
        editor.putInt("ld", 0);
        editor.putInt("red", 100);
        editor.putInt("blue", 100);
        editor.putInt("green", 100);
        editor.commit();
    }

    public void getData(){
        SharedPreferences preferences=getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        iv_main.setBackgroundColor(Color.argb(alapha,200,200,150));
        changeAppBrightness(ld);
        Log.e("this","getR"+red);
    }

    /**
     * open the dialog window
     */
    private void openAleterWindow() {

        //dialog=new Dialog(this,R.style.dialog_translucent);
        EyeDialog dialog = EyeDialog.getInstance(this, R.style.dialog_translucent);
        dialog.setContentView(R.layout.dailog);

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        lp.flags =WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        if(Build.VERSION.SDK_INT>=26){
            lp.type=WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else{
            lp.type=WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        }

        dialog.getWindow().setAttributes(lp);
        dialog.show();

        iv_main=dialog.findViewById(R.id.ll_main);
        getData();
    }

    public void permission(){
        if (Build.VERSION.SDK_INT >= 23) {
            if(!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }
        }
    }

    public void changeAppBrightness(int brightness) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("switch", eyemode);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
        theme = savedInstanceState.getInt("theme");
    }

}
