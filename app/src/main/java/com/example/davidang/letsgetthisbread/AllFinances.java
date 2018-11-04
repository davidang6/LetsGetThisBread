package com.example.davidang.letsgetthisbread;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CalendarView;

import java.util.Calendar;

public class AllFinances extends FragWithReference {
    private CalendarView calendarView;

    public AllFinances() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        createCalendar(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_all_finances, container, false);
    }

    private void createCalendar(@NonNull View view){
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setDate(Calendar.getInstance().getTimeInMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView cView, int year, int month, int day) {
                // 1 = Transactions List
                pager.setCurrentItem(1);
                PagerAdapter temp = (PagerAdapter) pager.getAdapter();
                temp.getItem(1);
                FragWithReference currPage = temp.getCurrentFragment();
                currPage.setDate(month + 1, day, year);
                System.out.println(currPage.getDate());
                System.out.println(currPage instanceof TransactionInput);
                System.out.println(currPage instanceof AllFinances);
                System.out.println(currPage instanceof Statistics);
                System.out.println(currPage instanceof FragWithReference);
                if(currPage instanceof TransactionInput) {
                    ((TransactionInput) currPage).loadData(currPage.getView());
                    ((TransactionInput) currPage).updatePage();
                }

            }
        });
    }
}
