package com.rait.registeractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    SessionManager sessionManager;

    String[] type = {"Choose a category...", "Query", "Feedback", "Others"};
    Spinner spin;
    public Button btn_feedback;
    private EditText name, email, feedback;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.1.6/FIC/feedback.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_feedback);
        loading = findViewById(R.id.loading);
        btn_feedback = (Button) findViewById(R.id.btn_feedback);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        spin = findViewById(R.id.spinner);
        feedback = findViewById(R.id.feedback);
        super.onCreate(savedInstanceState);


        sessionManager =new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user= sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail =user.get(sessionManager.EMAIL);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        name.setText(mName);
        email.setText(mEmail);

        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(feedback.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(Feedback.this,
                            "Please fill the required fields",Toast.LENGTH_SHORT).show();
                }
                else if(feedback.getText().toString().trim().length()<5)
                {
                    Toast.makeText(Feedback.this,
                            "Feedback description is too short",Toast.LENGTH_SHORT).show();
                }
                else{
                    FeedbackSubmit();
                }
            }
        });

    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        if (spin.getSelectedItem() == "Choose a category...") {


        } else {
            //FeedbackSubmit();
            //Toast.makeText(getApplicationContext(), type[position], Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    private void FeedbackSubmit() {

        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String spin = this.spin.getSelectedItem().toString();
        final String feedback = this.feedback.getText().toString().trim();
        final String cmp = new String("Choose a category...");
        loading.setVisibility(View.VISIBLE);
        btn_feedback.setVisibility(View.GONE);
        if (spin.equals("" + cmp)) {
            Toast.makeText(Feedback.this, "Please select a category", Toast.LENGTH_SHORT).show();
            btn_feedback.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);

        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        loading.setVisibility(View.GONE);

                        if (success.equals("1")) {
                            Toast.makeText(Feedback.this, "Feedback recorded Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Feedback.this, Userhome.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Feedback.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_feedback.setVisibility(View.VISIBLE);
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Feedback.this, "Feedback Error" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("email", email);
                    params.put("feedback_cat", spin);
                    params.put("feedback", feedback);
                    return params;

                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        finish();

    }
}