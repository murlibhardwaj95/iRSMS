package com.syon.isrms.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syon.isrms.Beans.ParentAttendanceDaywiseBean;
import com.syon.isrms.Interfaces_Teacher.ApiClient;
import com.syon.isrms.Interfaces_Teacher.ApiInterfce;
import com.syon.isrms.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calendar_No_Data_Recycle_Adapter extends RecyclerView.Adapter<Calendar_No_Data_Recycle_Adapter.ViewHolder> {

    Context context;
    String[] date;
    int daysofmonth;
    String month;
    int pos=-1;
    int firstday;



    public Calendar_No_Data_Recycle_Adapter(Context context, String[] date, int daysofmonth, String month,int firstday) {
        this.context = context;
        this.date = date;
        this.daysofmonth = daysofmonth;
        this.month = month;
        this.firstday=firstday;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.calender_custom_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if(position>=firstday) { // in bind holder for date set
            try {
                pos=position-(firstday);
                if (Integer.parseInt(date[pos]) > daysofmonth) {
                    holder.date.setVisibility(View.GONE);
                    holder.circle.setVisibility(View.GONE);
                } else {
                    holder.date.setText(date[pos].toString());
                    holder.circle.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {

            }
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        ApiInterfce apiInterfce = ApiClient.getApiClient(builder).create(ApiInterfce.class);
        Call<ParentAttendanceDaywiseBean> parentAttendanceDaywiseBeanCall = apiInterfce.getParentAttendaceDaywise(preferences.getString(context.getString(R.string.lUPIdNo), ""), month, preferences.getString(context.getString(R.string.lSessionIdNo), ""), preferences.getString(context.getString(R.string.sSchCode), ""));
        parentAttendanceDaywiseBeanCall.enqueue(new Callback<ParentAttendanceDaywiseBean>() {
            @Override
            public void onResponse(Call<ParentAttendanceDaywiseBean> call, Response<ParentAttendanceDaywiseBean> response) {
                if ((response.code() == 200)) {
                    if (position>=firstday)
                    {
                        try {
                            pos=position-(firstday);

                            if (response.body().getData().get(pos).getAttndStatus().equals("H")) {
                                holder.circle.setImageResource(R.drawable.blue_circle);
                            } else if (response.body().getData().get(pos).getAttndStatus().equals("A")) {
                                holder.circle.setImageResource(R.drawable.red_circle);
                            } else if (response.body().getData().get(pos).getAttndStatus().equals("P")) {
                                holder.circle.setImageResource(R.drawable.green_circle);
                            } else if (response.body().getData().get(pos).getAttndStatus().equals("S")) {
                                holder.circle.setImageResource(R.drawable.blue_circle);
                            } else if (response.body().getData().get(pos).getAttndStatus().equals("L")) {
                                holder.circle.setImageResource(R.drawable.yellow_circle);
                            } else if (response.body().getData().get(pos).getAttndStatus().equals("M")) {
                                holder.circle.setImageResource(R.drawable.purple__circle);
                            } else if (response.body().getData().get(pos).getAttndStatus().equals("D")) {
                                holder.circle.setImageResource(R.drawable.orange__circle);
                            } else {

                            }
                        } catch (Exception e) {
                        }

                    } else {

                    }
                }



            }

            @Override
            public void onFailure(Call<ParentAttendanceDaywiseBean> call, Throwable t) {
                Toast.makeText(context, "check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return date.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView circle;


        public ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.cal_date);
            circle = itemView.findViewById(R.id.cus_cal_circle);

        }


    }
}