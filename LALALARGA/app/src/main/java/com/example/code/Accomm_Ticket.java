package com.example.code;

public class Accomm_Ticket {

    public String AcmTransID, AcmID, Dest, GuestNum, AcmTktPayment;

    public Accomm_Ticket(String acmTransID, String acmID, String dest, String guestNum, String acmTktPayment) {
        this.AcmTransID = acmTransID;
        this.AcmID = acmID;
        this.Dest = dest;
        this.GuestNum = guestNum;
        this.AcmTktPayment = acmTktPayment;
    }
}
