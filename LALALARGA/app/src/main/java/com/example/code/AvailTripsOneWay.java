package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AvailTripsOneWay extends Explore {
    public AvailTripsOneWay() {
        super();
    }
    List<TripModel> fetchTrip;
    RecyclerView recyclerView;
    ExploreAdapterOneWay helperAdapter;
    DatabaseReference databaseReference;
    Button addBtn;
    ExploreAdapterOneWay.RecyclerViewClickListener listener;
    public static String travelOrig, travelDest, travelDate, VehType;

    public static void setVehType(String vehType) {
        VehType = vehType;
    }

    public static String getVehType() {
        return VehType;
    }

    public static String getTravelOrig() {
        return travelOrig;
    }

    public static String getTravelDest() {
        return travelDest;
    }

    public static String getTravelDate() {
        return travelDate;
    }

    public static void setTravelOrig(String travelOrig) {
        AvailTripsOneWay.travelOrig = travelOrig;
    }

    public static void setTravelDest(String travelDest) {
        AvailTripsOneWay.travelDest = travelDest;
    }

    public static void setTravelDate(String travelDate) {
        AvailTripsOneWay.travelDate = travelDate;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avail_one_way);

        recyclerView = (RecyclerView) findViewById(R.id.availOneWayRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView = findViewById(R.id.availOneWayRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchTrip = new ArrayList<>();
        setOnClickListener();
        Log.i("travelDate", travelDate);
        databaseReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRIP");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    TripModel data = ds.getValue(TripModel.class);
                    if (data.getTripOrigin().toString().equals(travelOrig)){
                        if(data.getTripDest().toString().equals(travelDest)){
                            if(data.getDeptDate().toString().equals(travelDate)){
                                fetchTrip.add(data);
                            };
                        }
                    }
                    Log.i("Trip Origin, travelOg:" ,data.getTripOrigin().toString() + travelOrig.toString());

                }
                helperAdapter=new ExploreAdapterOneWay(fetchTrip,listener);
                recyclerView.setAdapter(helperAdapter);

                int num = helperAdapter.getItemCount();
                if (num==0)
                    Toast.makeText(AvailTripsOneWay.this, "No available trips for your desired trip", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});
    }

    private void setOnClickListener() {
        listener = new ExploreAdapterOneWay.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Customer customer = new Customer();
                //customer.setUser(loginUser);

                Log.i("here","listenerrr");
                //userID = loginUser;

                Intent intent = new Intent(AvailTripsOneWay.this,AvailTripDetails.class);
                //Account account = new Account(fbday,fname,femail,user);
                AvailTripDetails details = new AvailTripDetails();
                details.setTripOrig(fetchTrip.get(position).getTripOrigin());
                details.setTripDest(fetchTrip.get(position).getTripDest());
                details.setDeptDate(fetchTrip.get(position).getDeptDate());
                details.setDeptTime(fetchTrip.get(position).getDeptTime());
                details.setArvDate(fetchTrip.get(position).getArvDate());
                details.setArvTime(fetchTrip.get(position).getArvTime());
                details.setCostTotal(fetchTrip.get(position).getTktPrice());
                details.setVehID(fetchTrip.get(position).getVehID());
                details.setTripID(fetchTrip.get(position).getTripId());
                details.setVehType(getVehType());
                String type = getVehType();
                if (TextUtils.isEmpty(type))
                    details.setVehType("Plane");
                Log.i("details","deets "+ getVehType());

                if (getVehType().equals("Plane")){
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("AIR");

                    ValueEventListener idListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                if (snapshot.child("planeID").getValue(String.class).equals(fetchTrip.get(position).getVehID()));{
                                    Log.i("skskks", "sksk here ");
                                    AvailTripDetails.setCompID(snapshot.child("compID").getValue(String.class));

                                    String compID = snapshot.child("compID").getValue(String.class);
                                    DatabaseReference compRef = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRANSPORT_COMPANY");

                                    ValueEventListener compListener = new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()){
                                                if (snapshot.child("compID").getValue(String.class).equals(compID)){
                                                    Log.i("skskks", "sksk here nest");
                                                    AvailTripDetails.setCompName(snapshot.child("compName").getValue(String.class));
                                                    details.setCompName(snapshot.child("compName").getValue(String.class));
                                                    Log.i("skskks", " nest " + AvailTripDetails.getCompName());

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
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };
                    databaseReference.child(details.getVehID()).addListenerForSingleValueEvent(idListener);

                }
                else{

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("SEA");

                    ValueEventListener idListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                if (snapshot.child("ferryID").getValue(String.class).equals(fetchTrip.get(position).getVehID()));{
                                    Log.i("skskks", "sksk here ");
                                    AvailTripDetails.setCompID(snapshot.child("compID").getValue(String.class));

                                    String compID = snapshot.child("compID").getValue(String.class);
                                    DatabaseReference compRef = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRANSPORT_COMPANY");

                                    ValueEventListener compListener = new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()){
                                                if (snapshot.child("compID").getValue(String.class).equals(compID)){
                                                    Log.i("skskks", "sksk here nest");
                                                    AvailTripDetails.setCompName(snapshot.child("compName").getValue(String.class));
                                                    Log.i("skskks", " nest " + AvailTripDetails.getCompName());
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
                        public void onCancelled(@NonNull DatabaseError error) {

                        }


                }

                            ;
                    databaseReference.child(details.getVehID()).addListenerForSingleValueEvent(idListener);
                //intent.putExtra("dest",fetchTrip.get(position).getTripDest());

            }
                Log.i("log","loggg"+ AvailTripDetails.getCompID());
                Log.i("log","logggN"+ AvailTripDetails.getCompName());
                startActivity(intent);
        }

    };


    }

    public void addBtn (View view){
        startActivity(new Intent(AvailTripsOneWay.this, Trip.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        travelDate = "";
        travelDest = "";
        travelOrig = "";
    }
}
