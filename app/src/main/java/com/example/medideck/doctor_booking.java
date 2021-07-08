package com.example.medideck;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static java.lang.System.exit;

public class doctor_booking extends AppCompatActivity
{
    public int countChild;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button time11_12,time10_11,time3_4,time12_1,time4_5;
    public String emailCheck;
    public String namefromDB;
    String date;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_booking);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        tv = (TextView) findViewById(R.id.doctor_Name);
        //user profile key
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        emailCheck = user.getEmail().toString();
        emailCheck = emailCheck.replace(".",",");
        emailCheck = emailCheck.replace("@",",");
        DatabaseReference referenceUser = FirebaseDatabase.getInstance().getReference("P_d");
        Query checkUser = referenceUser.orderByChild("emailkey").equalTo(emailCheck);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    namefromDB = dataSnapshot.child(emailCheck).child("name1").getValue(String.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //Adding hooks to buttons
        time10_11 = findViewById(R.id.button10_11);
        time11_12 = findViewById(R.id.button11_12);
        time12_1 = findViewById(R.id.button12_1);
        time3_4 = findViewById(R.id.button3_4);
        time4_5 = findViewById(R.id.button4_5);
        //adding doc name to textview
        tv.setText(getIntent().getStringExtra("doctor_name"));
        //making variable for doc name
        String doc_name = tv.getText().toString();
        //button for time 10-11
        time10_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Appointment");
                DatabaseReference referenceChild = FirebaseDatabase.getInstance().getReference().child("Appointment").child(doc_name);

                String makeChild = date+"_"+"10-11" ;

                referenceChild.child(makeChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()) {
                            countChild = (int) dataSnapshot.getChildrenCount();
                            if(countChild==6){
                                Toast.makeText(doctor_booking.this, "No more slots left", Toast.LENGTH_SHORT).show();
                            }else if(countChild==5){
                                reference.child(doc_name).child(makeChild).child("name5").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==4){
                                reference.child(doc_name).child(makeChild).child("name4").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==3){
                                reference.child(doc_name).child(makeChild).child("name3").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();

                            }else if(countChild==2){
                                reference.child(doc_name).child(makeChild).child("name2").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            reference.child(doc_name).child(makeChild).setValue(6);
                            reference.child(doc_name).child(makeChild).child("timekey").setValue("10am-11am");
                            reference.child(doc_name).child(makeChild).child("name1").setValue(namefromDB);
                            Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });

            }
        });

        //button for time 11-12
        time11_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Appointment");
                DatabaseReference referenceChild = FirebaseDatabase.getInstance().getReference().child("Appointment").child(doc_name);

                String makeChild = date+"_"+"11-12" ;



                referenceChild.child(makeChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()) {
                            countChild = (int) dataSnapshot.getChildrenCount();
                            if(countChild==6){
                                Toast.makeText(doctor_booking.this, "No more slots left", Toast.LENGTH_SHORT).show();
                            }else if(countChild==5){
                                reference.child(doc_name).child(makeChild).child("name5").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==4){
                                reference.child(doc_name).child(makeChild).child("name4").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==3){
                                reference.child(doc_name).child(makeChild).child("name3").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();

                            }else if(countChild==2){
                                reference.child(doc_name).child(makeChild).child("name2").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            reference.child(doc_name).child(makeChild).setValue(6);
                            reference.child(doc_name).child(makeChild).child("timekey").setValue("11am-12pm");
                            reference.child(doc_name).child(makeChild).child("name1").setValue(namefromDB);
                            Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });

            }
        });

        //button for time 12-1
        time12_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Appointment");
                DatabaseReference referenceChild = FirebaseDatabase.getInstance().getReference().child("Appointment").child(doc_name);

                String makeChild = date+"_"+"12-1" ;



                referenceChild.child(makeChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()) {
                            countChild = (int) dataSnapshot.getChildrenCount();
                            if(countChild==6){
                                Toast.makeText(doctor_booking.this, "No more slots left", Toast.LENGTH_SHORT).show();
                            }else if(countChild==5){
                                reference.child(doc_name).child(makeChild).child("name5").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==4){
                                reference.child(doc_name).child(makeChild).child("name4").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==3){
                                reference.child(doc_name).child(makeChild).child("name3").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();

                            }else if(countChild==2){
                                reference.child(doc_name).child(makeChild).child("name2").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            reference.child(doc_name).child(makeChild).setValue(6);
                            reference.child(doc_name).child(makeChild).child("timekey").setValue("12pm-1pm");
                            reference.child(doc_name).child(makeChild).child("name1").setValue(namefromDB);
                            Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });

            }
        });

        //button for time 3-4
        time3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Appointment");
                DatabaseReference referenceChild = FirebaseDatabase.getInstance().getReference().child("Appointment").child(doc_name);

                String makeChild = date+"_"+"3-4" ;



                referenceChild.child(makeChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()) {
                            countChild = (int) dataSnapshot.getChildrenCount();
                            if(countChild==6){
                                Toast.makeText(doctor_booking.this, "No more slots left", Toast.LENGTH_SHORT).show();
                            }else if(countChild==5){
                                reference.child(doc_name).child(makeChild).child("name5").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==4){
                                reference.child(doc_name).child(makeChild).child("name4").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==3){
                                reference.child(doc_name).child(makeChild).child("name3").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();

                            }else if(countChild==2){
                                reference.child(doc_name).child(makeChild).child("name2").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            reference.child(doc_name).child(makeChild).setValue(6);
                            reference.child(doc_name).child(makeChild).child("timekey").setValue("3pm-4pm");
                            reference.child(doc_name).child(makeChild).child("name1").setValue(namefromDB);
                            Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });

            }
        });

        //button for time 4-5
        time4_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Appointment");
                DatabaseReference referenceChild = FirebaseDatabase.getInstance().getReference().child("Appointment").child(doc_name);

                String makeChild = date+"_"+"4-5" ;



                referenceChild.child(makeChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()) {
                            countChild = (int) dataSnapshot.getChildrenCount();
                            if(countChild==6){
                                Toast.makeText(doctor_booking.this, "No more slots left", Toast.LENGTH_SHORT).show();
                            }else if(countChild==5){
                                reference.child(doc_name).child(makeChild).child("name5").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==4){
                                reference.child(doc_name).child(makeChild).child("name4").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }else if(countChild==3){
                                reference.child(doc_name).child(makeChild).child("name3").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();

                            }else if(countChild==2){
                                reference.child(doc_name).child(makeChild).child("name2").setValue(namefromDB);
                                Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            reference.child(doc_name).child(makeChild).setValue(6);
                            reference.child(doc_name).child(makeChild).child("timekey").setValue("4pm-5pm");
                            reference.child(doc_name).child(makeChild).child("name1").setValue(namefromDB);
                            Toast.makeText(doctor_booking.this, "Booked Successfully!!", Toast.LENGTH_SHORT).show();


                        }


                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });

            }
        });
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int  day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}