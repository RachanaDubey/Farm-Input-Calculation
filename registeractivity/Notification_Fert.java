package com.rait.registeractivity;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;


public class Notification_Fert extends Activity {
    SessionManager sessionManager;
    String str1;
    int a;
    EditText et1;
    TextView name;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification__fert);
        et1 = (EditText)findViewById(R.id.et1);
        name=(TextView)findViewById(R.id.name);
        String str2,str3,str4;
        str2="After every 2 months";
        str3="After every 4 months";
        str4="After every 6 months";

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);
        name.setText("Hi "+mName);

        bt1=(Button)findViewById(R.id.bt1);

        Bundle bundle=this.getIntent().getExtras();

        if(bundle!=null) {
            str1 = bundle.getString("dose");
            if(str1.toString().trim().equals(str2)==true) {
                et1.setText("2");
            }
            else if(str1.toString().trim().equals(str3)==true) {
                et1.setText("4");
            }
            else if(str1.toString().trim().equals(str4)==true) {
                et1.setText("6");
            }
            else
            {
                et1.setText("5");
            }
        }


    }
    public void bt1(View view)
    {
        a = Integer.parseInt(et1.getText().toString());
        Toast.makeText(Notification_Fert.this, "We'll Notify You Soon!" , Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, a);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }

}
