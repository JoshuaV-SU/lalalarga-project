package com.example.code;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter{

    List<TripModel> fetchTrip;

    public TripAdapter(List<TripModel> fetchTrip) {
        this.fetchTrip = fetchTrip;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        myViewHolder viewHolderClass = new myViewHolder(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        myViewHolder viewHolderClass = (myViewHolder)holder;
        TripModel fetchTripData = fetchTrip.get(position);
        viewHolderClass.TripId.setText(fetchTripData.getTripId());
        viewHolderClass.DeptDate.setText(fetchTripData.getDeptDate());
        viewHolderClass.DeptTime.setText(fetchTripData.getDeptTime());
        viewHolderClass.ArvDate.setText(fetchTripData.getArvDate());
        viewHolderClass.ArvTime.setText(fetchTripData.getArvTime());
        viewHolderClass.VehType.setText(fetchTripData.getVehType());
        viewHolderClass.VehID.setText(fetchTripData.getVehID());
        viewHolderClass.SeatsAvailable.setText(fetchTripData.getSeatsAvailable());
        viewHolderClass.TktPrice.setText(fetchTripData.getTktPrice());
        viewHolderClass.TripOrigin.setText(fetchTripData.getTripOrigin());
        viewHolderClass.TripDest.setText(fetchTripData.getTripDest());

    }

    @Override
    public int getItemCount() {
        return fetchTrip.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView TripId, DeptDate, DeptTime, ArvDate,
                ArvTime, VehType, VehID, SeatsAvailable, TktPrice,
                TripDest, TripOrigin;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            TripId= itemView.findViewById(R.id.TripIdTxtView);
            DeptDate= itemView.findViewById(R.id.DeptDateTxtView);
            DeptTime= itemView.findViewById(R.id.DeptTimeTxtView);
            ArvDate= itemView.findViewById(R.id.ArvDateTxtView);
            ArvTime= itemView.findViewById(R.id.ArvTimeTxtView);
            VehType= itemView.findViewById(R.id.vehTypeTxtView);
            VehID= itemView.findViewById(R.id.VehIDTxtView);
            SeatsAvailable =  itemView.findViewById(R.id.SeatsAvailableTxtView);
            TktPrice =  itemView.findViewById(R.id.TktPriceTxtView);
            TripDest = itemView.findViewById(R.id.tripdestTxtView);
            TripOrigin = itemView.findViewById(R.id.originTxtView);

        }
    }
}