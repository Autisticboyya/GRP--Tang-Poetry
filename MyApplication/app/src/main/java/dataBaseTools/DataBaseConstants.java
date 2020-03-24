package dataBaseTools;
/**
 * 重要！！！所有的数据库都是用了volley包去实现，可以简单去网上搜一下配置一下，是个非常高效官方文档推荐的外部库
 */

/**
 * 这个类用来储存一些固定的url，功能文件，如注册或者登陆
 */
public class DataBaseConstants {
    //！！！！这里你也需要安装xampp（什么都行，只要有Apache，mysql，phpmyadmin就行，就大二小酒管你用啥数据库就啥数据库，只不过这次是拿自己的电脑作为数据库，
    // ），啥不会可以问问我，用自己的域名即可，/Android/v1/是我自己创建的文件夹，你们也可以创一样的这样方便，统一放在htdoc这个文件夹里面
    //windows应该比mac操作简单很多。
    //上面的是圣逸的，windows
    private static final String ROOT_URL = "http://192.168.5.4:1314/Android/v1/";
    //private static final String ROOT_URL = "http://192.168.137.1/Android/v1/";
    public static final String URL_REGISTER = ROOT_URL + "registerUser.php";
    public static final String URL_LOGIN = ROOT_URL + "userLogin.php";
    public static final String URL_PICTURE = "http://192.168.5.4:1314/Android/user_picture/";
    //public static final String URL_PICTURE = "http://192.168.137.1/Android/user_picture/";
    public static final String URL_GETCOMMENT = ROOT_URL + "getComment.php";
    public static final String URL_WRITECOMMENT = ROOT_URL + "writeComment.php";
    public static final String URL_ADDLIKENUMBER = ROOT_URL + "addLikeNumber.php";
    public static final String URL_DELETECOMMENT = ROOT_URL + "deleteComment.php";
}
