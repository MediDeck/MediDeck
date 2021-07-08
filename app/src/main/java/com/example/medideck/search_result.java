package com.example.medideck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class search_result extends AppCompatActivity {

    public boolean flag = true;
    public int counter = 0;
    public String specialization;
    public String city;
    RecyclerView recview;
    DatabaseReference db;
    MyAdapter ap;
    ArrayList<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_vapi);
        Intent sc = getIntent();
        specialization = sc.getStringExtra("spec");
        city = sc.getStringExtra("city");
        recview = (RecyclerView) findViewById(R.id.rec_view);

        if(city.toLowerCase().equals("vapi")) {
            db = FirebaseDatabase.getInstance().getReference("D_Vapi");

        }
        else if(city.toLowerCase().equals("bareilly")) {
            db = FirebaseDatabase.getInstance().getReference("D_Bareilly");
            System.out.println(db);
        }

        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<User>();
        ap = new MyAdapter(this,list);
        recview.setAdapter(ap);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    User md=dataSnapshot.getValue(User.class);
                    String DSec = md.getD_speciality();
                    if(DSec.toLowerCase().equals(specialization.toLowerCase())){
                        list.add(md);
                        counter++;
                    }



                }
                if(counter==0){
                    Toast.makeText(search_result.this, "No result", Toast.LENGTH_SHORT).show();
                    flag = false ;
                    finish();
                }
                ap.notifyDataSetChanged();



            }


            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


    }
}