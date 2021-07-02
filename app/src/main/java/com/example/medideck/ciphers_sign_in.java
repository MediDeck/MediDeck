package com.example.medideck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ciphers_sign_in extends AppCompatActivity {

    private Button nextpage;
    EditText Name,number,age,email,pass;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cipher_sign_in);
        Name =findViewById(R.id.name_si_edittext);
        number = findViewById(R.id.number_si_edittext);
        age = findViewById(R.id.age_si_edittext);
        email = findViewById(R.id.email_si_edittext);
        pass = findViewById(R.id.password_si_edittext);
        nextpage = (Button) findViewById(R.id.signup_button);

        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("P_d");
                String Name1 = Name.getText().toString();
                String number1 = number.getText().toString();
                String age1 = age.getText().toString();
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();
                UserHelperClass uhc = new UserHelperClass(Name1,age1,number1,pass1,email1);
                reference.setValue(uhc);
                openDocorUser();
            }
        });
    }

    private void openDocorUser() {
        Intent intent3=new Intent(this, doc_or_user.class);
        startActivity(intent3);
    }
}