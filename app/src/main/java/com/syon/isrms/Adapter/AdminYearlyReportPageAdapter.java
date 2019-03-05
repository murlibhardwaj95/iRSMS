package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Admin_Yearly_Acadmic_Fragment;
import com.syon.isrms.Fragement.Admin_Yearly_Collection_Fragment;
import com.syon.isrms.Fragement.Admin_Yearly_Outstanding_Fragment;


public class AdminYearlyReportPageAdapter extends FragmentStatePagerAdapter {
    String[] fragment = {"Academic","Collection","Outstanding"};

    public AdminYearlyReportPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public CharSequence getPageTitle(int position) {
        return fragment[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new Admin_Yearly_Acadmic_Fragment();

            case 1:   return new Admin_Yearly_Collection_Fragment();

            case 2: return new Admin_Yearly_Outstanding_Fragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return fragment.length;
    }
}
