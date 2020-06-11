package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper equationDB;
    EditText editText_a, editText_b, editText_c;
    Button btnGo, btnView;
    TextView textView_R ;
    double a,b,c, D, x1, x2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equationDB = new DatabaseHelper(this);
        editText_a =  (EditText) findViewById(R.id.edit_text1);
        editText_b =  (EditText) findViewById(R.id.edit_text2);
        editText_c =  (EditText) findViewById(R.id.edit_text3);
        btnGo = (Button) findViewById(R.id.btn);
        textView_R = (TextView) findViewById(R.id.TextView2);
        btnView = (Button) findViewById(R.id.btnView);


    btnGo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String A = editText_a.getText().toString();
            String B = editText_b.getText().toString();
            String C = editText_c.getText().toString();

            if (!editText_a.getText().toString().equals("") && !editText_b.getText().toString().equals("")
                    && !editText_c.getText().toString().equals("")){
                a = Double.parseDouble(editText_a.getText().toString());
                b = Double.parseDouble(editText_b.getText().toString());
                c = Double.parseDouble(editText_c.getText().toString());
                D = Math.pow(b, 2) - 4*a*c;


                if (D == 0){
                    x1 = -b/(2*a);
                    textView_R.setText("D=" + D + "\nx= " + x1);

                }else if (D < 0){
                    textView_R.setText("Няма реални корени!");
                }else if (D > 0){
                    x1 = (-b + Math.sqrt(D))/(2*a);
                    x2 = (-b - Math.sqrt(D))/(2*a);
                    textView_R.setText("D=" + D + "\nx1= " + x1 + "\nx2= " + x2);


                }

                boolean insertData = equationDB.addData(A, B, C,String.valueOf(textView_R.getText()));

                if (insertData == true) {
                    Toast.makeText(MainActivity.this, "Успешно добавени данни!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Неуспешно добавени данни!.", Toast.LENGTH_LONG).show();
                }
    }
            else{
                textView_R.setText("Не сте въвели стойност");
            }
}
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    }

