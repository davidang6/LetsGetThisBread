package com.example.davidang.letsgetthisbread;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class RPagerAdapter extends FragmentStatePagerAdapter {

    private static final int numItems = 3;
    private Fragment currFrag;
    public RPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        switch(i) {
            case 0:
                currFrag = new DateSelector();
                break;
            case 1:
            default:
                currFrag = new Transactions();
                break;
            case 2:
                currFrag = new Statistics();
                break;

        }
        return currFrag;
    }

    public FragWithReference getCurrentFragment() {
        return (FragWithReference) currFrag;
    }

    public void setCurrFrag(int i) {
        getItem(i);
    }

    @Override
    public int getCount() {
        return numItems;
    }
    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }
}

