package com.example.code;

public class BankDetails {
    String bankID;
    String bankName;
    int compBankNum;
    int compNum;

    public BankDetails() {
    }

    public BankDetails(String bankID, String bankName, int compBankNum, int compNum) {
        this.bankID = bankID;
        this.bankName = bankName;
        this.compBankNum = compBankNum;
        this.compNum = compNum;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getCompBankNum() {
        return compBankNum;
    }

    public void setCompBankNum(int compBankNum) {
        this.compBankNum = compBankNum;
    }

    public int getCompNum() {
        return compNum;
    }

    public void setCompNum(int compNum) {
        this.compNum = compNum;
    }
}
