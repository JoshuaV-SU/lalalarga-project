package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayCompanies extends AppCompatActivity {

    public DisplayCompanies(){};

    List<ModelClassHotel> fetchCompany;
    RecyclerView recyclerView;
    CompanyAdapterHotel helperAdapter;
    DatabaseReference databaseReference;
    Button addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaycompanies);

        addBtn = findViewById(R.id.btnAdd);

        recyclerView = (RecyclerView) findViewById(R.id.companyRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView = findViewById(R.id.companyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchCompany = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("ACCOMMODATING_COMPANY");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()) {
                    ModelClassHotel data = ds.getValue(ModelClassHotel.class);
                    fetchCompany.add(data);
                }
                helperAdapter = new CompanyAdapterHotel(fetchCompany);
                recyclerView.setAdapter(helperAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentmain = new Intent(DisplayCompanies.this, MainActivityHotel.class);
                DisplayCompanies.this.startActivity(intentmain);

            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setSelectedItemId(R.id.hotel);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.transport:
                        Intent intent = new Intent(DisplayCompanies.this, TripList.class);
                        startActivity(intent);
                        break;
                    case R.id.hotel:

                        break;
                    case R.id.trips:
                        intent = new Intent(DisplayCompanies.this, TripList.class);
                        startActivity(intent);
                        break;
                }


                return false;
            }
        });

    }



}