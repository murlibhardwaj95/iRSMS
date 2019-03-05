package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterFragementA {

    private String sRoute;
    private String sPickPoint;
    private String sPickUp_Time;
    private String sDrop_Time;
    private String sBus_No;


    public GetterSetterFragementA(String sRoute, String sPickPoint, String sPickUp_Time , String sDrop_Time, String sBus_No) {
        this.sRoute = sRoute;
        this.sPickPoint = sPickPoint;
        this.sPickUp_Time = sPickUp_Time;
        this.sDrop_Time = sDrop_Time;
        this.sBus_No = sBus_No;
    }

    public String getsRoute() {
        return sRoute;
    }

    public void setsRoute(String sRoute) {
        this.sRoute = sRoute;
    }

    public String getsPickPoint() {
        return sPickPoint;
    }

    public void setsPickPoint(String sPickPoint) {
        this.sPickPoint = sPickPoint;
    }

    public String getsPickUp_Time() {
        return sPickUp_Time;
    }

    public void setsPickUp_Time(String sPickUp_Time) {
        this.sPickUp_Time = sPickUp_Time;
    }

    public String getsDrop_Time() {
        return sDrop_Time;
    }

    public void setsDrop_Time(String sDrop_Time) {
        this.sDrop_Time = sDrop_Time;
    }

    public String getsBus_No() {
        return sBus_No;
    }

    public void setsBus_No(String sBus_No) {
        this.sBus_No = sBus_No;
    }
}
