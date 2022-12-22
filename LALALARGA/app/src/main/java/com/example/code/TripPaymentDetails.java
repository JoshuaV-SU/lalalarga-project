package com.example.code;

import static android.R.layout.simple_expandable_list_item_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TripPaymentDetails extends AppCompatActivity {
    public TripPaymentDetails() {
    }

    public static void setPassList(ArrayList<PassengerDetails> passList) {
        TripPaymentDetails.passList = passList;
    }

    public static String getName() {
        return name;
    }

    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }

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

    public static void setName(String name) {
        TripPaymentDetails.name = name;
    }

    public static ArrayList<PassengerDetails> getPassList() {
        return passList;
    }

    public static void setUsername(String username) {
        TripPaymentDetails.username = username;
    }

    public static void setEmail(String email) {
        TripPaymentDetails.email = email;
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

    public static void setVehType(String vehType) {
        TripPaymentDetails.vehType = vehType;
    }

    public static String getVehType() {
        return vehType;
    }

    public static void setCounter(long counter) {
        TripPaymentDetails.counter = counter;
    }

    public static long getCounter() {
        return counter;
    }

    public static void setArvTime(String arvTime) {
        ArvTime = arvTime;
    }

    public static void setCostTotal(String costTotal) {
        CostTotal = costTotal;
    }

    public static void setTrvlcounter(long trvlcounter) {
        TripPaymentDetails.trvlcounter = trvlcounter;
    }

    public static long getTrvlcounter() {
        return trvlcounter;
    }

    public static String getVehID() {
        return vehID;
    }

    public static String getTripID() {
        return tripID;
    }

    public static void setVehID(String vehID) {
        TripPaymentDetails.vehID = vehID;
    }

    public static void setTripID(String tripID) {
        TripPaymentDetails.tripID = tripID;
    }

    public static ArrayList<PassengerDetails> passList ;
    public static String name, username, email, vehType;
    public static String TripOrig, TripDest, DeptDate, DeptTime, ArvDate, ArvTime, CostTotal;
    private DatabaseReference databaseReference;
    private TextView TVtripOG, TVtripDest, TVdeptDate, TVdeptTime, TVarvDate, TVarvTime, TVcustName, TVcustUser,TVcustEmail, TVcost, TVcompName;
    private static long counter = 0, trvlcounter = 0;
    public static String vehID, tripID;
    private static String compName, compID;

    public static String getCompName() {
        return compName;
    }

    public static String getCompID() {
        return compID;
    }

    public static void setCompName(String compName) {
        TripPaymentDetails.compName = compName;
    }

    public static void setCompID(String compID) {
        TripPaymentDetails.compID = compID;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avail_trip_payment);

        //Log.i("click","PayJH" + getTripDest().toString());
        String psgrName, nationality;
        ListView psgrList = findViewById(R.id.listPsgrs);
        ListView bankList = findViewById(R.id.bankkkList);
        ArrayList<String> psgrString = new ArrayList<String>();
        ArrayList<String> bankArray = new ArrayList<String>();
        Button bookTrip = findViewById(R.id.bookTripBTN);

        TripPaymentDetails trip = new TripPaymentDetails();

        int size = passList.size(),i=0;


        TVtripOG = (TextView) findViewById(R.id.TVdept);
        TVtripDest = (TextView) findViewById(R.id.TVdest);
        TVdeptDate = (TextView) findViewById(R.id.TVdeptDate);
        TVdeptTime = (TextView) findViewById(R.id.TVdeptTime);
        TVarvDate = (TextView) findViewById(R.id.TVdestDate);
        TVarvTime = (TextView) findViewById(R.id.TVdestTime);
        TVcustName = (TextView) findViewById(R.id.TVcustName);
        TVcustUser = (TextView) findViewById(R.id.TVcustUser);
        TVcustEmail = (TextView) findViewById(R.id.TVcustMail);
        TVcost = (TextView) findViewById(R.id.tripCostTxtView2);
        TVcompName = (TextView) findViewById(R.id.TVCompName);

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
        String tempCost= getCostTotal();


        for (i = 0; i<size; i++){
            PassengerDetails temp = new PassengerDetails();
            temp = passList.get(i);

            psgrName= temp.getPsgrName();
            nationality = temp.getNationality();

            String psgrDetails = psgrName + ", " + nationality;

            psgrString.add(psgrDetails);
        }

        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<String>(this, simple_expandable_list_item_1, psgrString);
        if (i == size)
            psgrList.setAdapter(arrayAdapter);
        ArrayAdapter <String> bkAdapter = new ArrayAdapter<String>(this, simple_expandable_list_item_1, bankArray);

        databaseReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("BANK");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Bank data = ds.getValue(Bank.class);
                    String bankName;
                    int bankNum;
                    bankNum = data.getCompBankNum();
                    bankName = data.getBankName();

                    String toAdd = bankName + " : " + bankNum;
                    Log.i("bank","bkk"+toAdd);
                    bankArray.add(toAdd);

                    bankList.setAdapter(bkAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        bookTrip.setOnClickListener(new View.OnClickListener() {
                       @Override
            public void onClick(View view) {
                           final int[] check = {0};
                   databaseReference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            EditText bankName, transID;
                            String bnkName, transIDstr;
                            bankName = findViewById(R.id.ETbankName);
                            transID = findViewById(R.id.ETtransID);

                            bnkName = bankName.getText().toString();
                            transIDstr = transID.getText().toString();
                            if (TextUtils.isEmpty(bnkName) || TextUtils.isEmpty(transIDstr))
                                Toast.makeText(TripPaymentDetails.this, "All boxes should be filled up", Toast.LENGTH_SHORT).show();
                            else{
                                for(DataSnapshot ds:dataSnapshot.getChildren()){
                                    Bank data = ds.getValue(Bank.class);
                                    String bankStr;
                                    bankStr = data.getBankName();

                                    if (bankStr.equals(bnkName)){
                                        check[0] = 1;
                                        Log.i("kbb",bankStr + ", "+ bnkName);
                                        break;
                                    }
                                }
                                if (check[0] == 1){
                                    //Toast.makeText(TripPaymentDetails.this, "Good", Toast.LENGTH_SHORT).show();
                                    addTripReservation();
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });


                //}
            }
        });

    }
    private void addTripReservation(){
        TripPaymentDetails temp = new TripPaymentDetails();
        final DatabaseReference[] ticketRef = new DatabaseReference[1];
        final DatabaseReference[] travelRef = new DatabaseReference[1];
        String deptDate;

        String custUserNameTkt;
        String type;
        deptDate = TVdeptDate.getText().toString();
        custUserNameTkt = getUsername();
        type = getVehType();

        TravelTicketDetails tempTick = new TravelTicketDetails();
        Query query = ticketRef[0] = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TICKET");

        ValueEventListener eventListener = new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();

                String ticketID = "";
                count+=1;

                temp.setCounter(count);
                ticketID = "TKT" + count;
                Log.i("record","rcrdedCount"+ ticketID);

                tempTick.setTktID(ticketID);

                ticketRef[0] = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TICKET");
                Map<String, Object> updates = new HashMap<>();
                updates.put("DeptDate", deptDate);
                updates.put("TktID", ticketID);
                updates.put("CustUName", custUserNameTkt);
                updates.put("TypeOfTranspo", type);

                ticketRef[0] = ticketRef[0].child(ticketID);
                DatabaseReference finalTickRef = ticketRef[0];
                finalTickRef.updateChildren(updates);


                travelRef[0] = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRAVEL_TICKET");
                Query travQuery = travelRef[0];
                String finalTicketID = ticketID;

                String finalTicketID1 = ticketID;
                ValueEventListener eventListener1 = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String tktPayment, psgrName, psgrGender, psgrNationality, psgrBDate, seatClass, tripOrigin, tripDest, transType, tripID;
                        String compID, compName;
                        String payTransID;

                        seatClass = "Economy";

                        tripOrigin = getTripOrig();
                        tripDest = getTripDest();
                        transType = getVehType();
                        tripID = getTripID();
                        compID = getCompID();
                        compName = getCompName();
                        EditText transID = findViewById(R.id.ETtransID);
                        payTransID= transID.getText().toString();

                        int size = passList.size();
                        Float total = Float.valueOf(getCostTotal());
                        total = total * size;
                        for (int i=0; i<size; i++){
                            long count = snapshot.getChildrenCount();
                            count+=1;
                            if (i!=0)
                                count+=1;
                            setCounter(count);
                            String travelTransID = "TRVL" + count;

                            PassengerDetails temp1 = new PassengerDetails();
                            temp1 = passList.get(i);

                            psgrName= temp1.getPsgrName();
                            psgrGender = temp1.getGender();
                            psgrNationality = temp1.getNationality();
                            psgrBDate = temp1.getBday();



                            if (i==0){
                                Map<String, Object> updates = new HashMap<>();

                                updates.put("TravelTransID", travelTransID);
                                updates.put("TktID", finalTicketID);
                                updates.put("TktPayment", total);
                                updates.put("PsgrName", psgrName);
                                updates.put("PsgrGender", psgrGender);
                                updates.put("PsgrNationality", psgrNationality);
                                updates.put("PsgrBDate", psgrBDate);
                                updates.put("SeatClass", seatClass);
                                updates.put("TripOrigin", tripOrigin);
                                updates.put("TripDest", tripDest);
                                updates.put("TransType", transType);
                                updates.put("TripID", tripID);
                                updates.put("CompID", compID);
                                updates.put("CompName", compName);
                                updates.put("PayTransID", payTransID);


                                if (snapshot.exists()){
                                    travelRef[0] = travelRef[0].child(travelTransID);
                                    Log.i("yo","it exists"+travelTransID);
                                }
                                DatabaseReference finalTickRef = travelRef[0];
                                finalTickRef.updateChildren(updates);
                            }

                            else if (i!=0){
                                Map<String, Object> updates = new HashMap<>();

                                updates.put("TravelTransID", travelTransID);
                                updates.put("TktID", finalTicketID);
                                updates.put("TktPayment", total);
                                updates.put("PsgrName", psgrName);
                                updates.put("PsgrGender", psgrGender);
                                updates.put("PsgrNationality", psgrNationality);
                                updates.put("PsgrBDate", psgrBDate);
                                updates.put("SeatClass", seatClass);
                                updates.put("TripOrigin", tripOrigin);
                                updates.put("TripDest", tripDest);
                                updates.put("TransType", transType);
                                updates.put("TripID", tripID);
                                updates.put("CompID", compID);
                                updates.put("CompName", compName);
                                updates.put("PayTransID", payTransID);

                                travelRef[0] = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRAVEL_TICKET").child(travelTransID);


                                DatabaseReference finalTickRef = travelRef[0];
                                finalTickRef.updateChildren(updates);
                            }
                       }//end of for loop

                    DatabaseReference payRef = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRAVEL_TKT_PAYMENT");


                        Map<String, Object> updates = new HashMap<>();

                        updates.put("TktPayment", total);
                        updates.put("PayTransID", payTransID);
                        updates.put("SoloPrice", Float.valueOf(getCostTotal()));


                        if (snapshot.exists()){
                            payRef = payRef.child(payTransID);
                            Log.i("yo","pay exists"+payTransID);
                        }
                        DatabaseReference finalPayRef = payRef;
                        payRef.updateChildren(updates);


                        TripReservationDetails finalTrip = new TripReservationDetails();

                        EditText ETbankName = findViewById(R.id.ETbankName);
                        String finbnkName = ETbankName.getText().toString();

                        finalTrip.setName(getName());
                        finalTrip.setUsername(getUsername());
                        finalTrip.setEmail(getEmail());
                        finalTrip.setTripOrig(getTripOrig());
                        finalTrip.setTripDest(getTripDest());
                        finalTrip.setDeptDate(getDeptDate());
                        finalTrip.setDeptTime(getDeptTime());
                        finalTrip.setArvDate(getArvDate());
                        finalTrip.setArvTime(getArvTime());
                        finalTrip.setBankName(finbnkName);
                        finalTrip.setTransID(payTransID);
                        finalTrip.setTktID(finalTicketID1);
                        finalTrip.setCostTotal(total.toString());
                        finalTrip.setCompName(getCompName());

                        Log.i("size","sizehere"+size);

                        Intent intent = new Intent(TripPaymentDetails.this,TripReservationDetails.class);
                        startActivity(intent);

                    }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        };

                };travQuery.addListenerForSingleValueEvent(eventListener1);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);


    }

}