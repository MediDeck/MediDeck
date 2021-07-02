package com.example.medideck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class login_act extends AppCompatActivity {
    private Button loginbutton;
    private TextView su;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginbutton = (Button) findViewById(R.id.login_button);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHome();
            }

        });
        su = (TextView) findViewById(R.id.newuser_su);
        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    private void openSignUp() {
        Intent i=new Intent(this, ciphers_sign_in.class);
        startActivity(i);
    }

    public void openHome(){
        Intent intent2=new Intent(this, patient_home_page.class);
        startActivity(intent2);
    }
}
