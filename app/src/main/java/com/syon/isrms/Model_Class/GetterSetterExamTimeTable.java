package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterExamTimeTable {

    private String examSrNo;
    private String examId;
    private String examName;


    public GetterSetterExamTimeTable(String examSrNo, String examId, String examName) {
        this.examSrNo = examSrNo;
        this.examId = examId;
        this.examName = examName;
    }

    public String getExamSrNo() {
        return examSrNo;
    }

    public void setExamSrNo(String examSrNo) {
        this.examSrNo = examSrNo;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
