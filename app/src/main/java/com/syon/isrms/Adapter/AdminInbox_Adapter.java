package com.syon.isrms.Adapter;


import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syon.isrms.Beans.Inbox_Bean_Data;
import com.syon.isrms.R;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class AdminInbox_Adapter extends RecyclerView.Adapter<AdminInbox_Adapter.Viewholder> {
    Context conte;
    List<Inbox_Bean_Data> list;
    ProgressDialog progressDialog;

    //List<Userbean_ParentInbox_data> list;
    public AdminInbox_Adapter(Context conte, List<Inbox_Bean_Data> list) {
        this.conte = conte;
      this.list=list;
        //this.list = list;
    }


    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_inboxoutbox_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {

        holder.frominbox.setText(list.get(position).getFromUser());
        holder.datein.setText(list.get(position).getSDate());
        holder.subjectin.setText(list.get(position).getSSubject());
        holder.messagein.setText(list.get(position).getSDescription());

        String download = list.get(position).getSAttachment().toString();
        if (TextUtils.isEmpty(download)) {
            holder.download.setVisibility(View.GONE);
            holder.attachementin.setText("None");
        } else {
            holder.download.setVisibility(View.VISIBLE);
            holder.attachementin.setText(list.get(position).getsFileName());
        }
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(conte);
                final DownloadManager finalDownloadManager = (DownloadManager) v.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                builder.setMessage("Do you want to download the attachment?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new inboxDowmload().execute(list.get(position).getSAttachment());
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    if (list.get(position).getSAttachment().equalsIgnoreCase("")) {
                                        Toast.makeText(conte, "There is no Attachment !!!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Uri Download_Uri = Uri.parse(list.get(position).getSAttachment().trim());

                                        String fileExtension = list.get(position).getSAttachment().trim();

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


        holder.detailinbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(conte);
                dialog.setContentView(R.layout.dialog_parent_communication);
                TextView tx = (TextView) dialog.findViewById(R.id.txsubject);
                TextView heading = (TextView) dialog.findViewById(R.id.fromdailog);
                TextView tx1 = dialog.findViewById(R.id.txdescription);
                ImageView im = (ImageView) dialog.findViewById(R.id.cancel);
                tx.setText(list.get(position).getSSubject());
                tx1.setText(list.get(position).getSDescription());
                heading.setText(list.get(position).getFromUser());
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setTitle("Inbox");
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView frominbox, datein;
        Button detailinbox;
        ImageView download;
        EditText subjectin, messagein, attachementin;

        public Viewholder(View itemView) {
            super(itemView);
            subjectin = itemView.findViewById(R.id.adsetSubjectparent);
            messagein = itemView.findViewById(R.id.adsetDescriptionparent);
            frominbox = itemView.findViewById(R.id.adsetfrom);
            datein = itemView.findViewById(R.id.adsetDate);
            attachementin = itemView.findViewById(R.id.adsetAttechmentparentparent);
            detailinbox = itemView.findViewById(R.id.adbtn_detailparent);
            download = itemView.findViewById(R.id.adattachmentinbox);
        }
    }

    public class inboxDowmload extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            progressDialog = new ProgressDialog(conte);
            // Set your progress dialog Title
            progressDialog.setTitle("Downloading File");
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
