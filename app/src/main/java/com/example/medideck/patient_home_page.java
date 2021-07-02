package com.example.medideck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class patient_home_page extends AppCompatActivity {
    private ImageButton profile;
    private ImageButton covid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_home_page);
        profile = (ImageButton) findViewById(R.id.imageButton1);
        covid = (ImageButton) findViewById(R.id.imageButton4);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUser();
            }
        });
                covid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openCorona();
                    }
                }
        );
    }

    private void openUser() {
        Intent intent=new Intent(this, user_profile.class);
        startActivity(intent);
    }

    private void openCorona() {
        Intent intent3=new Intent(this, corona.class);
        startActivity(intent3);
    }

}