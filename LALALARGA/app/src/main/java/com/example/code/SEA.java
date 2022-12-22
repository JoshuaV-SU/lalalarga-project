package com.example.code;

public class SEA {
    private String ferryID, transType, compID;
    private int ferryNum, ferrySeatsCount;

    public SEA() {}

    public SEA(String ferryID, String compID, int ferryNum, int ferrySeatsCount) {
        this.ferryID = ferryID;
        this.compID = compID;
        this.ferryNum = ferryNum;
        this.ferrySeatsCount = ferrySeatsCount;
    }

    public String getCompID() { return compID; }

    public void setCompID(String compID) { this.compID = compID; }

    public String getFerryID() {
        return ferryID;
    }

    public void setFerryID(String ferryID) {
        this.ferryID = ferryID;
    }

    public int getFerryNum() {
        return ferryNum;
    }

    public void setFerryNum(int ferryNum) {
        this.ferryNum = ferryNum;
    }

    public int getFerrySeatsCount() {
        return ferrySeatsCount;
    }

    public void setFerrySeatsCount(int ferrySeatsCount) {
        this.ferrySeatsCount = ferrySeatsCount;
    }
}
