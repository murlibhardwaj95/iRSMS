package com.syon.isrms.Interfaces_Teacher;


import com.syon.isrms.Beans.AdminChangePasswordBean;
import com.syon.isrms.Beans.AdminCircularBean;
import com.syon.isrms.Beans.AdminCircularViewBean;
import com.syon.isrms.Beans.AdminClassAttendanceBean;
import com.syon.isrms.Beans.AdminClassFillBean;
import com.syon.isrms.Beans.AdminClassResultFillExamBean;
import com.syon.isrms.Beans.AdminClassResultSelectedDetailExamBean;
import com.syon.isrms.Beans.AdminClassResultSelectedExamBean;
import com.syon.isrms.Beans.AdminClassTeacherResultFillTeacherBean;
import com.syon.isrms.Beans.AdminClassTimetableBean;
import com.syon.isrms.Beans.AdminDailyReportBean;
import com.syon.isrms.Beans.AdminDailySchoolCollectionBean;
import com.syon.isrms.Beans.AdminDepartmentFillBean;
import com.syon.isrms.Beans.AdminHeaderImageBean;
import com.syon.isrms.Beans.AdminLoginBean;
import com.syon.isrms.Beans.AdminNewsBean;
import com.syon.isrms.Beans.AdminNewsNotificationBean;
import com.syon.isrms.Beans.AdminNewsViewBean;
import com.syon.isrms.Beans.AdminSchoolStrengthBean;
import com.syon.isrms.Beans.AdminSchoolStrengthWebBean;
import com.syon.isrms.Beans.AdminToppersListBean;
import com.syon.isrms.Beans.AdminYearlyAcadmicReportBean;
import com.syon.isrms.Beans.AdminYearlyOutstandingBean;
import com.syon.isrms.Beans.AdminYearlyRefundBean;
import com.syon.isrms.Beans.AdminYearlySchoolCollectionBean;
import com.syon.isrms.Beans.Admin_Department_Salary;
import com.syon.isrms.Beans.Admin_DetailClick_Main;
import com.syon.isrms.Beans.Admin_Monthly_Salary;
import com.syon.isrms.Beans.Admin_TeacherResultDetails_Main;
import com.syon.isrms.Beans.Admin_Userbean_Emplyoee;
import com.syon.isrms.Beans.Admin_Userbean_ToopersList;
import com.syon.isrms.Beans.AttendanceBEan;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_One;
import com.syon.isrms.Beans.Bean_Admin_Discussion_Board_Post;
import com.syon.isrms.Beans.BirthdayBean;
import com.syon.isrms.Beans.CircularBean;
import com.syon.isrms.Beans.ClassAttendanceBean;
import com.syon.isrms.Beans.CoRemarkBean;
import com.syon.isrms.Beans.CoSchBean;
import com.syon.isrms.Beans.EventCalenderMonthlyBean;
import com.syon.isrms.Beans.EventCalenderYearlyBean;
import com.syon.isrms.Beans.Example;
import com.syon.isrms.Beans.FillClassBean;
import com.syon.isrms.Beans.FillExamBean;
import com.syon.isrms.Beans.FillSectionBean;
import com.syon.isrms.Beans.FillSubjectBean;
import com.syon.isrms.Beans.ISRMSSelectSchoolBean;
import com.syon.isrms.Beans.LibraryStatusBean;
import com.syon.isrms.Beans.LoginBean;
import com.syon.isrms.Beans.MarkBean;
import com.syon.isrms.Beans.MyAttendanceBean;
import com.syon.isrms.Beans.ParentAttendanceDaywiseBean;
import com.syon.isrms.Beans.ParentComposeAttechmentBean;
import com.syon.isrms.Beans.ParentManageAccountAddBean;
import com.syon.isrms.Beans.ParentManageAccountBean;
import com.syon.isrms.Beans.ParentManageAccountDeleteBean;
import com.syon.isrms.Beans.ParentMedicalRecordBean;
import com.syon.isrms.Beans.ParentMonthPickBean;
import com.syon.isrms.Beans.ParentNotificationBean;
import com.syon.isrms.Beans.ResultAnalysisBean;
import com.syon.isrms.Beans.SalarySlipBean;
import com.syon.isrms.Beans.Teacher_Userbean;
import com.syon.isrms.Beans.UserbeanNews;
import com.syon.isrms.Beans.Userbean_Admin;
import com.syon.isrms.Beans.Userbean_Admin_Leave_Approval_Put;
import com.syon.isrms.Beans.Userbean_Admin_Leave_Get_1;
import com.syon.isrms.Beans.Userbean_Check_Version;
import com.syon.isrms.Beans.Userbean_Communication_Notification;
import com.syon.isrms.Beans.Userbean_LeaveMain;
import com.syon.isrms.Beans.Userbean_Notification_Communication;
import com.syon.isrms.Beans.Userbean_Notification_Main;
import com.syon.isrms.Beans.Userbean_ParentInbox_Main;
import com.syon.isrms.Beans.Userbean_ParentOutbox_Main;
import com.syon.isrms.Beans.Userbean_Parent_Class;
import com.syon.isrms.Beans.Userbean_Parent_Section;
import com.syon.isrms.Beans.Userbean_Parent_Student;
import com.syon.isrms.Beans.Userbean_SMS_Main;
import com.syon.isrms.Beans.Userbean_Save;
import com.syon.isrms.Beans.Userbean_Send;
import com.syon.isrms.Beans.Userbean_Submit_Leave;
import com.syon.isrms.Beans.Userbean_adminparent_main;
import com.syon.isrms.Beans.Userbean_ctParent_main;
import com.syon.isrms.Beans.Userbean_stParent_main;
import com.syon.isrms.Beans.Userbean_type_Main;
import com.syon.isrms.Beans.ViewHomeworkBean;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterfce {
    @GET("TchrTeacherPersonalInfo")
    Call<Example> Login(@Query("lEmpId") String lEmpId, @Query("sSchCode") String sSchCode);

    @FormUrlEncoded
    @POST("TchrTeacherLogin")
    Call<LoginBean> getLoginData(@Field("user") String user, @Field("pwd") String pwd, @Field("logintype") String logintype, @Field("schoolcode") String schoolcode, @Field("deviceid") String deviceid, @Field("tokenno") String tokenno);

    @FormUrlEncoded
    @POST("TchrStudMarksEntry")
    Call<MarkBean> getMarksData(@Field("empid") String empid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    @FormUrlEncoded
    @POST("TchrStudAttndEntry")
    Call<AttendanceBEan> getAttendanceData(@Field("empid") String empid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    @GET("TchrCirculars")
    Call<CircularBean> getCircular(@Query("lDeptId") String lDeptId, @Query("nType") String nType, @Query("lsessionid") String lsessionid, @Query("sSchCode") String sSchCode);

    @GET("TchrNewsEvents")
    Call<UserbeanNews> getNews(@Query("lDeptId") String lDeptId, @Query("nType") String nType, @Query("lsessionid") String lsessionid, @Query("sSchCode") String sSchCode);

    @FormUrlEncoded
    @POST("TchrStudGeneralRemarkEntry")
    Call<CoRemarkBean> getRemarkSch(@Field("empid") String empid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    @FormUrlEncoded
    @POST("TchrStudCoSchRemarkEntry")
    Call<CoSchBean> getCoSch(@Field("empid") String empid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    @GET("TchrPendingLibBooks")
    Call<com.syon.isrms.Beans.IssueBookBean> getIssuedBookStatus(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrLibBookStatus")
    Call<LibraryStatusBean> getLibraryStatus(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrSalarySlip")
    Call<SalarySlipBean> getSalarySlip(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrFillSubTS")
    Call<FillSubjectBean> getFillSubject(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrFillSubTS")
    Call<FillClassBean> getFillClass(@Query("lEmpId") String lEmpId, @Query("lSubId") String lSubId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrFillSubTS")
    Call<FillSectionBean> getFillSection(@Query("lEmpId") String lEmpId, @Query("lSubId") String lSubId, @Query("lClassId") String lClassId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrUserCommTeacherInbox")
    Call<com.syon.isrms.Beans.InboxBean> getInbox(@Query("lToUserId") String lToUserId, @Query("nToUserType") String nToUserType, @Query("sSchCode") String sSchCode);

    @GET("TchrUserCommTeacherOutbox")
    Call<com.syon.isrms.Beans.SentBoxBean> getSentbox(@Query("lFromUserId") String lFromUserId, @Query("nFromUserType") String nFromUserType, @Query("sSchCode") String sSchCode);

    @GET("TchrMyAttendance")
    Call<MyAttendanceBean> getMyAttendance(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrResultAnalysis")
    Call<FillExamBean> getFillExam(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrResultAnalysis")
    Call<ResultAnalysisBean> getResultAnalysis(@Query("lEmpId") String lEmpId, @Query("lExamId") String lExamId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrResultAnalysis")
    Call<com.syon.isrms.Beans.ResultAnalysisDetailBean> getFillExamDeatil(@Query("lEmpId") String lEmpId, @Query("lExamId") String lExamId, @Query("TblId") String TblId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @FormUrlEncoded
    @POST("TchrClassAttendance")
    Call<ClassAttendanceBean> getClassAttendance(@Field("empid") String empid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    @GET("TchrEventCalendarYearly")
    Call<EventCalenderYearlyBean> getEventYearly(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrEventCalendarMonthly")
    Call<EventCalenderMonthlyBean> getEventMonthly(@Query("lEmpId") String lEmpId, @Query("nMonth") String nMonth, @Query("nYear") String nYear, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @FormUrlEncoded
    @POST("TchrHomeWorkEntry")
    Call<Userbean_Save> save(@Field("empid") String empid, @Field("dthomeworkdate") String dthomeworkdate, @Field("dtcompldate") String dtcompldate, @Field("subid") String subid, @Field("subject") String subject, @Field("classid") String classid, @Field("classname") String classname, @Field("secid") String secid, @Field("section") String section, @Field("title") String title, @Field("homework") String homework, @Field("attachment1") String attachment1, @Query("attachment2") String attachment2, @Query("attachment3") String attachment3, @Query("attachment4") String attachment4, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

/*    @Multipart
    @POST("retrofit_example/upload_multiple_files.php")
    Call <> uploadMulFile(@Part MultipartBody.Part file1, @Part MultipartBody.Part file2,@Part MultipartBody.Part file3,@Part MultipartBody.Part file4);*/

    @FormUrlEncoded
    @POST("TchrSaveLeaveApplication")
    Call<Userbean_Submit_Leave> submitleave(@Field("empid") String empid, @Field("fromdate") String fromdate, @Field("todate") String todate, @Field("leavetakenstr") String leavetakenstr, @Field("reason") String reason, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    @GET("TchrLeaveApplicationList")
    Call<Userbean_LeaveMain> tleave(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrLeaveStatus")
    Call<Userbean_type_Main> tleavestatus(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @FormUrlEncoded
    @POST("TchrUserCommSendMessage")
    Call<Userbean_Send> sendmail(@Field("empid") String empid, @Field("tousertype") String tousertype, @Field("touserids") String touserids, @Field("subject") String emsubject, @Field("message") String message, @Field("attachment") String attachment, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    @GET("UserCommAdminList")
    Call<Userbean_Admin> adminlist(@Query("sSchCode") String sSchCode);

    @GET("TchrUserCommStaffList")
    Call<Teacher_Userbean> teacherlist(@Query("sSchCode") String sSchCode);

    @GET("TchrFillTeacherSectionForClass")
    Call<Userbean_Parent_Section> sectionwise(@Query("lEmpId") String lEmpId, @Query("lClassId") String lClassId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrFillTeacherClass")
    Call<Userbean_Parent_Class> classwise(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrStudListForClassSec")
    Call<Userbean_Parent_Student> studentlist(@Query("lEmpId") String lEmpId, @Query("lClassId") String lClassId, @Query("lSecId") Integer lSecId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrHomeWorkView")
    Call<ViewHomeworkBean> getViewHomework(@Query("lEmpId") String lEmpId, @Query("lSubId") String lSubId, @Query("lClassId") String lClassId, @Query("lSecId") String lSecId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("TchrBirthdayList")
    Call<BirthdayBean> getBirthday(@Query("sSchCode") String sSchCode);

    @FormUrlEncoded
    @POST("StudFeeOnlinePayment")
    Call<com.syon.isrms.Beans.OnlinePaymentBean> getPayment(@Field("upid") String upid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);


    @GET("StudEventCalendarMonth")
    Call<com.syon.isrms.Beans.ParentEventYearlyBean> getParentEventYearly(@Query("lClassId") String lClassId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("StudEventCalendarMonth")
    Call<com.syon.isrms.Beans.ParentEventMonthlyBean> getParentEventMonthly(@Query("lClassId") String lClassId, @Query("nMonth") String nMonth, @Query("nYear") String nYear, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("StudBirthdayYearly")
    Call<com.syon.isrms.Beans.ParentBirthdayBean> studbirthday(@Query("lClassId") String lClassId, @Query("lSecId") String lSecId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    @GET("StudHealthStatus")
    Call<ParentMedicalRecordBean> getParentMedicalData(@Query("lUPIdNo") String lUPIdNo, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    //parent Comunication
    @GET("UserCommParentOutbox")
    Call<Userbean_ParentOutbox_Main> getParentOutbox(@Query("lFromUserId") String lFromUserId, @Query("nFromUserType") String nFromUserType, @Query("sSchCode") String sSchCode);

    @GET("UserCommParentInbox")
    Call<Userbean_ParentInbox_Main> getParentInbox(@Query("lToUserId") String lToUserId, @Query("nToUserType") String nToUserType, @Query("sSchCode") String sSchCode);

    @GET("UserCommAdminList")
    Call<Userbean_adminparent_main> admincom(@Query("sSchCode") String sSchCode);


    @GET("UserCommClassTeacherList")
    Call<Userbean_ctParent_main> ct(@Query("lClassId") String lClassId, @Query("lSecId") String lSecId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    @GET("UserCommSubTeacherList")
    Call<Userbean_stParent_main> st(@Query("lClassId") String lClassId, @Query("lSecId") String lSecId, @Query("lStreamId") String lStreamId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @FormUrlEncoded
    @POST("UserCommSendMessage")
    Call<com.syon.isrms.Beans.Userbean_Parent_SendMail> getParentSendMail(@Field("upid") String upid, @Field("touserids") String touserids, @Field("subject") String subject, @Field("message") String message, @Query("attachment") String attachment, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    //Parent notification
    @GET("StudNotifications")
    Call<ParentNotificationBean> getParentNotification(@Query("lStudId") String lStudId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //teacher notification

    @GET("EmpNotifications")
    Call<Userbean_Notification_Main> notifyteacher(@Query("lEmpId") String lEmpId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    //my teacher
    @GET("ClassTeacherInfo")
    Call<com.syon.isrms.Beans.Userbean_MyClassTeacher_Main> classteacher(@Query("lClassId") String lClassId, @Query("lSecId") String lSecId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("SubjectTeacherInfo")
    Call<com.syon.isrms.Beans.Userbean_MySubjectTeacher_Main> subjectteacher(@Query("lClassId") String lClassId, @Query("lSecId") String lSecId, @Query("lStreamId") String lStreamId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    // parent sms
    @GET("StudSMSLog")
    Call<Userbean_SMS_Main> sms(@Query("lStudId") String lStudId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    @FormUrlEncoded
    @POST("FileUpload")
    Call<ParentComposeAttechmentBean> uploadParentAttachment(@Field("folder") String folder, @Field("file") File file);

    //parent news
    @GET("NewsEvents")
    Call<com.syon.isrms.Beans.Userbean_News_Main> newsparent(@Query("lClassId") String lClassId, @Query("nType") String nType, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //Parent Attandance daywise
    @FormUrlEncoded
    @POST("StudAttndDateWise")
    Call<ParentAttendanceDaywiseBean> getParentAttendaceDaywise(@Field("upid") String upid, @Field("sMonth") String sMonth, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    // Parent Attendance Yearly
    @GET("StudAttndYearWise")
    Call<com.syon.isrms.Beans.ParentAttendanceYealyBean> GetParentAttendanceYearly(@Query("lUPIdNo") String lUPIdNo, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    // Parent Attendance Monthly
    @GET("StudAttndMonthWise")
    Call<com.syon.isrms.Beans.ParentAttendanceMonthlyBean> GetParentAttendanceMonthly(@Query("lUPIdNo") String lUPIdNo, @Query("sMonth") String sMonth, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("StudAttndStEndMonth")
    Call<ParentMonthPickBean> GetParentMonthPicker(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //send msg notification
    @FormUrlEncoded
    @POST("SendEmpNotifications")
    Call<Userbean_Notification_Communication> getParentSendNotificationComunication(@Field("sEmpIds") String sEmpIds, @Field("sBody") String sBody, @Field("nNotifyType") String nNotifyType, @Field("sNotifyType") String sNotifyType, @Field("sSchName") String sSchName, @Field("lUserId") String lUserId, @Field("lSessionId") String lSessionId, @Field("sSchCode") String sSchCode);

    //show date in homework
    @GET("StudHomeWorkDates")
    Call<com.syon.isrms.Beans.ParentHomeworkDateBean> getParentHomeworkDates(@Query("lClassId") String lClassId, @Query("lSecId") String lSecId, @Query("nMonth") String nMonth, @Query("nYear") String nYear, @Query("lsessionId") String lsessionId, @Query("sSchCode") String sSchCode);

    // share and rate teacher & Parent Both
    @GET("ShareRateUsApp")
    Call<com.syon.isrms.Beans.UserbeanShareRate> share();

    //Teacher Homework notification
    @FormUrlEncoded
    @POST("StudHWNotifications")
    Call<com.syon.isrms.Beans.Userbean_homework_Notification> notifyhomework(@Field("lClassId") String lClassId, @Field("lSecId") String lSecId, @Field("sBody") String sBody, @Field("nNotifyType") String nNotifyType, @Field("sNotifyType") String sNotifyType, @Field("sSchName") String sSchName, @Field("lUserId") String lUserId, @Field("lSessionId") String lSessionId, @Field("sSchCode") String sSchCode);

    //Teacher to parent notification
    @FormUrlEncoded
    @POST("SendStudNotifications")
    Call<Userbean_Communication_Notification> notifycommunication(@Field("sStudIds") String sStudIds, @Field("sBody") String sBody, @Field("nNotifyType") String nNotifyType, @Field("sNotifyType") String sNotifyType, @Field("sSchName") String sSchName, @Field("lUserId") String lUserId, @Field("lSessionId") String lSessionId, @Field("sSchCode") String sSchCode);


    //Admin_Login APi
    @FormUrlEncoded
    @POST("UserAdmin")
    Call<AdminLoginBean> getAdminLogin(@Field("user") String user, @Field("pwd") String pwd, @Field("logintype") String logintype, @Field("schoolcode") String schoolcode);

    //Admin class result fill exam
    @GET("AdminClassResultAnalysis")
    Call<AdminClassResultFillExamBean> getAdminFillExam(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //Admin class result selected exam
    @GET("AdminClassResultAnalysis")
    Call<AdminClassResultSelectedExamBean> getAdminSelectedExam(@Query("lExamId") String lExamId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //Admin class result selected exam
    @GET("AdminClassResultAnalysis")
    Call<AdminClassResultSelectedDetailExamBean> getAdminSelectedDeatilExam(@Query("lExamId") String lExamId, @Query("lTblId") String lTblId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //Admin class Attendance
    @GET("AdminClassWiseAttndPercentage")
    Call<AdminClassAttendanceBean> getAdminClassAttendance(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //Admin class teacher Result Fill Teacher
    @GET("AdminTeacherResultAnalysis")
    Call<AdminClassTeacherResultFillTeacherBean> getAdminClassTeacherFill(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    //Admin teacher Result detials
    @GET("AdminTeacherResultAnalysis")
    Call<Admin_TeacherResultDetails_Main> getteacherresultdetails(@Query("lTeacherId") String lTeacherId, @Query("lExamId") String lExamId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //Admin Teacher detial click
    @GET("AdminTeacherResultAnalysis")
    Call<Admin_DetailClick_Main> detailsclick(@Query("lTeacherId") String lTeacherId, @Query("lExamId") String lExamId, @Query("TblId") String TblId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //Admin Toppers List
    @GET("AdminClassExamTopperList")
    Call<AdminToppersListBean> getAdminToppersList(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    //Admin School Strength List
    @GET("AdminSchStrengthSumry")
    Call<AdminSchoolStrengthBean> getAdminSchoolStrength(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    //Admin Monthly salary slip
    @GET("AdminMonthSalSumry")
    Call<Admin_Monthly_Salary> monthsalary(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    @GET("AdminMonthSalSumry")
    Call<Admin_Department_Salary> departmentsalary(@Query("lMESDId") String lMESDId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //admin school strength detail
    @FormUrlEncoded
    @POST("AdminSchStrengthClassWise")
    Call<AdminSchoolStrengthWebBean> getAdminSchoolStrengthWeb(@Field("username") String username, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    //admin topper list detail
    @FormUrlEncoded
    @POST("AdminTopperList")
    Call<Admin_Userbean_ToopersList> topperslistweb(@Field("Username") String Username, @Field("classid") String classid, @Field("streamid") String streamid, @Field("examid") String examid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);


    //admin AdminEmpAttendance detail
    @FormUrlEncoded
    @POST("AdminEmpAttendance")
    Call<Admin_Userbean_Emplyoee> employeweb(@Field("Username") String Username, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);


    //School select
    @GET("SchoolSelect")
    Call<ISRMSSelectSchoolBean> getSchoolsNeme();

    //mobile app version check
    @GET("MobAppVersion")
    Call<Userbean_Check_Version> versioncheck(@Query("sSchCode") String sSchCode);


    //get Added account in parent
    @GET("ParentMultiAcnt")
    Call<ParentManageAccountBean> getLoggedAcocunt(@Query("lStudId") String lStudId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    // Add account in parent
    @FormUrlEncoded
    @POST("ParentMultiAcnt")
    Call<ParentManageAccountAddBean> getAddAccount(@Field("loginstudid") String loginstudid, @Field("studid") String studid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    //delete Added account in parent
    @DELETE("ParentMultiAcnt")
    Call<ParentManageAccountDeleteBean> getDeleteLoggedAcocunt(@Query("lLoginStudId") String lLoginStudId, @Query("lStudId") String lStudId, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);


    //admin daily Report

    @GET("AdminAcadDailyTxnCnt")
    Call<AdminDailyReportBean> getDailyReport();

    //admin daily school collection

    @GET("AdminDailySchoolCollection")
    Call<AdminDailySchoolCollectionBean> getAdminDailySchoolCollection();


    //admin Yearly Acadmic Report

    @GET("AdminAcadYearlyTxnCnt")
    Call<AdminYearlyAcadmicReportBean> getYearlyReport(@Query("lSessionId") String lSessionId);

    //admin Yearly school collection

    @GET("AdminYearlySchoolCollection")
    Call<AdminYearlySchoolCollectionBean> getAdminYearlySchoolCollection(@Query("lSessionId") String lSessionId);


    //admin Yearly Outstanding Report

    @GET("AdminYearlySchoolOutstandingFHeadWise")
    Call<AdminYearlyOutstandingBean> getYearlyOutstandingReport(@Query("lSessionId") String lSessionId);

    //admin Yearly Refund

    @GET("AdminYearlyRefundFHeadWise")
    Call<AdminYearlyRefundBean> getAdminYearlyRefund(@Query("lSessionId") String lSessionId);

    //admin Fill Class
    @GET("AdminFillClassDept")
    Call<AdminClassFillBean> getAdminFillClass(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //admin Fill Deparment
    @GET("AdminFillClassDept")
    Call<AdminDepartmentFillBean> getAdminFillDepartment(@Query("sSchCode") String sSchCode);


    // admin news/events
    @FormUrlEncoded
    @POST("AdminNewsEventsEntry")
    Call<AdminNewsBean> postNewsEvents(@Field("empid") String empid, @Field("dtnewseventsdate") String dtnewseventsdate, @Field("title") String title, @Field("description") String description, @Field("attachment") String attachment, @Field("aprvuserid") String aprvuserid, @Field("naprvstatus") String naprvstatus, @Field("sclassids") String sclassids, @Field("sdeptids") String sdeptids, @Field("ntype") String ntype, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    // admin circular
    @FormUrlEncoded
    @POST("AdminCircularsEntry")
    Call<AdminCircularBean> postCircular(@Field("empid") String empid, @Field("dtcirdate") String dtcirdate, @Field("title") String title, @Field("description") String description, @Field("attachment") String attachment, @Field("aprvuserid") String aprvuserid, @Field("naprvstatus") String naprvstatus, @Field("sclassids") String sclassids, @Field("sdeptids") String sdeptids, @Field("ntype") String ntype, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    //admin view news/events
    @GET("AdminNewsEventsEntry")
    Call<AdminNewsViewBean> getAdminNewsView(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //admin view circular
    @GET("AdminCircularsEntry")
    Call<AdminCircularViewBean> getAdminCircualrView(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //admin leave approval
    @GET("AdminLeaveApproval")
    Call<Userbean_Admin_Leave_Get_1> leaveaprovedadmin(@Query("nApproveStatus") String nApproveStatus, @Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //admin leave approval put(update)
    @FormUrlEncoded
    @PUT("AdminLeaveApproval")
    Call<Userbean_Admin_Leave_Approval_Put> putleaveadmin(@Field("lLeaveAppId") String lLeaveAppId, @Field("nLeaveAprvStatus") int nLeaveAprvStatus, @Field("sAdminRemark") String sAdminRemark, @Field("empid") String empid, @Field("lSessionId") String lSessionId, @Field("sSchCode") String sSchCode);

    //admin discussion board
    @GET("DiscussionBoard")
    Call<Bean_Admin_Discussion_Board_One> admindiscussion(@Query("lSessionId") String lSessionId, @Query("sSchCode") String sSchCode);

    //teacher discussion board
    //admin class timetable
    @FormUrlEncoded
    @POST("DiscussionBoard")
    Call<Bean_Admin_Discussion_Board_Post> admindiscussionpost(@Field("empid") String empid, @Field("ntype") String ntype, @Field("message") String message, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);



    //admin class timetable
    @FormUrlEncoded
    @POST("AdminClassTTEntry")
    Call<AdminClassTimetableBean> getAdminClassTimetable(@Field("username") String username, @Field("empid") String empid, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);


    //admin post news notification for class
    @FormUrlEncoded
    @POST("SendStudMultiClassNotifications")
    Call<AdminNewsNotificationBean> postAdminNewsNotification(@Field("sClassIds") String sClassIds,@Field("sBody") String sBody, @Field("nNotifyType") String nNotifyType, @Field("sNotifyType") String sNotifyType, @Field("sSchName") String sSchName, @Field("lUserId") String lUserId, @Field("lSessionId") String lSessionId, @Field("sSchCode") String sSchCode);


    //admin post news notification for department
    @FormUrlEncoded
    @POST("SendEmpMultiDeptNotifications")
    Call<AdminNewsNotificationBean> postAdminNewsNotificationDept(@Field("sDeptIds") String sDeptIds,@Field("sBody") String sBody, @Field("nNotifyType") String nNotifyType, @Field("sNotifyType") String sNotifyType, @Field("sSchName") String sSchName, @Field("lUserId") String lUserId, @Field("lSessionId") String lSessionId, @Field("sSchCode") String sSchCode);

    //upload file
    @FormUrlEncoded
    @POST("FileUpload")
    Call<ParentComposeAttechmentBean> uploadParentAttachment(@Field("folder") String folder, @Field("file") String file);


    @FormUrlEncoded
    @PUT("AdminChangePassword")
    Call<AdminChangePasswordBean> getChangePasswordAdmin(@Field("empid") String empid, @Field("oldpwd") String oldpwd, @Field("newpwd") String newpwd, @Field("confirmpwd") String confirmpwd, @Field("sessionid") String sessionid, @Field("schoolcode") String schoolcode);

    //admin Yearly Refund

    @GET("AdminHomePic")
    Call<AdminHeaderImageBean> getAdminHeaderImage();

}


