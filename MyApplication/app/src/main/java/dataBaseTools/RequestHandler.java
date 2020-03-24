package dataBaseTools;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 重要！！！所有的数据库都是用了volley包去实现，可以简单去网上搜一下配置一下，是个非常高效官方文档推荐的外部库
 */

/**
 * 这个类是一个单例类，作用是将用户互动时的请求加入请求队列，至于为什么设置成单例的原因是因为这样不需要每次
 * 在你需要加入队列时重新初始化一个队列的对象，这个类会一直在运行时存在，提高效率。
 *
 * 需要用的就是最后一个addToRequestQueue方法
 */
public class RequestHandler {
    private static RequestHandler instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private RequestHandler(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestHandler getInstance(Context context) {
        if (instance == null) {
            instance = new RequestHandler(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
