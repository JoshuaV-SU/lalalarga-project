package com.example.code;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends MainActivity{
    private EditText ETlogin, ETpword;
    protected DatabaseReference CustReference,EmpReference;
    private Button LoginBtn;
    public String loginUser, loginPword, userType;
    private Button btnCust, btnEmp;
    //protected Customer customer;
    public String userID, userPword;

    protected String getUserID() {
        return userID;
    }

    // @Override

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.log_in);

       CustReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("CUSTOMER");
       EmpReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("EMPLOYEE");

       ETlogin = findViewById(R.id.logUser);
       ETpword = findViewById(R.id.logPword);

       btnCust = findViewById(R.id.btnCust1);
       btnEmp = findViewById(R.id.btnEmp1);

       btnCust.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               userType = "CUSTOMER";
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



    public void loginBTN (View view){

        loginUser = ETlogin.getText().toString();
        loginPword = ETpword.getText().toString();

        if (userType.equals("CUSTOMER"))
        {
            CustReference.child(loginUser).addListenerForSingleValueEvent(custListener);
        }
        if (userType.equals("EMPLOYEE"))
        {
            EmpReference.child(loginUser).addListenerForSingleValueEvent(empListener);
        }
    }

    ValueEventListener custListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                    String pass = snapshot.child("CustPword").getValue(String.class);

                    if (pass.equals(loginPword)){

                        Customer customer = new Customer();
                        customer.setUser(loginUser);


                        userID = loginUser;

                        startActivity(new Intent(LogIn.this, Customer.class));

                    }

                    else{
                        Toast.makeText(LogIn.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    }


            }
            else
            {
                Toast.makeText(LogIn.this, "User does not exist", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(LogIn.this, error.toString(), Toast.LENGTH_LONG);
        }
    };

    ValueEventListener empListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                if(userType.equals("EMPLOYEE")){
                    String pass = snapshot.child("EmpPword").getValue(String.class);
                    if (pass.equals(loginPword)){
                        Employee employee = new Employee();
                        employee.setUser(loginUser);


                        userID = loginUser;
                        startActivity(new Intent(LogIn.this, Employee.class));
                    }

                    else{
                        Toast.makeText(LogIn.this, "Wrong Password", Toast.LENGTH_LONG).show();
                    }
                }


            }
            else
            {
                Toast.makeText(LogIn.this, "User does not exist", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(LogIn.this, error.toString(), Toast.LENGTH_LONG);
        }
    };
}
