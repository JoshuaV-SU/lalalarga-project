package com.example.code;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private EditText etname, etmail, etpword, etuser, etbday;
    private String CustName, CustEmail, CustPword, CustUser, CustBday, userType, EmpName, EmpEmail, EmpPword, EmpUser, EmpBday;

  //  private Task<Void> CustReference;
    private DatabaseReference EmpReference,CustReference;
    private Button btnCust, btnEmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        CustReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("CUSTOMER");
        EmpReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("EMPLOYEE");

        Button btnSignUp = findViewById(R.id.btnAdd);

        etname = findViewById(R.id.tripID);
        etmail = findViewById(R.id.vehType);
        etpword = findViewById(R.id.tktPrice);
        etuser = findViewById(R.id.username);
        etbday = findViewById(R.id.bday);

        btnCust = findViewById(R.id.btnCust);
        btnEmp = findViewById(R.id.btnEmp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             record(view);

                                         }
                                     }

        );

        btnCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userType = "CUSTOMER1";
                btnCust.setBackgroundColor(Color.rgb(226, 11, 11));
                btnEmp.setBackgroundColor(Color.rgb(169,169,169));
            }
        });

        btnEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userType = "EMPLOYEE";
                btnEmp.setBackgroundColor(Color.rgb(226, 11, 11));
                btnCust.setBackgroundColor(Color.rgb(169,169,169));
            }
        });
    }

    protected void record(View view) {
        if(userType.equals("CUSTOMER1")){
            Log.i("recorded", "helloooo its recorded brow");
            CustName = etname.getText().toString();
            CustEmail = etmail.getText().toString();
            CustPword = etpword.getText().toString();
            CustUser = etuser.getText().toString();
            CustBday = etbday.getText().toString();

            Customer signUpCust = new Customer ();
            CustReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("CUSTOMER");

            Map<String, Object> updates = new HashMap<>();
            updates.put("CustBday", CustBday);
            updates.put("CustEmail", CustEmail);
            updates.put("CustName", CustName);
            updates.put("CustPword", CustPword);
            updates.put("CustUser", CustUser);

            CustReference = CustReference.child(CustUser);
            DatabaseReference finalCustReference1 = CustReference;
            finalCustReference1.updateChildren(updates);

            Toast.makeText(SignUp.this, "Account created successfully", Toast.LENGTH_LONG).show();
            Log.i("recorded", "helloooo its recorded brow");
        }
        else if(userType.equals("EMPLOYEE")){
            EmpName = etname.getText().toString();
            EmpEmail = etmail.getText().toString();
            EmpPword = etpword.getText().toString();
            EmpUser = etuser.getText().toString();
            EmpBday = etbday.getText().toString();

            Employee signUpCust = new Employee ();
            EmpReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("EMPLOYEE");

            Map<String, Object> updates = new HashMap<>();
            updates.put("EmpName", EmpName);
            updates.put("EmpEmail", EmpEmail);
            updates.put("EmpPword", EmpPword);
            updates.put("EmpUser", EmpUser);
            updates.put("EmpBday", EmpBday);

            EmpReference = EmpReference.child(EmpUser);
            DatabaseReference finalEmpReference1 = EmpReference;
            finalEmpReference1.updateChildren(updates);

            Toast.makeText(SignUp.this, "Account created successfully", Toast.LENGTH_LONG).show();
            Log.i("recorded", "helloooo its recorded brow");
        }

    }


}
