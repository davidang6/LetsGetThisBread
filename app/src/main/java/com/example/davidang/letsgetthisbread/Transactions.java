package com.example.davidang.letsgetthisbread;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;

import java.util.ArrayList;
import java.util.Calendar;


public class Transactions extends FragWithReference {

    protected static EditText enterAction, enterValue;
    protected static Button addTransaction;

    protected static ImageView imageView;
    protected static ListView listView;
    protected static TextView dateView;
    protected static Typeface inconsolata, nunito;
    protected ArrayList<Transaction> transactions;
    protected static ArrayAdapter<Transaction> stringArrayAdapter;
    protected static View root;
    public Transactions() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_main_screen, container, false);
        setRefs();
        createGUI(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inconsolata = Typeface.createFromAsset(getContext().getAssets(),"fonts/Inconsolata-Regular.ttf");
        nunito = Typeface.createFromAsset(getContext().getAssets(),"fonts/Nunito-Regular.ttf");
    }

    private void createGUI(@NonNull View view) {
        // Initialize
        imageView = view.findViewById(R.id.imageView);
        addTransaction = view.findViewById(R.id.addTransaction);
        listView = view.findViewById(R.id.listView);
        dateView = view.findViewById(R.id.dateView);
        enterAction = view.findViewById(R.id.enterAction);
        enterValue = view.findViewById(R.id.enterValue);

        dateView.setText(date);

        loadData();
    }

    public void loadData(){
        if(!allTransactions.containsKey(date)){
            System.out.println("New Arr");
            allTransactions.put(date, new ArrayList<Transaction>());
        }
        System.out.println("Key " + date);
        transactions = allTransactions.get(date);
        /*
        for(int i = 0; i < transactions.size(); i++){
            System.out.println(transactions.get(i));
        }*/
        stringArrayAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, transactions);


        //List View Stuff

        listView.setAdapter(stringArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterAction.getText().toString().length() > 0 && enterValue.getText().toString().length() > 0) {
                    transactions.add(new Transaction(enterAction.getText().toString(), Double.parseDouble(enterValue.getText().toString())));
                    enterAction.setText("");
                    enterValue.setText("");
                    ref.save(MainActivity.keys[0], allTransactions);
                    RPagerAdapter temp = (RPagerAdapter) pager.getAdapter();
                    temp.setCurrFrag(2);
                    FragWithReference currPage = temp.getCurrentFragment();
                    if(currPage instanceof Statistics){
                        Statistics curr = (Statistics) currPage;
                        curr.updateAll();
                    }
                    temp.setCurrFrag(1); // Reset to current
                }
                stringArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    public void updateDate(){
        System.out.println("Update with " + date);
        dateView.setText(date);
        if (!dateView.getText().toString().substring(0,2).contains(" ") && Integer.parseInt(dateView.getText().toString().substring(0, 2)) - 1 == 9){
            imageView.setImageResource(R.drawable.pumpkin);
            return;
        }
        else if (!dateView.getText().toString().substring(0,2).contains(" ") && Integer.parseInt(dateView.getText().toString().substring(0, 2)) - 1 == 10){
            imageView.setImageResource(R.drawable.turkey);
            return;
        }
        else if (!dateView.getText().toString().substring(0,2).contains(" ") && Integer.parseInt(dateView.getText().toString().substring(0, 2)) - 1 == 11) {
            imageView.setImageResource(R.drawable.christmas);
            return;
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 0){
            imageView.setImageResource(R.drawable.firework);
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 1){
            imageView.setImageResource(R.drawable.heart);
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 2){
            imageView.setImageResource(R.drawable.shamrock);
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 3){
            imageView.setImageResource(R.drawable.rain);
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 4){
            imageView.setImageResource(R.drawable.flower);
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 5){
            imageView.setImageResource(R.drawable.ladybug);
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 6){
            imageView.setImageResource(R.drawable.sun);
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 7){
            imageView.setImageResource(R.drawable.beach);
        }
        else if (Integer.parseInt(dateView.getText().toString().substring(0, 1)) - 1 == 8){
            imageView.setImageResource(R.drawable.school);
        }else {
            imageView.setImageResource(R.drawable.cursor);
        }
    }

}