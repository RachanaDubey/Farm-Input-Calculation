package com.rait.registeractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private AwesomeValidation awesomeValidation;

    private EditText name,email,password,c_password;
    private Button btn_regist;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.1.6/FIC/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        btn_regist = (Button)findViewById(R.id.btn_regist);

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String conpass=new String(c_password.getText().toString().trim());

                if(email.getText().toString().trim().matches("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*\n" +
                        "      @[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$;\n"))
                {
                    email.requestFocus();
                    Toast.makeText(RegisterActivity.this,
                            "Please enter a valid email",Toast.LENGTH_SHORT).show();
                }
                else if(email.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()
                        || name.getText().toString().trim().isEmpty() || c_password.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,
                            "Please fill the required fields",Toast.LENGTH_SHORT).show();
                }

                else if((password.getText().toString().trim()).equals(conpass)==false)
                {
                    Toast.makeText(RegisterActivity.this,
                            "The passwords do not match",Toast.LENGTH_SHORT).show();
                }

                else if(password.getText().toString().trim().length()<3)
                {
                    Toast.makeText(RegisterActivity.this,
                            "Password length is too short",Toast.LENGTH_SHORT).show();
                }

                else {
                    Regist();
                }
            }
        });
    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String name=this.name.getText().toString().trim();
        final String email=this.email.getText().toString().trim();
        final String password=this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success =jsonObject.getString("success");
                    loading.setVisibility(View.GONE);

                    if(success.equals("1"))
                    {
                        Toast.makeText(RegisterActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this,"Registered Error"+e.toString(),Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    btn_regist.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,"Registered Error"+error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("name",name);
                params.put("email",email);
                params.put("password",password);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
