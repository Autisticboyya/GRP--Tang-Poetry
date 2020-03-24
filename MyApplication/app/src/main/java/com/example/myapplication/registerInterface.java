package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
* 和login的类几乎一摸一样，就不多说了
*/
public class registerInterface extends AppCompatActivity {
   private EditText editTextUsername, editTextPassword, editTextEmail;
   private Button buttonRegister, backToLogin;
   private ProgressDialog progressDialog;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.registration_interface);

       if(SharedPrefManager.getInstance().isLoggedIn()){
           finish();
           Intent intent = new Intent(registerInterface.this, homePage.class);
           startActivity(intent);
           return;
       }

       editTextUsername = findViewById(R.id.username_registration);
       editTextPassword = findViewById(R.id.password_registration);
       editTextEmail = findViewById(R.id.email_registration);

       buttonRegister = findViewById(R.id.register);
       backToLogin = findViewById(R.id.go_back_login);

       progressDialog = new ProgressDialog(this);

       buttonRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               registerUser();
           }
       });

       backToLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(registerInterface.this, loginInterface.class);
               startActivity(intent);
           }
       });

   }

   public void registerUser(){
       final String email = editTextEmail.getText().toString().trim();
       final String password = editTextPassword.getText().toString().trim();
       final String username = editTextUsername.getText().toString().trim();
       progressDialog.setMessage("Registering...");
       progressDialog.show();

       StringRequest stringRequest = new StringRequest(Request.Method.POST, DataBaseConstants.URL_REGISTER, new Response.Listener<String>() {
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
               params.put("username", username);
               params.put("password", password);
               params.put("email", email);
               return params;
           }
       };

       RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
   }
}
