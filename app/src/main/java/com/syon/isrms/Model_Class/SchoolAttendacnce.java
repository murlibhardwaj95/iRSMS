package com.syon.isrms.Model_Class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


/**
 * Created by Gaurav on 14/02/2017.
 */


public class SchoolAttendacnce implements Serializable {

    @SerializedName("attndDate")
    @Expose
    private String attndDate;

    @SerializedName("attndStatus")
    @Expose
    private String attndStatus;


    @SerializedName("sMonthName")
    @Expose
    private String sMonthName;


    public String getAttndDate() {
        return attndDate;
    }

    public void setAttndDate(String attndDate) {
        this.attndDate = attndDate;
    }

    public String getAttndStatus() {
        return attndStatus;
    }

    public void setAttndStatus(String attndStatus) {
        this.attndStatus = attndStatus;
    }

    public String getsMonthName() {
        return sMonthName;
    }

    public void setsMonthName(String sMonthName) {
        this.sMonthName = sMonthName;
    }
}
