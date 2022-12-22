package com.example.code;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteBankDetails extends AppCompatActivity {
    DatabaseReference dataref;
    private ListView listData;
    private AutoCompleteTextView txtSearch;
    private TextView bankID,bankName,compBankNum,compNum;
    private Button deleteBtn,backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_bank_details);

        dataref = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("BANK");
        listData = findViewById(R.id.listBank);
        txtSearch = findViewById(R.id.txtSearch1);
        bankID =  findViewById(R.id.TextBankID);
        bankName = findViewById(R.id.TextBankName);
        compBankNum = findViewById(R.id.TextCompBankNum);
        compNum = findViewById(R.id.TextCompNum);
        backbtn = findViewById(R.id.backDelete);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteBankDetails.this, BankMaintenance.class));
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
        deleteBtn = findViewById(R.id.buttonDelete);

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


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<String> listBank = new ArrayList<>();
                    String intBKN,intBN,bankid,bankname;

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        BankDetailsClass bank = new BankDetailsClass(ds.child("bankID").getValue(String.class), ds.child("bankName").getValue(String.class),
                                ds.child("compBankNum").getValue(Integer.class),ds.child("compNum").getValue(Integer.class));
                        bankid = bank.getBankID();
                        bankname = bank.getBankName();
                        intBKN = String.valueOf(bank.getCompBankNum());
                        intBN = String.valueOf(bank.getCompNum());
                        bankID.setText(bankid);
                        bankName.setText(bankname);
                        compBankNum.setText(intBKN);
                        compNum.setText(intBN);
                    }

                } else {
                    Log.d("BANK", "no data");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i){
                            case DialogInterface.BUTTON_POSITIVE:
                                deleteBank(id);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("You are going to permanently delete this Bank information. Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    private void deleteBank(String BankID) {
        DatabaseReference drBank = FirebaseDatabase.getInstance().getReference("BANK").child(BankID);
        drBank.removeValue();
        txtSearch.getText().clear();
        bankID.setText("");
        bankName.setText("");
        compBankNum.setText("");
        compNum.setText("");
        Toast.makeText(DeleteBankDetails.this, "Bank details deleted successfully!",Toast.LENGTH_LONG).show();
    }

}
