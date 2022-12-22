package com.example.code;

import androidx.appcompat.app.AppCompatActivity;

public class TravelTktPaymentDetails extends AppCompatActivity {
    public TravelTktPaymentDetails(){};
    public String PayTransID;
    public Float TktPayment, SoloPrice;
    public TravelTktPaymentDetails (Float tktPayment, String PayTransID, Float SoloPrice){
        this.PayTransID = PayTransID;
        this.TktPayment = tktPayment;
        this.SoloPrice = SoloPrice;
    }

    public String getPayTransID() {
        return PayTransID;
    }

    public Float getTktPayment() {
        return TktPayment;
    }

    public void setPayTransID(String payTransID) {
        PayTransID = payTransID;
    }

    public void setTktPayment(Float tktPayment) {
        TktPayment = tktPayment;
    }
}
