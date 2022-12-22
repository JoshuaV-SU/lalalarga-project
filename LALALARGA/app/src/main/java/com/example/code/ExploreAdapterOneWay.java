package com.example.code;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ExploreAdapterOneWay extends RecyclerView.Adapter{

    List<TripModel> fetchTrip;
    RecyclerViewClickListener listener;
    private static String companyID;

    public static void setCompanyID(String companyID) {
        ExploreAdapterOneWay.companyID = companyID;
    }

    public static String getCompanyID() {
        return companyID;
    }

    public interface OnItemClickListener{
        void onItemClick (int position);
    }


    public ExploreAdapterOneWay(List<TripModel> fetchTrip, RecyclerViewClickListener listener) {
        this.fetchTrip = fetchTrip;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avail_one_way_item,parent,false);
        myViewHolder viewHolderClass = new myViewHolder(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        myViewHolder viewHolderClass = (myViewHolder)holder;
        TripModel fetchTripData = fetchTrip.get(position);
        viewHolderClass.DeptDate.setText(fetchTripData.getDeptDate());
        viewHolderClass.DeptTime.setText(fetchTripData.getDeptTime());
        viewHolderClass.ArvDate.setText(fetchTripData.getArvDate());
        viewHolderClass.ArvTime.setText(fetchTripData.getArvTime());
        viewHolderClass.TktPrice.setText(fetchTripData.getTktPrice());
        viewHolderClass.TripOrigin.setText(fetchTripData.getTripOrigin());
        viewHolderClass.TripDest.setText(fetchTripData.getTripDest());
        String vehID = fetchTripData.getVehID();
        String type = fetchTripData.getVehType();
        Log.i("type","type = " + type);
        //viewHolderClass.CompName.setText("Yo");

        if (type.equals("Plane")){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("AIR");

            ValueEventListener idListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        if (snapshot.child("planeID").getValue(String.class).equals(vehID));{
                            Log.i("skskks", "sksk here ");
                            ExploreAdapterOneWay.setCompanyID(snapshot.child("compID").getValue(String.class));
                            viewHolderClass.CompID = snapshot.child("compID").getValue(String.class);

                            String compID = snapshot.child("compID").getValue(String.class);
                            DatabaseReference compRef = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRANSPORT_COMPANY");

                            ValueEventListener compListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()){
                                        if (snapshot.child("compID").getValue(String.class).equals(compID)){
                                            Log.i("skskks", "sksk here nest");
                                            viewHolderClass.CompName.setText(snapshot.child("compName").getValue(String.class));
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}
                            };
                            compRef.child(compID).addListenerForSingleValueEvent(compListener);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) { }
            };
            databaseReference.child(vehID).addListenerForSingleValueEvent(idListener);
        }
        else{

            DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("SEA");

            ValueEventListener idListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        if (snapshot.child("ferryID").getValue(String.class).equals(vehID));{
                            Log.i("skskks", "sksk here ");
                            ExploreAdapterOneWay.setCompanyID(snapshot.child("compID").getValue(String.class));
                            viewHolderClass.CompID = snapshot.child("compID").getValue(String.class);

                            String compID = snapshot.child("compID").getValue(String.class);
                            DatabaseReference compRef = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRANSPORT_COMPANY");

                            ValueEventListener compListener = new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()){
                                        if (snapshot.child("compID").getValue(String.class).equals(compID)){
                                            Log.i("skskks", "sksk here nest");
                                            viewHolderClass.CompName.setText(snapshot.child("compName").getValue(String.class));
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}
                            };
                            compRef.child(compID).addListenerForSingleValueEvent(compListener);

                            Log.i("skskks", "sksk here! "+viewHolderClass.CompID.toString());

                            Log.i("skskks", "sksk here outjk "+viewHolderClass.CompName.getText().toString());
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            databaseReference.child(vehID).addListenerForSingleValueEvent(idListener);

        }

        Log.i("skskks", "sksk here out "+viewHolderClass.CompName.getText().toString());
    }

    @Override
    public int getItemCount() {
        return fetchTrip.size();
    }

    public interface RecyclerViewClickListener{
        void onClick (View v, int position);
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView DeptDate, DeptTime, ArvDate,
                ArvTime, TktPrice, TripDest, TripOrigin,
                CompName;

         String CompID;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            DeptDate = itemView.findViewById(R.id.deptDateTxtView);
            DeptTime = itemView.findViewById(R.id.deptTimeTxtView);
            ArvDate = itemView.findViewById(R.id.arvDateTxtView);
            ArvTime = itemView.findViewById(R.id.arvTimeTxtView);
            TktPrice = itemView.findViewById(R.id.costTxtView);
            TripDest = itemView.findViewById(R.id.tripDestTxtView);
            TripOrigin = itemView.findViewById(R.id.tripOrigTxtView);
            CompName = itemView.findViewById(R.id.compNameTxtView);

            itemView.setOnClickListener(this);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAbsoluteAdapterPosition());
        }


    }

}