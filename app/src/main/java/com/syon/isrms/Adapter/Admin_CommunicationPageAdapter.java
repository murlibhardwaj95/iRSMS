package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Admin_Comm_Inbox_Frag;
import com.syon.isrms.Fragement.Admin_Comm_Outbox_Frag;


public class Admin_CommunicationPageAdapter extends FragmentStatePagerAdapter {
    String[] frag={"Inbox","Outbox"} ;
    public Admin_CommunicationPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return frag[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Admin_Comm_Inbox_Frag();
            case 1: return new Admin_Comm_Outbox_Frag();



        }
        return null;
    }

    @Override
    public int getCount() {
        return frag.length;
    }}
