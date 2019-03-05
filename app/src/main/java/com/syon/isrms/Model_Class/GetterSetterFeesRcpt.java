package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterFeesRcpt {

    private String nRcptNo;
    private String dtRcptDate;
    private String sFeeBook;
    private String sPaidAmount;
    private String sPayMode;


    public GetterSetterFeesRcpt(String nRcptNo, String dtRcptDate, String sFeeBook,String sPaidAmount, String sPayMode) {
        this.nRcptNo = nRcptNo;
        this.dtRcptDate = dtRcptDate;
        this.sFeeBook = sFeeBook;
        this.sPaidAmount = sPaidAmount;
        this.sPayMode = sPayMode;
    }

    public String getnRcptNo() {
        return nRcptNo;
    }

    public void setnRcptNo(String nRcptNo) {
        this.nRcptNo = nRcptNo;
    }

    public String getDtRcptDate() {
        return dtRcptDate;
    }

    public void setDtRcptDate(String dtRcptDate) {
        this.dtRcptDate = dtRcptDate;
    }

    public String getsFeeBook() {
        return sFeeBook;
    }

    public void setsFeeBook(String sFeeBook) {
        this.sFeeBook = sFeeBook;
    }

    public String getsPaidAmount() {
        return sPaidAmount;
    }

    public void setsPaidAmount(String sPaidAmount) {
        this.sPaidAmount = sPaidAmount;
    }

    public String getsPayMode() {
        return sPayMode;
    }

    public void setsPayMode(String sPayMode) {
        this.sPayMode = sPayMode;
    }
}
