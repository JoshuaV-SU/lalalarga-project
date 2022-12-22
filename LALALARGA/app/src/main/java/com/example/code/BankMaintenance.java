package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class BankMaintenance extends AppCompatActivity {
    Button btnAdd,btnEdit,btnDisplay,btnDelete, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_maintenance);

        btnAdd = findViewById(R.id.tripBtn);
        btnEdit = findViewById(R.id.roomBTN);
        btnDisplay = findViewById(R.id.button3);
        btnDelete = findViewById(R.id.button4);
        btnBack = findViewById(R.id.bankBackBtn);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BankMaintenance.this,Employee.class));
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BankMaintenance.this,AddBankDetails.class));
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BankMaintenance.this,EditBankDetails.class));
            }
        });
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BankMaintenance.this,DisplayBankDetails.class));
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BankMaintenance.this,DeleteBankDetails.class));
            }
        });

    }
}
