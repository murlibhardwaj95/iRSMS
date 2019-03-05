package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterNews {

    private String sNEDate;
    private String sTitle;
    private String sDescp;
    private String sAttachment;


    public GetterSetterNews(String sNEDate, String sTitle, String sDescp ,String sAttachment) {
        this.sNEDate = sNEDate;
        this.sTitle = sTitle;
        this.sDescp = sDescp;
        this.sAttachment = sAttachment;
    }

    public String getsNEDate() {
        return sNEDate;
    }

    public void setsNEDate(String sNEDate) {
        this.sNEDate = sNEDate;
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
