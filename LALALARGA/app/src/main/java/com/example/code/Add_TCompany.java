package com.example.code;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_TCompany extends AppCompatActivity {
    DatabaseReference dataref;
    TextInputLayout compID,compName,compNum;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transport_company);

        compID = findViewById(R.id.cmpID);
        compName = findViewById(R.id.cmpNameID);
        compNum = findViewById(R.id.cmpNumID);
        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataref = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRAVEL_AGENCY");
                String cmpid = compID.getEditText().getText().toString();
                String cmpname = compName.getEditText().getText().toString();
                String cmpnum = compNum.getEditText().getText().toString();

                if(cmpid.matches("") || cmpname.matches("") || cmpnum.matches(""))
                    Toast.makeText(Add_TCompany.this, "Error: Please fill up all boxes.",Toast.LENGTH_LONG).show();
                else{

                    Integer intBN = Integer.parseInt(cmpnum);
                    TCompanyClass agency = new TCompanyClass(cmpid,cmpname,intBN);
                    dataref.child(cmpid).setValue(agency);
                    Toast.makeText(Add_TCompany.this, "Agency details successfully added!",Toast.LENGTH_LONG).show();
                    compID.getEditText().getText().clear();
                    compName.getEditText().getText().clear();
                    compNum.getEditText().getText().clear();
                }
            }
        });

    }
}
