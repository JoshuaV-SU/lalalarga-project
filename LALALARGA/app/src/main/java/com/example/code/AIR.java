package com.example.code;

public class AIR {
    private String planeID, transType, compID;
    private int planeNum, planeSeatsCount;

    public AIR(){}

    public AIR(String planeID, String compID ,int planeNum, int planeSeatsCount) {
        this.planeID = planeID;
        this.transType = transType;
        this.compID = compID;
        this.planeNum = planeNum;
        this.planeSeatsCount = planeSeatsCount;
    }

    public String getCompID() { return compID;}

    public void setCompID(String compID) { this.compID = compID; }

    public String getPlaneID() {
        return planeID;
    }

    public void setPlaneID(String planeID) {
        this.planeID = planeID;
    }

    public int getPlaneNum() {
        return planeNum;
    }

    public void setPlaneNum(int planeNum) {
        this.planeNum = planeNum;
    }

    public int getPlaneSeatsCount() {
        return planeSeatsCount;
    }

    public void setPlaneSeatsCount(int planeSeatsCount) {
        this.planeSeatsCount = planeSeatsCount;
    }
}
