package com.example.medideck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class doctor_home extends AppCompatActivity {
    private ImageButton Docprofile,appointments;
    public String emailCheck="";
    String namefromDB,cityfromDB,profileSaveDB;
    public String finalemailcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        Docprofile = (ImageButton) findViewById(R.id.Doctor_H_Profile);
        appointments = (ImageButton) findViewById(R.id.Doctor_H_Doc);

        //data
        DatabaseReference referencedoc = FirebaseDatabase.getInstance().getReference("P_d");
        Intent intent56 = getIntent();
        emailCheck = intent56.getStringExtra("emailidkey");
        if(emailCheck.isEmpty()){
            Intent intent11 = getIntent();
            emailCheck = intent11.getStringExtra("emailidkey");
        }
        Query checkUser = referencedoc.orderByChild("emailkey").equalTo(emailCheck);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {


                    namefromDB = dataSnapshot.child(emailCheck).child("name1").getValue(String.class);
                    cityfromDB = dataSnapshot.child(emailCheck).child("city1").getValue(String.class);
                    profileSaveDB = dataSnapshot.child(emailCheck).child("profileSave").getValue(String.class);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        Docprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent41 = new Intent(getApplicationContext(),doc_profile.class);

                intent41.putExtra("name", namefromDB);
                intent41.putExtra("city", cityfromDB);
                intent41.putExtra("profSave", profileSaveDB);
                intent41.putExtra("key", emailCheck);

                startActivity(intent41);


            }
        });
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(profileSaveDB.equals("false")){
                    Toast.makeText(doctor_home.this, "Please complete your Profile", Toast.LENGTH_SHORT).show();

                }else{
                Intent appo = new Intent(getApplicationContext(), doc_appointments.class);
                appo.putExtra("key", emailCheck);
                appo.putExtra("namedoc",namefromDB);
                startActivity(appo);}
            }
        });

    }
}