package com.intern.managementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText usern,email,password;
    RelativeLayout root;
    Api api;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        usern=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        root=findViewById(R.id.root);
        api=new Api();
        findViewById(R.id.gotologin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.signup_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usern.getText().toString().equals(""))
                {
                    // Toast.makeText(RegisterActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(root, "Enter Your Username", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(email.getText().toString().equals(""))
                {
                    // Toast.makeText(RegisterActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(root, "Enter Your EmailID", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else if(password.getText().toString().equals(""))
                {
                    // Toast.makeText(RegisterActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar
                            .make(root, "Enter Your Password", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                  //  Toast.makeText(RegisterActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//
                    StringRequest request=new StringRequest(Request.Method.POST, api.registerurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(RegisterActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }


                    ){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                          Map<String,String>params=new HashMap<>();
                          params.put("username",usern.getText().toString());
                          params.put("email",email.getText().toString());
                          params.put("password",password.getText().toString());
                          return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(RegisterActivity.this);
                    requestQueue.add(request);
                }
            }
        });


    }
}
