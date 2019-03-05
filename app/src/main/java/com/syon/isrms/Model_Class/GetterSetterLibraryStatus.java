package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterLibraryStatus {

    private String sBookCode;
    private String sBookTitle;
    private String sIssueDate;
    private String sReturnDate;


    public GetterSetterLibraryStatus(String sBookCode, String sBookTitle, String sIssueDate , String sReturnDate) {
        this.sBookCode = sBookCode;
        this.sBookTitle = sBookTitle;
        this.sIssueDate = sIssueDate;
        this.sReturnDate = sReturnDate;
    }

    public String getsBookCode() {
        return sBookCode;
    }

    public void setsBookCode(String sBookCode) {
        this.sBookCode = sBookCode;
    }

    public String getsBookTitle() {
        return sBookTitle;
    }

    public void setsBookTitle(String sBookTitle) {
        this.sBookTitle = sBookTitle;
    }

    public String getsIssueDate() {
        return sIssueDate;
    }

    public void setsIssueDate(String sIssueDate) {
        this.sIssueDate = sIssueDate;
    }

    public String getsReturnDate() {
        return sReturnDate;
    }

    public void setsReturnDate(String sReturnDate) {
        this.sReturnDate = sReturnDate;
    }
}
