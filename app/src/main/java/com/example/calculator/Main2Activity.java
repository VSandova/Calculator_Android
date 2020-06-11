package com.example.calculator;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {

    DatabaseHelper equationDB;
    private ListView listView;
    private Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView) findViewById(R.id.listView1);
        equationDB = new DatabaseHelper(this);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = equationDB.getListContents();

       if (data.getCount() == 0) {
            Toast.makeText(Main2Activity.this, "Няма съдържание в този списък!", Toast.LENGTH_LONG).show();
        } else {
            if (data.moveToFirst()) {
                do {
                    theList.add("A =  " + data.getString(1) +
                            "\nB = " + data.getString(2) +
                            "\nC = " + data.getString(3)+
                            "\nРезултат = " + data.getString(4));
                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                    listView.setAdapter(listAdapter);

                } while (data.moveToNext());


            }
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] items = adapterView.getItemAtPosition(i).toString().split("\n");
                String A = items[0];
                String B = items[1];
                String C = items[2];
                String result = items[3];

                Cursor data = equationDB.getItemID();

                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                    intent.putExtra("id", itemID);
                    intent.putExtra("A", A);
                    intent.putExtra("B", B);
                   intent.putExtra("C", C);
                   intent.putExtra("Result", result);
                    startActivity(intent);
                }
            }
        });
        }
    }
