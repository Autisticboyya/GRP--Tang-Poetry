package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public abstract class BaseActivity extends AppCompatActivity {
    private static boolean enableNightMode = false;
    private static boolean enableColorMode = false;
    private static int numOfColor = 0;
    private static boolean enableEyeProtectMode = false;
    private static Context mContext;
    private static float fontsize = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!enableNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

    }
    /**
     *If enabled night mode
     * @return  true or false
     */
    public boolean isEnableNightMode() {
        return enableNightMode;
    }
    public boolean isEnableColorMode() {
        return enableColorMode;
    }
    public boolean isEnableEyeMode() {
        return enableEyeProtectMode;
    }
    public int checkThemeColor() {
        return numOfColor;
    }

    /**
     * enable night mode or not
     * @param enableNightMode   true or false
     */
    public void setEnableNightMode(boolean enableNightMode) {
        this.enableNightMode = enableNightMode;
        if(enableNightMode) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    public void changeNightMode(){
        if (enableNightMode == false){
            this.enableNightMode = true;
        }
        else{
            this.enableNightMode = false;
        };
    }

    public void changeColorMode(int mode){
        if (enableColorMode == false){
            this.enableColorMode = true;
            numOfColor = mode;
        }
        else{
            this.enableColorMode = false;
            numOfColor = 0;
        };
    }

    public void changeEyeProtectMode(){
        if (enableEyeProtectMode == false){
            this.enableEyeProtectMode = true;
        }
        else{
            this.enableEyeProtectMode = false;
        };
    }

    public static float getFontSize() {
        return fontsize;
    }

    public static void setFontSize(float value) {
        fontsize = value;
    }

    @Override
    public Resources getResources() {
        Resources res =super.getResources();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            Configuration config = res.getConfiguration();
            SharedPreferences sharedPreferences = getSharedPreferences("font_size", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            config.fontScale = getFontSize();
            res.updateConfiguration(config,res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.N){
            final Resources res = newBase.getResources();
            final Configuration config = res.getConfiguration();
            config.fontScale = getFontSize();
            final Context newContext = newBase.createConfigurationContext(config);
            super.attachBaseContext(newContext);
        }
        else{
            super.attachBaseContext(newBase);
        }
    }
}
