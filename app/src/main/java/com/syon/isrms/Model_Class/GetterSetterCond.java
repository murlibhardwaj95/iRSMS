package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterCond {

    private String sBusIncharge_Name;
    private String sBusIncharge_ContactNo;
    private String sDriver_Name;
    private String sDriver_ContactNo;
    private String sConductor_Name;
    private String sConductor_ContactNo;

    public GetterSetterCond(String sBusIncharge_Name, String sBusIncharge_ContactNo, String sDriver_Name, String sDriver_ContactNo, String sConductor_Name, String sConductor_ContactNo) {
        this.sBusIncharge_Name = sBusIncharge_Name;
        this.sBusIncharge_ContactNo = sBusIncharge_ContactNo;
        this.sDriver_Name = sDriver_Name;
        this.sDriver_ContactNo = sDriver_ContactNo;
        this.sConductor_Name = sConductor_Name;
        this.sConductor_ContactNo = sConductor_ContactNo;

    }

    public String getsBusIncharge_Name() {
        return sBusIncharge_Name;
    }

    public void setsBusIncharge_Name(String sBusIncharge_Name) {
        this.sBusIncharge_Name = sBusIncharge_Name;
    }

    public String getsBusIncharge_ContactNo() {
        return sBusIncharge_ContactNo;
    }

    public void setsBusIncharge_ContactNo(String sBusIncharge_ContactNo) {
        this.sBusIncharge_ContactNo = sBusIncharge_ContactNo;
    }

    public String getsDriver_Name() {
        return sDriver_Name;
    }

    public void setsDriver_Name(String sDriver_Name) {
        this.sDriver_Name = sDriver_Name;
    }

    public String getsDriver_ContactNo() {
        return sDriver_ContactNo;
    }

    public void setsDriver_ContactNo(String sDriver_ContactNo) {
        this.sDriver_ContactNo = sDriver_ContactNo;
    }

    public String getsConductor_Name() {
        return sConductor_Name;
    }

    public void setsConductor_Name(String sConductor_Name) {
        this.sConductor_Name = sConductor_Name;
    }

    public String getsConductor_ContactNo() {
        return sConductor_ContactNo;
    }

    public void setsConductor_ContactNo(String sConductor_ContactNo) {
        this.sConductor_ContactNo = sConductor_ContactNo;
    }
}
