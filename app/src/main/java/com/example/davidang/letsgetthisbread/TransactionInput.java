package com.example.davidang.letsgetthisbread;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionInput extends FragWithReference {

    protected EditText enterAction, enterValue;
    protected Button addTransaction;

    protected ListView listView;
    protected TextView dateView;
    protected static Typeface inconsolata;

    protected ArrayList<Transaction> transactions;
    protected ArrayAdapter<Transaction> stringArrayAdapter;

    public TransactionInput() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inconsolata = Typeface.createFromAsset(getContext().getAssets(),"fonts/Inconsolata-Regular.ttf");
        createGUI(view);
    }

    private void createGUI(@NonNull View view) {
        // Transactions, List + Button
        // Check if Key for date exists
        // If True set transaction to the data for that date
        // Else set transaction to new list and create KV Pair
        loadData(view);
        stringArrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, transactions);
        addTransaction = view.findViewById(R.id.addTransaction);
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterAction.getText().toString().length() > 0 && enterValue.getText().toString().length() > 0) {
                    transactions.add(new Transaction(enterAction.getText().toString(), Double.parseDouble(enterValue.getText().toString())));
                    enterAction.setText("");
                    enterValue.setText("");
                }
                stringArrayAdapter.notifyDataSetChanged();
            }
        });

        // set typeface of all TextViews
        dateView = view.findViewById(R.id.dateView);
        dateView.setText(date);
        enterAction = view.findViewById(R.id.enterAction);
        enterValue = view.findViewById(R.id.enterValue);

        //List View Stuff
        listView = view.findViewById(R.id.listView);
        listView.setAdapter(stringArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

    }

    public void loadData(@NonNull View view){
        if(allTransactions.containsKey(date)){
            System.out.println("Existing Arr " + date);
            transactions = allTransactions.get(date);
        }
        else{
            System.out.println("New Arr " + date);
            transactions = new ArrayList<>();
            allTransactions.put(date, transactions);
        }
        for(int i = 0; i < transactions.size(); i++){
            System.out.println(transactions.get(i));
        }

        stringArrayAdapter.notifyDataSetChanged();

    }

    public void updatePage(){
        System.out.println("Update with " + date);
        dateView.setText(date);
    }

}
