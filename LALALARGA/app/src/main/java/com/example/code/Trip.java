package com.example.code;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Trip extends AppCompatActivity {

    String TripId, DeptDate, DeptTime, ArvDate, ArvTime, VehType, VehID, SeatsAvailable, TktPrice, TripOrigin, TripDest;
    Button ETdeptDate,ETarvDate,ETdeptTime, ETarvTime;
    public static int IDcount;
    private String type, vID, deptDate, deptTime, arvDate, arvTime, strPrice, tripID, tripDest, tripOrigin;
    String seats = "0";
    private EditText ETvtype, ETvID, ETprice, ETtripID, ETdest, ETorigin;
    DatabaseReference TripReference;

    public String getTripOrigin() {
        return TripOrigin;
    }

    public void setTripOrigin(String tripOrigin) {
        TripOrigin = tripOrigin;
    }

    public void setTripDest(String tripDest) {
        TripDest = tripDest;
    }

    public String getTripDest() {
        return TripDest;
    }

    public String getTripId() {
        return TripId;
    }

    public String getDeptDate() {
        return DeptDate;
    }

    public String getDeptTime() {
        return DeptTime;
    }

    public String getArvDate() {
        return ArvDate;
    }

    public String getArvTime() {
        return ArvTime;
    }

    public String getVehType() {
        return VehType;
    }

    public String getVehID() {
        return VehID;
    }

    public String getSeatsAvailable() {
        return SeatsAvailable;
    }

    public String getTktPrice() {
        return TktPrice;
    }

    public void setTripId(String tripId) {
        TripId = tripId;
    }

    public void setDeptDate(String deptDate) {
        DeptDate = deptDate;
    }

    public void setDeptTime(String deptTime) {
        DeptTime = deptTime;
    }

    public void setArvDate(String arvDate) {
        ArvDate = arvDate;
    }

    public void setArvTime(String arvTime) {
        ArvTime = arvTime;
    }

    public void setVehType(String vehType) {
        VehType = vehType;
    }

    public void setVehID(String vehID) {
        VehID = vehID;
    }

    public void setSeatsAvailable(String seatsAvailable) {
        SeatsAvailable = seatsAvailable;
    }

    public void setTktPrice(String tktPrice) {
        TktPrice = tktPrice;
    }

    public Trip(String TripId, String DeptDate, String DeptTime, String ArvDate, String ArvTime, String VehType, String VehID, String SeatsAvailable, String Price, String TripOrigin, String TripDest) {
        this.TripId = TripId;
        this.VehType = VehType;
        this.VehID = VehID;
        this.DeptDate = DeptDate;
        this.DeptTime = DeptTime;
        this.ArvDate = ArvDate;
        this.ArvTime = ArvTime;
        this.SeatsAvailable = SeatsAvailable;
        this.TktPrice = Price;
        this.TripOrigin = TripOrigin;
        this.TripDest = TripDest;
    }

    public Trip() {
    }

    private DatePickerDialog datePickerDialog;
    private Button deptDateBtn,arrDateBtn,deptTimeBtn, arvTimeBtn;
    private String finalDate;
    private int chckdept = 0, chkarv = 0, hr, min, chkDeptTime = 0, chkArvTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trip);

        initDatePicker();
        deptDateBtn =findViewById(R.id.deptDate);
        deptDateBtn.setText(getTodayDate());
        deptTimeBtn = findViewById(R.id.deptTime);

        arrDateBtn = findViewById(R.id.arrDate);
        arrDateBtn.setText(getTodayDate());
        arvTimeBtn = findViewById(R.id.arrTime);

        Spinner typeSpinner = (Spinner) findViewById(R.id.vehTypeSpin);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(Trip.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.trip_type));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);


        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        ImageView back = (ImageView) findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Trip.this, TripList.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(Trip.this);
                alertbox.setTitle("Add Trip");
                alertbox.setMessage("Add Trip? ");

                type = typeSpinner.getSelectedItem().toString();
                ETtripID = findViewById(R.id.tripID);
                ETvID = findViewById(R.id.vehID);
                ETdeptDate = (Button) findViewById(R.id.deptDate);
                ETdeptTime = (Button)findViewById(R.id.deptTime);
                ETarvDate = (Button)findViewById(R.id.arrDate);
                ETarvTime = (Button)findViewById(R.id.arrTime);
                ETprice = findViewById(R.id.tktPrice);
                ETdest = findViewById(R.id.tripDest);
                ETorigin = findViewById(R.id.tripOrig);

                tripID = ETtripID.getText().toString();
                tripID = "TR" + tripID;
                vID = ETvID.getText().toString();
                deptDate = ETdeptDate.getText().toString();
                deptTime = ETdeptTime.getText().toString();
                arvDate = ETarvDate.getText().toString();
                arvTime = ETarvTime.getText().toString();
                strPrice = ETprice.getText().toString();
                tripDest = ETdest.getText().toString();
                tripOrigin = ETorigin.getText().toString();

                if ((tripID.length()==0) || (vID.length()==0) || (deptDate.length()==0) || (deptTime.length()==0)
                    || (arvDate.length()==0) || (arvTime.length()==0) || (strPrice.length()==0)
                    || (tripDest.length()==0)  || (tripOrigin.length()==0)) {
                    Toast.makeText(Trip.this, "All boxes should be filled up", Toast.LENGTH_LONG).show();
                }
                else{
                    alertbox.setPositiveButton("Add",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {


                                    if (type.equals("Plane")) {
                                        seats = "150";
                                    } else if (type.equals("Cruise")) {
                                        seats = "200";
                                    }
                                    Log.i("here", "VID=" + vID);
                                    TripReference = FirebaseDatabase.getInstance("https://lalalarga-6569a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("TRIP");

                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("TripId", tripID);
                                    updates.put("DeptDate", deptDate);
                                    updates.put("DeptTime", deptTime);
                                    updates.put("ArvDate", arvDate);
                                    updates.put("ArvTime", arvTime);
                                    updates.put("VehType", type);
                                    updates.put("VehID", vID);
                                    updates.put("SeatsAvailable", seats);
                                    updates.put("TktPrice", strPrice);
                                    updates.put("TripOrigin", tripOrigin);
                                    updates.put("TripDest",tripDest);
                                    TripReference = TripReference.child(tripID);
                                    DatabaseReference finalCustReference1 = TripReference;
                                    finalCustReference1.updateChildren(updates);

                                    Toast.makeText(Trip.this, "Trip added successfully", Toast.LENGTH_LONG).show();
                                    clearText();
                                }
                            });

                    alertbox.setNeutralButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                }
                            });

                    alertbox.show();
                }
            }

        });


    }

    private String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        month = month+1;
        int day = calendar.get(calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                finalDate = makeDateString(day, month, year);
                if (chkarv == 1){
                    arrDateBtn.setText(finalDate);
                    chkarv = 0;
                }
                else if (chckdept == 1){
                    deptDateBtn.setText(finalDate);
                    chckdept = 0;
                }
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dataSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        String monthString = " ",date;
        if (month==1)
            monthString = "JAN";
        if (month==2)
            monthString = "FEB";
        if (month==3)
            monthString = "MAR";
        if (month==4)
            monthString = "APR";
        if (month==5)
            monthString = "MAY";
        if (month==6)
            monthString = "JUN";
        if (month==7)
            monthString = "JUL";
        if (month==8)
            monthString = "AUG";
        if (month==9)
            monthString = "SEP";
        if (month==10)
            monthString = "OCT";
        if (month==11)
            monthString = "NOV";
        if (month==12)
            monthString = "DEC";

        date = monthString + " " + day + " " + year;
        return date;

    }

    public void clearText() {
        ETtripID = findViewById(R.id.tripID);
        ETtripID.setText("");
        ETvID = findViewById(R.id.vehID);
        ETvID.setText("");
        ETdeptDate = (Button) findViewById(R.id.deptDate);
        ETdeptDate.setText("");
        ETdeptTime = (Button)findViewById(R.id.deptTime);
        ETdeptTime.setText("");
        ETarvDate = (Button) findViewById(R.id.arrDate);
        ETarvDate.setText("");
        ETarvTime = (Button)findViewById(R.id.arrTime);
        ETarvTime.setText("");
        ETprice = findViewById(R.id.tktPrice);
        ETprice.setText("");
        ETdest = findViewById(R.id.tripDest);
        ETdest.setText("");
        ETorigin = findViewById(R.id.tripOrig);
        ETorigin.setText("");
    }

    public void opendeptDatePicker(View view){
        chckdept =1;
        datePickerDialog.show();

    }
    public void openarvDatePicker(View view){
        chkarv = 1;
        datePickerDialog.show();
    }

    public void opendeptTimePicker(View view){
        chkDeptTime = 1;
        popTimePicker(view);
    }

    public void openarvTimePicker(View view){
        chkArvTime = 1;
        popTimePicker(view);
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHr, int selectedMin) {
                hr = selectedHr;
                min = selectedMin;
                if (chkDeptTime == 1){
                    deptTimeBtn.setText(String.format(Locale.getDefault(),"%02d %02d",hr,min));
                    chkDeptTime = 0;
                }
                else if (chkArvTime == 1){
                    arvTimeBtn.setText(String.format(Locale.getDefault(),"%02d %02d",hr,min));
                    chkArvTime = 0;
                }

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,hr,min,true);
        timePickerDialog.show();
    }
}

//
// }
