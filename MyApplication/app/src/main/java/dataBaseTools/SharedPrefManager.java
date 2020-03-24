package dataBaseTools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 重要！！！所有的数据库都是用了volley包去实现，可以简单去网上搜一下配置一下，是个非常高效官方文档推荐的外部库
 */

/**
 * 这个类是用在用户登录后共享数据的具体实现看下面的注释，依旧是单例，原因和RequestHandler一样
 */
public class SharedPrefManager {
    private static SharedPrefManager instance;

    //下面是每个要素对应的key，后面的代码都是用这个key去获取变量
    private static String KEY_USERNAME = "username";
    private static String KEY_USERPIC = "userPic";

    //单例
    public static synchronized SharedPrefManager getInstance() {
        if (instance == null) {
            instance = new SharedPrefManager();
        }
        return instance;
    }

    /**
     * 此方法是在用户登陆时使用，将从数据库返回的值写入共享文件
     */
    public void userLogin(String username, String picture){
        setUsername(username);
        setUserpic(picture);
        try{
            String content = username + ":" + picture;
            File file =new File("user_info.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            //使用true，即进行append file
            FileWriter fileWriter = new FileWriter(file.getName(),true);
            fileWriter.write(content);
            fileWriter.close();
            System.out.println("user info stored");
        }catch(IOException e){
            e.printStackTrace();
        }
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        //下面便是将需要共享的变量一个key一个key的对应起来并且通过edit()储存
//        editor.putInt(KEY_USERID, id).commit();
//        editor.putString(KEY_USERNAME, username).commit();
//        editor.putString(KEY_USEREMAIL, email).commit();
//        editor.putString(KEY_USERPIC, picture).commit();
//
//        return true;
    }

    /**
     * 用于判断用户是否登陆，登陆了下面的if句是会返回true的，该方法的应用可以去看每个页面类，里面都有
     * 一个该函数的判断，若返回true则进入，false则自动跳转到登陆界面
     */
    public boolean isLoggedIn(){
        File file = new File("user_info.txt");
        if(file.exists()){
            System.out.println("user logged in");
            return true;
        }else {
            return false;
        }
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        if (sharedPreferences.getString(KEY_USERNAME, null) != null){
//            System.out.println(sharedPreferences.getString(KEY_USERNAME, null));
//            return true;
//        }
//        return false;
    }

    /**
     * 退出登录时使用，清空并关闭共享空间
     */
    public boolean logOut(){
        File file = new File("user_info.txt");
        if(file.exists()){
            file.delete();
            System.out.println("user logged out");
            return true;
        }
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//
//        return true;
        return true;
    }

    //下面这些就不说了，获取自己需要的变量
    public String getUsername(){
        return KEY_USERNAME;
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getUserPic(){
        return DataBaseConstants.URL_PICTURE + KEY_USERPIC;
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return DataBaseConstants.URL_PICTURE + sharedPreferences.getString(KEY_USERPIC, null);
    }

    private static void setUsername(String keyUsername) {
        KEY_USERNAME = keyUsername;
    }

    private static void setUserpic(String keyUserpic) {
        KEY_USERPIC = keyUserpic;
    }

}
