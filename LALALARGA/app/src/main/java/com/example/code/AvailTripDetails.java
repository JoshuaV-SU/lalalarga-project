package com.example.code;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AvailTripDetails extends AvailTripsOneWay{
    public static String TripOrig, TripDest, DeptDate, DeptTime, ArvDate, ArvTime, CostTotal, VehType;
    public static String TripID, VehID, CompID, CompName;

    public static void setCompName(String compName) {        CompName = compName;    }

    public static String getCompName() {        return CompName;    }

    public static String getCompID() {        return CompID;    }

    public static void setCompID(String compID) {        CompID = compID;    }

    public static String getTripID() {
        return TripID;
    }

    public static String getVehID() {
        return VehID;
    }

    public static void setTripID(String tripID) {
        TripID = tripID;
    }

    public static void setVehID(String vehID) {
        VehID = vehID;
    }

    public AvailTripDetails() {    }

    public static String getTripOrig() {  return TripOrig;    }

    public static void setVehType(String vehType) {
        VehType = vehType;
    }

    public static String getVehType() {
        return VehType;
    }

    public static String getTripDest() {        return TripDest;    }

    public static String getDeptDate() {        return DeptDate;    }

    public static String getDeptTime() {        return DeptTime;    }

    public static String getArvDate() {        return ArvDate; }

    public static String getArvTime() {        return ArvTime;    }

    public static String getCostTotal() {        return CostTotal;    }

    public static void setTripOrig(String tripOrig) {        TripOrig = tripOrig;    }
    public static void setTripDest(String tripDest) {        TripDest = tripDest;    }

    public static void setDeptDate(String deptDate) {        DeptDate = deptDate;    }

    public static void setDeptTime(String deptTime) {        DeptTime = deptTime;    }

    public static void setArvDate(String arvDate) {        ArvDate = arvDate;    }

    public static void setArvTime(String arvTime) {        ArvTime = arvTime;    }

    public static void setCostTotal(String costTotal) {        CostTotal = costTotal;    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avail_trip_details);

        TextView destination = findViewById(R.id.tripDetDestTxtView);
        TextView origin = findViewById(R.id.tripDetOrigTxtView);
        TextView deptDate = findViewById(R.id.tripDeptDateTxtView);
        TextView deptTime = findViewById(R.id.tripDeptTimeTxtView);
        TextView arvDate = findViewById(R.id.tripArvDateTxtView);
        TextView arvTime = findViewById(R.id.tripArvTimeTxtView);
        TextView cost = findViewById(R.id.tripCostTxtView);
        TextView compName = findViewById(R.id.compNameTV);

        origin.setText(getTripOrig());
        destination.setText(getTripDest());
        deptDate.setText(getDeptDate());
        deptTime.setText(getDeptTime());
        arvDate.setText(getArvDate());
        arvTime.setText(getArvTime());
        cost.setText(getCostTotal());
        compName.setText(getCompName());

        Button proceed = (Button) findViewById(R.id.addPassBtn);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText ETname = findViewById(R.id.psgrNameET), ETuser = findViewById(R.id.custUserNameET), ETmail = findViewById(R.id.custEmailET);
                String name, user, email;

                name = ETname.getText().toString();
                user = ETuser.getText().toString();
                email = ETmail.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(user) || TextUtils.isEmpty(email))
                    Toast.makeText(AvailTripDetails.this, "All information should be provided", Toast.LENGTH_SHORT).show();
                else{
                   TripPaymentDetails addPsgrsPay = new TripPaymentDetails();
                    addPsgrsPay.setName(name);
                    addPsgrsPay.setUsername(user);
                    addPsgrsPay.setEmail(email);
                    addPsgrsPay.setVehType(getVehType());
                    addPsgrsPay.setTripID(getTripID());
                    addPsgrsPay.setVehID(getVehID());
                    addPsgrsPay.setCompID(getCompID());
                    addPsgrsPay.setCompName(getCompName());

                    PassengerDetails pass = new PassengerDetails();

                    pass.setTripOrig(origin.getText().toString());
                    pass.setTripDest(destination.getText().toString());
                    pass.setDeptDate(deptDate.getText().toString());
                    pass.setDeptTime(deptTime.getText().toString());
                    pass.setArvDate(arvDate.getText().toString());
                    pass.setArvTime(arvTime.getText().toString());
                    pass.setCostTotal(cost.getText().toString());
                    pass.setVehType(getVehType());
                    pass.setTripID(getTripID());
                    pass.setVehID(getVehID());
                    pass.setCompID(getCompID());
                    pass.setCompName(getCompName());

                    Intent intent = new Intent(AvailTripDetails.this,PassengerDetails.class);
                    startActivity(intent);

                    Log.i("rserveddd", pass.getTripOrig().toString());
                }

            }
        });
    }
}
