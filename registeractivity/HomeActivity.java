package com.rait.registeractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    private TextView name,email;
    private Button btn_logout,btn_feedback,btn_proceed;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager =new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user= sessionManager.getUserDetail();
        final String mName = user.get(sessionManager.NAME);
        final String mEmail =user.get(sessionManager.EMAIL);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        name.setText(mName);
        email.setText(mEmail);
        btn_logout=findViewById(R.id.btn_logout);
        btn_feedback=findViewById(R.id.btn_feedback);
        btn_proceed=findViewById(R.id.btn_proceed);



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sessionManager.logout();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
               finish();

            }
        });
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Feedback.class);
                startActivity(intent);
                finish();

            }
        });


        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(mEmail.equals("admin@gmail.com"))
                {
                        Intent intent = new Intent(HomeActivity.this, AdminHome.class);
                        startActivity(intent);
                        finish();
                }
                else if(name.equals("Admin")==false) {
                    Intent intent = new Intent(HomeActivity.this, Userhome.class);
                    startActivity(intent);
                    finish();

                }

                else {
                   Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                   startActivity(intent);
                   finish();

               }

            }
        });

    }
}
