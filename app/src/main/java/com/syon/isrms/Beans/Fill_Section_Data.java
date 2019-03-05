
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fill_Section_Data implements Serializable
{

    @SerializedName("lSecId")
    @Expose
    private Integer lSecId;
    @SerializedName("sSection")
    @Expose
    private String sSection;
    private final static long serialVersionUID = 1418350943674365348L;

    public Integer getLSecId() {
        return lSecId;
    }

    public void setLSecId(Integer lSecId) {
        this.lSecId = lSecId;
    }

    public String getSSection() {
        return sSection;
    }

    public void setSSection(String sSection) {
        this.sSection = sSection;
    }

}
