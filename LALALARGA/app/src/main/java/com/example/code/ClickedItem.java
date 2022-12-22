package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClickedItem extends AppCompatActivity {

    TextView nameTxt, locTxt, priceTxt, typeTxt;
    Button btnBook;

    DatabaseReference reference, reference2;

    String name, location, price, type, checkInDate, checkOutDate, heads, AcmID, costStr, AcmTransID;
    float cost;

    long id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_clicked);

        nameTxt = findViewById(R.id.nameTextView);
        locTxt = findViewById(R.id.locationTextView);
        priceTxt = findViewById(R.id.rmPriceTextView);
        typeTxt = findViewById(R.id.typeTextView);
        btnBook = findViewById(R.id.btnBook);

        float temphead, tempprice;

        reference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("ACCOMM_TICKET");


        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            name = extras.getString("name");
            location = extras.getString("location");
            price = extras.getString("price");
            type = extras.getString("type");
            checkInDate = extras.getString("checkin");
            checkOutDate = extras.getString("checkout");
            heads = extras.getString("heads");
            AcmID = extras.getString("acmid");

            nameTxt.setText(name);
            locTxt.setText(location);
            priceTxt.setText(price);
            typeTxt.setText(type);
        }

        temphead = Float.parseFloat(heads);
        tempprice = Float.parseFloat(price);
        cost = temphead * tempprice;
        costStr = String.valueOf(cost);


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        id = snapshot.getChildrenCount();
                        AcmTransID = generateID(id);
                        Accomm_Ticket addTicket = new Accomm_Ticket(AcmTransID, AcmID, location, heads, costStr);
                        reference.child(AcmTransID).setValue(addTicket);

                        Intent intent = new Intent(getApplicationContext(), BookDetails.class);
                        intent.putExtra("hotelname", nameTxt.getText().toString());
                        intent.putExtra("hotelloc", locTxt.getText().toString());
                        intent.putExtra("hoteltype", typeTxt.getText().toString());
                        intent.putExtra("hotelprice", priceTxt.getText().toString());
                        intent.putExtra("checkin", checkInDate);
                        intent.putExtra("checkout", checkOutDate);
                        intent.putExtra("heads", heads);
                        intent.putExtra("totalcost", costStr);
                        intent.putExtra("loc", location);
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
        id = "AT" + num;

        return id;
    }


}
