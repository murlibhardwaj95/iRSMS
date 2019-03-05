package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSet {

    private String dtExamDate;
    private String sExamTime;
    private String sWeekDay;
    private String sSubject;
    private String sPaper;


    public GetterSet(String dtExamDate, String sExamTime, String sWeekDay, String sSubject, String sPaper) {
        this.dtExamDate = dtExamDate;
        this.sExamTime = sExamTime;
        this.sWeekDay = sWeekDay;
        this.sSubject = sSubject;
        this.sPaper = sPaper;
    }

    public String getDtExamDate() {
        return dtExamDate;
    }

    public void setDtExamDate(String dtExamDate) {
        this.dtExamDate = dtExamDate;
    }

    public String getsExamTime() {
        return sExamTime;
    }

    public void setsExamTime(String sExamTime) {
        this.sExamTime = sExamTime;
    }

    public String getsWeekDay() {
        return sWeekDay;
    }

    public void setsWeekDay(String sWeekDay) {
        this.sWeekDay = sWeekDay;
    }

    public String getsSubject() {
        return sSubject;
    }

    public void setsSubject(String sSubject) {
        this.sSubject = sSubject;
    }

    public String getsPaper() {
        return sPaper;
    }

    public void setsPaper(String sPaper) {
        this.sPaper = sPaper;
    }
}
