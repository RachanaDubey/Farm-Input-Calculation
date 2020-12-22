package com.rait.registeractivity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NearbyMarket extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_market);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {

        String url = "https://api.myjson.com/bins/1825zg";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("mandi");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject mandi = jsonArray.getJSONObject(i);

                                String state = mandi.getString("state");
                                String district = mandi.getString("district");
                                String market = mandi.getString("market");
                                String commodity = mandi.getString("commodity");
                                int min_price = mandi.getInt("min_price");
                                int max_price = mandi.getInt("max_price");
                                int modal_price = mandi.getInt("modal_price");

                                mTextViewResult.append("State : "+ state + "\nDistrict : " + district + "\nMarket : " + market +
                                        "\nCommodity : " + commodity + "\nMin Price : " + String.valueOf(min_price) + "\nMax Price : " + max_price + "\nModal Price : " + modal_price + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}