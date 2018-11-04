package com.example.davidang.letsgetthisbread;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.widget.TextView;



import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;


public abstract class FragWithReference extends Fragment {

    protected static HashMap<String, ArrayList<Transaction>> allTransactions = new HashMap<>();

    protected static ViewPager pager;
    protected static RPagerAdapter adapter;

    protected static Calendar calendar = Calendar.getInstance();
    protected TextView dateView;

    // Dates
    protected int currentYear = calendar.get(Calendar.YEAR);
    protected int currentMonth = calendar.get(Calendar.MONTH);
    protected int dayOfMonth = calendar.get(Calendar.DATE);
    protected static String date;


    protected static MainActivity ref;



    protected void setRefs(){
        // Get HashMap of all transactions from main activity
        ref = (MainActivity) getActivity();
        this.allTransactions = ref.getAllTransactions();
        this.pager = ref.getPager();
        this.adapter = ref.getAdapter();
        this.date = getDate(currentMonth,dayOfMonth,currentYear);
    }


    public void setDate(int month, int day, int year){
        this.date = getDate(month,day,year);
    }

    public void setDate(String date){
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    protected String getDate(int month, int day, int year){
        return String.format("%d / %d / %d", month + 1, day, year);
    }






}

