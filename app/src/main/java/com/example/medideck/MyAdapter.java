package com.example.medideck;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {

    Context context;

    ArrayList<User> list;



    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.D_Hospital.setText(user.getD_Hospital());
        holder.D_charges.setText(user.getD_charges());
        holder.D_days.setText(user.getD_days());
        holder.D_edulevel.setText(user.getD_edulevel());
        holder.D_speciality.setText(user.getD_speciality());
        holder.D_time.setText(user.getD_time());
        holder.D_name.setText(user.getD_name());

        holder.D_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dna = new Intent(context, doctor_booking.class);
                dna.putExtra("doctor_name", user.getD_name());
                dna.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(dna);

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView D_Hospital, D_charges, D_days, D_edulevel, D_speciality, D_time,D_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            D_days=itemView.findViewById(R.id.mtf);
            D_name=itemView.findViewById(R.id.docName);
            D_edulevel=itemView.findViewById(R.id.exp);
            D_charges=itemView.findViewById(R.id.fee);
            D_time=itemView.findViewById(R.id.hours);
            D_speciality=itemView.findViewById(R.id.spec);
            D_Hospital=itemView.findViewById(R.id.hosp);

        }


    }



}