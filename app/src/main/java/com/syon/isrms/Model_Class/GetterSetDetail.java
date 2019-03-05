package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetDetail {

    private String sFHeadName;
    private String paidAmount;


    public GetterSetDetail(String sFHeadName, String paidAmount) {
        this.sFHeadName = sFHeadName;
        this.paidAmount = paidAmount;

    }

    public String getsFHeadName() {
        return sFHeadName;
    }

    public void setsFHeadName(String sFHeadName) {
        this.sFHeadName = sFHeadName;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }
}
