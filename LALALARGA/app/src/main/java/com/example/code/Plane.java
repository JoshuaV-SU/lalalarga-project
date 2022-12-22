package com.example.code;

public class Plane {
    String planeId, planeNum;
    int planeSeatsCount;

    public <T> Plane(T planeID, T planeNum, T planeSeatsNum) {
    }

    public String getPlaneId() {
        return planeId;
    }

    public String getPlaneNum() {
        return planeNum;
    }

    public int getPlaneSeatsCount() {
        return planeSeatsCount;
    }
}
