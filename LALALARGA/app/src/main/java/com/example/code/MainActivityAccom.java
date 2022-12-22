package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

public class MainActivityAccom extends AppCompatActivity {

    public MainActivityAccom(){};

    List<ModelClass> fetchCompany;
    RecyclerView recyclerView;
    CompanyAdapter helperAdapter;
    DatabaseReference databaseReference;
    public CompanyAdapter.RecyclerViewClickListener listener;

    String checkInDate, checkOutDate, heads;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_accom);

        recyclerView = (RecyclerView) findViewById(R.id.companyRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView = findViewById(R.id.companyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchCompany = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            checkInDate = extras.getString("checkin");
            checkOutDate = extras.getString("checkout");
            heads = extras.getString("heads");
        }

        databaseReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("ACCOMMODATING_COMPANY");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    ModelClass data = ds.getValue(ModelClass.class);
                    fetchCompany.add(data);
                }
                setOnClickListener();
                helperAdapter = new CompanyAdapter(fetchCompany, listener);
                recyclerView.setAdapter(helperAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setOnClickListener() {
        listener = new CompanyAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ClickedItem.class);
                intent.putExtra("name", fetchCompany.get(position).getAcmName());
                intent.putExtra("location", fetchCompany.get(position).getAcmLoc());
                intent.putExtra("type", fetchCompany.get(position).getAcmType());
                intent.putExtra("price", fetchCompany.get(position).getAcmRmPrc());
                intent.putExtra("acmid", fetchCompany.get(position).getAcmID());
                intent.putExtra("checkin", checkInDate);
                intent.putExtra("checkout", checkOutDate);
                intent.putExtra("heads", heads);
                startActivity(intent);
            }
        };
    }
}