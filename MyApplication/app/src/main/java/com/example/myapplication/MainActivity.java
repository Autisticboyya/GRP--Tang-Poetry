package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private TextView txt_home;
    private TextView txt_game;
    private TextView txt_me;
    private FrameLayout ly_content;

    private FragmentManager fManager;
    private Fragment currentFragment = new Fragment();//全局fragment
    private homePage hp = new homePage();
    private gamePage gp = new gamePage();
    private personalCenterPage pcp = new personalCenterPage();
    int mFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        txt_home = findViewById(R.id.txt_home);
        txt_game = findViewById(R.id.txt_game);
        txt_me = findViewById(R.id.txt_me);
        txt_home.setOnClickListener(l);
        txt_game.setOnClickListener(l);
        txt_me.setOnClickListener(l);
        ly_content = findViewById(R.id.ly_content);
        txt_home.performClick();

    }
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - exitTime)> 2000) {
                Toast.makeText(getApplicationContext(), "Next Press To Exit", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mFlag = intent.getIntExtra("flag",-1);
        checkModule(mFlag);
    }

    public void checkModule(int position){
        switch (position){
            case 0:
                txt_home.performClick();
                break;
            case 1:
                txt_game.performClick();
                break;
            case 2:
                txt_me.performClick();
                break;
            default:
                break;
        }
    }

    private void showFragment(Fragment fragment){
        if(currentFragment != fragment){
            FragmentTransaction transaction = fManager.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment = fragment;
            if(!fragment.isAdded()) {
                transaction.add(R.id.ly_content, fragment).show(fragment).commit();
            }else{
                transaction.show(fragment);
                transaction.commit();
            }
        }
    }

    private void setSelected() {
        txt_home.setSelected(false);
        txt_game.setSelected(false);
        txt_me.setSelected(false);
    }

    View.OnClickListener l = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txt_home:
                    setSelected();
                    txt_home.setSelected(true);
                    showFragment(hp);
                    break;
                case R.id.txt_game:
                    setSelected();
                    txt_game.setSelected(true);
                    showFragment(gp);
                    break;
                case R.id.txt_me:
                    setSelected();
                    txt_me.setSelected(true);
                    showFragment(pcp);
                    break;
            }
        }
    };
}