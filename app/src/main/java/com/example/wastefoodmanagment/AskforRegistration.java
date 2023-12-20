package com.example.wastefoodmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AskforRegistration extends AppCompatActivity {
    Button btnworker,btndonor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askfor_registration);
        btndonor = findViewById(R.id.btndonor);
        btnworker = findViewById(R.id.btnworker);
        btndonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(AskforRegistration.this,DonorRegistration.class);
                startActivity(i1);
            }
        });
        btnworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(AskforRegistration.this,WorkerRegistration.class);
                startActivity(i1);
            }
        });
    }

}