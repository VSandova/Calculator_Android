package com.example.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private Button btnDelete;
    private EditText editable_itemA, editable_itemB, editable_itemC, editable_itemResult;
    DatabaseHelper equationDB;
    private  int id;
    private String A;
    private String B;
    private String C;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final ArrayList<String> theList = new ArrayList<>();
        final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);


        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_itemA = (EditText) findViewById(R.id.editable_itemA);
       editable_itemB = (EditText) findViewById(R.id.editable_itemB);
       editable_itemC = (EditText) findViewById(R.id.editable_itemC);
        editable_itemResult = (EditText) findViewById(R.id.editable_itemResult);

        equationDB = new DatabaseHelper(this);

        Intent intent = getIntent();

        id = intent.getIntExtra("id",-1);
        A = intent.getStringExtra("A");
        B = intent.getStringExtra("B");
        C = intent.getStringExtra("C");
        result = intent.getStringExtra("Result");
        editable_itemA.setText(A);
        editable_itemB.setText(B);
        editable_itemC.setText(C);
        editable_itemResult.setText(result);


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equationDB.deleteName(id);
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);


            }
        });
    }
}

