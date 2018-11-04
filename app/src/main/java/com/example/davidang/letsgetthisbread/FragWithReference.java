package com.example.davidang.letsgetthisbread;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;


public abstract class FragWithReference extends Fragment{

    protected HashMap<String, ArrayList<Transaction>> allTransactions = new java.util.HashMap<>();

    protected ViewPager pager;
    protected PagerAdapter adapter;

    protected Calendar calendar = Calendar.getInstance();
    protected TextView dateView;

    // Dates
    protected int currentYear = calendar.get(Calendar.YEAR);
    protected int currentMonth = calendar.get(Calendar.MONTH) + 1;
    protected int dayOfMonth = calendar.get(Calendar.DATE);
    protected String date;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        setRefs();
    }


    private void setRefs(){
        // Get HashMap of all transactions from main activity
        MainActivity ref = (MainActivity) getActivity();
        this.allTransactions = ref.getAllTransactions();
        this.pager = ref.getPager();
        this.adapter = ref.getAdapter();
        this.date = getDate(currentMonth,dayOfMonth,currentYear);
    }


    public void setDate(int month, int day, int year){
        date = String.format("%d / %d / %d", month, day, year);
    }

    public void setDate(String date){
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    protected String getDate(int month, int day, int year){
        return String.format("%d / %d / %d", month, day, year);
    }




}

