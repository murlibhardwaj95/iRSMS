
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Toppers_List_Data implements Serializable
{

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("lClassId")
    @Expose
    private Integer lClassId;
    @SerializedName("sClass")
    @Expose
    private String sClass;
    @SerializedName("nClassSrNo")
    @Expose
    private Integer nClassSrNo;
    @SerializedName("lStreamId")
    @Expose
    private Integer lStreamId;
    @SerializedName("sStream")
    @Expose
    private String sStream;
    @SerializedName("sClassStream")
    @Expose
    private String sClassStream;
    @SerializedName("lExamId")
    @Expose
    private Integer lExamId;
    @SerializedName("sExamName")
    @Expose
    private String sExamName;
    @SerializedName("nExamSrNo")
    @Expose
    private Integer nExamSrNo;
    private final static long serialVersionUID = -1052561653775921009L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getLClassId() {
        return lClassId;
    }

    public void setLClassId(Integer lClassId) {
        this.lClassId = lClassId;
    }

    public String getSClass() {
        return sClass;
    }

    public void setSClass(String sClass) {
        this.sClass = sClass;
    }

    public Integer getNClassSrNo() {
        return nClassSrNo;
    }

    public void setNClassSrNo(Integer nClassSrNo) {
        this.nClassSrNo = nClassSrNo;
    }

    public Integer getLStreamId() {
        return lStreamId;
    }

    public void setLStreamId(Integer lStreamId) {
        this.lStreamId = lStreamId;
    }

    public String getSStream() {
        return sStream;
    }

    public void setSStream(String sStream) {
        this.sStream = sStream;
    }

    public String getSClassStream() {
        return sClassStream;
    }

    public void setSClassStream(String sClassStream) {
        this.sClassStream = sClassStream;
    }

    public Integer getLExamId() {
        return lExamId;
    }

    public void setLExamId(Integer lExamId) {
        this.lExamId = lExamId;
    }

    public String getSExamName() {
        return sExamName;
    }

    public void setSExamName(String sExamName) {
        this.sExamName = sExamName;
    }

    public Integer getNExamSrNo() {
        return nExamSrNo;
    }

    public void setNExamSrNo(Integer nExamSrNo) {
        this.nExamSrNo = nExamSrNo;
    }

}
