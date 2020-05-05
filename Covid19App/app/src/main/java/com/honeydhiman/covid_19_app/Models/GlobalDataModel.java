package com.honeydhiman.covid_19_app.Models;

public class GlobalDataModel {

    private String CasesName;
    private String CasesValue;

    public GlobalDataModel(String casesName, String casesValue) {
        CasesName = casesName;
        CasesValue = casesValue;
    }

    public String getCasesName() {
        return CasesName;
    }

    public void setCasesName(String casesName) {
        CasesName = casesName;
    }

    public String getCasesValue() {
        return CasesValue;
    }

    public void setCasesValue(String casesValue) {
        CasesValue = casesValue;
    }

    @Override
    public String toString() {
        return "GlobalDataModel{" +
                "CasesName='" + CasesName + '\'' +
                ", CasesValue='" + CasesValue + '\'' +
                '}';
    }
}
