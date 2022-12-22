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

public class Customer extends LogIn {
    public String CustName, CustEmail, CustPword, CustUser, CustBday;
    private ImageView background,avatar;
    private TextView TVname;
    private String name,loginuser;
    protected DatabaseReference CustReference;
    public Customer tempCust;
    public static String user;
    TextView TVpersonalinfo;

    public Customer(){ }
    public Customer(String loginUser){
        CustUser = loginUser;

    }

    public Customer (String SUname, String SUemail, String SUpword, String SUuser, String SUbday) {
        this.CustName = SUname;
        this.CustEmail = SUemail;
        this.CustPword = SUpword;
        this.CustUser = SUuser;
        this.CustBday = SUbday;
    }

    public static void setUser(String user) {
        Customer.user = user;
    }

    public String getCustUser() {
        return CustUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer);

        background = (ImageView) findViewById(R.id.imageView2);
        avatar = (ImageView) findViewById(R.id.avatar);

        ImageView imgLogOut = (ImageView) findViewById(R.id.logOutPic);
        TextView TVlogout = (TextView) findViewById(R.id.booking);

        imgLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(Customer.this);
                alertbox.setTitle("Log Out");
                alertbox.setMessage("Log Out ??? ");

                alertbox.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                finishAffinity();
                                Intent intent = new Intent(Customer.this,MainActivity.class);
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

        TVlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(Customer.this);
                alertbox.setTitle("Log Out");
                alertbox.setMessage("Log Out ??? ");

                alertbox.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Customer.user=null;
                                finishAffinity();
                                Intent intent = new Intent(Customer.this,MainActivity.class);
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
                    case R.id.home:
                        Intent intent = new Intent(Customer.this, LogIn.class);
                        startActivity(intent);
                        break;
                    case R.id.explore:
                        intent = new Intent(Customer.this, ExploreChoose.class);
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

        CustReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("CUSTOMER");
        CustReference.child(user).addListenerForSingleValueEvent(listener);

        TVpersonalinfo = (TextView) findViewById(R.id.TVPersonalInfo);
        CustReference.child(user).addListenerForSingleValueEvent(listener2);

        TVpersonalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer.this,Account.class);
                startActivity(intent);
            }
        });



    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()){
                name = snapshot.child("CustName").getValue(String.class).toString();
                TVname.setText(name);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(Customer.this, error.toString(), Toast.LENGTH_LONG);
        }
    };

    ValueEventListener listener2 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            String fname = snapshot.child("CustName").getValue(String.class).toString();
            String fbday = snapshot.child("CustBday").getValue(String.class).toString();
            String femail = snapshot.child("CustEmail").getValue(String.class).toString();

            Account account = new Account(fbday,fname,femail,user);
            account.setPosition("Customer");
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(Customer.this, error.toString(), Toast.LENGTH_LONG);
        }
    };





}
