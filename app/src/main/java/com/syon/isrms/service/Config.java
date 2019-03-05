package com.syon.isrms.service;

/**
 * Created by websharan11 on 2/8/2017.
 */

public class Config {

    private Config() {
    }
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "ah_firebase";
    public static final String YOUTUBE_API_KEY = "AIzaSyBGo0gdLOpcvEIGk4rCo-PAZznlofxH_y0";

}
