package com.example.medideck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LabTest extends AppCompatActivity {
    Button jbt1_link;
    Button jbt2_link;
    Button jbt3_link;
    Button jbt4_link;
    Button jbt5_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        jbt1_link =(Button) findViewById(R.id.thp_button);
        jbt1_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1_tp="https://www.lalpathlabs.com/test/details/wsp284?q=thyroid%20profile";
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(url1_tp));
                startActivity(i1);
            }
        });

        jbt2_link =(Button) findViewById(R.id.ltp_button);
        jbt2_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2_tp="https://www.lalpathlabs.com/test/details/b131?q=lipid%20pro";
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(url2_tp));
                startActivity(i2);
            }
        });

        jbt3_link =(Button) findViewById(R.id.kp_button);
        jbt3_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url3_tp="https://www.lalpathlabs.com/test/details/wu256?q=kidney";
                Intent i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse(url3_tp));
                startActivity(i3);
            }
        });

        jbt4_link =(Button) findViewById(R.id.bhs_button);
        jbt4_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url4_tp="https://www.lalpathlabs.com/test/details/wt001?q=basic%20health";
                Intent i4 = new Intent(Intent.ACTION_VIEW);
                i4.setData(Uri.parse(url4_tp));
                startActivity(i4);
            }
        });

        jbt5_link =(Button) findViewById(R.id.mp_button);
        jbt5_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url5_tp="https://www.lalpathlabs.com/test/details/z1030?q=metabolic";
                Intent i5 = new Intent(Intent.ACTION_VIEW);
                i5.setData(Uri.parse(url5_tp));
                startActivity(i5);
            }
        });



    }

}