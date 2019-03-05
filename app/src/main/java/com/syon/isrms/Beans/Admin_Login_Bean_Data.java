
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Login_Bean_Data implements Serializable
{

    @SerializedName("lUAIdNo")
    @Expose
    private Integer lUAIdNo;
    @SerializedName("sUserName")
    @Expose
    private String sUserName;
    @SerializedName("sPassword")
    @Expose
    private String sPassword;
    @SerializedName("lEmpIdNo")
    @Expose
    private Integer lEmpIdNo;
    @SerializedName("sEmpCode")
    @Expose
    private String sEmpCode;
    @SerializedName("sEmpName")
    @Expose
    private String sEmpName;
    @SerializedName("sAdminPhoto")
    @Expose
    private String sAdminPhoto;
    @SerializedName("lDeptIdNo")
    @Expose
    private Integer lDeptIdNo;
    @SerializedName("sDeptName")
    @Expose
    private String sDeptName;
    @SerializedName("dtJoinDate")
    @Expose
    private String dtJoinDate;
    @SerializedName("sJoinDate")
    @Expose
    private String sJoinDate;
    @SerializedName("dtLeftDate")
    @Expose
    private String dtLeftDate;
    @SerializedName("sLeftDate")
    @Expose
    private String sLeftDate;
    @SerializedName("dtDOB")
    @Expose
    private String dtDOB;
    @SerializedName("sDOB")
    @Expose
    private String sDOB;
    @SerializedName("lSessionIdNo")
    @Expose
    private Integer lSessionIdNo;
    @SerializedName("sSessionName")
    @Expose
    private String sSessionName;
    @SerializedName("sSchCode")
    @Expose
    private String sSchCode;
    private final static long serialVersionUID = -8115355973481104260L;

    public Integer getLUAIdNo() {
        return lUAIdNo;
    }

    public void setLUAIdNo(Integer lUAIdNo) {
        this.lUAIdNo = lUAIdNo;
    }

    public String getSUserName() {
        return sUserName;
    }

    public void setSUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public Integer getLEmpIdNo() {
        return lEmpIdNo;
    }

    public void setLEmpIdNo(Integer lEmpIdNo) {
        this.lEmpIdNo = lEmpIdNo;
    }

    public String getSEmpCode() {
        return sEmpCode;
    }

    public void setSEmpCode(String sEmpCode) {
        this.sEmpCode = sEmpCode;
    }

    public String getSEmpName() {
        return sEmpName;
    }

    public void setSEmpName(String sEmpName) {
        this.sEmpName = sEmpName;
    }

    public String getSAdminPhoto() {
        return sAdminPhoto;
    }

    public void setSAdminPhoto(String sAdminPhoto) {
        this.sAdminPhoto = sAdminPhoto;
    }

    public Integer getLDeptIdNo() {
        return lDeptIdNo;
    }

    public void setLDeptIdNo(Integer lDeptIdNo) {
        this.lDeptIdNo = lDeptIdNo;
    }

    public String getSDeptName() {
        return sDeptName;
    }

    public void setSDeptName(String sDeptName) {
        this.sDeptName = sDeptName;
    }

    public String getDtJoinDate() {
        return dtJoinDate;
    }

    public void setDtJoinDate(String dtJoinDate) {
        this.dtJoinDate = dtJoinDate;
    }

    public String getSJoinDate() {
        return sJoinDate;
    }

    public void setSJoinDate(String sJoinDate) {
        this.sJoinDate = sJoinDate;
    }

    public String getDtLeftDate() {
        return dtLeftDate;
    }

    public void setDtLeftDate(String dtLeftDate) {
        this.dtLeftDate = dtLeftDate;
    }

    public String getSLeftDate() {
        return sLeftDate;
    }

    public void setSLeftDate(String sLeftDate) {
        this.sLeftDate = sLeftDate;
    }

    public String getDtDOB() {
        return dtDOB;
    }

    public void setDtDOB(String dtDOB) {
        this.dtDOB = dtDOB;
    }

    public String getSDOB() {
        return sDOB;
    }

    public void setSDOB(String sDOB) {
        this.sDOB = sDOB;
    }

    public Integer getLSessionIdNo() {
        return lSessionIdNo;
    }

    public void setLSessionIdNo(Integer lSessionIdNo) {
        this.lSessionIdNo = lSessionIdNo;
    }

    public String getSSessionName() {
        return sSessionName;
    }

    public void setSSessionName(String sSessionName) {
        this.sSessionName = sSessionName;
    }

    public String getSSchCode() {
        return sSchCode;
    }

    public void setSSchCode(String sSchCode) {
        this.sSchCode = sSchCode;
    }

}
