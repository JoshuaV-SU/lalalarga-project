package com.example.code;

import androidx.appcompat.app.AppCompatActivity;

public class TransportCompanyModel extends AppCompatActivity {
    TransportCompanyModel(){}

    public void setCompID(String compID) {
        this.compID = compID;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String compID, compName, transType;

    public String getCompID() {
        return compID;
    }

    public String getCompName() {
        return compName;
    }

    public String getTransType() {
        return transType;
    }
}
