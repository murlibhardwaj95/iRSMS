package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Admin_Communication_frag;
import com.syon.isrms.Fragement.Parent_Communication_Frag;
import com.syon.isrms.Fragement.Teacher_Communication_Frag;


public class CoummunicationPageAdepter extends FragmentStatePagerAdapter {
    String[] frag={"Admin","Teacher","Parent"} ;
    public CoummunicationPageAdepter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return frag[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new Admin_Communication_frag();

            case 1:     return new Teacher_Communication_Frag();

            case 2: return new Parent_Communication_Frag();

        }
        return null;
    }

    @Override
    public int getCount() {
        return frag.length;
    }
}
