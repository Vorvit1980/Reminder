package com.example.vorvit1980.reminder.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.example.vorvit1980.reminder.fragment.CurrentTaskFragment;
import com.example.vorvit1980.reminder.fragment.DoneTaskFragment;

/**
 * Created by vorvit1980 on 31.01.2017.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public TabAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs=numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CurrentTaskFragment();
            case 1:
                return new  DoneTaskFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
