package com.example.davidang.letsgetthisbread;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Statistics extends FragWithReference {

    private static Stats daily; // Stats for Current Date
    private static Stats weekly; // Stats for Previous 7 Days
    private static Stats monthly; // Stats for Previous 30 Days
    /*
    public static HashMap<String, Double> data;
    */
    private static TextView dayNetView, dayIncomeView, dayExpensesView,
            weekNetView, weekIncomeView, weekExpensesView,
            monthNetView, monthIncomeView, monthExpensesView;
    private static double worstDay, worstMonth, bestDay, bestMonth;

    private String[] months = {
        "January", "February", "March", "April",
        "May", "June", "July", "August",
        "September", "October", "November", "December"
    };

    /*#TODO
     * CHANGE HEADER FROM "STATISTICS" TO "STATISTICS IN {CURRENT MONTH} {CURRENT YEAR}"
     * ADD VARIABLES INVOLVED WITH THE GUI
     * CREATE SET VARIABLE METHOD FOR ORGANIZATION
     * CALL SET VARIABLE METHOD IN onViewCreated
     */

    protected static  View root;

    public Statistics() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_statistics, container, false);

        dayNetView = root.findViewById(R.id.dayNetView);
        dayIncomeView = root.findViewById(R.id.dayIncomeView);
        dayExpensesView = root.findViewById(R.id.dayExpensesView);

        weekNetView = root.findViewById(R.id.weekNetView);
        weekIncomeView = root.findViewById(R.id.weekIncomeView);
        weekExpensesView = root.findViewById(R.id.weekExpensesView);

        monthNetView = root.findViewById(R.id.monthNetView);
        monthIncomeView = root.findViewById(R.id.monthIncomeView);
        monthExpensesView = root.findViewById(R.id.monthExpensesView);

        setRefs();
        //data = ref.loadStats();
        getDailyTransactions();
        getWeeklyTransactions();
        getMonthlyTransactions();
        /*@TODO
         * DO STUFF WITH STATISTICS
         * MAKE SURE STATISTICS ARE CORRECT
         */
        return root;
    }


    private void getDailyTransactions(){
        double income = 0;
        double expenses = 0;
        //Create arraylist for current date
        ArrayList<Transaction> curr = allTransactions.get(getDate(currentMonth, dayOfMonth, currentYear));
        //sum all transactions for date
        for(int j = 0; curr != null && j < curr.size(); j++){
            double value = curr.get(j).getValue();
            //Sum all positive values
            if(value > 0){
                income += value;
            }
            //Sum all negative values
            else {
                expenses += value;
            }
        }
         /*
        ArrayList<Transaction> oneDay = allTransactions.get(date);
        Stats single;

        income = 0;
        expenses = 0;
        //sum all transactions for date
        for (int j = 0; oneDay != null && j < oneDay.size(); j++) {
            double value = oneDay.get(j).getValue();
            //Sum all positive values
            if (value > 0) {
                income += value;
            }
            //Sum all negative values
            else {
                expenses += value;
            }
        }

        single = new Stats(income, expenses, income + expenses);
        double val = single.getDifference();

        // Check if it exists in map, else set to default values
        worstDay = val;
        bestDay = val;
        if (data.get("worstDay") != null) {
            worstDay = data.get("worstDay");
        } else {
            data.put("worstDay", worstDay);
        }

        if (data.get("bestDay") != null) {
            bestDay = data.get("bestDay");
        } else {
            data.put("bestDay", bestDay);
        }

        if (val <= worstDay) {
            data.put("worstDay", val);

        }
        if (val >= bestDay) {
            data.put("bestDay", val);
        }

        System.out.println(data.get("worstDay"));
        System.out.println(worstDay);
        System.out.println(data.get("bestDay"));
        System.out.println(bestDay);
        System.out.println(daily.getDifference());
        */


        /*
        String worst = worstDay < 0 ? String.format("-$%.2f", worstDay) : String.format("$%.2f", worstDay);
        String best = bestDay < 0 ? String.format("-$%.2f", bestDay) : String.format("$%.2f", bestDay);
        worstDayView.setText(worst);
        bestDayView.setText(best);

        if (worstDayView.getText().toString().contains("-")) {
            worstDayView.setTextColor(Color.RED);
        } else {
            worstDayView.setTextColor(Color.GREEN);
        }
        if (bestDayView.getText().toString().contains("-")) {
            bestDayView.setTextColor(Color.RED);
        } else {
            bestDayView.setTextColor(Color.GREEN);
        }*/

        this.daily = new Stats(income, expenses, income + expenses);

        dayIncomeView.setText(daily.incomeString());
        dayIncomeView.setTextColor(Color.GREEN);

        dayExpensesView.setText(daily.expensesString());
        dayExpensesView.setTextColor(Color.RED);

        dayNetView.setText(daily.differenceString());
        if (daily.getDifference() < 0){
            dayNetView.setTextColor(Color.RED);
        } else {
            dayNetView.setTextColor(Color.GREEN);
        }


    }


    private void getWeeklyTransactions(){
        double income = 0;
        double expenses = 0;
        //Get 7 days worth of info
        for (int i = 6; i >= 0 ; i--) {
            //Create arraylist for current date
            ArrayList<Transaction> curr = allTransactions.get(getDate(currentMonth, dayOfMonth - i, currentYear));
            //sum all transactions for date
            for(int j = 0; curr != null && j < curr.size(); j++){
                double value = curr.get(j).getValue();
                //Sum all positive values
                if(value > 0){
                    income += value;
                }
                //Sum all negative values
                else {
                    expenses += value;
                }
            }
        }
        this.weekly = new Stats(income, expenses, income + expenses);

        weekIncomeView.setText(weekly.incomeString());
        weekIncomeView.setTextColor(Color.GREEN);

        weekExpensesView.setText(weekly.expensesString());
        weekExpensesView.setTextColor(Color.RED);

        weekNetView.setText(weekly.differenceString());
        if (weekly.getDifference() < 0){
            weekNetView.setTextColor(Color.RED);
        } else {
            weekNetView.setTextColor(Color.GREEN);
        }

    }

    private void getMonthlyTransactions(){
        double income = 0;
        double expenses = 0;
        //get 30 days worth of info
        for (int i = 31; i >= 0 ; i--) {
            //Create arraylist for current date
            ArrayList<Transaction> curr = allTransactions.get(getDate(currentMonth, i, currentYear));
            //sum all transactions for date
            for(int j = 0; curr != null && j < curr.size(); j++){
                double value = curr.get(j).getValue();
                //Sum all positive values
                if(value > 0){
                    income += value;
                }
                //Sum all negative values
                else {
                    expenses += value;
                }
            }
        }


        this.monthly = new Stats(income, expenses, income + expenses);
        /*
        double val = monthly.getDifference();
        // Check if it exists in map, else set to default values
        worstMonth = val;
        bestMonth = val;
        if(data.get("worstMonth") != null) {
            worstMonth = data.get("worstMonth");
        }
        else {
            data.put("worstMonth", worstMonth);
        }

        if(data.get("bestMonth") != null) {
            bestMonth = data.get("bestMonth");
        }
        else {
            data.put("bestMonth", bestMonth);
        }

        if (val <= worstMonth) {
            data.put("worstMonth", val);
        }
        if (val >= bestMonth) {
            data.put("bestMonth", val);
        }


        System.out.println(data.get("worstMonth"));
        System.out.println(worstMonth);
        System.out.println(data.get("bestMonth"));
        System.out.println(bestMonth);
        System.out.println(monthly.getDifference());
        */

        monthIncomeView.setText(monthly.incomeString());
        monthIncomeView.setTextColor(Color.GREEN);

        monthExpensesView.setText(monthly.expensesString());
        monthExpensesView.setTextColor(Color.RED);

        monthNetView.setText(monthly.differenceString());
        if (monthly.getDifference() < 0){
            monthNetView.setTextColor(Color.RED);
        } else {
            monthNetView.setTextColor(Color.GREEN);
        }
        /*
        String worst = worstMonth < 0 ? String.format("-$%.2f", worstMonth) : String.format("$%.2f", worstMonth);
        String best = bestMonth < 0 ? String.format("-$%.2f", bestMonth) : String.format("$%.2f", bestMonth);
        worstDayView.setText(worst);
        bestDayView.setText(best);
        if (worstMonthView.getText().toString().contains("-")){
            worstMonthView.setTextColor(Color.RED);
        } else {
            worstMonthView.setTextColor(Color.GREEN);
        }
        if (bestMonthView.getText().toString().contains("-")){
            bestMonthView.setTextColor(Color.RED);
        } else {
            bestMonthView.setTextColor(Color.GREEN);
        }*/
    }

    private String getMonth(){
        return months[currentMonth];
    }


    public void updateAll() {
        System.out.println(date);
        getMonthlyTransactions();
        getWeeklyTransactions();
        getDailyTransactions();
        //ref.save(MainActivity.keys[1], data);
    }

}
