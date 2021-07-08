package com.example.medideck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choose_city extends AppCompatActivity {

    private Button B1;
    private Button B2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        B1= (Button) findViewById(R.id.bareilly);
        B2= (Button) findViewById(R.id.vapi);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBareilly();
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVapi();
            }
        });
    }

    private void openVapi() {
        Intent bare=new Intent(this, appointment_book.class);
        startActivity(bare);
    }

    private void openBareilly() {
        Intent vp=new Intent(this,book_vapi.class);
        startActivity(vp);
    }
}