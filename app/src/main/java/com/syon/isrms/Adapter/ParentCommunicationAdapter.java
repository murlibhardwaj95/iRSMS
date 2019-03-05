package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Parent_Class_Teacher_Fragment;
import com.syon.isrms.Fragement.Parent_Subject_Teacher;
import com.syon.isrms.Fragement.Parent_admin_Fragment;

public class ParentCommunicationAdapter extends FragmentStatePagerAdapter {
    String[] frag={"Admin","Class Teacher","Subject Teacher"} ;
    public ParentCommunicationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return frag[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new Parent_admin_Fragment();

            case 1:     return new Parent_Class_Teacher_Fragment();

            case 2:     return new Parent_Subject_Teacher();

        }
        return null;
    }

    @Override
    public int getCount() {
        return frag.length;
    }
}
