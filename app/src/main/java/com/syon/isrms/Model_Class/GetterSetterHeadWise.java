package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterHeadWise {

    private String sInstl_MonthYr;
    private String sFHeadName;
    private String dueAmount;
    private String paidAmount;
    private String balAmount;
    private String total;


    public GetterSetterHeadWise(String sInstl_MonthYr,String sFHeadName, String dueAmount, String paidAmount, String balAmount) {
        this.sInstl_MonthYr = sInstl_MonthYr;
        this.sFHeadName = sFHeadName;
        this.dueAmount = dueAmount;
        this.paidAmount = paidAmount;
        this.balAmount = balAmount;
    }

    public String getsInstl_MonthYr() {
        return sInstl_MonthYr;
    }

    public void setsInstl_MonthYr(String sInstl_MonthYr) {
        this.sInstl_MonthYr = sInstl_MonthYr;
    }

    public String getsFHeadName() {
        return sFHeadName;
    }

    public void setsFHeadName(String sFHeadName) {
        this.sFHeadName = sFHeadName;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getBalAmount() {
        return balAmount;
    }

    public void setBalAmount(String balAmount) {
        this.balAmount = balAmount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
