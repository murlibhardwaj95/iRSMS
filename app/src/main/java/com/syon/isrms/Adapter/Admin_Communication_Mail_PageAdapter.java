package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Admin_Comm_ParentList;
import com.syon.isrms.Fragement.Admin_Comm_TeacherList;
import com.syon.isrms.Fragement.Admin_Communication_frag;
import com.syon.isrms.Fragement.Parent_Communication_Frag;
import com.syon.isrms.Fragement.Teacher_Communication_Frag;

public class Admin_Communication_Mail_PageAdapter extends FragmentStatePagerAdapter {
    String[] frag={"Teacher","Parent"} ;
    public Admin_Communication_Mail_PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return frag[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new Admin_Comm_TeacherList();

            case 1:     return new Admin_Comm_ParentList();

        }
        return null;
    }

    @Override
    public int getCount() {
        return frag.length;
    }
}
