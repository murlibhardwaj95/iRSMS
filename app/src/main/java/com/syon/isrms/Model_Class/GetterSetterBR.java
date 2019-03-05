package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterBR {

    private String sBirthday;
    private String sStudName;
    private String sStudPhoto;


    public GetterSetterBR(String sBirthday, String sStudName, String sStudPhoto) {
        this.sBirthday = sBirthday;
        this.sStudName = sStudName;
        this.sStudPhoto = sStudPhoto;

    }

    public String getsBirthday() {
        return sBirthday;
    }

    public void setsBirthday(String sBirthday) {
        this.sBirthday = sBirthday;
    }

    public String getsStudName() {
        return sStudName;
    }

    public void setsStudName(String sStudName) {
        this.sStudName = sStudName;
    }

    public String getsStudPhoto() {
        return sStudPhoto;
    }

    public void setsStudPhoto(String sStudPhoto) {
        this.sStudPhoto = sStudPhoto;
    }
}
