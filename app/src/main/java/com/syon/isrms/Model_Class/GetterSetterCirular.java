package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterCirular {

    private String sCirDate;
    private String sTitle;
    private String sDescp;
    private String sAttachment;


    public GetterSetterCirular(String sCirDate, String sTitle, String sDescp , String sAttachment) {
        this.sCirDate = sCirDate;
        this.sTitle = sTitle;
        this.sDescp = sDescp;
        this.sAttachment = sAttachment;
    }

    public String getsCirDate() {
        return sCirDate;
    }

    public void setsCirDate(String sCirDate) {
        this.sCirDate = sCirDate;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsDescp() {
        return sDescp;
    }

    public void setsDescp(String sDescp) {
        this.sDescp = sDescp;
    }

    public String getsAttachment() {
        return sAttachment;
    }

    public void setsAttachment(String sAttachment) {
        this.sAttachment = sAttachment;
    }
}
