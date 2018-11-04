package com.example.davidang.letsgetthisbread;

import android.graphics.Color;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class MainActivity extends AppCompatActivity{

    //Key - Date
    //Value - ArrayList of Transactions for that Date
    private static HashMap<String, ArrayList<Transaction>> allTransactions = new HashMap<>();
    private ViewPager pager;
    private RPagerAdapter adapter;
    public static final String[] keys = {"data", "stats"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setNavigationBarColor(Color.rgb(50,50,50));
        this.getWindow().setStatusBarColor(Color.rgb(50,50,50));
        setVars();
    }
    private void setVars(){
        adapter = new RPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
        allTransactions = loadTransactions();
    }

    public HashMap<String, ArrayList<Transaction>> getAllTransactions() {
        return allTransactions;
    }

    public ViewPager getPager() {
        return pager;
    }

    public RPagerAdapter getAdapter() {
        return adapter;
    }


    public void save(String key, Object obj) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key,json);
        editor.apply();     // This line is IMPORTANT !!!
        System.out.println("Saved");
    }

    public HashMap<String, ArrayList<Transaction>> loadTransactions() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(keys[0],"");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, ArrayList<Transaction>>>(){}.getType();
        HashMap<String, ArrayList<Transaction>> obj = gson.fromJson(json, type);
        if(obj != null){
            System.out.println("Loaded Transactions");
        }
        else{
            System.out.println("New Transactions");
        }
        return obj != null ? obj : new HashMap<String, ArrayList<Transaction>>();
    }

    public HashMap<String,Double> loadStats(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = prefs.getString(keys[1],"");
        java.lang.reflect.Type type = new TypeToken<HashMap<String, Double>>(){}.getType();
        HashMap<String, Double> obj = gson.fromJson(json, type);
        if(obj != null){
            System.out.println("Loaded Stats");
        }
        else{
            System.out.println("New Stats");
        }
        return obj != null ? obj : new HashMap<String, Double>();
    }

}
