package com.example.davidang.letsgetthisbread;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final int ITEMS = 3;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){
            case 0: return new AllFinances();
            case 1: return new MainScreen();
            case 2: return new Statistics();
            default: return new MainScreen();
        }
    }

    @Override
    public int getCount() {
        return ITEMS;
    }
}

