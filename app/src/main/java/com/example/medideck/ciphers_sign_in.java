package com.example.medideck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ciphers_sign_in extends AppCompatActivity {

    private Button b;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cipher_sign_in);
        b = (Button) findViewById(R.id.signup_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDocorUser();
            }
        });
    }

    private void openDocorUser() {
        Intent intent3=new Intent(this, doc_or_user.class);
        startActivity(intent3);
    }
}