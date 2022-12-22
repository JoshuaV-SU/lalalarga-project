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

public class FerryList extends AppCompatActivity {
    RecyclerView ferryRV;
    DatabaseReference dbref;
    FerryAdapter adapter;
    ArrayList<Ferry> ferry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferry_list);

        Button add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVehAdd();
            }
        });

        ferryRV = findViewById(R.id.ferrylist);
        dbref = FirebaseDatabase.getInstance().getReference("SEA");
        ferryRV.setHasFixedSize(true);
        ferryRV.setLayoutManager(new LinearLayoutManager(this));

        ferry = new ArrayList<>();
        adapter = new FerryAdapter(this,ferry);
        ferryRV.setAdapter(adapter);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Ferry data = snapshot1.getValue(Ferry.class);
                    ferry.add(data);
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