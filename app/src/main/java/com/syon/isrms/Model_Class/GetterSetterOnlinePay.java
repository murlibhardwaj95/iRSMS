package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterOnlinePay {

    private String installment;
    private String dueAmount;
    private String lateFee;


    public GetterSetterOnlinePay(String installment, String dueAmount, String lateFee) {
        this.installment = installment;
        this.dueAmount = dueAmount;
        this.lateFee = lateFee;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }
}
