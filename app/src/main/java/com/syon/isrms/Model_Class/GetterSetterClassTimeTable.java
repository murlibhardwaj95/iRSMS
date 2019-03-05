package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterClassTimeTable {

    private String lCTTId;
    private String attachment;
    private String sClass;
    private String sSection;


    public GetterSetterClassTimeTable(String lCTTId, String attachment, String sClass , String sSection) {
        this.lCTTId = lCTTId;
        this.attachment = attachment;
        this.sClass = sClass;
        this.sSection = sSection;
    }

    public String getlCTTId() {
        return lCTTId;
    }

    public void setlCTTId(String lCTTId) {
        this.lCTTId = lCTTId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getsSection() {
        return sSection;
    }

    public void setsSection(String sSection) {
        this.sSection = sSection;
    }
}
