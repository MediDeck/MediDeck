package com.example.medideck;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class corona extends AppCompatActivity {
    ImageView bt1_Link;
    Button bt2_Link;
    Button bt3_Link;
    Button bt4_Link;
     @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.corona);

            bt1_Link = (ImageView) findViewById(R.id.corona_cases);
            bt1_Link.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String url1_tp="https://www.worldometers.info/coronavirus/country/india/";
                 Intent i1 = new Intent(Intent.ACTION_VIEW);
                 i1.setData(Uri.parse(url1_tp));
                 startActivity(i1);
             }
         });

         bt2_Link =(Button) findViewById(R.id.cowin_button);
         bt2_Link.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String url2_tp="https://www.cowin.gov.in//";
                 Intent i2 = new Intent(Intent.ACTION_VIEW);
                 i2.setData(Uri.parse(url2_tp));
                 startActivity(i2);
             }
         });


         bt3_Link =(Button) findViewById(R.id.corona_lab);
         bt3_Link.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String url3_tp="https://covid.icmr.org.in/";
                 Intent i3 = new Intent(Intent.ACTION_VIEW);
                 i3.setData(Uri.parse(url3_tp));
                 startActivity(i3);
             }
         });

         bt4_Link =(Button) findViewById(R.id.corona_hemkunt_f);
         bt4_Link.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String url4_tp="https://hemkuntfoundation.com/causes/oxygen-cylinder/";
                 Intent i4 = new Intent(Intent.ACTION_VIEW);
                 i4.setData(Uri.parse(url4_tp));
                 startActivity(i4);
             }
         });

        }

    }
