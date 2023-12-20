package com.example.wastefoodmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Addcertificate extends AppCompatActivity {
    EditText name,date;
    Button ceradd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcertificate);
        name = findViewById(R.id.cername);
        date = findViewById(R.id.cerid);
        ceradd = findViewById(R.id.ceradd);
        ceradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest2(name.getText().toString(), date.getText().toString());

            }
        });
    }
        void sendRequest2(String name, String date){
            RequestQueue queue = Volley.newRequestQueue(Addcertificate.this);
            StringRequest myReq = new StringRequest(Request.Method.POST,
                    "http://192.168.13.216/wastefoodmanagment/get_result1.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("VOLLEYTAG", "" + response);
                            try{
                                JSONObject obj = new JSONObject(response);
                                Log.d("VOLLEYTAG", "" + obj.getString("success"));
                                if( obj.getInt("success")==1){

                                    Intent intent = new Intent(Addcertificate.this,WorkerDashboard.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();

                                } else{
                                    Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_SHORT).show();
                                }


                            }
                            catch (Exception e){

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("VOLLEYTAG", "IN Error " + error);
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }) {

                protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name","" +name);
                    params.put("date","" +date);


                    params.put("RESULT_TYPE","ADDCER");
                    return params;
                };
            };
            queue.add(myReq);

        }

    }