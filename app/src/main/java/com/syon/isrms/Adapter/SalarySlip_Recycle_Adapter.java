package com.syon.isrms.Adapter;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.syon.isrms.Beans.SalarySlipData;
import com.syon.isrms.R;

import java.util.List;

import static android.content.Context.DOWNLOAD_SERVICE;

public class SalarySlip_Recycle_Adapter extends RecyclerView.Adapter<SalarySlip_Recycle_Adapter.ViewHolder> {
    List<SalarySlipData> data;
    Context context;
    ProgressDialog progressDialog;

    public SalarySlip_Recycle_Adapter(Context context, List<SalarySlipData> data) {
        this.context = context;
        this.data = data;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.current_session_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.month.setText(data.get(position).getSMonth().toString()+" "+data.get(position).getLYear());
        holder.wd.setText("WD :"+data.get(position).getWd().toString());
        holder.basic.setText(context.getString(R.string.rupee)+""+data.get(position).getMBasic().toString());
        holder.allowance.setText(context.getString(R.string.rupee)+""+data.get(position).getMAllowance().toString());
        holder.gross_salary.setText(context.getString(R.string.rupee)+""+data.get(position).getMGrossSal().toString());
        holder.deduction.setText(context.getString(R.string.rupee)+""+""+data.get(position).getMDeduction().toString());
        holder.netsalary.setText(context.getString(R.string.rupee)+""+data.get(position).getMNetSal().toString());
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
             AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Do you want to download salary slip");
                alert.setCancelable(true);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        final ProgressDialog dialog1=new ProgressDialog(v.getContext());
                     /*   dialog1.show();
                        dialog1.setTitle("Downloading...");
                        dialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);*/


                        final WebView wv = new WebView(v.getContext());
                        wv.loadUrl(data.get(position).getSAttachment().toString());
                        wv.setWebViewClient(new WebViewClient() {
                            @Override
                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                view.loadUrl(url);

                                return true;
                            }
                        });
                        wv.setDownloadListener(new DownloadListener() {
                            @Override
                            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                                DownloadManager.Request request = new DownloadManager.Request(

                                        Uri.parse(url));
                                context.registerReceiver(new com.syon.isrms.Broadcast_Recievers.MyReceiver(),new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                                request.allowScanningByMediaScanner();
                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download");
                                DownloadManager dm = (DownloadManager) v.getContext().getSystemService(DOWNLOAD_SERVICE);
                                dm.enqueue(request);
                            }
                        });

                        wv.setWebViewClient(new WebViewClient() {

                            @Override
                            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                super.onPageStarted(view, url, favicon);
                              /*  dialog1.setMax(100);
                                dialog1.setTitle("");
                                dialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);*/
                              dialog1.setMessage("Downloading...");
                                dialog1.show();
                            }

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                dialog1.setProgress(wv.getProgress());
                                if (dialog1.isShowing()) {
                                    dialog1.dismiss();
                                }
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });






                alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alert.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView month, wd, basic, allowance, gross_salary, deduction, netsalary;
        Button download;
        WebView webView;

        public ViewHolder(View itemView) {
            super(itemView);

            month = itemView.findViewById(R.id.setMonth);
            wd = itemView.findViewById(R.id.setWorkingDay);
            basic = itemView.findViewById(R.id.setBasic);
            allowance = itemView.findViewById(R.id.setAllowance);
            gross_salary = itemView.findViewById(R.id.setGrossSalary);
            deduction = itemView.findViewById(R.id.setDeduction);
            netsalary = itemView.findViewById(R.id.setNetSalary);
            download = itemView.findViewById(R.id.getSlipAttechment);

        }
    }

}
