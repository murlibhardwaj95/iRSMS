package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterHomework {

    private String sSubject;
    private String sTitle;
    private String sHomeWork;
    private String attachment1;


    public GetterSetterHomework(String sSubject, String sTitle, String sHomeWork,String attachment1) {
        this.sSubject = sSubject;
        this.sTitle = sTitle;
        this.sHomeWork = sHomeWork;
        this.attachment1 = attachment1;

    }

    public String getsSubject() {
        return sSubject;
    }

    public void setsSubject(String sSubject) {
        this.sSubject = sSubject;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    public String getsHomeWork() {
        return sHomeWork;
    }

    public void setsHomeWork(String sHomeWork) {
        this.sHomeWork = sHomeWork;
    }

    public String getAttachment1() {
        return attachment1;
    }

    public void setAttachment1(String attachment1) {
        this.attachment1 = attachment1;
    }
}
