package com.example.code;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Account extends AppCompatActivity {

    public String Name, Email, Pword, User, Bday;
    protected static String FName, FEmail, FPword, FUser, FBday;
    private String TVnamestring, TVuserString,TVbdaystring, TVemail;
    public Account(){}
    public Account(String fbday, String fname, String femail, String user){
        Account.setFName(fname);
        Account.setFUser(user);
        Account.setFEmail(femail);
        Account.setFBday(fbday);
    }
    public static void setFName(String FName) {
        Account.FName = FName;
    }

    public static void setFEmail(String FEmail) {
        Account.FEmail = FEmail;
    }

    public static void setFPword(String FPword) {
        Account.FPword = FPword;
    }

    public static void setFUser(String FUser) {
        Account.FUser = FUser;
    }

    public static void setFBday(String FBday) {
        Account.FBday = FBday;
    }

    public static String getFName() {
        return FName;
    }

    public static String getFEmail() {
        return FEmail;
    }

    public static String getFPword() {
        return FPword;
    }

    public static String getFUser() {
        return FUser;
    }

    public static String getFBday() {
        return FBday;
    }

    public static String position;

    public static void setPosition(String position) {        Account.position = position;    }

    public static String getPosition() {        return position;    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        TextView hello,custname,custbday,user;
        EditText custemail;
        DatabaseReference CustReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("CUSTOMER");
        DatabaseReference EmpReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("EMPLOYEE");


        hello = (TextView) findViewById(R.id.StringUserName);
        hello.setText("Hello, "+FName);

        TVnamestring = getFName();
        TVuserString = getFUser();
        TVbdaystring = getFBday();
        TVemail = getFEmail();
        custemail = findViewById(R.id.ETemail);
        custemail.setClickable(true);
        custemail.setEnabled(true);
        custname = (TextView) findViewById(R.id.TVname);
        custname.setText(TVnamestring);

        custbday = (TextView) findViewById(R.id.TVbday);
        custbday.setText(TVbdaystring);

        user = (TextView) findViewById(R.id.TVuser);
        user.setText(TVuserString);

        custemail.setText(TVemail);
        ImageView backBtn, saveBtn;

        backBtn = (ImageView) findViewById(R.id.btnBack);
        saveBtn = (ImageView) findViewById(R.id.btnSave);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = getPosition();
                if(position.equals("Employee")){
                    Intent intent = new Intent(Account.this,Employee.class);
                    startActivity(intent);
                }
                else if(position.equals("Customer")){
                    Intent intent = new Intent(Account.this,Customer.class);
                    startActivity(intent);
                }

            }
        });
        CustReference = CustReference.child(TVuserString);
        DatabaseReference finalCustReference1 = CustReference;
        EmpReference = EmpReference.child(TVuserString);
        DatabaseReference finalEmpReference1 = EmpReference;
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String position = getPosition();

                if (position.equals("Employee")){
                    if(!FEmail.equals(custemail.getText().toString())){
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("EmpEmail",custemail.getText().toString());
                        finalEmpReference1.updateChildren(updates);
                        Toast.makeText(Account.this, "Email updated successfully", Toast.LENGTH_LONG).show();
                        setFEmail(custemail.getText().toString());
                    }
                }
                else{

                    if(!FEmail.equals(custemail.getText().toString())){
                        Map<String, Object> updates = new HashMap<>();
                        updates.put("CustEmail",custemail.getText().toString());
                        finalCustReference1.updateChildren(updates);
                        Toast.makeText(Account.this, "Email updated successfully", Toast.LENGTH_LONG).show();
                        setFEmail(custemail.getText().toString());
                    }
                }

            }
        });


    }


}