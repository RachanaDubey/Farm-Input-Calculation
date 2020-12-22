package com.rait.registeractivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class Aboutus extends AppCompatActivity {
    SessionManager sessionManager;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        name=findViewById(R.id.name);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);
        name=findViewById(R.id.name);
        name.setText("Hi "+mName);
    }
}
