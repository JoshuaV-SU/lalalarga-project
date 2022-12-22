package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentDetails extends AppCompatActivity {

    TextView hotelname, hoteltype, hotelloc, checkIn, checkOut, noOfHeads, name, username, email, cost;
    String hName, hType, hPrice, checkInDate, checkOutDate, heads, costStr, location, contactname, user, emailadd, bName, tID;
    EditText bankName, transID;

    ListView listView;
    Boolean exists = false;

    Button btnBook;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_payment);

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

        bankName = findViewById(R.id.bankNameEditText);
        transID = findViewById(R.id.transIDEditText);
        btnBook = findViewById(R.id.btnHome);


        listView = findViewById(R.id.bankListView);



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

        }

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("BANK");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    BankDetails bank = ds.getValue(BankDetails.class);
                    String str = bank.getBankName() + " : " + bank.getCompBankNum();
                    list.add(str);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bName = bankName.getText().toString();
                tID = transID.getText().toString();

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                     for(DataSnapshot data : snapshot.getChildren()){
                            BankDetails bank = data.getValue(BankDetails.class);
                            String comp = bank.getBankName();

                            if(comp.equals(bName)){
                                exists = true;
                                break;
                            }
                            else
                                ;

                        }

                     if(exists){
                         Intent intent = new Intent(getApplicationContext(), FinishedPayment.class);
                         intent.putExtra("hotelname", hName);
                         intent.putExtra("loc", location);
                         intent.putExtra("type", hType);
                         intent.putExtra("contactname", contactname);
                         intent.putExtra("username", user);
                         intent.putExtra("email", emailadd);
                         intent.putExtra("checkin", checkInDate);
                         intent.putExtra("checkout", checkOutDate);
                         intent.putExtra("heads", heads);
                         intent.putExtra("totalcost", costStr);
                         intent.putExtra("bankname", bName);
                         intent.putExtra("transID", tID);
                         startActivity(intent);
                     }
                     else
                        errorDialog();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    public void successDialog() {
        AddDialog addDialog = new AddDialog();
        addDialog.show(getSupportFragmentManager(), "dialog");
    }

    public void errorDialog() {
        ErrorDialog errorDialog = new ErrorDialog();
        errorDialog.show(getSupportFragmentManager(), "dialog2");
    }
}
