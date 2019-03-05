package com.syon.isrms.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.syon.isrms.Fragement.Parent_Inbox_Fragment;
import com.syon.isrms.Fragement.Parent_Outbox_Fragment;

public class Parent_Communication_Viewpaer_adapter extends FragmentStatePagerAdapter {
    String[] frag={"Inbox","Outbox"} ;
    public Parent_Communication_Viewpaer_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return frag[position];
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:   return new Parent_Inbox_Fragment();

            case 1:     return new Parent_Outbox_Fragment();


        }
        return null;
    }

    @Override
    public int getCount() {
        return frag.length;
    }
}
