package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetLeave {

    private String fromDate;
    private String toDate;
    private String reason;
    private String approved;


    public GetterSetLeave(String fromDate, String toDate, String reason, String approved) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.approved = approved;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }
}
