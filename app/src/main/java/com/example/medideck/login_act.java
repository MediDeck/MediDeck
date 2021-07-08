package com.example.medideck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login_act extends AppCompatActivity {
    private Button loginbutton;
    FirebaseAuth mAuth;
    private TextView su;
    EditText  emailEditText, passwordEditText;
    public String emailCheck;
    public String typefromDb;
    public String Userpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginbutton = (Button) findViewById(R.id.login_button);
        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("P_d");
                String pass = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                emailCheck = email.replace(".",",");
                emailCheck = emailCheck.replace("@",",");
                Query checkUser = reference.orderByChild("emailkey").equalTo(emailCheck);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            typefromDb = dataSnapshot.child(emailCheck).child("type1").getValue(String.class);
                            typefromDb = typefromDb.trim();
                            Userpass = dataSnapshot.child(emailCheck).child("pass1").getValue(String.class);
                        }else{
                            Toast.makeText(login_act.this, "User does not exist!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });



                if (email.isEmpty()) {
                    emailEditText.setError("Please enter email id");
                    emailEditText.requestFocus();
                }else if (pass.isEmpty()) {
                    passwordEditText.setError("Please enter your password");
                    passwordEditText.requestFocus();
                }else if (!pass.equals(Userpass)) {
                    passwordEditText.setError("Please enter correct password");
                    passwordEditText.requestFocus();
                }else{
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                if(typefromDb.equals("Patient")) {
                                    openHome();
                                }else if (typefromDb.equals("Doctor")){
                                    openDoc();
                                }


                                Toast.makeText(login_act.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(login_act.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

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
        Intent intent55=new Intent(this,patient_home_page.class );
        intent55.putExtra("emailidkey",emailCheck);
        startActivity(intent55);
    }

    public void openDoc(){
        Intent intent56=new Intent(this,doctor_home.class );
        intent56.putExtra("emailidkey",emailCheck);
        startActivity(intent56);
    }


}
