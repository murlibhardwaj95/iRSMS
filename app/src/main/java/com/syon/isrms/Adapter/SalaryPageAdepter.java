package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Current_Session_Fragment;


public class SalaryPageAdepter extends FragmentStatePagerAdapter {
    String[] frag={"Current Session","Previous Session"} ;
    public SalaryPageAdepter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return frag[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new Current_Session_Fragment();

            case 1:     return new com.syon.isrms.Fragement.Previous_Session_Fragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return frag.length;
    }
}
