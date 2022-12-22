package com.example.code;

import static android.R.layout.simple_expandable_list_item_1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TripReservationDetails extends AppCompatActivity {
    public static ArrayList<PassengerDetails> passList ;
    public static String name, username, email;
    public static String TripOrig, TripDest, DeptDate, DeptTime, ArvDate, ArvTime, CostTotal;
    public static String bankName, transID, tktID;
    public static String compName;

    public static String getCompName() {        return compName;    }

    public static void setCompName(String compName) {        TripReservationDetails.compName = compName;    }

    public static String getBankName() {        return bankName;    }

    public static String getTransID() {        return transID;    }

    public static String getTktID() {        return tktID;    }

    public static void setBankName(String bankName) {        TripReservationDetails.bankName = bankName;    }

    public static void setTransID(String transID) {        TripReservationDetails.transID = transID;    }

    public static void setTktID(String tktID) {        TripReservationDetails.tktID = tktID;    }

    public static String getTripOrig() {
        return TripOrig;
    }

    public static String getTripDest() {
        return TripDest;
    }

    public static String getDeptDate() {
        return DeptDate;
    }

    public static String getDeptTime() {
        return DeptTime;
    }

    public static String getArvDate() {
        return ArvDate;
    }

    public static String getArvTime() {
        return ArvTime;
    }

    public static String getCostTotal() {
        return CostTotal;
    }

    public static void setTripOrig(String tripOrig) {
        TripOrig = tripOrig;
    }

    public static void setTripDest(String tripDest) {
        TripDest = tripDest;
    }

    public static void setDeptDate(String deptDate) {
        DeptDate = deptDate;
    }

    public static void setDeptTime(String deptTime) {
        DeptTime = deptTime;
    }

    public static void setArvDate(String arvDate) {
        ArvDate = arvDate;
    }

    public static void setArvTime(String arvTime) {
        ArvTime = arvTime;
    }

    public static void setCostTotal(String costTotal) {
        CostTotal = costTotal;
    }

    public static void setName(String name) {
        TripReservationDetails.name = name;
    }

    public static String getName() {        return name;    }

    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }

    public TripReservationDetails() {}


    public static void setPassList(ArrayList<PassengerDetails> passList) {        TripReservationDetails.passList = passList;    }

    public static ArrayList<PassengerDetails> getPassList() {        return passList;    }

    public static void setUsername(String username) {
        TripReservationDetails.username = username;
    }

    public static void setEmail(String email) {
        TripReservationDetails.email = email;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avail_booking_complete);
        //passList = new ArrayList<PassengerDetails>();
        ListView psgrList = findViewById(R.id.listPsgrs);
        ArrayList<String> psgrString = new ArrayList<String>();
        TextView TVtripOG, TVtripDest, TVdeptDate, TVdeptTime,
                TVbankName, TVtransID, TVarvDate, TVarvTime, TVcustName, TVcustUser,TVcustEmail, TVticket,TVcost, TVcompName;
        String psgrName, nationality;

        TVtripOG = (TextView) findViewById(R.id.TVdept);
        TVtripDest = (TextView) findViewById(R.id.TVdest);
        TVdeptDate = (TextView) findViewById(R.id.TVdeptDate);
        TVdeptTime = (TextView) findViewById(R.id.TVdeptTime);
        TVarvDate = (TextView) findViewById(R.id.TVdestDate);
        TVarvTime = (TextView) findViewById(R.id.TVdestTime);
        TVcustName = (TextView) findViewById(R.id.TVcustName);
        TVcustUser = (TextView) findViewById(R.id.TVcustUser);
        TVcustEmail = (TextView) findViewById(R.id.TVcustMail);
        TVticket = (TextView) findViewById(R.id.TVticket);
        TVcost = (TextView) findViewById(R.id.TVtotalCost);
        TVcompName = (TextView) findViewById(R.id.TVCompName);
        TVbankName = (TextView) findViewById(R.id.TVbankName);
        TVtransID = (TextView) findViewById(R.id.TVtransID);

        TVtripOG.setText(getTripOrig());
        TVtripDest.setText(getTripDest());
        TVdeptDate.setText(getDeptDate());
        TVdeptTime.setText(getDeptTime());
        TVarvDate.setText(getArvDate());
        TVarvTime.setText(getArvTime());
        TVcustName.setText(getName());
        TVcustUser.setText(getUsername());
        TVcustEmail.setText(getEmail());
        TVcompName.setText(getCompName());
        TVticket.setText(getTktID());
        TVcost.setText(getCostTotal());
        TVbankName.setText(getBankName());
        TVtransID.setText(getTransID());

        Button home = (Button) findViewById(R.id.bookTripBTN);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TripReservationDetails.this,Customer.class);
                startActivity(intent);
            }
        });
        int size = passList.size(),i=0;

        Log.i("helloo","xyz"+size);
        for (i = 0; i<size; i++){
            PassengerDetails temp = new PassengerDetails();
            temp = passList.get(i);

            psgrName= temp.getPsgrName();
            nationality = temp.getNationality();

            String psgrDetails = psgrName + ", " + nationality;
            Log.i("helloo","xyz"+psgrDetails);
            psgrString.add(psgrDetails);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, simple_expandable_list_item_1, psgrString);
        if (i == size)
            psgrList.setAdapter(arrayAdapter);
    }
}
