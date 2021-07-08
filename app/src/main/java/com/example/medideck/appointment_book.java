package com.example.medideck;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class appointment_book extends AppCompatActivity {

    RecyclerView recview;
    DatabaseReference db;
    MyAdapter ap;
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_book);

        recview = (RecyclerView) findViewById(R.id.recy_view);
        db = FirebaseDatabase.getInstance().getReference("D_Bareilly");
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<User>();
        ap = new MyAdapter(this,list);
        recview.setAdapter(ap);



        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    User md = dataSnapshot.getValue(User.class);

                    list.add(md);


                }
                ap.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


    }
}