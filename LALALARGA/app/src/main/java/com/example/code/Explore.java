package com.example.code;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Explore extends Customer {

    ImageView arrow;
    TextView retTitle;
    Button retBtn, setDeptDateBtn;
    EditText orig, dest;

    int plane=0,cruise=0;

    public static String FName, FEmail, FPword, FUser, FBday;
    public static String custUser;
    public Explore (String fbday, String fname, String femail, String user){
        Account.setFName(fname);
        Account.setFUser(user);
        Account.setFEmail(femail);
        Account.setFBday(fbday);
    }

    public Explore() {

    }

    public static void setCustUser(String user) {
        custUser = user;
    }

    protected String travelOrig, travelDest, travelDate;
    private DatePickerDialog datePickerDialog;
    String finalDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explore_set_date);

        arrow = (ImageView) findViewById(R.id.arrow);
        arrow.setVisibility(View.INVISIBLE);

        retTitle = (TextView) findViewById(R.id.returnTitle);
        retTitle.setVisibility(View.INVISIBLE);

        retBtn = (Button) findViewById(R.id.returnDateTxtView);
        retBtn.setVisibility(View.INVISIBLE);
        setDeptDateBtn = (Button)findViewById(R.id.deptDateSetTxtView);

        orig = (EditText) findViewById(R.id.originTxtView) ;
        dest = (EditText) findViewById(R.id.tripdestTxtView) ;
        initDatePicker();
    }

    public void cruiseClick (View view){
        cruise = 1;
        plane = 0;
        Button btnCruise, btnPlane;
        btnCruise = (Button) findViewById(R.id.btnCruise);
        btnPlane = (Button) findViewById(R.id.btnPlane);

        btnCruise.setBackgroundColor(Color.rgb(226, 11, 11));
        btnPlane.setBackgroundColor(Color.rgb(169,169,169));
    }

    public void planeClick (View view){
        cruise = 0;
        plane = 1;
        Button btnCruise, btnPlane;
        btnCruise = (Button) findViewById(R.id.btnCruise);
        btnPlane = (Button) findViewById(R.id.btnPlane);

        btnPlane.setBackgroundColor(Color.rgb(226, 11, 11));
        btnCruise.setBackgroundColor(Color.rgb(169,169,169));
    }

    public void btnSearch (View view){
        travelOrig = orig.getText().toString();
        travelDest = dest.getText().toString();
        travelDate = setDeptDateBtn.getText().toString();
        Log.i("travel date",travelDate);
        AvailTripsOneWay availThis = new AvailTripsOneWay();
        availThis.setTravelOrig(travelOrig);
        availThis.setTravelDest(travelDest);
        availThis.setTravelDate(travelDate);

        String vehicle = "";
        if (plane == 1){
            vehicle = "Plane";
        }
        else if (cruise == 1)
            vehicle = "Cruise";

        if(TextUtils.isEmpty(vehicle))
            vehicle="Plane";
        availThis.setVehType(vehicle);
        Log.i("deets","deets1"+vehicle);

        startActivity(new Intent(Explore.this, AvailTripsOneWay.class));
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
                Log.i("hello", finalDate);
                setDeptDateBtn.setText(finalDate);
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

    public void setDeptDate(View view){
        //chckdept =1;
        Log.i("hello", "comment");
        datePickerDialog.show();

    }
}
