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

public class DonormainActivity extends AppCompatActivity {
    RecyclerView rc1;
    ArrayList<ItemModel> itemModelArrayList =new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donormain);
        rc1 = findViewById(R.id.rc1);


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
            params.put("RESULT_TYPE","GETNGO");
            return params;
        };
        };

        queue.add(stringRequest);


    }

    private void setRecyclerview(JSONArray jarray)  {
        for(int i = 0;i< jarray.length(); i++) {
            try {
                JSONObject jobj = jarray.getJSONObject(i);

                String jname = jobj.getString("name");
                String jaddress = jobj.getString("address");
                String jid = jobj.getString("id");
              String jcontact= jobj.getString("wphone");



                ItemModel i1 = new ItemModel();


                i1.setName(jname);
                i1. setAddress(jaddress);
                i1.setId(jid);
               i1.setContact(jcontact);

                itemModelArrayList.add(i1);
            } catch( Exception e){
                e.printStackTrace();
            }
        }
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rc1.setLayoutManager(lm);
        rc1.setAdapter(new RecyclerAdapter( this,itemModelArrayList));
    }
}