package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class EditBankDetails extends AppCompatActivity {
    DatabaseReference dataref;
    private ListView listData;
    private AutoCompleteTextView txtSearch;
    private EditText bankID,bankName,compBankNum,compNum;
    private Button editBtn,backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bank_details);

        dataref = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("BANK");
        listData = findViewById(R.id.listBank);
        txtSearch = findViewById(R.id.txtSearch1);
        bankID =  findViewById(R.id.editTextBankID);
        bankName = findViewById(R.id.editTextBankName);
        compBankNum = findViewById(R.id.editTextCompBankNum);
        compNum = findViewById(R.id.editTextCompNum);
        backbtn = findViewById(R.id.backEdit);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditBankDetails.this, BankMaintenance.class));
            }
        });

        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dataref.addListenerForSingleValueEvent(event);
        editBtn = findViewById(R.id.buttonEdit);


    }

    private void populateSearch(DataSnapshot snapshot) {
        ArrayList<String> bankDetails = new ArrayList<>();
        if(snapshot.exists()){
            for(DataSnapshot ds: snapshot.getChildren()){
                String bankID = ds.child("bankID").getValue(String.class);
                bankDetails.add(bankID);
            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,bankDetails);
            txtSearch.setAdapter(adapter);
            txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String id = txtSearch.getText().toString();
                    searchID(id);
                }
            });
        }else{
            Log.d("BANK","no data");
        }
    }

    private void searchID(String id) {
        Query query = dataref.orderByChild("bankID").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            EditText bankID =  findViewById(R.id.editTextBankID);
            EditText bankName = findViewById(R.id.editTextBankName);
            EditText compBankNum = findViewById(R.id.editTextCompBankNum);
            EditText compNum = findViewById(R.id.editTextCompNum);
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<String> listBank = new ArrayList<>();
                   String intBKN,intBN;

                    for (DataSnapshot ds : snapshot.getChildren()) {
                       BankDetailsClass bank = new BankDetailsClass(ds.child("bankID").getValue(String.class), ds.child("bankName").getValue(String.class),
                                ds.child("compBankNum").getValue(Integer.class),ds.child("compNum").getValue(Integer.class));
                        intBKN = String.valueOf(bank.getCompBankNum());
                        intBN = String.valueOf(bank.getCompNum());
                        listBank.add(bank.getBankID());
                        listBank.add(bank.getBankName());
                        listBank.add(intBKN);
                        listBank.add(intBN);
                        bankID.setText(listBank.get(0));
                        bankName.setText(listBank.get(1));
                        compBankNum.setText(listBank.get(2));
                        compNum.setText(listBank.get(3));
                    }

                    ArrayAdapter adapter1 = new ArrayAdapter(EditBankDetails.this, android.R.layout.simple_list_item_1, listBank);
                    listData.setAdapter(adapter1);
                } else {
                    Log.d("BANK", "no data");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id,name;
                int compBnkNum,compnum;
                id = bankID.getText().toString();
                name = bankName.getText().toString();
                compBnkNum = Integer.parseInt(compBankNum.getText().toString());
                compnum = Integer.parseInt(compNum.getText().toString());
                HashMap hashMap = new HashMap();
                hashMap.put("bankID",id);
                hashMap.put("bankName",name);
                hashMap.put("compBankNum",compBnkNum);
                hashMap.put("compNum",compnum);

                dataref.child(id).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditBankDetails.this, "Changes Saved",Toast.LENGTH_LONG).show();
                        bankID.getEditableText().clear();
                        bankName.getEditableText().clear();
                        compBankNum.getEditableText().clear();

                        txtSearch.getText().clear();
                        listData.setAdapter(null);
                    }
                });
            }
        });
    }

}
