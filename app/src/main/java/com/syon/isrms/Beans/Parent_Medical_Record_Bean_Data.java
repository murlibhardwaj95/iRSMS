
package com.syon.isrms.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parent_Medical_Record_Bean_Data implements Serializable
{

    @SerializedName("sHeight")
    @Expose
    private String sHeight;
    @SerializedName("sWeight")
    @Expose
    private String sWeight;
    @SerializedName("sBMI")
    @Expose
    private String sBMI;
    @SerializedName("sLeftEyeVision")
    @Expose
    private String sLeftEyeVision;
    @SerializedName("sRightEyeVision")
    @Expose
    private String sRightEyeVision;
    @SerializedName("sDentalHygiene")
    @Expose
    private String sDentalHygiene;
    @SerializedName("sBloodGroup")
    @Expose
    private String sBloodGroup;
    @SerializedName("sAllergy")
    @Expose
    private String sAllergy;
    @SerializedName("sHealthProblem")
    @Expose
    private String sHealthProblem;
    private final static long serialVersionUID = 1501602110803993675L;

    public String getSHeight() {
        return sHeight;
    }

    public void setSHeight(String sHeight) {
        this.sHeight = sHeight;
    }

    public String getSWeight() {
        return sWeight;
    }

    public void setSWeight(String sWeight) {
        this.sWeight = sWeight;
    }

    public String getSBMI() {
        return sBMI;
    }

    public void setSBMI(String sBMI) {
        this.sBMI = sBMI;
    }

    public String getSLeftEyeVision() {
        return sLeftEyeVision;
    }

    public void setSLeftEyeVision(String sLeftEyeVision) {
        this.sLeftEyeVision = sLeftEyeVision;
    }

    public String getSRightEyeVision() {
        return sRightEyeVision;
    }

    public void setSRightEyeVision(String sRightEyeVision) {
        this.sRightEyeVision = sRightEyeVision;
    }

    public String getSDentalHygiene() {
        return sDentalHygiene;
    }

    public void setSDentalHygiene(String sDentalHygiene) {
        this.sDentalHygiene = sDentalHygiene;
    }

    public String getSBloodGroup() {
        return sBloodGroup;
    }

    public void setSBloodGroup(String sBloodGroup) {
        this.sBloodGroup = sBloodGroup;
    }

    public String getSAllergy() {
        return sAllergy;
    }

    public void setSAllergy(String sAllergy) {
        this.sAllergy = sAllergy;
    }

    public String getSHealthProblem() {
        return sHealthProblem;
    }

    public void setSHealthProblem(String sHealthProblem) {
        this.sHealthProblem = sHealthProblem;
    }

}
