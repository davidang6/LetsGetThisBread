package com.example.davidang.letsgetthisbread;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private static final int numItems = 3;
    private Fragment currFrag;
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        switch(i) {
            case 0:
                currFrag = new AllFinances();
                break;
            case 1:
            default:
                currFrag = new TransactionInput();
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




    @Override
    public int getCount() {
        return numItems;
    }
}

