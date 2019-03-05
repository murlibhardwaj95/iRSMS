package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Admin_Class_Result_Analysis;
import com.syon.isrms.Fragement.Admin_Class_Teacher_Result_Analysis;

public class Admin_Result_Analysis_Page_Adapter extends FragmentStatePagerAdapter {
    String[] frag={"Class Result","Teacher Result"} ;
    public Admin_Result_Analysis_Page_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return frag[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new Admin_Class_Result_Analysis();

            case 1:     return new Admin_Class_Teacher_Result_Analysis();



        }
        return null;
    }

    @Override
    public int getCount() {
        return frag.length;
    }
}
