package com.example.medideck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class doctor_adapter extends RecyclerView.Adapter<doctor_adapter.docViewHolder>{

    Context cdoc;

    ArrayList<User2> list4;

    public doctor_adapter(Context cdoc, ArrayList<User2> list4) {
        this.cdoc = cdoc;
        this.list4 = list4;
    }

    @NonNull

    @Override
    public docViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v2 = LayoutInflater.from(cdoc).inflate(R.layout.doctor_item, parent, false);
        return new docViewHolder(v2);

    }

    @Override
    public void onBindViewHolder(@NonNull doctor_adapter.docViewHolder holder, int position) {

        User2 user2 = list4.get(position);

        holder.name1.setText(user2.getName1());
        holder.name2.setText(user2.getName2());
        holder.name3.setText(user2.getName3());
        holder.name4.setText(user2.getName4());
        holder.name5.setText(user2.getName5());
        holder.timekey.setText(user2.getTimekey());



    }

    @Override
    public int getItemCount() {
        return list4.size();
    }

    public static class docViewHolder extends RecyclerView.ViewHolder {

        TextView name1,name2, name3, name4, name5,timekey;


        public docViewHolder(@NonNull  View itemView) {
            super(itemView);

            name1 = itemView.findViewById(R.id.p_1);
            name2 = itemView.findViewById(R.id.p_2);
            name3 = itemView.findViewById(R.id.p_3);
            name4 = itemView.findViewById(R.id.p_4);
            name5 = itemView.findViewById(R.id.p_5);
            timekey = itemView.findViewById(R.id.time_ap);


        }
    }
}