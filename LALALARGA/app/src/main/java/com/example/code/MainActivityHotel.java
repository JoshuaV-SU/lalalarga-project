package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivityHotel extends AppCompatActivity {

    EditText AccomID, AccomName, AccomType, AccomLoc, AccomRooms, AccomRmPrc;
    String AcmID, AcmName, AcmType, AcmLoc, AcmRooms, AcmRmPrc;
    Button addBtn, viewBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hotel);

        reference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("ACCOMMODATING_COMPANY");

        AccomID = findViewById(R.id.idText);
        AccomName = findViewById(R.id.nameText);
        AccomType = findViewById(R.id.typeText);
        AccomLoc = findViewById(R.id.locationText);
        AccomRooms = findViewById(R.id.roomText);
        AccomRmPrc = findViewById(R.id.priceText);
        addBtn = findViewById(R.id.addBtn);
        viewBtn = findViewById(R.id.viewBtn);




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AcmID = AccomID.getText().toString();
                AcmName = AccomName.getText().toString();
                AcmType = AccomType.getText().toString();
                AcmLoc = AccomLoc.getText().toString();
                AcmRooms = AccomRooms.getText().toString();
                AcmRmPrc = AccomRmPrc.getText().toString();

                Accommodating_Company addCompany = new Accommodating_Company(AcmID, AcmName, AcmType, AcmLoc, AcmRooms, AcmRmPrc);
                reference.child(AcmID).setValue(addCompany);


                openDialog();
                reset();

            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intentmain = new Intent(MainActivityHotel.this, DisplayCompanies.class);
                MainActivityHotel.this.startActivity(intentmain);
            }
        });

    }

    public void reset() {
        AccomID = findViewById(R.id.idText);
        AccomName = findViewById(R.id.nameText);
        AccomType = findViewById(R.id.typeText);
        AccomLoc = findViewById(R.id.locationText);
        AccomRooms = findViewById(R.id.roomText);
        AccomRmPrc = findViewById(R.id.priceText);

        AccomID.setText("");
        AccomName.setText("");
        AccomType.setText("");
        AccomLoc.setText("");
        AccomRooms.setText("");
        AccomRmPrc.setText("");
    }

    public void openDialog() {
        AddDialogHotel addDialog = new AddDialogHotel();
        addDialog.show(getSupportFragmentManager(), "dialog");
    }
}