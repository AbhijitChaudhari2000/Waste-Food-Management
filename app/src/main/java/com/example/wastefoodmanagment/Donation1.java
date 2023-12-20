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

public class Donation1 extends AppCompatActivity {
    EditText namedonor,idngo,donate,contact,address;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation1);
        namedonor = findViewById(R.id.cername);
        idngo = findViewById(R.id.cerid);
        contact = findViewById(R.id.contact1);
        address = findViewById(R.id.address1);
        donate = findViewById(R.id.donate1);
        btn = findViewById(R.id.ceradd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest2(namedonor.getText().toString(),idngo.getText().toString(), contact.getText().toString(), address.getText().toString(),donate.getText().toString());

            }});
    }
    void sendRequest2(String namedonor, String idngo,String contact, String address,String donate){
        RequestQueue queue = Volley.newRequestQueue(Donation1.this);
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

                                Intent intent = new Intent(Donation1.this,MainActivity.class);
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
                params.put("namedonor","" +namedonor);
                params.put("idngo","" +idngo);
                params.put("contact",""+contact);
                params.put("address","" +address);
                params.put("donate", ""+donate);
                params.put("RESULT_TYPE","DONATION");
                return params;
            };
        };
        queue.add(myReq);


    }
}