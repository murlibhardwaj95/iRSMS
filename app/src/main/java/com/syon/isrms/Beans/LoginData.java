
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginData implements Serializable
{

    @SerializedName("lUTIdNo")
    @Expose
    private Integer lUTIdNo;
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
    @SerializedName("sTeacherPhoto")
    @Expose
    private String sTeacherPhoto;
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
    @SerializedName("ynClassTeacher")
    @Expose
    private Integer ynClassTeacher;
    @SerializedName("lClsTClassIdNo")
    @Expose
    private Integer lClsTClassIdNo;
    @SerializedName("lClsTSecIdNo")
    @Expose
    private Integer lClsTSecIdNo;
    @SerializedName("lClsTSubIdNo")
    @Expose
    private Integer lClsTSubIdNo;
    @SerializedName("lSessionIdNo")
    @Expose
    private Integer lSessionIdNo;
    @SerializedName("sSessionName")
    @Expose
    private String sSessionName;
    @SerializedName("sSchCode")
    @Expose
    private String sSchCode;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("tokenNo")
    @Expose
    private String tokenNo;
    private final static long serialVersionUID = -7927839804241211124L;

    public Integer getLUTIdNo() {
        return lUTIdNo;
    }

    public void setLUTIdNo(Integer lUTIdNo) {
        this.lUTIdNo = lUTIdNo;
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

    public String getSTeacherPhoto() {
        return sTeacherPhoto;
    }

    public void setSTeacherPhoto(String sTeacherPhoto) {
        this.sTeacherPhoto = sTeacherPhoto;
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

    public Integer getYnClassTeacher() {
        return ynClassTeacher;
    }

    public void setYnClassTeacher(Integer ynClassTeacher) {
        this.ynClassTeacher = ynClassTeacher;
    }

    public Integer getLClsTClassIdNo() {
        return lClsTClassIdNo;
    }

    public void setLClsTClassIdNo(Integer lClsTClassIdNo) {
        this.lClsTClassIdNo = lClsTClassIdNo;
    }

    public Integer getLClsTSecIdNo() {
        return lClsTSecIdNo;
    }

    public void setLClsTSecIdNo(Integer lClsTSecIdNo) {
        this.lClsTSecIdNo = lClsTSecIdNo;
    }

    public Integer getLClsTSubIdNo() {
        return lClsTSubIdNo;
    }

    public void setLClsTSubIdNo(Integer lClsTSubIdNo) {
        this.lClsTSubIdNo = lClsTSubIdNo;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

}
