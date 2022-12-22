package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FinishedPayment extends AppCompatActivity {

    TextView hotelname, hoteltype, hotelloc, checkIn, checkOut, noOfHeads, name, username, email, cost, bankName, transID;
    String hName, hType, hPrice, checkInDate, checkOutDate, heads, costStr, location, contactname, user, emailadd, bName, tID;
    Button btnHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_finish);

        hotelname = findViewById(R.id.hotelNameTxtView);
        hotelloc = findViewById(R.id.locTxtView);
        hoteltype = findViewById(R.id.typeTxtView);
        name = findViewById(R.id.nameTxtView);
        username = findViewById(R.id.usernameTxtView);
        email = findViewById(R.id.emailTxtView);
        checkIn = findViewById(R.id.checkInTxtView);
        checkOut = findViewById(R.id.checkOutTxtView);
        noOfHeads = findViewById(R.id.headsTxtView);
        cost = findViewById(R.id.totalCostTxtView);

        bankName = findViewById(R.id.bankNameTxtView);
        transID = findViewById(R.id.transIDTxtView);


        btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinishedPayment.this,Customer.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            hName = extras.getString("hotelname");
            location = extras.getString("loc");
            hType = extras.getString("type");
            contactname = extras.getString("contactname");
            user = extras.getString("username");
            emailadd = extras.getString("email");
            checkInDate = extras.getString("checkin");
            checkOutDate = extras.getString("checkout");
            heads = extras.getString("heads");
            costStr = extras.getString("totalcost");

            bName = extras.getString("bankname");
            tID = extras.getString("transID");


            hotelname.setText(hName);
            hotelloc.setText(location);
            hoteltype.setText(hType);
            name.setText(contactname);
            username.setText(user);
            email.setText(emailadd);
            checkIn.setText(checkInDate);
            checkOut.setText(checkOutDate);
            noOfHeads.setText(heads);
            cost.setText(costStr);
            bankName.setText(bName);
            transID.setText(tID);

        }


    }
}


