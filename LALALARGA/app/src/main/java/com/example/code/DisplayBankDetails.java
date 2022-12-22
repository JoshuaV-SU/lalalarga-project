package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DisplayBankDetails extends AppCompatActivity {

    private ListView BankDetView;
    private DatabaseReference reference;
    ArrayList<Bank> bank;
    RecyclerView recyclerView;
    SearchView searchView;
    AdapterClass adapterClass;
    private Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_list);
      //BankDetView = findViewById(R.id.bankRecyclerView);
        reference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("BANK");
        searchView = findViewById(R.id.bankSV);
        recyclerView = (RecyclerView) findViewById(R.id.bankRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView = findViewById(R.id.bankRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bank = new ArrayList<>();
        recyclerView.setAdapter(adapterClass);

        backbtn = findViewById(R.id.btnBack2);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayBankDetails.this, BankMaintenance.class));
            }
        });

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    Bank data = new Bank(ds.child("bankID").getValue(String.class), ds.child("bankName").getValue(String.class),
                            ds.child("compBankNum").getValue(Integer.class),ds.child("compNum").getValue(Integer.class));
                    bank.add(data);
                }
                adapterClass = new AdapterClass(bank);
                recyclerView.setAdapter(adapterClass);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

   @Override
    protected void onStart() {
        super.onStart();

        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }
    }

    private void search(String str){
        ArrayList<Bank> myList = new ArrayList<>();
        for(Bank object: bank){
            if(object.getBankName().toLowerCase().contains(str.toLowerCase())){
                myList.add(object);
            }
        }
        adapterClass = new AdapterClass(myList);
        recyclerView.setAdapter(adapterClass);
    }

}
