package com.rait.registeractivity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Startharvest extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner region,season,soil,land,crop;
    SessionManager sessionManager;
    public Button btn_submit;
    private ProgressBar loading;
    TextView name,email;
    private static String URL_REGIST = "http://192.168.1.6/FIC/startharvest.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startharvest);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        loading = findViewById(R.id.loading);

        sessionManager =new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user= sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail =user.get(sessionManager.EMAIL);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.email);
        name.setText(mName);
        email.setText(mEmail);

        region = (Spinner) findViewById(R.id.region);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.region, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(adapter);
        region.setOnItemSelectedListener(this);

        season = (Spinner) findViewById(R.id.season);
        //ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.season, android.R.layout.simple_spinner_item);
        //adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //season.setAdapter(adapter1);
        season.setOnItemSelectedListener(this);

        soil = (Spinner) findViewById(R.id.soil);
        //ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.soiltype, android.R.layout.simple_spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //soiltype.setAdapter(adapter2);
        soil.setOnItemSelectedListener(this);

        land = (Spinner) findViewById(R.id.land);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.landsize, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        land.setAdapter(adapter3);
        land.setOnItemSelectedListener(this);

        crop = findViewById(R.id.crop);
        //ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.crops, android.R.layout.simple_spinner_item);
        //adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //crops.setAdapter(adapter4);
        //crops.setOnItemSelectedListener(this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HarvestSubmit();
            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        String sp1= String.valueOf(season.getSelectedItem());
        String sp2 = String.valueOf(soil.getSelectedItem());
        //String sp3 = String.valueOf(soiltype.getSelectedItem());
        //Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, sp2, Toast.LENGTH_SHORT).show();

        if(sp1.contentEquals("March to June(Summer)") && sp2.contentEquals("Black Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Cucumber");
            list.add("Sunflower");
            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter1.notifyDataSetChanged();
            crop.setAdapter(adapter1);
        }
        if(sp1.contentEquals("March to June(Summer)") && sp2.contentEquals("Red Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Pumpkin");
            list.add("Water-melon");
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter2.notifyDataSetChanged();
            crop.setAdapter(adapter2);
        }
        if(sp1.contentEquals("March to June(Summer)") && sp2.contentEquals("Alluvial Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Bitter ground");
            list.add("Red Chilles");
            list.add("Musk-Melon");
            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter3.notifyDataSetChanged();
            crop.setAdapter(adapter3);
        }
        if(sp1.contentEquals("July to Oct(Rainy)") && sp2.contentEquals("Black Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Cotton");
            list.add("Sugarcane");
            list.add("Jowar");
            list.add("Ragi");
            list.add("maize");
            list.add("Tomato");

            ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter4.notifyDataSetChanged();
            crop.setAdapter(adapter4);
        }
        if(sp1.contentEquals("July to Oct(Rainy)") && sp2.contentEquals("Red Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Groundnuts");
            list.add("Millets");
            list.add("Soya beans");
            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter5.notifyDataSetChanged();
            crop.setAdapter(adapter5);
        }
        if(sp1.contentEquals("July to Oct(Rainy)") && sp2.contentEquals("Alluvial Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Rice");
            list.add("Sugarcane");
            list.add("Jute");
            list.add("Red Chillies");

            ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter6.notifyDataSetChanged();
            crop.setAdapter(adapter6);
        }
        if(sp1.contentEquals("Oct to March(Winter)") && sp2.contentEquals("Black Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Wheat");
            list.add("Potato");
            list.add("Onion");
            list.add("Garlic");

            ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter7.notifyDataSetChanged();
            crop.setAdapter(adapter7);
        }
        if(sp1.contentEquals("Oct to March(Winter)") && sp2.contentEquals("Red Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Barley");
            list.add("Mustard");
            list.add("Cauliflower");
            list.add("Cabbage");

            ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter8.notifyDataSetChanged();
            crop.setAdapter(adapter8);
        }
        if(sp1.contentEquals("Oct to March(Winter)") && sp2.contentEquals("Alluvial Soil")) {
            List<String> list = new ArrayList<String>();
            list.add("Wheat");
            list.add("Green Pea");
            list.add("Spinach");
            list.add("Cabbage");

            ArrayAdapter<String> adapter9 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter9.notifyDataSetChanged();
            crop.setAdapter(adapter9);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




    private void HarvestSubmit() {

        //final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String region = this.region.getSelectedItem().toString();
        final String season = this.season.getSelectedItem().toString();
        final String soil = this.soil.getSelectedItem().toString();
        final String land = this.land.getSelectedItem().toString();
        final String crop = this.crop.getSelectedItem().toString();

        //final String feedback = this.feedback.getText().toString().trim();
        //final String cmp = new String("Choose a category");
        loading.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.GONE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        loading.setVisibility(View.GONE);

                        if (success.equals("1")) {
                            Toast.makeText(Startharvest.this, "Request recorded Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Startharvest.this, FertilizerRecommend.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("region",region);
                            bundle.putString("season",season);
                            bundle.putString("soil",soil);
                            bundle.putString("land",land);
                            bundle.putString("crop",crop);
                            intent.putExtras(bundle);
                            startActivityForResult(intent,0);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Startharvest.this, "Registered Error" + e.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_submit.setVisibility(View.VISIBLE);
                    }
                    btn_submit.setVisibility(View.VISIBLE);

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Startharvest.this, "Feedback Error" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("region", region);
                    params.put("season", season);
                    params.put("soil", soil);
                    params.put("land", land);
                    params.put("crop", crop);
                    return params;

                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


    }

}
