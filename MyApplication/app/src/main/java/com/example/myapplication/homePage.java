package com.example.myapplication;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.greendao.Author;
import com.example.greendao.AuthorDao;
import com.example.greendao.DaoSession;
import com.example.greendao.Poem;
import com.example.greendao.PoemDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class homePage extends Fragment {
    public static PoemDao poemDao;
    public static AuthorDao authorDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.home,container,false);

        /**
         * goto poems list interface
         */
        Button btn_poems_list = view.findViewById(R.id.viewing_poems);
        btn_poems_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), chooseDifficulty.class);
                startActivity(intent);
            }
        });

        /**
         * goto daily poetry interface
         */
        Button btn_daily_poetry = view.findViewById(R.id.daily_poetry);
        btn_daily_poetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),dailyPoetryPage.class);
                startActivity(intent);
            }
        });

        /**
         * goto user guide interface
         */
        Button btn_user_guide = view.findViewById(R.id.user_guide);
        btn_user_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), userGuidePage.class);
                startActivity(intent);
            }
        });



        //读取数据库
        DaoSession daoSession = PoemList.getDaoSession();
        poemDao = daoSession.getPoemDao();
        authorDao = daoSession.getAuthorDao();

        initData();
        return view;


    }

    /**
     * 初始化诗歌 和 作者 的信息
     */
    protected void initData() {
        poemDao.deleteAll(); //删除所有数据库之前存的信息
        readPoemsFromFile(); //读取诗歌信息
        readAuthorFromFile(); //读取作者信息
    }

    private void readAuthorFromFile() {
        AssetManager assetManager = getActivity().getAssets();
        try {
            InputStream inputStream = assetManager.open("author_introduction.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                //分行读取
                buffReader.readLine();
                while ((line = buffReader.readLine()) != null) {
                    String[] strArr = line.split(":");
                    Author author = new Author(strArr[0],strArr[1]);
                    authorDao.insert(author);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readPoemsFromFile() {
        AssetManager assetManager = getActivity().getAssets();
        try {
            InputStream inputStream = assetManager.open("a.txt");
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                //分行读取
                buffReader.readLine();
                while ((line = buffReader.readLine()) != null) {
                    String[] strArr = line.split(":");
                    Poem poem = new Poem(null,strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5],strArr[6],strArr[8],strArr[9],strArr[7],null,null);
                    poemDao.insert(poem);
                }
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
