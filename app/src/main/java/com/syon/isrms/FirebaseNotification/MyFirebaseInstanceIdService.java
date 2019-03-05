package com.syon.isrms.FirebaseNotification;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;


import com.syon.isrms.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String REG_TOKEN="REG_TOKEN";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN,refreshedToken);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
       /* SharedPreferences sharedPreferences=getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);*/
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(getString(R.string.FCM_TOKEN),refreshedToken);
        editor.commit();
        //sendRegistrationToServer(refreshedToken);
    }
}
