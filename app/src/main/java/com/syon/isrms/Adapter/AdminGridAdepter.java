package com.syon.isrms.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.syon.isrms.Activity.Admin_Class_Attendance;
import com.syon.isrms.Activity.Admin_Class_Time_Table;
import com.syon.isrms.Activity.Admin_Communication;
import com.syon.isrms.Activity.Admin_Daily_Reports;
import com.syon.isrms.Activity.Admin_Employee_Attendance;
import com.syon.isrms.Activity.Admin_Event_Calender;
import com.syon.isrms.Activity.Admin_Leave_Approval;
import com.syon.isrms.Activity.Admin_Monthly_Salary_Summary;
import com.syon.isrms.Activity.Admin_Result_Analysis;
import com.syon.isrms.Activity.Admin_School_Strength;
import com.syon.isrms.Activity.Admin_Topper_List;
import com.syon.isrms.Activity.Admin_Yearly_Report;
import com.syon.isrms.R;

public class AdminGridAdepter extends ArrayAdapter<String> {
    String[] names;
    int[] imgs;
    String[] emails;
    LayoutInflater inflater;

    public AdminGridAdepter(@NonNull Context context, int resource, @NonNull String[] names, int[] imgs) {
        super(context, resource, names);
        this.names = names;
        this.imgs = imgs;
        this.emails = emails;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(final int position, View view, ViewGroup parent) {
        View v = inflater.inflate(R.layout.admin_dashboard_grid_layout, parent, false);
        //ImageView image1 = v.findViewById(R.id.homeimages);
        TextView t1 = v.findViewById(R.id.hometext);
    //    image1.setImageResource(imgs[position]);
        t1.setText(names[position]);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        getContext().startActivity(new Intent(getContext(), Admin_Daily_Reports.class));
                        break;
                    case 1:
                        getContext().startActivity(new Intent(getContext(), Admin_Yearly_Report.class));
                        break;
                    case 2:
                        getContext().startActivity(new Intent(getContext(),Admin_School_Strength.class));
                        break;
                    case 3:
                        getContext().startActivity(new Intent(getContext(),Admin_Result_Analysis.class));
                        break;
                    case 4:
                        getContext().startActivity(new Intent(getContext(),Admin_Topper_List.class));
                        break;
                    case 5:
                        getContext().startActivity(new Intent(getContext(),Admin_Leave_Approval.class));
                        break;
                    case 6:
                        getContext().startActivity(new Intent(getContext(), Admin_Event_Calender.class));
                        break;
                    case 7:
                        getContext().startActivity(new Intent(getContext(), Admin_Monthly_Salary_Summary.class));
                        break;
                    case 8:
                        getContext().startActivity(new Intent(getContext(),Admin_Class_Time_Table.class));
                        break;
                    case 9:
                        getContext().startActivity(new Intent(getContext(),Admin_Class_Attendance.class));
                        break;
                    case 10:
                        getContext().startActivity(new Intent(getContext(), Admin_Employee_Attendance.class));
                        break;
                    case 11:
                        getContext().startActivity(new Intent(getContext(), Admin_Communication.class));
                        break;
                }

            }
        });
        return v;
    }
}