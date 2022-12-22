package com.example.code;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Employee extends LogIn {
    public String EmpName, EmpEmail, EmpPword, EmpUser, EmpBday;

    private ImageView background,avatar;
    private TextView TVname;
    private String name,loginuser;
    protected DatabaseReference EmpRefrence;
    public Customer tempCust;
    public static String user;
    TextView TVpersonalinfo;

    public Employee(){ }

    public Employee(String EmpName, String EmpEmail, String EmpPword, String EmpUser, String EmpBday) {
        this.EmpName = EmpName;
        this.EmpEmail = EmpEmail;
        this.EmpPword = EmpPword;
        this.EmpUser = EmpUser;
        this.EmpBday = EmpBday;
    }

    public static void setUser(String user) {
        Employee.user = user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee);

        EmpRefrence = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("EMPLOYEE");

        background = (ImageView) findViewById(R.id.imageView2);
        avatar = (ImageView) findViewById(R.id.avatar);

        TextView replace = (TextView) findViewById(R.id.booking);
        replace.setText("Logout");

        TextView delete = (TextView) findViewById(R.id.logOut);
        delete.setAlpha(0);

        ImageView replacePic = (ImageView) findViewById(R.id.bookingPic);
        replacePic.setImageResource(R.drawable.logout);

        ImageView deletePic = (ImageView) findViewById(R.id.logOutPic);
        deletePic.setAlpha(0);


        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(Employee.this);
                alertbox.setTitle("Log Out");
                alertbox.setMessage("Log Out ??? ");

                alertbox.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                finishAffinity();
                                Intent intent = new Intent(Employee.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });

                alertbox.setNeutralButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });

                alertbox.show();

            }
        });

        replacePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(Employee.this);
                alertbox.setTitle("Log Out");
                alertbox.setMessage("Log Out ??? ");

                alertbox.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                setUser(null);
                                Intent intent = new Intent(Employee.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });

                alertbox.setNeutralButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });

                alertbox.show();

            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.manage:
                        Intent intent = new Intent(Employee.this, TripList.class);
                        startActivity(intent);
                        break;
                    case R.id.bank:
                        intent = new Intent(Employee.this, BankMaintenance.class);
                        startActivity(intent);
                        break;
                    case R.id.account:

                        break;
                }


                return false;
            }
        });
        TVname = (TextView) findViewById(R.id.StringUserName);
        TVname.setText(user);

        EmpRefrence.child(user).addListenerForSingleValueEvent(listener);

        TVpersonalinfo = (TextView) findViewById(R.id.TVPersonalInfo);
        EmpRefrence.child(user).addListenerForSingleValueEvent(listener2);

        TVpersonalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Employee.this,Account.class);
                startActivity(intent);
            }
        });



    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                name = snapshot.child("EmpName").getValue(String.class).toString();
                TVname.setText(name);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(Employee.this, error.toString(), Toast.LENGTH_LONG);
        }
    };

    ValueEventListener listener2 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            String fname = snapshot.child("EmpName").getValue(String.class).toString();
            String fbday = snapshot.child("EmpBday").getValue(String.class).toString();
            String femail = snapshot.child("EmpEmail").getValue(String.class).toString();

            Account account = new Account(fbday,fname,femail,user);
            Account.setPosition("Employee");
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(Employee.this, error.toString(), Toast.LENGTH_LONG);
        }
    };





}
