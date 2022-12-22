package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookDetails extends AppCompatActivity {

    TextView hotelname, hoteltype, hotelprice, checkIn, checkOut, noOfHeads;
    String hName, hType, hPrice, checkInDate, checkOutDate, heads, costStr, location, idStr;
    EditText name, username, emailadd;
    Button btnPay;

    static int num;
    long id;

    DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_book);

        reference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("ACCOMM_TKT_PAYMENT");

        hotelname = findViewById(R.id.hotelNameTextView);
        hoteltype = findViewById(R.id.hotelTypeTextView);
        hotelprice = findViewById(R.id.hotelPriceTextView);
        checkIn = findViewById(R.id.checkInTextView);
        checkOut = findViewById(R.id.checkOutTextView);
        noOfHeads = findViewById(R.id.headsTextView);

        btnPay = findViewById(R.id.btnPay);

        name = findViewById(R.id.nameText);
        username = findViewById(R.id.usernameText);
        emailadd = findViewById(R.id.emailAddText);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            hName = extras.getString("hotelname");
            hPrice = extras.getString("hotelprice");
            hType = extras.getString("hoteltype");
            checkInDate = extras.getString("checkin");
            checkOutDate = extras.getString("checkout");
            heads = extras.getString("heads");
            costStr = extras.getString("totalcost");
            location = extras.getString("loc");


            hotelname.setText(hName);
            hoteltype.setText(hType);
            hotelprice.setText(hPrice);
            checkIn.setText(checkInDate);
            checkOut.setText(checkOutDate);
            noOfHeads.setText(heads);
        }

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        id = snapshot.getChildrenCount();
                        idStr = generateID(id);
                        Accomm_Tkt_Payment addTkt = new Accomm_Tkt_Payment(costStr, hPrice, checkInDate, checkOutDate);
                        reference.child(idStr).setValue(addTkt);

                        Intent intent = new Intent(getApplicationContext(), PaymentDetails.class);
                        intent.putExtra("hotelname", hName);
                        intent.putExtra("loc", location);
                        intent.putExtra("type", hType);
                        intent.putExtra("contactname", name.getText().toString());
                        intent.putExtra("username", username.getText().toString());
                        intent.putExtra("email", emailadd.getText().toString());
                        intent.putExtra("checkin", checkInDate);
                        intent.putExtra("checkout", checkOutDate);
                        intent.putExtra("heads", heads);
                        intent.putExtra("totalcost", costStr);

                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }

    public String generateID(long num) {
        String id;
        num = num + 1;
        id = "TKT" + num;

        return id;
    }

}
