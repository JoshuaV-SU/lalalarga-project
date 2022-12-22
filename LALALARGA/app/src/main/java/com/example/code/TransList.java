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

public class TransList extends AppCompatActivity {

    RecyclerView rcView;
    DatabaseReference dbref;
    com.example.code.TransportAdapter transportAdapter;
    ArrayList<Transport> tlist;
    com.example.code.TransportAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_list);

        Button add = (Button) findViewById(R.id.btn_add);
        rcView = findViewById(R.id.translist);
        dbref = FirebaseDatabase.getInstance().getReference("TRANSPORT_COMPANY");

        setTransList();
        setAdapter();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrans();
            }
        });

    }

    private void setAdapter() {
        //setOnClickListener();
        tlist = new ArrayList<>();
        transportAdapter = new com.example.code.TransportAdapter(tlist, listener);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        rcView.setAdapter(transportAdapter);
    }

    private void setTransList() {
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Transport transport = dataSnapshot.getValue(Transport.class);
                    tlist.add(transport);
                }
                transportAdapter.notifyDataSetChanged();
            }

            @Override/* private void setOnClickListener() {

        listener = new TransportAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getApplicationContext(), PlaneList.class);

                intent.putExtra("compName", tlist.get(position).getCompName());
                intent.putExtra("compNum", tlist.get(position).getCompNum());
                intent.putExtra("Compid", tlist.get(position).getCompID());
                intent.putExtra("transType", tlist.get(position).getTransType());
                startActivity(intent);
            }
        };
    }*/
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void openTrans() {
        Intent intent = new Intent(this, com.example.code.MainActivity.class);
        startActivity(intent);
    }
}