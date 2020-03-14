package com.example.myapplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.DaoMaster;
import com.example.greendao.DaoSession;

public class PoemList extends Application {
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public static PoemList instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
    }

    public static PoemList getInstances(){
        return instances;
    }

    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this,"Poem.db",null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


}
