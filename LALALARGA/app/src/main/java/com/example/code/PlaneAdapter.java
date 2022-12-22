package com.example.code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlaneAdapter extends RecyclerView.Adapter<PlaneAdapter.MyViewHolder> {
    Context context;
    ArrayList<Plane> plist;

    public PlaneAdapter(Context context, ArrayList<Plane> plist) {
        this.context = context;
        this.plist = plist;
    }

    public PlaneAdapter(ArrayList<Plane> plist) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plane_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String pId = plist.get(position).getPlaneId();
        String pNum = plist .get(position).getPlaneNum();
       // int seats = plist.get(position).getPlaneSeatsCount();
        //String pSeats = String.valueOf(seats);

        holder.planeId.setText(pId);
        holder.planeNum.setText(pNum);
       // holder.planeSeatsCount.setText(pSeats);

    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView planeId, planeNum, planeSeatsCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            planeId = itemView.findViewById(R.id.planeid);
            planeNum = itemView.findViewById(R.id.planenum);
            //planeSeatsCount = itemView.findViewById(R.id.planeseats);
        }
    }
}
