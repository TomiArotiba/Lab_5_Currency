package com.example.lab_5_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    static ArrayList<String> currencies = new ArrayList<>();
    static ArrayAdapter adapter;
    ListView currencyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyList = (ListView) findViewById(R.id.currencyList);
        Button ShowCurrencyList = findViewById(R.id.btnShowCurrencyList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, currencies);
        currencyList.setAdapter(adapter);
    }

    public void btnShowCurrencyList(View view) {
        currencies.add("Generating Currency List...");
        adapter.notifyDataSetChanged();
        new DataLoader(){
            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPostExecute(ArrayList<String> strings) {
                super.onPostExecute(strings);
                int size = strings.size();
                currencies.clear();
                currencies = new ArrayList<>(strings);
                for(int i = 0; i < size; i++){
                    adapter.add(currencies.get(i));
                    adapter.notifyDataSetChanged();
                }
            }
        }.execute();
    }
}