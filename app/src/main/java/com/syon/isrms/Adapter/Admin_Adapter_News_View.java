package com.syon.isrms.Adapter;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
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

import com.syon.isrms.Beans.Admin_News_View_Bean_Data;
import com.syon.isrms.Beans.UserbeandataNews;
import com.syon.isrms.R;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Admin_Adapter_News_View extends RecyclerView.Adapter<Admin_Adapter_News_View.ViewHolder> {
    List<Admin_News_View_Bean_Data> data;
    Context context;
    ProgressDialog progressDialog;

    public Admin_Adapter_News_View(Context context, List<Admin_News_View_Bean_Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view_news_api_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.headingnews.setText(data.get(position).getSTitle().toString());
        holder.dateneews.setText(data.get(position).getSNEDate().toString());
        holder.discriptionnews.setText(data.get(position).getSDescp().toString());
        holder.news_status.setText("Status: "+data.get(position).getSAprvStatus().toString());
        String download = data.get(position).getSAttachment().toString();
        if (TextUtils.isEmpty(download)) {
            holder.download.setVisibility(View.GONE);

        } else {
            holder.download.setVisibility(View.VISIBLE);
            holder.view.setVisibility(View.VISIBLE);

        }
        if (holder.discriptionnews.length() > 58) {
            holder.readmore.setVisibility(View.VISIBLE);
        } else {
            holder.readmore.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);
        }
        holder.readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.teacher_alert);
                TextView tx=(TextView)dialog.findViewById(R.id.tx);
                TextView heading=(TextView)dialog.findViewById(R.id.heading);
                ImageView im=(ImageView)dialog.findViewById(R.id.cancel);
                tx.setText(data.get(position).getSDescp());
                heading.setText(data.get(position).getSTitle());
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setTitle(data.get(position).getSTitle());
                dialog.show();

            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadManager downloadManager;
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                final DownloadManager finalDownloadManager = (DownloadManager) v.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                builder.setMessage("Do you want to download the attachment?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new DownloadNewsFile().execute(data.get(position).getSAttachment());
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    if (data.get(position).getSAttachment().equalsIgnoreCase("")) {
                                        Toast.makeText(context, "There is no Attachment !!!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        com.syon.isrms.Broadcast_Recievers.MyReceiver myReceiver = new com.syon.isrms.Broadcast_Recievers.MyReceiver();
                                        context.registerReceiver(myReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                        Uri Download_Uri = Uri.parse(data.get(position).getSAttachment().trim());

                                        String fileExtension = data.get(position).getSAttachment().trim();

                                        String fileExt = fileExtension.substring(fileExtension.lastIndexOf("."));

                                        String fileNameWithoutExtension = fileExtension.substring(0, fileExtension.lastIndexOf('.'));

                                        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
                                        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                                        request.setAllowedOverRoaming(false);
                                        request.setTitle("Downloading " + fileNameWithoutExtension + fileExt);
                                        request.setDescription("Downloading " + fileNameWithoutExtension + fileExt);
                                        request.setVisibleInDownloadsUi(true);
                                        request.setDestinationInExternalPublicDir("/iSRMS", fileExt);

                                        finalDownloadManager.enqueue(request);

                                    }

                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                android.support.v7.app.AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView headingnews, dateneews, discriptionnews, readmore,news_status;
        ImageView download;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            headingnews = itemView.findViewById(R.id.headingnews);
            dateneews = itemView.findViewById(R.id.datenews);
            discriptionnews = itemView.findViewById(R.id.descriptionnews);
            readmore = itemView.findViewById(R.id.readmore);
            download = itemView.findViewById(R.id.btn_download);
            view=itemView.findViewById(R.id.view);
            news_status=itemView.findViewById(R.id.news_status);
        }
    }

    public class DownloadNewsFile extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            progressDialog = new ProgressDialog(context);
            // Set your progress dialog Title
            progressDialog.setTitle("");
            // Set your progress dialog Message
            progressDialog.setMessage("Downloading, Please Wait!");
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            // Show progress dialog
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... Url) {
            try {
                URL url = new URL(Url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // Detect the file lenghth
                int fileLength = connection.getContentLength();

                // Locate storage location
                String filepath = Environment.getExternalStorageDirectory()
                        .getPath();

                // Download the file
                InputStream input = new BufferedInputStream(url.openStream());

                // Save the downloaded file
                OutputStream output = new FileOutputStream(filepath + "/"
                        + "testimage.jpg");

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // Publish the progress
                    publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }

                // Close connection
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                // Error Log
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            progressDialog.setProgress(progress[0]);
            // Dismiss the progress dialog
            if (progressDialog.getProgress() == 100) {
                progressDialog.dismiss();
            }
            /* progressDialog.dismiss();*/
        }
    }
}
