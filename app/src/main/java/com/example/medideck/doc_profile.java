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

public class doc_profile extends AppCompatActivity {
    EditText name_d,city_d,hospital_d,charges_d,daysav_d,edu_d,speci_d,timing_d;
    Button save_doc;
    public String emailCheck;
    public String pathname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);

        //Hooks
        name_d = findViewById(R.id.name_dp_edittext);
        city_d = findViewById(R.id.address_dp_edittext);
        hospital_d = findViewById(R.id.hospital_dp_edittext);
        charges_d = findViewById(R.id.charges_dp_edittext);
        daysav_d = findViewById(R.id.daysav_dp_edittext);
        edu_d = findViewById(R.id.edulevel_dp_edittext);
        speci_d = findViewById(R.id.speciality_dp_edittext);
        timing_d = findViewById(R.id.time_dp_edittext);

        //Save Button
        save_doc = findViewById(R.id.save_du_button);

        //Getting values from Intent
        Intent intent41 = getIntent();
        String doc_city = intent41.getStringExtra("city");
        String doc_name = intent41.getStringExtra("name");
        emailCheck = intent41.getStringExtra("key");
        //Data to display
        shouwalldoctorData();

        save_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //determining the city for our doctor
                if (doc_city.equals("vapi") ||  doc_city.equals("Vapi")){
                    pathname = "D_Vapi";
                }else if(doc_city.equals("bareilly") ||  doc_city.equals("Bareilly")){
                    pathname = "D_Bareilly";
                }

                //Update info in doctor database
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(pathname);

                //Update info in main database
                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("P_d");
                String hospital = hospital_d.getText().toString();
                String charges = charges_d.getText().toString();
                String specialization = speci_d.getText().toString();
                String timing = timing_d.getText().toString();
                String education = edu_d.getText().toString();
                String days_av = daysav_d.getText().toString();
                if (hospital.isEmpty()) {
                    hospital_d.setError("This field can't be empty");
                    hospital_d.requestFocus();
                } else if (charges.isEmpty()) {
                    charges_d.setError("This field can't be empty");
                    charges_d.requestFocus();
                } else if (specialization.isEmpty()) {
                    speci_d.setError("This field can't be empty");
                    speci_d.requestFocus();
                } else if (timing.isEmpty()) {
                    timing_d.setError("This field can't be empty");
                    timing_d.requestFocus();
                } else if (education.isEmpty()) {
                    edu_d.setError("This field can't be empty");
                    edu_d.requestFocus();
                } else if (days_av.isEmpty()) {
                    daysav_d.setError("This field can't be empty");
                    daysav_d.requestFocus();

                } else {
                    //adding data to doctor database based on their city
                    reference.child(doc_name).child("D_name").setValue(doc_name);
                    reference.child(doc_name).child("D_charges").setValue(charges);
                    reference.child(doc_name).child("D_speciality").setValue(specialization);
                    reference.child(doc_name).child("D_Hospital").setValue(hospital);
                    reference.child(doc_name).child("D_edulevel").setValue(education);
                    reference.child(doc_name).child("D_time").setValue(timing);
                    reference.child(doc_name).child("D_days").setValue(days_av);

                    //Updating profileSave in P_d
                    reference2.child(emailCheck).child("profileSave").setValue("true");

                }
            }
        });

    }

    private void shouwalldoctorData() {
        Intent intent41 = getIntent();
        String doc_name = intent41.getStringExtra("name");
        String doc_city = intent41.getStringExtra("city");
        String profileSave = intent41.getStringExtra("profSave");
        emailCheck = intent41.getStringExtra("key");
        //checking the value of profileSave in P_d
        if (profileSave.equals("false"))
        {
            name_d.setText(doc_name);
            city_d.setText(doc_city);
        }else if(profileSave.equals("true")){
            name_d.setText(doc_name);
            city_d.setText(doc_city);

            //Determining the city

            if (doc_city.equals("vapi") ||  doc_city.equals("Vapi")){
                pathname = "D_Vapi";
            }else if(doc_city.equals("bareilly") ||  doc_city.equals("Bareilly")){
                pathname = "D_Bareilly";
            }

            //getting the reference based on the city
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(pathname);
            Query checkUser = reference.orderByChild("D_name").equalTo(doc_name);
            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        String chargesfromDB = dataSnapshot.child(doc_name).child("D_charges").getValue(String.class);
                        String SpecfromDB = dataSnapshot.child(doc_name).child("D_speciality").getValue(String.class);
                        String timingfromDB = dataSnapshot.child(doc_name).child("D_time").getValue(String.class);
                        String daysfromDB = dataSnapshot.child(doc_name).child("D_days").getValue(String.class);
                        String edufromDB = dataSnapshot.child(doc_name).child("D_edulevel").getValue(String.class);
                        String HospitalfromDB = dataSnapshot.child(doc_name).child("D_Hospital").getValue(String.class);

                        hospital_d.setText(HospitalfromDB);
                        speci_d.setText(SpecfromDB);
                        timing_d.setText(timingfromDB);
                        daysav_d.setText(daysfromDB);
                        edu_d.setText(edufromDB);
                        charges_d.setText(chargesfromDB);

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });

        }

    }

}