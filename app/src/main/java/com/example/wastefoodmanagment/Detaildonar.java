package com.example.wastefoodmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Detaildonar extends AppCompatActivity {
    RecyclerView rc2;
    ArrayList<Item_Model> itemModelArrayList =new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaildonar);
        rc2 = findViewById(R.id.rc2);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.13.216/wastefoodmanagment/get_result1.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("VOLLEYTAG",""+response);
                try {
                    JSONArray jarray = new JSONArray(response);
                    setRecyclerview(jarray);
                }catch(JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEYTAG",""+error);


            }


        }) { protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("RESULT_TYPE","GETDONATION");
            return params;
        };
        };

        queue.add(stringRequest);


    }

    private void setRecyclerview(JSONArray jarray)  {
        for(int i = 0;i< jarray.length(); i++) {
            try {
                JSONObject jobj = jarray.getJSONObject(i);
                String jidngo = jobj.getString("idngo");
                String jnamedonor = jobj.getString("namedonor");
                String jaddress = jobj.getString("address");
                String jdonate = jobj.getString("donate");
                String jcontact = jobj.getString("contact");
                String jid = jobj.getString("id");



                Item_Model i1 = new Item_Model();
                i1.setContact(jcontact);
                i1.setDonate(jdonate);
                i1. setAddress(jaddress);
                i1.setId(jid);
                i1.setIdngo(jidngo);
                i1.setName(jnamedonor);
                itemModelArrayList.add(i1);
            } catch( Exception e){
                e.printStackTrace();
            }
        }
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rc2.setLayoutManager(lm);
        rc2.setAdapter(new RecyclerAdapter2( this,itemModelArrayList));
    }

}