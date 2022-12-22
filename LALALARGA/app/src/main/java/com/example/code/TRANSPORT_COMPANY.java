package com.example.code;

public class TRANSPORT_COMPANY {
    private String compName, transType, compID;
    int compNum;

    public TRANSPORT_COMPANY(){
    }

    public TRANSPORT_COMPANY(String compName, String transType, String compID, int compNum) {
        this.compName = compName;
        this.transType = transType;
        this.compID = compID;
        this.compNum = compNum;
    }

    public int getCompNum() { return compNum; }

    public void setCompNum(int compNum) { this.compNum = compNum; }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
    }
}
