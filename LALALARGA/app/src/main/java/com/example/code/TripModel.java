package com.example.code;

public class TripModel {
    String TripId, DeptDate, DeptTime, ArvDate, ArvTime, VehType, VehID, SeatsAvailable, TktPrice, TripOrigin, TripDest;

    public TripModel(String tripId, String deptDate, String deptTime, String arvDate, String arvTime, String vehType, String vehID, String seatsAvailable, String tktPrice,String tripOrigin, String tripDest) {
        TripId = tripId;
        DeptDate = deptDate;
        DeptTime = deptTime;
        ArvDate = arvDate;
        ArvTime = arvTime;
        VehType = vehType;
        VehID = vehID;
        SeatsAvailable = seatsAvailable;
        TktPrice = tktPrice;
        TripOrigin = tripOrigin;
        TripDest = tripDest;
    }
    TripModel(){}

    public void setTripOrigin(String tripOrigin) {
        TripOrigin = tripOrigin;
    }

    public void setTripDest(String tripDest) {
        TripDest = tripDest;
    }

    public String getTripOrigin() {
        return TripOrigin;
    }

    public String getTripDest() {
        return TripDest;
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
}
