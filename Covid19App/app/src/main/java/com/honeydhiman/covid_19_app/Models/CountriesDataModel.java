package com.honeydhiman.covid_19_app.Models;

public class CountriesDataModel {

    private String countryname,flag,cases,todaycase,deaths,todaydeath,recovered,active,critical;

    public CountriesDataModel() {
    }

    public CountriesDataModel(String countryname, String flag, String cases, String todaycase, String deaths, String todaydeath, String recovered, String active, String critical) {
        this.countryname = countryname;
        this.flag = flag;
        this.cases = cases;
        this.todaycase = todaycase;
        this.deaths = deaths;
        this.todaydeath = todaydeath;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycase() {
        return todaycase;
    }

    public void setTodaycase(String todaycase) {
        this.todaycase = todaycase;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeath() {
        return todaydeath;
    }

    public void setTodaydeath(String todaydeath) {
        this.todaydeath = todaydeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }


    @Override
    public String toString() {
        return "CountriesDataModel{" +
                "countryname='" + countryname + '\'' +
                ", flag='" + flag + '\'' +
                ", cases='" + cases + '\'' +
                ", todaycase='" + todaycase + '\'' +
                ", deaths='" + deaths + '\'' +
                ", todaydeath='" + todaydeath + '\'' +
                ", recovered='" + recovered + '\'' +
                ", active='" + active + '\'' +
                ", critical='" + critical + '\'' +
                '}';
    }
}
