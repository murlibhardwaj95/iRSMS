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

import com.syon.isrms.Beans.Userbean_ParentOutbox_data;
import com.syon.isrms.Broadcast_Recievers.MyReceiver;
import com.syon.isrms.R;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class Parent_Outbox_Adapter extends RecyclerView.Adapter<Parent_Outbox_Adapter.Viewholder> {

    Context conte;
    List<Userbean_ParentOutbox_data> outbox;
    ProgressDialog progressDialog;
    public Parent_Outbox_Adapter(Context conte,List<Userbean_ParentOutbox_data> outbox) {
        this.conte = conte;
        this.outbox = outbox;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_inbox_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        holder.fromoutbox.setText(outbox.get(position).getToUser());
        holder.dateout.setText(outbox.get(position).getSDate());
        holder.subject.setText(outbox.get(position).getSSubject());
        holder.message.setText(outbox.get(position).getSDescription());


        String download = outbox.get(position).getSAttachment().toString();
        if (TextUtils.isEmpty(download)) {
            holder.download.setVisibility(View.GONE);
            holder.attachement.setText("None");
        } else {
            holder.download.setVisibility(View.VISIBLE);
            holder.attachement.setText(outbox.get(position).getSFileName());
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
                                new outbooxDownload().execute(outbox.get(position).getSAttachment());
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    if (outbox.get(position).getSAttachment().equalsIgnoreCase("")) {
                                        Toast.makeText(conte, "There is no Attachment !!!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        conte.registerReceiver(new MyReceiver(),new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                        Uri Download_Uri = Uri.parse(outbox.get(position).getSAttachment().trim());

                                        String fileExtension = outbox.get(position).getSAttachment().trim();

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

        holder.detailoutbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(conte);
                dialog.setContentView(R.layout.dialog_parent_communication);
                TextView tx=(TextView)dialog.findViewById(R.id.txsubject);
                TextView tx1 = dialog.findViewById(R.id.txdescription);
                TextView heading=(TextView)dialog.findViewById(R.id.fromdailog);
                ImageView im=(ImageView)dialog.findViewById(R.id.cancel);
                tx.setText(outbox.get(position).getSSubject());
                heading.setText(outbox.get(position).getToUser());
                tx1.setText(outbox.get(position).getSDescription());
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setTitle("Outbox");
                dialog.show();



                /*AlertDialog.Builder builder1 = new AlertDialog.Builder(conte);
                builder1.setTitle(outbox.get(position).getToUser()+"\n"+outbox.get(position).getSSubject().toString());
                builder1.setMessage(outbox.get(position).getSDescription().toString());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return outbox.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView fromoutbox,dateout;
        Button detailoutbox;
        ImageView download;
        EditText subject,message,attachement;
        public Viewholder(View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.setSubjectparent);
            message = itemView.findViewById(R.id.setDescriptionparent);
            fromoutbox = itemView.findViewById(R.id.setfrom);
            dateout = itemView.findViewById(R.id.setDate);
            download = itemView.findViewById(R.id.attachmentinbox);
            attachement = itemView.findViewById(R.id.setAttechmentparentparent);
            detailoutbox = itemView.findViewById(R.id.btn_detailparent);
        }
    }
    public class outbooxDownload extends AsyncTask<String, Integer, String> {

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
