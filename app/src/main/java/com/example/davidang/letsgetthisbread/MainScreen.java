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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreen extends Fragment {

    TextView dateView;
    EditText enterAction, enterValue;
    Button addTransaction;

    ListView listView;

    List<Transaction> currentDayArray;
    ArrayAdapter<Transaction> stringArrayAdapter;
    static Typeface inconsolata;

    public MainScreen() {
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


        dateView = view.findViewById(R.id.dateView);

        enterAction = view.findViewById(R.id.enterAction);
        enterValue = view.findViewById(R.id.enterValue);
        addTransaction = view.findViewById(R.id.addTransaction);

        listView = view.findViewById(R.id.listView);

        // set typeface of all TextViews
        dateView.setTypeface(inconsolata);
        enterAction.setTypeface(inconsolata);
        enterValue.setTypeface(inconsolata);

        //get current day to display at top of screen
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MM / dd / yyyy");

        dateView.setText(mdformat.format(calendar.getTime()));

        currentDayArray = new ArrayList<>();

        stringArrayAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, currentDayArray);

        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        // on click listener for the add transaction button
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enterAction.getText().toString().length() > 0 && enterValue.getText().toString().length() > 0){
                    currentDayArray.add(new Transaction(enterAction.getText().toString(),Double.parseDouble(enterValue.getText().toString())));
                    enterAction.setText("");
                    enterValue.setText("");
                }
                stringArrayAdapter.notifyDataSetChanged();
            }
        });

    }

}
