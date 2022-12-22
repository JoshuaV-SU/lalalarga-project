package com.example.code;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class PassengerDetails extends AppCompatActivity {
    String psgrName, gender, nationality, bday;
    public static String TripOrig, TripDest, DeptDate, DeptTime, ArvDate, ArvTime, CostTotal, VehType;
    public static String TripID, VehID, CompID, CompName;

    public static String getCompID() {
        return CompID;
    }

    public static String getCompName() {
        return CompName;
    }

    public static void setCompID(String compID) {
        CompID = compID;
    }

    public static void setCompName(String compName) {
        CompName = compName;
    }

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

    public static String getTripOrig() {        return TripOrig;    }

    public static String getTripDest() {        return TripDest;    }

    public static String getDeptDate() {        return DeptDate;    }

    public static void setVehType(String vehType) {
        VehType = vehType;
    }

    public static String getVehType() {
        return VehType;
    }

    public static String getDeptTime() {        return DeptTime;    }

    public static String getArvDate() {        return ArvDate;    }

    public static String getArvTime() {        return ArvTime;    }

    public static String getCostTotal() {        return CostTotal;    }

    public static void setTripOrig(String tripOrig) {        TripOrig = tripOrig;    }

    public static void setTripDest(String tripDest) {        TripDest = tripDest;    }

    public static void setDeptDate(String deptDate) {        DeptDate = deptDate;    }

    public static void setDeptTime(String deptTime) {        DeptTime = deptTime;    }

    public static void setArvDate(String arvDate) {        ArvDate = arvDate;    }

    public static void setArvTime(String arvTime) {        ArvTime = arvTime;    }

    public static void setCostTotal(String costTotal) {        CostTotal = costTotal;    }

    private PassengerDetails(String name, String gender, String nationality, String bday){
        this.psgrName = name;
        this.gender = gender;
        this.nationality = nationality;
        this.bday = bday;
    }
    public void setPsgrName(String psgrName) {
        this.psgrName = psgrName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public String getPsgrName() {
        return psgrName;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getBday() {
        return bday;
    }

    public PassengerDetails(){};

    Button bdayBtn, addPass, proceedBtn;
    private DatePickerDialog datePickerDialog;
    ArrayList <PassengerDetails> passList;
    private TextView TVtripOG, TVtripDest, TVdeptDate, TVdeptTime, TVarvDate, TVarvTime, TVcost, TVcompName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avail_psgr_details);

        bdayBtn =(Button) findViewById(R.id.bdayBtn);
        addPass = (Button) findViewById(R.id.addPassBtn) ;
        proceedBtn = (Button) findViewById(R.id.procBtn) ;

        TVtripOG = (TextView) findViewById(R.id.tripDetOrigTxtView);
        TVtripDest = (TextView) findViewById(R.id.tripDetDestTxtView);
        TVdeptDate = (TextView) findViewById(R.id.tripDeptDateTxtView);
        TVdeptTime = (TextView) findViewById(R.id.tripDeptTimeTxtView);
        TVarvDate = (TextView) findViewById(R.id.tripArvDateTxtView);
        TVarvTime = (TextView) findViewById(R.id.tripArvTimeTxtView);
        TVcost = (TextView) findViewById(R.id.tripCostTxtView);
        TVcompName = (TextView) findViewById(R.id.compNameTV);

        TVtripOG.setText(getTripOrig());
        TVtripDest.setText(getTripDest());
        TVdeptDate.setText(getDeptDate());
        TVdeptTime.setText(getDeptTime());
        TVarvDate.setText(getArvDate());
        TVarvTime.setText(getArvTime());
        TVcost.setText(getCostTotal());
        TVcompName.setText(getCompName());

        Spinner typeSpinner = (Spinner) findViewById(R.id.genderSpin);

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(PassengerDetails.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        initDatePicker();
        passList = new ArrayList<PassengerDetails>();
        bdayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog.show();
            }
        });



        addPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = typeSpinner.getSelectedItem().toString();
                add(gender);

            }
        });

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender = typeSpinner.getSelectedItem().toString();
                add(gender);


                TripPaymentDetails addPsgrsPay = new TripPaymentDetails();

                addPsgrsPay.setPassList(passList);
                addPsgrsPay.setTripOrig(TVtripOG.getText().toString());
                addPsgrsPay.setTripDest(getTripDest());
                addPsgrsPay.setDeptDate(getDeptDate());
                addPsgrsPay.setDeptTime(getDeptTime());
                addPsgrsPay.setArvDate(getArvDate());
                addPsgrsPay.setArvTime(getArvTime());
                addPsgrsPay.setCostTotal(getCostTotal());

                TripReservationDetails addRes = new TripReservationDetails();

                addRes.setPassList(passList);

                   //Log.i("pay","ClickJH"+addPsgrsPay.getTripOrig().toString());
                Intent intent = new Intent(PassengerDetails.this,TripPaymentDetails.class);
                startActivity(intent);
            }
        });

    }
    private void add (String genderSelected){
        String name, gender, nationality, bday;
        EditText nameET, nationalityET;
        nameET = findViewById(R.id.psgrNameET);
        nationalityET =findViewById(R.id.nationalityET);
        name = nameET.getText().toString();
        nationality = nationalityET.getText().toString();
        bday = bdayBtn.getText().toString();
        gender = genderSelected.toString();

        PassengerDetails newPsgr = new PassengerDetails(name, gender,nationality,bday);
        passList.add(newPsgr);
        nameET.setText("");
        nationalityET.setText("");
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
                String date = makeDateString(day, month, year);
                bdayBtn.setText(date);
            }
            //}
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

}
