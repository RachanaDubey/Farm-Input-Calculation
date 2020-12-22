package com.rait.registeractivity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class Userhome extends AppCompatActivity {
    TextView text1,text2,text3,text4,text5,text6,text7,name;
    TextView logout;
    ImageView img1,img2,img3,img4,img5,img6;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        sessionManager =new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user= sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail =user.get(sessionManager.EMAIL);
        name=findViewById(R.id.name);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        text5 = (TextView)findViewById(R.id.text5);
        text6 = (TextView)findViewById(R.id.text6);
        text7 = (TextView)findViewById(R.id.text7);
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        img4 = (ImageView)findViewById(R.id.img4);
        img5 = (ImageView)findViewById(R.id.img5);
        img6 = (ImageView)findViewById(R.id.img6);
        name.setText(mName);
        logout=(TextView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
                Intent intent = new Intent(Userhome.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        if(name.equals("Admin"))
        {
            Intent intent = new Intent(Userhome.this, AdminHome.class);
            startActivity(intent);
            finish();
        }


    }
    public void weather(View view)
    {
        Intent intent = new Intent(Userhome.this,WeatherPage.class);
        startActivity(intent);
    }
    public void Startharvest(View view)
    {
        Intent intent = new Intent(Userhome.this,Startharvest.class);
        startActivity(intent);
    }public void Nearbymarket(View view)
    {
        Intent intent = new Intent(Userhome.this,NearbyMarket.class);
        startActivity(intent);
    }public void queryResolver(View view)
    {
        Intent intent = new Intent(Userhome.this,Feedback.class);
        startActivity(intent);
    }public void Progresstracker(View view)
    {
        Intent intent = new Intent(Userhome.this,Graphsample.class);
        startActivity(intent);
    }public void feedback(View view)
    {
        Intent intent = new Intent(Userhome.this,Aboutus.class);
        startActivity(intent);
    }



}
