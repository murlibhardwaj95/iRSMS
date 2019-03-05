package com.syon.isrms;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MassageReciver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action=intent.getAction();
        if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        {
            Toast.makeText(context, "Download Complete", Toast.LENGTH_SHORT).show();
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
