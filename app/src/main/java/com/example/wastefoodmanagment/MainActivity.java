package com.example.wastefoodmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Spinner sp1;
    EditText username,password;
    Button btnlogin,certificate, newreg;
    TextView forpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp1 = findViewById(R.id.category);
        username = findViewById(R.id.logusername);
        password = findViewById(R.id.logpassword);
        btnlogin = findViewById(R.id.logbutton);
        newreg = findViewById(R.id.newreg);
        certificate = findViewById(R.id.certificate);

        forpass = findViewById(R.id.forget);
        newreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, AskforRegistration.class);
                startActivity(i1);
            }
        });
        certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, GenerateCertificate.class);
                startActivity(i1);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senddata(username.getText().toString(), password.getText().toString(),sp1.getSelectedItem().toString());

            }


        });
    }



    void senddata(String username, String password,String sp1) {

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myReq = new StringRequest(Request.Method.POST, "http://192.168.13.216/wastefoodmanagment/get_result1.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("VOLLEYTAG", "" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    //Log.d("VOLLEYTAG", "" + obj.getString("success"));


                    if(sp1.equals("DONOR")&&(obj.getInt("success")==1)){

                        Intent i1= new Intent(MainActivity.this,DonormainActivity.class);
                        startActivity(i1);
                    }else if(sp1.equals("WORKER")&&(obj.getInt("success")==1)){
                        Intent i1= new Intent(MainActivity.this,WorkerDashboard.class);
                        startActivity(i1);
                    }



                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEYTAG", "" + error);
            }

        }) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", "" + username);
                params.put("password", "" + password);
                params.put("sp1",""+sp1);


                params.put("RESULT_TYPE", "LOGIN");
                return params;
            }

            ;

        };
        queue.add(myReq);
    }
}