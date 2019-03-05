package com.syon.isrms.Model_Class;

/**
 * Created by CHAMP on 6/14/2018.
 */

public class GetterSetterMyTeacher {

    private String sSubject;
    private String subTeacherName;
    private String subTeacherPhoto;



    public GetterSetterMyTeacher(String sSubject, String subTeacherName, String subTeacherPhoto) {
        this.sSubject = sSubject;
        this.subTeacherName = subTeacherName;
        this.subTeacherPhoto = subTeacherPhoto;
    }

    public String getsSubject() {
        return sSubject;
    }

    public void setsSubject(String sSubject) {
        this.sSubject = sSubject;
    }

    public String getSubTeacherName() {
        return subTeacherName;
    }

    public void setSubTeacherName(String subTeacherName) {
        this.subTeacherName = subTeacherName;
    }

    public String getSubTeacherPhoto() {
        return subTeacherPhoto;
    }

    public void setSubTeacherPhoto(String subTeacherPhoto) {
        this.subTeacherPhoto = subTeacherPhoto;
    }
}
