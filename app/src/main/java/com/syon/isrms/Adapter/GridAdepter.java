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

import com.syon.isrms.Activity.Library_Status;
import com.syon.isrms.Activity.Taacher_Gen_Remark_Entry;
import com.syon.isrms.Activity.Teacher_Attendance_Entry;
import com.syon.isrms.Activity.Teacher_Class_Attendance;
import com.syon.isrms.Activity.Teacher_Co_Sch_Remark_Entry;
import com.syon.isrms.Activity.Teacher_Homework;
import com.syon.isrms.Activity.Teacher_Marks_Entry;
import com.syon.isrms.Activity.Teacher_MyLeaves;
import com.syon.isrms.Activity.Teacher_My_Attendance;
import com.syon.isrms.Activity.Teacher_Profile;
import com.syon.isrms.Activity.Teacher_Result_Analysis;
import com.syon.isrms.Activity.Teacher_Salary_Slip;
import com.syon.isrms.R;

public class GridAdepter extends ArrayAdapter<String> {
    String[] names;
    int[] imgs;
    String[] emails;
    LayoutInflater inflater;

    public GridAdepter(@NonNull Context context, int resource, @NonNull String[] names, int[] imgs) {
        super(context, resource, names);
        this.names = names;
        this.imgs = imgs;
        this.emails = emails;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(final int position, View view, ViewGroup parent) {
        View v = inflater.inflate(R.layout.home_grid_layout, parent, false);
        ImageView image1 = v.findViewById(R.id.homeimages);
        TextView t1 = v.findViewById(R.id.hometext);
        image1.setImageResource(imgs[position]);
        t1.setText(names[position]);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        getContext().startActivity(new Intent(getContext(), Teacher_Profile.class));
                        break;
                    case 1:
                        getContext().startActivity(new Intent(getContext(), Teacher_Attendance_Entry.class));
                        break;
                    case 2:
                        getContext().startActivity(new Intent(getContext(), Teacher_Marks_Entry.class));
                        break;
                    case 3:
                        getContext().startActivity(new Intent(getContext(), Taacher_Gen_Remark_Entry.class));
                        break;
                    case 4:
                        getContext().startActivity(new Intent(getContext(), Teacher_Co_Sch_Remark_Entry.class));
                        break;
                    case 5:
                        getContext().startActivity(new Intent(getContext(), Teacher_Homework.class));
                        break;
                    case 6:
                        getContext().startActivity(new Intent(getContext(), Teacher_MyLeaves.class));
                        break;
                    case 7:
                        getContext().startActivity(new Intent(getContext(), Teacher_Salary_Slip.class));
                        break;
                    case 8:
                        getContext().startActivity(new Intent(getContext(), Teacher_My_Attendance.class));
                        break;
                    case 9:
                        getContext().startActivity(new Intent(getContext(), Teacher_Class_Attendance.class));
                        break;
                    case 10:
                        getContext().startActivity(new Intent(getContext(), Teacher_Result_Analysis.class));
                        break;
                    case 11:
                        getContext().startActivity(new Intent(getContext(), Library_Status.class));
                        break;

                  /*  case 0:
                        getContext().startActivity(new Intent(getContext(),Profile.class));

                        break;
                    case 1:
                        getContext().startActivity(new Intent(getContext(),Attandance_Entry.class));
                        break;
                    case 2:
                        getContext().startActivity(new Intent(getContext(),Marks_Entry.class));
                        break;
                    case 3:
                        getContext().startActivity(new Intent(getContext(),Co_Remark_Entry.class));
                        break;
                    case 4:
                        getContext().startActivity(new Intent(getContext(),Co_Sch_Remark_Entry.class));
                        break;
                    case 5:
                        getContext().startActivity(new Intent(getContext(),Homework.class));
                        break;
                    case 6:
                        getContext().startActivity(new Intent(getContext(),Marks_Entry.class));
                        break;
                    case 7:
                        getContext().startActivity(new Intent(getContext(),Salary_Slip.class));
                        break;
                    case 8:
                        getContext().startActivity(new Intent(getContext(),Marks_Entry.class));
                        break;
                    case 9:
                        getContext().startActivity(new Intent(getContext(),Marks_Entry.class));
                        break;
                    case 10:
                        getContext().startActivity(new Intent(getContext(),Marks_Entry.class));
                        break;
                    */
                }

            }
        });
        return v;
    }
}