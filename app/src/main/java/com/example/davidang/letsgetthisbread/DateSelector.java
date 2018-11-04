package com.example.davidang.letsgetthisbread;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class DateSelector extends FragWithReference {
    private static CalendarView calendarView;
    private static TextView incomeView, expensesView, netView;
    private static int currDay;
    private static int currMonth;
    private static int currYear;
    private static Stats curr ;
    protected static View root;
    public DateSelector() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_all_finances, container, false);
        setRefs();
        createCalendar(root);
        return root;
    }

    private void createCalendar(@NonNull View view){
        incomeView = view.findViewById(R.id.incomeView);
        expensesView = view.findViewById(R.id.expensesView);
        netView = view.findViewById(R.id.netView);

        //Create arraylist for current date
        ArrayList<Transaction> data = allTransactions.get(date);
        //sum all transactions for date
        double income = 0;
        double expenses = 0;
        for(int j = 0; data != null && j < data.size(); j++){
            double value = data.get(j).getValue();
            //Sum all positive values
            if(value > 0){
                income += value;
            }
            //Sum all negative values
            else {
                expenses += value;
            }
        }
        curr = new Stats(income, expenses, income + expenses);
        incomeView.setText(curr.incomeString());
        incomeView.setTextColor(Color.GREEN);
        expensesView.setText(curr.expensesString());
        expensesView.setTextColor(Color.RED);
        netView.setText(curr.differenceString());
        if(netView.getText().toString().contains("-")){
            netView.setTextColor(Color.RED);
        } else {
            netView.setTextColor(Color.GREEN);
        }

        Button goToTransaction = view.findViewById(R.id.button);
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setDate(Calendar.getInstance().getTimeInMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView cView, int year, int month, int day) {

                double income = 0;
                double expenses = 0;
                //Create arraylist for current date
                ArrayList<Transaction> data = allTransactions.get(getDate(month, day, year));
                //sum all transactions for date
                for(int j = 0; data != null && j < data.size(); j++){
                    double value = data.get(j).getValue();
                    //Sum all positive values
                    if(value > 0){
                        income += value;
                    }
                    //Sum all negative values
                    else {
                        expenses += value;
                    }
                }
                currYear = year;
                currDay = day;
                currMonth = month;
                curr = new Stats(income, expenses, income + expenses);
                incomeView.setText(curr.incomeString());
                incomeView.setTextColor(Color.GREEN);
                expensesView.setText(curr.expensesString());
                expensesView.setTextColor(Color.RED);
                netView.setText(curr.differenceString());
                if(netView.getText().toString().contains("-")){
                    netView.setTextColor(Color.RED);
                } else {
                    netView.setTextColor(Color.GREEN);
                }
            }
        });
        /*
        dateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTransactionDate(currMonth,currDay,currYear);
            }
        });
        */
        goToTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTransactionDate(currMonth,currDay,currYear);
            }
        });
    }


    private void changeTransactionDate(int month, int day, int year){
        // 1 = Transactions List
        pager.setCurrentItem(1);
        RPagerAdapter temp = (RPagerAdapter) pager.getAdapter();
        temp.setCurrFrag(1);
        FragWithReference currPage = temp.getCurrentFragment();
        currPage.setDate(month, day, year);
        if(currPage instanceof Transactions){
            ((Transactions) currPage).loadData();
            ((Transactions) currPage).updateDate();
        }
    }
}
