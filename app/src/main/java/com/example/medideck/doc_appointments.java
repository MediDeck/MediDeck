package com.example.medideck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.WorkSource;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class doc_appointments extends AppCompatActivity {

    RecyclerView recview2;
    DatabaseReference db2;
    doctor_adapter ap2;
    ArrayList<User2> list4;
    public String nameofdoc = "";
    public String emailCheck= "";

    doc_profile ob;
    public String work1,work2,work3,work4,work5,dateString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_appointments);

        recview2 = (RecyclerView) findViewById(R.id.rec_view_doc);

        //getting doctor key
        Intent appo = getIntent();
        emailCheck = appo.getStringExtra("key");
        nameofdoc = appo.getStringExtra("namedoc");

        System.out.println(emailCheck);
        System.out.println(nameofdoc);


        Calendar cd=Calendar.getInstance();
        int day1 = cd.get(Calendar.DAY_OF_MONTH);
        int month1 = cd.get(Calendar.MONTH);
        int year1 = cd.get(Calendar.YEAR);

        dateString = makeDateString(day1, month1, year1);
        work1 = dateString+"_"+"10-11";
        work2 = dateString+"_"+"11-12";
        work3 = dateString+"_"+"12-1";
        work4 = dateString+"_"+"3-4";
        work5 = dateString+"_"+"4-5";
        System.out.println(work2);


        recview2.setHasFixedSize(true);
        recview2.setLayoutManager(new LinearLayoutManager(this));

        list4 = new ArrayList<User2>();


        ap2 = new doctor_adapter(this, list4 );
        recview2.setAdapter(ap2);

        db2 = FirebaseDatabase.getInstance().getReference("Appointment").child(nameofdoc);

        Query query1 = FirebaseDatabase.getInstance().getReference("Appointment").child(nameofdoc).orderByKey().equalTo(work1);
        query1.addListenerForSingleValueEvent(valueEventListener1);

        Query query2 = FirebaseDatabase.getInstance().getReference("Appointment").child(nameofdoc).orderByKey().equalTo(work2);
        query2.addListenerForSingleValueEvent(valueEventListener2);

        Query query3 = FirebaseDatabase.getInstance().getReference("Appointment").child(nameofdoc).orderByKey().equalTo(work3);
        query3.addListenerForSingleValueEvent(valueEventListener3);

        Query query4 = FirebaseDatabase.getInstance().getReference("Appointment").child(nameofdoc).orderByKey().equalTo(work4);
        query4.addListenerForSingleValueEvent(valueEventListener4);

        Query query5 = FirebaseDatabase.getInstance().getReference("Appointment").child(nameofdoc).orderByKey().equalTo(work5);
        query5.addListenerForSingleValueEvent(valueEventListener5);









    }

    ValueEventListener valueEventListener1=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

            list4.clear();
            if(snapshot.exists())
            {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    User2 ob5 = dataSnapshot.getValue(User2.class);
                    list4.add(ob5);


                }
                ap2.notifyDataSetChanged();

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    ValueEventListener valueEventListener2=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {


            if(snapshot.exists())
            {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    User2 ob6 = dataSnapshot.getValue(User2.class);
                    list4.add(ob6);


                }
                ap2.notifyDataSetChanged();

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    ValueEventListener valueEventListener3=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {


            if(snapshot.exists())
            {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    User2 ob7 = dataSnapshot.getValue(User2.class);
                    list4.add(ob7);


                }
                ap2.notifyDataSetChanged();

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    ValueEventListener valueEventListener4=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {


            if(snapshot.exists())
            {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    User2 ob8 = dataSnapshot.getValue(User2.class);
                    list4.add(ob8);


                }
                ap2.notifyDataSetChanged();

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    ValueEventListener valueEventListener5=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {


            if(snapshot.exists())
            {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    User2 ob9 = dataSnapshot.getValue(User2.class);
                    list4.add(ob9);


                }
                ap2.notifyDataSetChanged();

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    private String makeDateString(int day1, int month1, int year1)
    {
        return getMonthFormat(month1) + " " + day1 + " " + year1;
    }

    private String getMonthFormat(int month1)
    {
        month1++;
        if(month1 == 1)
            return "JAN";
        if(month1 == 2)
            return "FEB";
        if(month1 == 3)
            return "MAR";
        if(month1 == 4)
            return "APR";
        if(month1 == 5)
            return "MAY";
        if(month1 == 6)
            return "JUN";
        if(month1 == 7)
            return "JUL";
        if(month1 == 8)
            return "AUG";
        if(month1 == 9)
            return "SEP";
        if(month1 == 10)
            return "OCT";
        if(month1 == 11)
            return "NOV";
        if(month1 == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }
}