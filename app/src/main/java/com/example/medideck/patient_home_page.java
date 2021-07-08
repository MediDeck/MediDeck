package com.example.medideck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class patient_home_page extends AppCompatActivity {
    public String namefromDB;
    public String numberfromDB;
    public String agefromDB;
    public String cityfromDB;
    public String profileSaveDB;
    private ImageButton profile;
    private ImageButton covid;
    private ImageButton docbutton;
    private ImageButton labbutton;
    private SearchView search;
    public String emailCheck="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_home_page);
        search = (SearchView) findViewById(R.id.searchView);
        profile = (ImageButton) findViewById(R.id.imageButton1);
        covid = (ImageButton) findViewById(R.id.imageButton4);
        docbutton = (ImageButton) findViewById(R.id.imageButton2);
        labbutton = (ImageButton) findViewById(R.id.imageButton3);

        //gathering info
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

                     namefromDB = dataSnapshot.child(emailCheck).child("name1").getValue(String.class);
                     numberfromDB = dataSnapshot.child(emailCheck).child("number1").getValue(String.class);
                     agefromDB = dataSnapshot.child(emailCheck).child("age1").getValue(String.class);
                     cityfromDB = dataSnapshot.child(emailCheck).child("city1").getValue(String.class);
                     profileSaveDB = dataSnapshot.child(emailCheck).child("profileSave").getValue(String.class);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        //profile button
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent40 = new Intent(getApplicationContext(), user_profile.class);

                intent40.putExtra("name", namefromDB);
                intent40.putExtra("number", numberfromDB);
                intent40.putExtra("age", agefromDB);
                intent40.putExtra("city", cityfromDB);
                intent40.putExtra("profSave", profileSaveDB);
                intent40.putExtra("key", emailCheck);

                startActivity(intent40);

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

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent sc = new Intent(getApplicationContext(), search_result.class);
                sc.putExtra("spec",query);
                sc.putExtra("city",cityfromDB);
                startActivity(sc);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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