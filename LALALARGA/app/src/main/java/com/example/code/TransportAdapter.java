package com.example.code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.MyViewHolder> {

    Context context;
    ArrayList<Transport> tlist;
    RecyclerViewClickListener listener;

    public TransportAdapter( ArrayList<Transport> tlist, RecyclerViewClickListener listener) {
        this.tlist = tlist;
        this.listener = listener;
    }

    public TransportAdapter( ArrayList<Transport> tlist) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView transName, transID;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            transName = itemView.findViewById(R.id.txt_transname);
            transID = itemView.findViewById(R.id.txt_transid);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trans_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String name = tlist.get(position).getCompName();
        String id = tlist.get(position).getCompID();

        holder.transID.setText(id);
        holder.transName.setText(name);
    }

    @Override
    public int getItemCount() {
        return tlist.size();
    }


    public interface RecyclerViewClickListener{
        void onClick(View v, int position);

    }
}
