
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fill_Subject_Data implements Serializable
{

    @SerializedName("lSubjectId")
    @Expose
    private Integer lSubjectId;
    @SerializedName("sSubject")
    @Expose
    private String sSubject;
    private final static long serialVersionUID = 3123120448578347784L;

    public Integer getLSubjectId() {
        return lSubjectId;
    }

    public void setLSubjectId(Integer lSubjectId) {
        this.lSubjectId = lSubjectId;
    }

    public String getSSubject() {
        return sSubject;
    }

    public void setSSubject(String sSubject) {
        this.sSubject = sSubject;
    }

}
