
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Admin_Change_Password_Bean_Data implements Serializable
{

    @SerializedName("lUA_IdNo")
    @Expose
    private Integer lUAIdNo;
    @SerializedName("lEmp_IdNo")
    @Expose
    private Integer lEmpIdNo;
    @SerializedName("sEmp_Code")
    @Expose
    private String sEmpCode;
    @SerializedName("sUser_Name")
    @Expose
    private String sUserName;
    @SerializedName("sUser_Pwd")
    @Expose
    private String sUserPwd;
    @SerializedName("dtMod_Date")
    @Expose
    private String dtModDate;
    @SerializedName("lSession_IdNo")
    @Expose
    private Integer lSessionIdNo;
    @SerializedName("bActive")
    @Expose
    private Boolean bActive;
    @SerializedName("sEmail")
    @Expose
    private String sEmail;
    @SerializedName("bMail_Sent")
    @Expose
    private Boolean bMailSent;
    @SerializedName("sEmp_FName")
    @Expose
    private String sEmpFName;
    @SerializedName("sEmp_MName")
    @Expose
    private String sEmpMName;
    @SerializedName("sEmp_LName")
    @Expose
    private String sEmpLName;
    @SerializedName("sFather_Name")
    @Expose
    private String sFatherName;
    @SerializedName("sPhone_No")
    @Expose
    private String sPhoneNo;
    @SerializedName("sEmail_Id")
    @Expose
    private String sEmailId;
    @SerializedName("lDept_IdNo")
    @Expose
    private Integer lDeptIdNo;
    @SerializedName("sDepartment")
    @Expose
    private String sDepartment;
    @SerializedName("lDesig_IdNo")
    @Expose
    private Integer lDesigIdNo;
    @SerializedName("sDesignation")
    @Expose
    private String sDesignation;
    @SerializedName("dtCreate_Date")
    @Expose
    private String dtCreateDate;
    @SerializedName("sSch_Code")
    @Expose
    private String sSchCode;
    @SerializedName("bSMS_Sent")
    @Expose
    private Boolean bSMSSent;
    @SerializedName("sMobile_No")
    @Expose
    private String sMobileNo;
    @SerializedName("lUser_IdNo")
    @Expose
    private Integer lUserIdNo;
    private final static long serialVersionUID = -3599130629079246786L;

    public Integer getLUAIdNo() {
        return lUAIdNo;
    }

    public void setLUAIdNo(Integer lUAIdNo) {
        this.lUAIdNo = lUAIdNo;
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

    public String getSUserName() {
        return sUserName;
    }

    public void setSUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getSUserPwd() {
        return sUserPwd;
    }

    public void setSUserPwd(String sUserPwd) {
        this.sUserPwd = sUserPwd;
    }

    public String getDtModDate() {
        return dtModDate;
    }

    public void setDtModDate(String dtModDate) {
        this.dtModDate = dtModDate;
    }

    public Integer getLSessionIdNo() {
        return lSessionIdNo;
    }

    public void setLSessionIdNo(Integer lSessionIdNo) {
        this.lSessionIdNo = lSessionIdNo;
    }

    public Boolean getBActive() {
        return bActive;
    }

    public void setBActive(Boolean bActive) {
        this.bActive = bActive;
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public Boolean getBMailSent() {
        return bMailSent;
    }

    public void setBMailSent(Boolean bMailSent) {
        this.bMailSent = bMailSent;
    }

    public String getSEmpFName() {
        return sEmpFName;
    }

    public void setSEmpFName(String sEmpFName) {
        this.sEmpFName = sEmpFName;
    }

    public String getSEmpMName() {
        return sEmpMName;
    }

    public void setSEmpMName(String sEmpMName) {
        this.sEmpMName = sEmpMName;
    }

    public String getSEmpLName() {
        return sEmpLName;
    }

    public void setSEmpLName(String sEmpLName) {
        this.sEmpLName = sEmpLName;
    }

    public String getSFatherName() {
        return sFatherName;
    }

    public void setSFatherName(String sFatherName) {
        this.sFatherName = sFatherName;
    }

    public String getSPhoneNo() {
        return sPhoneNo;
    }

    public void setSPhoneNo(String sPhoneNo) {
        this.sPhoneNo = sPhoneNo;
    }

    public String getSEmailId() {
        return sEmailId;
    }

    public void setSEmailId(String sEmailId) {
        this.sEmailId = sEmailId;
    }

    public Integer getLDeptIdNo() {
        return lDeptIdNo;
    }

    public void setLDeptIdNo(Integer lDeptIdNo) {
        this.lDeptIdNo = lDeptIdNo;
    }

    public String getSDepartment() {
        return sDepartment;
    }

    public void setSDepartment(String sDepartment) {
        this.sDepartment = sDepartment;
    }

    public Integer getLDesigIdNo() {
        return lDesigIdNo;
    }

    public void setLDesigIdNo(Integer lDesigIdNo) {
        this.lDesigIdNo = lDesigIdNo;
    }

    public String getSDesignation() {
        return sDesignation;
    }

    public void setSDesignation(String sDesignation) {
        this.sDesignation = sDesignation;
    }

    public String getDtCreateDate() {
        return dtCreateDate;
    }

    public void setDtCreateDate(String dtCreateDate) {
        this.dtCreateDate = dtCreateDate;
    }

    public String getSSchCode() {
        return sSchCode;
    }

    public void setSSchCode(String sSchCode) {
        this.sSchCode = sSchCode;
    }

    public Boolean getBSMSSent() {
        return bSMSSent;
    }

    public void setBSMSSent(Boolean bSMSSent) {
        this.bSMSSent = bSMSSent;
    }

    public String getSMobileNo() {
        return sMobileNo;
    }

    public void setSMobileNo(String sMobileNo) {
        this.sMobileNo = sMobileNo;
    }

    public Integer getLUserIdNo() {
        return lUserIdNo;
    }

    public void setLUserIdNo(Integer lUserIdNo) {
        this.lUserIdNo = lUserIdNo;
    }

}
