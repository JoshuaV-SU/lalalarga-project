package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddBankDetails extends AppCompatActivity {
    FirebaseAuth dbAuth;
    FirebaseDatabase rootNode;

    DatabaseReference reference;
    TextInputLayout BankID,BankName,CompBankNum,BankNum;
    private Button addBtn,backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_details);
        BankID = findViewById(R.id.bnkID);
        BankName = findViewById(R.id.bnkNameID);
        CompBankNum = findViewById(R.id.compBnkNumID);
        BankNum = findViewById(R.id.compNumID);
        addBtn = findViewById(R.id.addBtn);
        backbtn = findViewById(R.id.backBtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddBankDetails.this, BankMaintenance.class));
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/");
                reference = rootNode.getReference("BANK");

                String bnkId = BankID.getEditText().getText().toString();
                String bnkName = BankName.getEditText().getText().toString();
                String cmpbnkNum = CompBankNum.getEditText().getText().toString();
                String bnkNum = BankNum.getEditText().getText().toString();


                            if(bnkId.matches("") || bnkName.matches("") || cmpbnkNum.matches("") || bnkNum.matches(""))
                                Toast.makeText(AddBankDetails.this, "Error: Please fill up all boxes.",Toast.LENGTH_LONG).show();
                            else{
                                Integer intCBN = Integer.parseInt(cmpbnkNum);
                                Integer intBN = Integer.parseInt(bnkNum);
                                BankDetailsClass bankDeets = new BankDetailsClass(bnkId,bnkName,intCBN,intBN);
                                BankDetailsClass bank = new BankDetailsClass();
                                reference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("BANK");
                                //ticketRef[0] = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TICKET");
                                Map<String, Object> updates = new HashMap<>();
                                updates.put("bankID", bnkId);
                                updates.put("bankName", bnkName);
                                updates.put("compBankNum", intCBN);
                                updates.put("compNum", intBN);

                                //ticketRef[0] = ticketRef[0].child(ticketID);
                               // DatabaseReference finalTickRef = ticketRef[0];
                               // finalTickRef.updateChildren(updates);

                                reference = reference.child(bnkId);
                                DatabaseReference newRef = reference;
                                newRef.updateChildren(updates);
                                //reference.child(bnkId).setValue(bankDeets);
                                Toast.makeText(AddBankDetails.this, "Bank details successfully added!",Toast.LENGTH_LONG).show();
                                BankID.getEditText().getText().clear();
                                BankName.getEditText().getText().clear();
                                CompBankNum.getEditText().getText().clear();
                                BankNum.getEditText().getText().clear();
                            }
            }
        });

    }

}