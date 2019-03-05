package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Admin_Collection_Fragment;

public class AdminDailyReportAdapter extends FragmentStatePagerAdapter {

    String[] fragment = {"Academic","Collection"};

    public AdminDailyReportAdapter(FragmentManager fm) {
        super(fm);
    }
    public CharSequence getPageTitle(int position) {
        return fragment[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new com.syon.isrms.Fragement.Admin_Acadmic_Fragment();

            case 1:   return new Admin_Collection_Fragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return fragment.length;
    }
}
