package com.example.code;

public class Bank {
    private String bankID,bankName;
    private Integer compBankNum,compNum;

    public Bank() {
    }

    public Bank(String bankID, String bankName, Integer compBankNum,Integer compNum) {
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

    public Integer getCompBankNum() {
        return compBankNum;
    }

    public void setCompBankNum(Integer compBankNum) {
        this.compBankNum = compBankNum;
    }

    public Integer getCompNum() {
        return compNum;
    }

    public void setCompNum(Integer compNum) {
        this.compNum = compNum;
    }
}
