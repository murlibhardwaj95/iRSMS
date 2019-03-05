    package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

    public class Userbean_SMS_Data implements Serializable {

        @SerializedName("nSrNo")
        @Expose
        private Integer nSrNo;
        @SerializedName("dtSMSDate")
        @Expose
        private String dtSMSDate;
        @SerializedName("sSMSDate")
        @Expose
        private String sSMSDate;
        @SerializedName("sSMSTime")
        @Expose
        private String sSMSTime;
        @SerializedName("sSMS")
        @Expose
        private String sSMS;
        @SerializedName("sMobile")
        @Expose
        private String sMobile;
        private final static long serialVersionUID = 8520554403513253749L;

        public Integer getNSrNo() {
            return nSrNo;
        }

        public void setNSrNo(Integer nSrNo) {
            this.nSrNo = nSrNo;
        }

        public String getDtSMSDate() {
            return dtSMSDate;
        }

        public void setDtSMSDate(String dtSMSDate) {
            this.dtSMSDate = dtSMSDate;
        }

        public String getSSMSDate() {
            return sSMSDate;
        }

        public void setSSMSDate(String sSMSDate) {
            this.sSMSDate = sSMSDate;
        }

        public String getSSMSTime() {
            return sSMSTime;
        }

        public void setSSMSTime(String sSMSTime) {
            this.sSMSTime = sSMSTime;
        }

        public String getSSMS() {
            return sSMS;
        }

        public void setSSMS(String sSMS) {
            this.sSMS = sSMS;
        }

        public String getSMobile() {
            return sMobile;
        }

        public void setSMobile(String sMobile) {
            this.sMobile = sMobile;
        }
    }
