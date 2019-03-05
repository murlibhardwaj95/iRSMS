package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Teacher_Inbox_Fragment;
import com.syon.isrms.Fragement.Teacher_Sent_Fragment;


public class CommunicationPageAdepter extends FragmentStatePagerAdapter {
    String[] frag={"Inbox","Outbox"} ;
    public CommunicationPageAdepter(FragmentManager fm) {
        super(fm);
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return frag[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new Teacher_Inbox_Fragment();

            case 1:     return new Teacher_Sent_Fragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return frag.length;
    }
}
