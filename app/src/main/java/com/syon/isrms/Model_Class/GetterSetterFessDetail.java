package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterFessDetail {

    private String sInstl_MonthYr;
    private String dueAmount;
    private String paidAmount;
    private String balAmount;
    private String total;
    private String image;

    public GetterSetterFessDetail(String sInstl_MonthYr, String dueAmount, String paidAmount, String balAmount) {
        this.sInstl_MonthYr=sInstl_MonthYr;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
