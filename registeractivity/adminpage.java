package com.rait.registeractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class adminpage extends AppCompatActivity {
    private TextView name,email;
    private Button btn_logout,btn_feedback,btn_proceed;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        sessionManager =new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user= sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail =user.get(sessionManager.EMAIL);
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

            }
        });
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminpage.this, Feedback.class);
                startActivity(intent);

            }
        });


        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminpage.this, AdminHome.class);
                startActivity(intent);

            }
        });

    }
}
