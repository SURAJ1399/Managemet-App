package com.intern.managementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class LoginActivity extends AppCompatActivity {
    EditText email,password,signp;
    RelativeLayout root;
Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        getSupportActionBar().hide();
        root=findViewById(R.id.root);

        signp=findViewById(R.id.register);
        signp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
      api=new Api();

        findViewById(R.id.signinbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(LoginActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                if(email.getText().toString().equals(""))
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
                   // Toast.makeText(LoginActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//
                    StringRequest request=new StringRequest(Request.Method.POST, api.loginurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                           if(response.equals("Login Successful"))
                           {
                               Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                               startActivity(intent);
                               finish();

                           }


                            Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }


                    ){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String>params=new HashMap<>();

                            params.put("email",email.getText().toString());
                            params.put("password",password.getText().toString());
                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
                    requestQueue.add(request);
                }
            }
        });


    }
}
