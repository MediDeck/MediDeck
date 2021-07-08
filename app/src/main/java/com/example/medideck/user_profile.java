package com.example.medideck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_profile extends AppCompatActivity {
    EditText name_u,number_u,age_u,city_u,bloodgoup_in,weight_in,height_in;
    Button saveinfo;
    public String emailCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        //Hooks
        name_u = findViewById(R.id.name_up_edittext);
        number_u = findViewById(R.id.number_up_edittext);
        age_u = findViewById(R.id.age_up_edittext);
        city_u = findViewById(R.id.address_up_edittext);
        saveinfo = findViewById(R.id.save_button);
        bloodgoup_in = findViewById(R.id.bloodgroup_up_edittext);
        weight_in = findViewById(R.id.weight_up_edittext);
        height_in = findViewById(R.id.height_up_edittext);
        //Show all data
        shouwalluserData();

        saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("P_d");
                String height = height_in.getText().toString();
                String bloodgroup = bloodgoup_in.getText().toString();
                String weight = weight_in.getText().toString();

                if (height.isEmpty()) {
                    height_in.setError("Please enter height");
                    height_in.requestFocus();
                } else if (bloodgroup.isEmpty()) {
                    bloodgoup_in.setError("Please enter your Blood Group");
                    bloodgoup_in.requestFocus();
                } else if (weight.isEmpty()) {
                    weight_in.setError("Please enter your Weight");
                    weight_in.requestFocus();
                } else {
                    reference.child(emailCheck).child("height").setValue(height);
                    reference.child(emailCheck).child("blood group").setValue(bloodgroup);
                    reference.child(emailCheck).child("weight").setValue(weight);
                    reference.child(emailCheck).child("profileSave").setValue("true");
                }

            }
        });

    }

    private void shouwalluserData() {
        Intent intent40 = getIntent();
        String user_name = intent40.getStringExtra("name");
        String user_number = intent40.getStringExtra("number");
        String user_age = intent40.getStringExtra("age");
        String user_city = intent40.getStringExtra("city");
        String profileSave = intent40.getStringExtra("profSave");
        emailCheck = intent40.getStringExtra("key");

        if (profileSave.equals("false"));
        {
            name_u.setText(user_name);
            number_u.setText(user_number);
            age_u.setText(user_age);
            city_u.setText(user_city);
        }
        if (profileSave.equals("true")){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("P_d");
            name_u.setText(user_name);
            number_u.setText(user_number);
            age_u.setText(user_age);
            city_u.setText(user_city);
            Query checkUser = reference.orderByChild("emailkey").equalTo(emailCheck);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        String heightfromDB = dataSnapshot.child(emailCheck).child("height").getValue(String.class);
                        String weightfromDB = dataSnapshot.child(emailCheck).child("weight").getValue(String.class);
                        String bloodgroupfromDB = dataSnapshot.child(emailCheck).child("blood group").getValue(String.class);

                        bloodgoup_in.setText(bloodgroupfromDB);
                        weight_in.setText(weightfromDB);
                        height_in.setText(heightfromDB);

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        }


    }
}