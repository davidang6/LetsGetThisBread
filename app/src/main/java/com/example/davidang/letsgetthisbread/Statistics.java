package com.example.davidang.letsgetthisbread;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Statistics extends FragWithReference {

    private Stats daily; // Stats for Current Date
    private Stats weekly; // Stats for Previous 7 Days
    private Stats monthly; // Stats for Previous 30 Days

    /*#TODO
     * ADD VARIABLES INVOLVED WITH THE GUI
     * CREATE SET VARIABLE METHOD FOR ORGANIZATION
     * CALL SET VARIABLE METHOD IN onViewCreated
     */


    public Statistics() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getDailyTransactions();
        getWeeklyTransactions();
        getMonthlyTransactions();

        /*@TODO
         * DO SHIT WITH STATISTICS
         * MAKE SURE STATISTICS ARE CORRECT
         */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
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
        this.daily = new Stats(income, expenses, income - expenses);
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
        this.weekly = new Stats(income, expenses, income - expenses);
    }

    private void getMonthlyTransactions(){
        double income = 0;
        double expenses = 0;
        //get 30 days worth of info
        for (int i = 29; i >= 0 ; i--) {
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
        this.monthly = new Stats(income, expenses, income - expenses);
    }
}
