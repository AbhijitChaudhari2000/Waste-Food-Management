package com.example.wastefoodmanagment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Addngo extends AppCompatActivity {
    EditText name,ndetail,wname,wphone,address;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addngo);
        name = findViewById(R.id.ngoname);
        ndetail = findViewById(R.id.ngodet);
        wname = findViewById(R.id.nameworker);
        wphone = findViewById(R.id.workercontact);
        address = findViewById(R.id.emailid);
        b1 = findViewById(R.id.btn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest2(name.getText().toString(),ndetail.getText().toString(), wname.getText().toString(),  wphone.getText().toString(),  address.getText().toString());
                registeruser();
            }});

    }



    void registeruser(){
        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError("Please enter username");
            name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ndetail.getText().toString())) {
            ndetail.setError("Please enter emailid");
            ndetail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(wname.getText().toString()))
        {wname.setError("Please enter username");
            wname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty( wphone.getText().toString())) {
            wphone.setError("Please enter username");
            wphone.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            address.setError("Please enter username");
            address.requestFocus();
            return;
        }

    }



    void sendRequest2(String name, String ndetail, String wname, String wphone, String address ){
        RequestQueue queue = Volley.newRequestQueue(Addngo.this);
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

                                Intent intent = new Intent(Addngo.this,WorkerDashboard.class);
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
                params.put("ndetail","" +ndetail);
                params.put("wname", ""+wname);
                params.put("wphone",""+wphone);
                params.put("address","" +address);


                params.put("RESULT_TYPE","ADDNGO");
                return params;
            };
        };
        queue.add(myReq);

    }

}