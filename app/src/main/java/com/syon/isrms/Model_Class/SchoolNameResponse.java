package com.syon.isrms.Model_Class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gaurav on 05/02/2017.
 */
public class SchoolNameResponse {
    @SerializedName("schoolId")
    @Expose
    private String message;

    @SerializedName("schoolCode")
    @Expose
    private String sSch_Code;

    @SerializedName("schoolName")
    @Expose
    private String sSch_Name;

    @SerializedName("schoolAddress")
    @Expose
    private String schoolAddress;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("sState")
    @Expose
    private String sState;

    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;


    @SerializedName("fax")
    @Expose
    private String fax;


    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("webSite")
    @Expose
    private String webSite;

    @SerializedName("yearStart")
    @Expose
    private String yearStart;

    @SerializedName("yearRecog")
    @Expose
    private String yearRecog;

    @SerializedName("medium")
    @Expose
    private String medium;

    @SerializedName("boardName")
    @Expose
    private String boardName;

    @SerializedName("director")
    @Expose
    private String director;

    @SerializedName("principal")
    @Expose
    private String principal;

    @SerializedName("schoolSessionStartDate")
    @Expose
    private String schoolSessionStartDate;


    @SerializedName("affiliationCode")
    @Expose
    private String affiliationCode;

    @SerializedName("schoolNo")
    @Expose
    private String schoolNo;


    @SerializedName("webSessionId")
    @Expose
    private String webSessionId;

    @SerializedName("branchName")
    @Expose
    private String branchName;


    @SerializedName("schoolType")
    @Expose
    private String schoolType;


    @SerializedName("schoolDesription")
    @Expose
    private String schoolDesription;


    @SerializedName("sessionStartDate")
    @Expose
    private String sessionStartDate;

    @SerializedName("sessionEndDate")
    @Expose
    private String sessionEndDate;


    @SerializedName("schoolLogo")
    @Expose
    private String schoolLogo;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getsSch_Code() {
        return sSch_Code;
    }

    public void setsSch_Code(String sSch_Code) {
        this.sSch_Code = sSch_Code;
    }

    public String getsSch_Name() {
        return sSch_Name;
    }

    public void setsSch_Name(String sSch_Name) {
        this.sSch_Name = sSch_Name;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getsState() {
        return sState;
    }

    public void setsState(String sState) {
        this.sState = sState;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getYearStart() {
        return yearStart;
    }

    public void setYearStart(String yearStart) {
        this.yearStart = yearStart;
    }

    public String getYearRecog() {
        return yearRecog;
    }

    public void setYearRecog(String yearRecog) {
        this.yearRecog = yearRecog;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSchoolSessionStartDate() {
        return schoolSessionStartDate;
    }

    public void setSchoolSessionStartDate(String schoolSessionStartDate) {
        this.schoolSessionStartDate = schoolSessionStartDate;
    }

    public String getAffiliationCode() {
        return affiliationCode;
    }

    public void setAffiliationCode(String affiliationCode) {
        this.affiliationCode = affiliationCode;
    }

    public String getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(String schoolNo) {
        this.schoolNo = schoolNo;
    }

    public String getWebSessionId() {
        return webSessionId;
    }

    public void setWebSessionId(String webSessionId) {
        this.webSessionId = webSessionId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolDesription() {
        return schoolDesription;
    }

    public void setSchoolDesription(String schoolDesription) {
        this.schoolDesription = schoolDesription;
    }

    public String getSessionStartDate() {
        return sessionStartDate;
    }

    public void setSessionStartDate(String sessionStartDate) {
        this.sessionStartDate = sessionStartDate;
    }

    public String getSessionEndDate() {
        return sessionEndDate;
    }

    public void setSessionEndDate(String sessionEndDate) {
        this.sessionEndDate = sessionEndDate;
    }

    public String getSchoolLogo() {
        return schoolLogo;
    }

    public void setSchoolLogo(String schoolLogo) {
        this.schoolLogo = schoolLogo;
    }
}
