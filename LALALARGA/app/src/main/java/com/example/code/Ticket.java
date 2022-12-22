package com.example.code;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Ticket extends AppCompatActivity {
    public String DeptDate, TktID, CustUName, TypeOfTranspo;
    public Ticket (){}
    public Ticket(String deptDate, String tktID, String custUName, String typeOfTranspo){
        this. DeptDate = deptDate;
        this.TktID = tktID;
        this.CustUName = custUName;
        this.TypeOfTranspo = typeOfTranspo;
    }
    public String getDeptDate() {
        return DeptDate;
    }

    public String getTktID() {
        return TktID;
    }

    public String getCustUName() {
        return CustUName;
    }

    public String getTypeOfTranspo() {
        return TypeOfTranspo;
    }

    public void setDeptDate(String deptDate) {
        DeptDate = deptDate;
    }

    public void setTktID(String tktID) {
        TktID = tktID;
    }

    public void setCustUName(String custUName) {
        CustUName = custUName;
    }

    public void setTypeOfTranspo(String typeOfTranspo) {
        TypeOfTranspo = typeOfTranspo;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
