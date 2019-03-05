package com.syon.isrms.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.syon.isrms.Activity.Splash;
import com.syon.isrms.Beans.Isrms_School_Select_Bean_Data;
import com.syon.isrms.Beans.UserbeandataNews;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.R;
import com.syon.isrms.urlsApimanage.ApiUrls;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class School_Chooser_Adapter extends RecyclerView.Adapter<School_Chooser_Adapter.ViewHolder> {
    List<Isrms_School_Select_Bean_Data> data;
    Context context;


    public School_Chooser_Adapter(Context context, List<Isrms_School_Select_Bean_Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_school_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.school_name.setText(data.get(position).getSSchName().toString()+", "+data.get(position).getSCity().toString());
        Picasso.with(context).load(data.get(position).getSLogoPath().toString()).into(holder.school_logo);
        holder.school_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("isrms_screen","show");
                editor.putString("isrms_school_code",data.get(position).getSSchCode().toString());
                editor.putString("BASE_URL",data.get(position).getSBaseURL().toString());
                editor.commit();


                ApiClient apiClient=new ApiClient(data.get(position).getSBaseURL().toString());
                ApiUrls  apiUrls=new ApiUrls(data.get(position).getSBaseURL().toString());

                context.startActivity(new Intent(context, Splash.class));

                ((Activity)context).finish();


            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView school_name;
        ImageView school_logo;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            school_name = itemView.findViewById(R.id.school_name);
            school_logo = itemView.findViewById(R.id.school_logo);

        }
    }

}
