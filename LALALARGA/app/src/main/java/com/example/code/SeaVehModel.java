package com.example.code;

import androidx.appcompat.app.AppCompatActivity;

public class SeaVehModel extends AppCompatActivity {
    public SeaVehModel(){}
    public String compID, ferryID, ferryNum, ferrySeatsCount;

    public void setCompID(String compID) {
        this.compID = compID;
    }

    public void setFerryID(String ferryID) {
        this.ferryID = ferryID;
    }

    public void setFerryNum(String ferryNum) {
        this.ferryNum = ferryNum;
    }

    public void setFerrySeatsCount(String ferrySeatsCount) {
        this.ferrySeatsCount = ferrySeatsCount;
    }

    public String getCompID() {
        return compID;
    }

    public String getFerryID() {
        return ferryID;
    }

    public String getFerryNum() {
        return ferryNum;
    }

    public String getFerrySeatsCount() {
        return ferrySeatsCount;
    }
}
