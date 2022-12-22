package com.example.code;

public class BankDetailsClass {
    String BankID,BankName;
    Integer CompNum,CompBankNum;
    String CompNum1,CompBankNum1;

    public BankDetailsClass() {
    }

    public BankDetailsClass(String BnkID, String BnkName, Integer CmpBankNum, Integer CmpNum) {
        BankID = BnkID;
        BankName = BnkName;
        CompBankNum = CmpBankNum;
        CompNum = CmpNum;
    }

    public String getBankID() {
        return BankID;
    }

    public void setBankID(String bankID) {
        BankID = bankID;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public Integer getCompNum() {
        return CompNum;
    }

    public void setCompNum(Integer compNum) {
        CompNum = compNum;
    }

    public Integer getCompBankNum() {
        return CompBankNum;
    }

    public void setCompBankNum(Integer compBankNum) {
        CompBankNum = compBankNum;
    }

    public String getCompNum1() {
        return CompNum1;
    }

    public void setCompNum1(String compNum1) {
        CompNum1 = compNum1;
    }

    public String getCompBankNum1() {
        return CompBankNum1;
    }

    public void setCompBankNum1(String compBankNum1) {
        CompBankNum1 = compBankNum1;
    }
}

