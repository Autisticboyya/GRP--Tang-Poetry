package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dataBaseTools.DataBaseConstants;
import dataBaseTools.RequestHandler;
import dataBaseTools.SharedPrefManager;

/**
 * 实现用户登陆的界面，布局很丑你喜欢也可以改一下反正我真的不知道怎么放好看哈哈哈哈哈
 */
public class loginInterface extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonRegister, buttonLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_interface);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        //这个就是调用了SharedPrefManager这个类的方法去判断是否登陆，若true，直接跳转到homepage
        if(SharedPrefManager.getInstance().isLoggedIn()){
            finish();
            Intent intent = new Intent(loginInterface.this, MainActivity.class);
            startActivity(intent);
            return;
        }

        editTextPassword = findViewById(R.id.password_login);
        editTextUsername = findViewById(R.id.username_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        /**
         * goto register interface
         */
        buttonLogin = findViewById(R.id.go_to_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        /**
         * goto register interface
         */
        buttonRegister = findViewById(R.id.go_to_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginInterface.this, registerInterface.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 这个方法很关键，用户输入的内容就是通过这个方法post给数据判断，若登陆成功再从数据库返回需要的用户信息
     */
    private void userLogin(){
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        //提示框，不用太在意
        progressDialog.show();

        //创建一个StringReques类去post用户输入的值，这个创建很复杂，就按照这个格式实现两个方法，一个是正常返回的结果，一个是错误返回的结果
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DataBaseConstants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        //保存登录信息
                        SharedPrefManager.getInstance().userLogin(obj.getString("username"), obj.getString("picture"));
                        Toast.makeText(getApplicationContext(), "user login successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(loginInterface.this, homePage.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
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
                param.put("username", username);
                param.put("password", password);
                return param;
            }
        };
        //加入队列，执行
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}
