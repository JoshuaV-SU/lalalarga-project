package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class PlaneList extends AppCompatActivity {
    RecyclerView recyclerV;
    DatabaseReference dbref;
    com.example.transportcompany.PlaneAdapter adapter;
    ArrayList<Plane> plist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_list);

        Button add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVehAdd();
            }
        });

        recyclerV = findViewById(R.id.planelist);
        dbref = FirebaseDatabase.getInstance().getReference("AIR");
        recyclerV.setHasFixedSize(true);
        recyclerV.setLayoutManager(new LinearLayoutManager(this));


        plist = new ArrayList<>();
        adapter = new com.example.transportcompany.PlaneAdapter(this,plist);
        recyclerV.setAdapter(adapter);

        dbref.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                   Plane plane = dataSnapshot.getValue(Plane.class);
                   // Plane plane = new Plane (dataSnapshot.child("planeID").getValue(String.class), dataSnapshot.child("planeNum").getValue(String.class), dataSnapshot.child("planeSeatsNum").getValue(Integer.class));
                    plist.add(plane);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void openVehAdd() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}