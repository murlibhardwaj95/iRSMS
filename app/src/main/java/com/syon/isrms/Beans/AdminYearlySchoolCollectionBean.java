
package com.syon.isrms.Beans;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminYearlySchoolCollectionBean implements Serializable
{

    @SerializedName("sProsSaleAmt")
    @Expose
    private String sProsSaleAmt;
    @SerializedName("sFeeCollAmt")
    @Expose
    private String sFeeCollAmt;
    @SerializedName("sSpFeeCollAmt")
    @Expose
    private String sSpFeeCollAmt;
    @SerializedName("sLibFineAmt")
    @Expose
    private String sLibFineAmt;
    private final static long serialVersionUID = -8052478985100871593L;

    public String getSProsSaleAmt() {
        return sProsSaleAmt;
    }

    public void setSProsSaleAmt(String sProsSaleAmt) {
        this.sProsSaleAmt = sProsSaleAmt;
    }

    public String getSFeeCollAmt() {
        return sFeeCollAmt;
    }

    public void setSFeeCollAmt(String sFeeCollAmt) {
        this.sFeeCollAmt = sFeeCollAmt;
    }

    public String getSSpFeeCollAmt() {
        return sSpFeeCollAmt;
    }

    public void setSSpFeeCollAmt(String sSpFeeCollAmt) {
        this.sSpFeeCollAmt = sSpFeeCollAmt;
    }

    public String getSLibFineAmt() {
        return sLibFineAmt;
    }

    public void setSLibFineAmt(String sLibFineAmt) {
        this.sLibFineAmt = sLibFineAmt;
    }

}
