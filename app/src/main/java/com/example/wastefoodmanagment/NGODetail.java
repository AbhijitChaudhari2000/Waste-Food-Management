package com.example.wastefoodmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class NGODetail extends AppCompatActivity {
    Button cancle,donation;
    TextView name,ndetail,wname,wphone,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodetail);
        cancle = findViewById(R.id.canbtn);
        donation = findViewById(R.id.msgbuton);
        name = findViewById(R.id.namengo);
        ndetail = findViewById(R.id.detailngo);
        wname = findViewById(R.id.workername);
        wphone = findViewById(R.id.contactno);
        id = findViewById(R.id.id1);

        String id1 = getIntent().getStringExtra("id");
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(NGODetail.this,DonormainActivity.class) ;
                startActivity(i1);
            }
        });
        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(NGODetail.this,Donation1.class) ;
                startActivity(i1);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        String url="http://192.168.13.216/wastefoodmanagment/get_result1.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("VOLLEYTAG","IN Response"+response);

                try {

                    //   JSONArray jarray = new JSONArray(response);
                    //String id11=  jarray.getString(0);
                    JSONObject jsonObject = new JSONObject(response);
                    String jid = jsonObject.getString("id");
                    String jname = jsonObject.getString("name");
                    String jndetail = jsonObject.getString("ndetail");
                    String jwname = jsonObject.getString("wname");
                    String jwphone = jsonObject.getString("wphone");

                    name.setText(jname);
                    ndetail.setText(jndetail);
                    wname.setText(jwname);
                    wphone.setText(jwphone);
                    id.setText(jid);


                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("VOLLEYTAG","IN Error "+error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", ""+id1);
                params.put("RESULT_TYPE", "GETNGODETAIL");
                return params;
            }

            ;
        };
        queue.add(stringRequest);





    }
}