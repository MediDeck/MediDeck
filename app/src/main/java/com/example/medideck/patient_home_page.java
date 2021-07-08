package com.example.medideck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class patient_home_page extends AppCompatActivity {
    private ImageButton profile;
    private ImageButton covid;
    private ImageButton docbutton;
    private ImageButton labbutton;
    public String emailCheck="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_home_page);
        profile = (ImageButton) findViewById(R.id.imageButton1);
        covid = (ImageButton) findViewById(R.id.imageButton4);
        docbutton = (ImageButton) findViewById(R.id.imageButton2);
        labbutton = (ImageButton) findViewById(R.id.imageButton3);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("P_d");

                Intent intent55 = getIntent();
                emailCheck = intent55.getStringExtra("emailidkey");
                if(emailCheck.isEmpty()){
                    Intent intent3 = getIntent();
                    emailCheck = intent3.getStringExtra("emailidkey");
                }

                Query checkUser = reference.orderByChild("emailkey").equalTo(emailCheck);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {


                            String namefromDB = dataSnapshot.child(emailCheck).child("name1").getValue(String.class);
                            String numberfromDB = dataSnapshot.child(emailCheck).child("number1").getValue(String.class);
                            String agefromDB = dataSnapshot.child(emailCheck).child("age1").getValue(String.class);
                            String cityfromDB = dataSnapshot.child(emailCheck).child("city1").getValue(String.class);
                            String profileSaveDB = dataSnapshot.child(emailCheck).child("profileSave").getValue(String.class);

                            Intent intent40 = new Intent(getApplicationContext(), user_profile.class);

                            intent40.putExtra("name", namefromDB);
                            intent40.putExtra("number", numberfromDB);
                            intent40.putExtra("age", agefromDB);
                            intent40.putExtra("city", cityfromDB);
                            intent40.putExtra("profSave", profileSaveDB);
                            intent40.putExtra("key", emailCheck);

                            startActivity(intent40);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
                covid.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {openCorona();

                                             }
                                         }
                );
            }

        });
        covid.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {openCorona();

                                     }
                                 }
        );
        docbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoc();
            }
        });
        labbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLab();
            }
        });

    }

    private void openLab() {
        Intent l = new Intent(this, LabTest.class);
        startActivity(l);
    }

    private void openDoc() {
        Intent d=new Intent(this, choose_city.class);
        startActivity(d);
    }

    private void openCorona() {
        Intent c=new Intent(this, corona.class);
        startActivity(c);
    }
}