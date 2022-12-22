package com.example.code;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TravelTicketDetails extends AppCompatActivity {
    public TravelTicketDetails (){}
    public String TravelTransID, TktID, PsgrName, PsgrGender, PsgrNationality, PsgrBDate, SeatClass,
            TripOrigin, TripDest, TransType, TripID, CompID, CompName, PayTransID;
    public Float TktPayment;
    public TravelTicketDetails(String TravelTransID, String TktID, Float TktPayment, String PsgrName,
                               String PsgrGender, String PsgrNationality, String PsgrBDate,
                               String SeatClass, String TripOrigin, String TripDest, String TransType, String TripID,
                               String CompID, String CompName, String PayTransID){
        this.TravelTransID = TravelTransID;
        this.TktID = TktID;
        this.TktPayment = TktPayment;
        this.PsgrGender = PsgrGender;
        this.PsgrName = PsgrName;
        this.PsgrNationality = PsgrNationality;
        this.PsgrBDate = PsgrBDate;
        this.SeatClass = SeatClass;
        this.TripOrigin = TripOrigin;
        this.TripDest = TripDest;
        this.TransType = TransType;
        this.TripID = TripID;
        this.CompID = CompID;
        this.CompName = CompName;
        this.PayTransID = PayTransID;
    }
    public String getTravelTransID() {
        return TravelTransID;
    }

    public String getTktID() {
        return TktID;
    }

    public Float getTktPayment() {
        return TktPayment;
    }

    public String getPsgrName() {
        return PsgrName;
    }

    public String getPsgrGender() {
        return PsgrGender;
    }

    public String getPsgrNationality() {
        return PsgrNationality;
    }

    public String getPsgrBDate() {
        return PsgrBDate;
    }


    public String getSeatClass() {
        return SeatClass;
    }

    public String getTripOrigin() {
        return TripOrigin;
    }

    public String getTripDest() {
        return TripDest;
    }

    public String getTransType() {
        return TransType;
    }

    public String getTripID() {
        return TripID;
    }

    public String getCompID() {
        return CompID;
    }

    public String getCompName() {
        return CompName;
    }

    public String getPayTransID() {
        return PayTransID;
    }

    public void setTravelTransID(String travelTransID) {
        TravelTransID = travelTransID;
    }

    public void setTktID(String tktID) {
        TktID = tktID;
    }

    public void setTktPayment(Float tktPayment) {
        TktPayment = tktPayment;
    }

    public void setPsgrName(String psgrName) {
        PsgrName = psgrName;
    }

    public void setPsgrGender(String psgrGender) {
        PsgrGender = psgrGender;
    }

    public void setPsgrNationality(String psgrNationality) {
        PsgrNationality = psgrNationality;
    }

    public void setPsgrBDate(String psgrBDate) {
        PsgrBDate = psgrBDate;
    }

    public void setSeatClass(String seatNum) {
        SeatClass = seatNum;
    }

    public void setTripOrigin(String tripOrigin) {
        TripOrigin = tripOrigin;
    }

    public void setTripDest(String tripDest) {
        TripDest = tripDest;
    }

    public void setTransType(String transType) {
        TransType = transType;
    }

    public void setTripID(String tripID) {
        TripID = tripID;
    }

    public void setCompID(String compID) {
        CompID = compID;
    }

    public void setCompName(String compName) {
        CompName = compName;
    }

    public void setPayTransID(String payTransID) {
        PayTransID = payTransID;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
