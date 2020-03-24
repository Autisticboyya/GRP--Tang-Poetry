package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commentTools.Comment;
import dataBaseTools.DataBaseConstants;
import dataBaseTools.RequestHandler;
import dataBaseTools.SharedPrefManager;

import static com.example.myapplication.poemPage.poem_index;


public class commentPage extends AppCompatActivity implements View.OnClickListener {

    private ImageView comment;
    private TextView hide_down;
    private EditText comment_content;
    private Button comment_send;
    private ImageView back;

    private LinearLayout rl_enroll;
    private RelativeLayout rl_comment;

    private ListView comment_list;
    private CommentAdapter adapterComment;
    private List<Comment> data;
    private ProgressDialog progressDialog;

    public int id_reply_to = 0;
    public String name_reply_to = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_page);
        initView();
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        // 初始化评论列表
        comment_list = findViewById(R.id.comment_list);
        // 初始化数据
        data = new ArrayList<>();
        // 初始化适配器
        adapterComment = new CommentAdapter(getApplicationContext(), data);
        // 为评论列表设置适配器
        comment_list.setAdapter(adapterComment);

        comment = findViewById(R.id.comment);
        hide_down = findViewById(R.id.hide_down);
        comment_content = findViewById(R.id.comment_content);
        comment_send = findViewById(R.id.comment_send);

        rl_enroll = findViewById(R.id.rl_enroll);
        rl_comment = findViewById(R.id.rl_comment);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance().logOut();
                finish();
                Intent intent = new Intent(commentPage.this, poemPage.class);
                startActivity(intent);
            }
        });

        //初始化已有的评论
        getComment();

        setListener();
    }

    /**
     * 设置监听
     */
    public void setListener(){
        comment.setOnClickListener(this);

        hide_down.setOnClickListener(this);
        comment_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment:
                // 不回复任何人，将名字设为空
                name_reply_to = "";
                id_reply_to = 0;
                // 弹出输入法
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                // 显示评论框
                rl_enroll.setVisibility(View.GONE);
                rl_comment.setVisibility(View.VISIBLE);
                break;
            case R.id.hide_down:
                // 不回复任何人，将名字设为空
                name_reply_to = "";
                id_reply_to = 0;
                // 隐藏评论框
                rl_enroll.setVisibility(View.VISIBLE);
                rl_comment.setVisibility(View.GONE);
                // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                InputMethodManager im = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                break;
            case R.id.comment_send:
                sendComment(name_reply_to, id_reply_to);
                break;
            case R.id.back:
                Intent intent = new Intent(commentPage.this, poemPage.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 发送评论
     */
    public void sendComment(){
        progressDialog.show();
        if(comment_content.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        }else{
            //需要加入数据库的地方
            // 生成评论数据
            Calendar timeGetter = getCalendar();

            //发送至数据库
            final String table_name = ("poem_" + poem_index).trim();
            final String content = comment_content.getText().toString().trim();
            final String username = SharedPrefManager.getInstance().getUsername().trim();
            final String poem_id = String.valueOf(poem_index);
            final String date = "at " + timeGetter.getTime();
            final String parent_id = "0";
            final String like_number = "0";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, DataBaseConstants.URL_WRITECOMMENT, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("table", table_name);
                    params.put("content", content);
                    params.put("poem_id", poem_id);
                    params.put("username", username);
                    params.put("parent_id", parent_id);
                    params.put("date", date);
                    params.put("like_number", like_number);
                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            // 发送完，清空输入框
            comment_content.setText("");
            //刷新评论
            getComment();
        }
    }

    /**
     * 发送回复评论
     */
    public void sendComment(String reply_to, int reply_id){
        progressDialog.show();
        if(comment_content.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "reply cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            //需要加入数据库的地方
            // 生成评论数据
            Calendar timeGetter = getCalendar();

            //发送至数据库
            final String table_name = ("poem_" + poem_index).trim();
            final String content = comment_content.getText().toString().trim();
            final String username = SharedPrefManager.getInstance().getUsername().trim();
            final String poem_id = String.valueOf(poem_index);
            final String date = "at " + timeGetter.getTime();
            final String parent_id = String.valueOf(reply_id);
            final String like_number = "0";
            final String parentName = reply_to;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, DataBaseConstants.URL_WRITECOMMENT, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    if(parentName.equals("")){
                        Map<String, String> params = new HashMap<>();
                        params.put("table", table_name);
                        params.put("content", content);
                        params.put("poem_id", poem_id);
                        params.put("username", username);
                        params.put("parent_id", parent_id);
                        params.put("date", date);
                        params.put("like_number", like_number);
                        return params;
                    }else{
                        Map<String, String> params = new HashMap<>();
                        params.put("table", table_name);
                        params.put("content", "reply to " + parentName + ": " + content);
                        params.put("poem_id", poem_id);
                        params.put("username", username);
                        params.put("parent_id", parent_id);
                        params.put("date", date);
                        params.put("like_number", like_number);
                        return params;
                    }
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            // 发送完，清空输入框
            comment_content.setText("");
            //刷新评论
            getComment();
        }
    }

    /**
     * 获取已有评论
     */
    public void getComment(){
        //创建一个StringRequest类去post用户输入的值，这个创建很复杂，就按照这个格式实现两个方法，一个是正常返回的结果，一个是错误返回的结果
        progressDialog.show();
        adapterComment.clearComment();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DataBaseConstants.URL_GETCOMMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    //获取数据库全表的数据
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        if(!object.getBoolean("error")){
                            //若无异常，现将头像设置好
                            String url = DataBaseConstants.URL_PICTURE + object.getString("picture");
                            System.out.println("id: " + object.getInt("comment_id") + " content: " + object.getString("content"));
                            Comment comment = new Comment(object.getInt("comment_id"), object.getString("username"), object.getString("content"), object.getString("like_number"), object.getString("date"), object.getString("parent_id"), url);
                            adapterComment.addComment(comment);
                        }else {
                            Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("table", "poem_" + poem_index);
                System.out.println("poem_" + poem_index + "======================================");
                return param;
            }
        };
        //加入队列，执行
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        calendar.setTime(date);

        return calendar;
    }

    /**
     * 这个方法用来放头像，是需要和lxy对接的地方，我能实现获取你需要实现一下上传图片，换头像之类的需要访问用户手机的功能
     */
    private void getUserPic(String url, final ImageView imageView) {
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }

        },35, 35, Bitmap.Config.RGB_565,  new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestHandler.getInstance(this).addToRequestQueue(imageRequest);
    }

    /**
     * 增加点赞数
     */
    public void addLikeNumber(int id){
        //发送至数据库
        final String table_name = ("poem_" + poem_index).trim();
        final String commentID = Integer.toString(id);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DataBaseConstants.URL_ADDLIKENUMBER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("table", table_name);
                params.put("commentID", commentID);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    /**
     * 删除评论
     */
    public void deleteComment(int id){
        //发送至数据库
        final String table_name = ("poem_" + poem_index).trim();
        final String commentID = Integer.toString(id);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DataBaseConstants.URL_DELETECOMMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("table", table_name);
                params.put("commentID", commentID);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    /**
     * 内部类适配器
     */
    class CommentAdapter extends BaseAdapter {

        Context context;
        List<Comment> data;

        public CommentAdapter(Context c, List<Comment> data){
            this.context = c;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            //显示的楼层数也是数据库中的comment_id
            final int floor = data.get(i).getId();
            //data中的每一项i
            final int id = i;

            // 重用convertView
            if(convertView == null){
                System.out.println("loading item" + i);
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);
                holder.username = convertView.findViewById(R.id.comment_name);
                holder.comment_content = convertView.findViewById(R.id.comment_content);
                holder.date = convertView.findViewById(R.id.comment_time);
                holder.user_pic = convertView.findViewById(R.id.user_picture);
                holder.like = convertView.findViewById(R.id.comment_like);
                holder.delete = convertView.findViewById(R.id.comment_delete);
                holder.reply = convertView.findViewById(R.id.comment_reply);
                holder.floor_num = convertView.findViewById(R.id.comment_floor);


                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }


            // 适配数据
            holder.username.setText(data.get(i).getName());
            holder.comment_content.setText(data.get(i).getContent());
            holder.date.setText(data.get(i).getDate());
            holder.like.setText("like (" + data.get(i).getLike_num() + ")");
            holder.floor_num.setText("#" + floor);

            //绑定动作
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteComment(floor);
                    notifyDataSetChanged();
                }
            });

            //绑定点赞
            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int like_num = Integer.valueOf(data.get(id).getLike_num()) + 1;
                    data.get(id).setLike_num(Integer.toString(like_num));
                    addLikeNumber(floor);
                    notifyDataSetChanged();
                }
            });

            //绑定回复
            holder.reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 设定回复的用户名
                    name_reply_to = data.get(id).getName();
                    id_reply_to = data.get(id).getId();
                    // 弹出输入法
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    // 显示评论框
                    rl_enroll.setVisibility(View.GONE);
                    rl_comment.setVisibility(View.VISIBLE);
                }
            });

            //获取imageview的bitmap.
            getUserPic(data.get(id).getImageURL(), holder.user_pic);

            System.out.println("loading item " + i + " outside the if");

            //如果是自己的评论 可以删除
            if (data.get(i).getName().equals(SharedPrefManager.getInstance().getUsername())){
                System.out.println("comment id: " + data.get(i).getId() + " comment name: "+ data.get(i).getName() +  " usernmame: " + SharedPrefManager.getInstance().getUsername());
                holder.delete.setVisibility(View.VISIBLE);
                notifyDataSetChanged();
            }

            //如果不是自己的评论 不可以删除
            if (! data.get(i).getName().equals(SharedPrefManager.getInstance().getUsername())){
                System.out.println("comment id: " + data.get(i).getId() + " comment name: "+ data.get(i).getName() +  " usernmame: " + SharedPrefManager.getInstance().getUsername());
                holder.delete.setVisibility(View.GONE);
                notifyDataSetChanged();
            }

            return convertView;
        }

        /**
         * 添加一条评论,刷新列表
         * @param comment
         */
        public void addComment(Comment comment){
            data.add(comment);
            notifyDataSetChanged();
        }

        public void clearComment(){
            data.clear();
            notifyDataSetChanged();
        }

        class ViewHolder{
            TextView username;
            TextView floor_num;
            ImageView user_pic;
            TextView comment_content;
            TextView date;
            TextView like;
            TextView delete;
            TextView reply;
        }

    }

}
