package com.example.code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FerryAdapter extends RecyclerView.Adapter<FerryAdapter.MyViewHolder> {
    Context context;
    ArrayList<com.example.code.Ferry> ferry;

    public FerryAdapter(Context context, ArrayList<com.example.code.Ferry> ferry) {
        this.context = context;
        this.ferry = ferry;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ferry_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        /*String id = ferry.get(position).getFerryId();
        String num = ferry.get(position).getFerryNum();
        int seats = ferry.get(position).getFerrySeatsCount();
        String fSeats = String.valueOf(seats);

        holder.ferryId.setText(id);
        holder.ferryNum.setText(num);
        holder.ferrySeatsCount.setText(fSeats);*/

        com.example.code.Ferry data = ferry.get(position);
        holder.ferryId.setText(data.getFerryId());
        holder.ferryNum.setText(data.getFerryNum());
       // holder.ferrySeatsCount.setText(data.getFerrySeatsCount());

    }

    @Override
    public int getItemCount() {
        return ferry.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ferryId, ferryNum, ferrySeatsCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ferryId = itemView.findViewById(R.id.ferryid);
            ferryNum = itemView.findViewById(R.id.ferrynum);
            ferrySeatsCount = itemView.findViewById(R.id.ferryseats);
        }
    }
}
