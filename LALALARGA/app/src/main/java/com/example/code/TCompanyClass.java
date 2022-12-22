package com.example.code;

public class TCompanyClass {
    String compId,compName;
    Integer compNum;

    public TCompanyClass() {
    }

    public TCompanyClass(String compId, String compName, Integer compNum) {
        this.compId = compId;
        this.compName = compName;
        this.compNum = compNum;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Integer getCompNum() {
        return compNum;
    }

    public void setCompNum(Integer compNum) {
        this.compNum = compNum;
    }
}
