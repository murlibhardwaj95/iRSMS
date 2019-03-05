package com.syon.isrms.interfaces.custom;


import com.syon.isrms.Model_Class.ModelNews;
import com.syon.isrms.Model_Class.ResponseAttendances;
import com.syon.isrms.Model_Class.SchoolNameResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Gaurav Mangal
 */
public interface ApiIHandler {

    @GET("SchoolInfo")
    Call<SchoolNameResponse> getNewsDataFROMSERVER();

    @GET("LoginType")
    Call<ModelNews> getLoginType(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    @FormUrlEncoded
    @POST("StudAttndDateWise")
    Call<ResponseAttendances> getAttendanceData(@Field("upid") String upid, @Field("smonth") String smonth, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

}
