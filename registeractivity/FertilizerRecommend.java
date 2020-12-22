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
import android.widget.TextView;
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

public class FertilizerRecommend extends AppCompatActivity {
    SessionManager sessionManager;

    public Button btn_schedule;
    private EditText region, season, soil,land,crop,fertilizer,quantity,dose;
    TextView name,email;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.1.6/FIC/fertilizer_recommend.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_fertilizer_recommend);
        super.onCreate(savedInstanceState);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        loading = findViewById(R.id.loading);
        btn_schedule = (Button) findViewById(R.id.btn_schedule);
        region = (EditText) findViewById(R.id.region);
        season = (EditText) findViewById(R.id.season);
        soil= (EditText)findViewById(R.id.soil);
        land = (EditText)findViewById(R.id.land);
        crop = (EditText)findViewById(R.id.crop);
        fertilizer = (EditText)findViewById(R.id.fertilizer);
        quantity = (EditText)findViewById(R.id.quantity);
        dose = (EditText)findViewById(R.id.dose);



        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);
        name.setText("Hi  " +mName);
        email.setText(mEmail);


        Bundle bundle=this.getIntent().getExtras();

        if(bundle!=null){
            String str1=bundle.getString("region");
            String str2=bundle.getString("season");
            String str3=bundle.getString("soil");
            String str4=bundle.getString("land");
            String str5=bundle.getString("crop");

            region.setText(str1);
            season.setText(str2);
            soil.setText(str3);
            land.setText(str4);
            crop.setText(str5);


            if(str4.toString().equals("1000-2000 sq.m"))
            {
                dose.setText("After every 2 months");
                quantity.setText("60kg approx");
            }
            else if(str4.toString().equals("2000-3000 sq.m"))
            {
                dose.setText("After every 4 months");
                quantity.setText("80kg approx");
            }
            else if(str4.toString().equals("3000-4000 sq.m"))
            {
                dose.setText("After every 6 months");
                quantity.setText("100kg approx");
            }
            else{
                dose.setText("After every 5 months");
                quantity.setText("120kg approx");
            }



            if(str5.toString().equals("Cucumber") || str5.toString().equals("Red Chillies") || str5.toString().equals("Rice") || str5.toString().equals("Cabbage"))
            {
                fertilizer.setText("Ammonium Nitrate");
            }
            else if(str5.toString().equals("Sunflower") || str5.toString().equals("Musk-Melon") || str5.toString().equals("Sugarcane")|| str5.toString().equals("Wheat"))
            {
                fertilizer.setText("N-P-K 20-10-10");
            }
            else if(str5.toString().equals("Pumpkin") || str5.toString().equals("Jute") || str5.toString().equals("Barley")
                    || str5.toString().equals("Green Pea") || str5.toString().equals("Millets"))
            {
                fertilizer.setText("Diammonium Phosphate");
            }
            else if(str5.toString().equals("Watermelon")|| str5.toString().equals("Mustard") || str5.toString().equals("Spinach")|| str5.toString().equals("Cotton"))
            {
                fertilizer.setText("Triple Super Phosphate");
            }
            else if(str5.toString().equals("Bitter gourd") || str5.toString().equals("Cauliflower")|| str5.toString().equals("Groundnuts"))
            {
                fertilizer.setText("Potassium Chloride");
            }
            else
            {
                fertilizer.setText("Potassium Chloride");
            }


        }


        btn_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Schedule();
            }
        });

    }


    private void Schedule() {

        final String region = this.region.getText().toString().trim();
        final String season = this.season.getText().toString().trim();
        final String soil = this.soil.getText().toString().trim();
        final String land = this.land.getText().toString().trim();
        final String crop = this.crop.getText().toString().trim();
        final String fertilizer = this.fertilizer.getText().toString().trim();
        final String quantity = this.quantity.getText().toString().trim();
        final String dose = this.dose.getText().toString().trim();

        loading.setVisibility(View.VISIBLE);
        btn_schedule.setVisibility(View.GONE);


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        loading.setVisibility(View.GONE);

                        if (success.equals("1")) {
                            Toast.makeText(FertilizerRecommend.this, "We'll notify you soon", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(FertilizerRecommend.this, Notification_Fert.class);

                            Bundle bundle=new Bundle();
                            bundle.putString("dose",dose);
                            intent.putExtras(bundle);
                            startActivityForResult(intent,0);

                            //startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(FertilizerRecommend.this, "Registered Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_schedule.setVisibility(View.VISIBLE);
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(FertilizerRecommend.this, "Feedback Error" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email",email.getText().toString().trim());
                    params.put("region", region);
                    params.put("season", season);
                    params.put("soil", soil);
                    params.put("land", land);
                    params.put("crop", crop);
                    params.put("fertilizer", fertilizer);
                    params.put("quantity", quantity);
                    params.put("dose", dose);
                    return params;

                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


    }
}



