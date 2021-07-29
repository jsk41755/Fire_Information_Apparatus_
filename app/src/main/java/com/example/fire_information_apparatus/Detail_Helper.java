package com.example.fire_information_apparatus;

public class Detail_Helper {
    String Reporting_Time;
    String By_Case_Cause;
    String Jurisdiction_Center;
    String Reported_Content;

    public Detail_Helper() {
    }

    public Detail_Helper(String Reporting_Time, String By_Case_Cause, String Jurisdiction_Center, String Reported_Content) {
        this.Reporting_Time = Reporting_Time;
        this.By_Case_Cause = By_Case_Cause;
        this.Jurisdiction_Center = Jurisdiction_Center;
        this.Reported_Content = Reported_Content;
    }

    public String getReporting_Time() {
        return Reporting_Time;
    }

    public void setReporting_Time(String reporting_Time) {
        Reporting_Time = reporting_Time;
    }

    public String getBy_Case_Cause() {
        return By_Case_Cause;
    }

    public void setBy_Case_Cause(String by_Case_Cause) {
        this.By_Case_Cause = by_Case_Cause;
    }

    public String getJurisdiction_Center() {
        return Jurisdiction_Center;
    }

    public void setJurisdiction_Center(String jurisdiction_Center) {
        Jurisdiction_Center = jurisdiction_Center;
    }

    public String getReported_Content() {
        return Reported_Content;
    }

    public void setReported_Content(String reported_Content) {
        Reported_Content = reported_Content;
    }


}
