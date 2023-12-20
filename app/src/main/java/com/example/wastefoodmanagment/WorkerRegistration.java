package com.example.wastefoodmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class WorkerRegistration extends AppCompatActivity {
    EditText username,emailid,password,contact,address,ngolicen;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_registration);
        username = findViewById(R.id.ngoname);
        password = findViewById(R.id.ngodet);
        contact = findViewById(R.id.nameworker);
        address = findViewById(R.id.workercontact);
        emailid = findViewById(R.id.emailid);
        ngolicen = findViewById(R.id.liecen);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest2(username.getText().toString(),emailid.getText().toString(), password.getText().toString(), contact.getText().toString(), address.getText().toString(),ngolicen.getText().toString());
                registeruser();
            }});

    }
    void registeruser(){
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Please enter username");
            username.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(emailid.getText().toString())) {
            emailid.setError("Please enter emailid");
            emailid.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString()))
        {password.setError("Please enter password");
            password.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(contact.getText().toString())) {
            contact.setError("Please enter contact");
            contact.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address.getText().toString())) {
            address.setError("Please enter address");
            address.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ngolicen.getText().toString())) {
            ngolicen.setError("Please enter Licen no.");
            ngolicen.requestFocus();
            return;
        }

    }

    void sendRequest2(String username, String emailid,String password, String contact, String address,String ngolicen){
        RequestQueue queue = Volley.newRequestQueue(WorkerRegistration.this);
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

                                Intent intent = new Intent(WorkerRegistration.this,MainActivity.class);
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
                params.put("username","" +username);
                params.put("emailid","" +emailid);
                params.put("password", ""+password);
                params.put("contact",""+contact);
                params.put("address","" +address);
                params.put("ngolicen", ""+ngolicen);
                params.put("RESULT_TYPE","REGISTRATIONWORKER");
                return params;
            };
        };
        queue.add(myReq);


    }

}