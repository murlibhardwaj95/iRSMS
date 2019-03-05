package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterObservation {

    private String dtObsDate;
    private String sTeacherName;
    private String sRemark;
    private String lTeacherId;
    private String sPaper;


    public GetterSetterObservation(String dtObsDate, String sTeacherName, String sRemark, String lTeacherId) {
        this.dtObsDate = dtObsDate;
        this.sTeacherName = sTeacherName;
        this.sRemark = sRemark;
        this.lTeacherId = lTeacherId;
    }

    public String getDtObsDate() {
        return dtObsDate;
    }

    public void setDtObsDate(String dtObsDate) {
        this.dtObsDate = dtObsDate;
    }

    public String getsTeacherName() {
        return sTeacherName;
    }

    public void setsTeacherName(String sTeacherName) {
        this.sTeacherName = sTeacherName;
    }

    public String getsRemark() {
        return sRemark;
    }

    public void setsRemark(String sRemark) {
        this.sRemark = sRemark;
    }

    public String getlTeacherId() {
        return lTeacherId;
    }

    public void setlTeacherId(String lTeacherId) {
        this.lTeacherId = lTeacherId;
    }

    public String getsPaper() {
        return sPaper;
    }

    public void setsPaper(String sPaper) {
        this.sPaper = sPaper;
    }
}
