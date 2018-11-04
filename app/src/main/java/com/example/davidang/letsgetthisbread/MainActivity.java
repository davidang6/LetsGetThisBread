package com.example.davidang.letsgetthisbread;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

    //Key - Date
    //Value - ArrayList of Transactions for that Date
    private HashMap<String, ArrayList<Transaction>> allTransactions = new HashMap<>();
    private ViewPager pager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVars();
    }
    private void setVars(){
        adapter = new PagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
    }

    public HashMap<String, ArrayList<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public ViewPager getPager() {
        return pager;
    }

    public PagerAdapter getAdapter() {
        return adapter;
    }
}
