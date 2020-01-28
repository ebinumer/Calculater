package com.example.calculater2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
                               simple calculater
created by Najmudheen
 */
public class MainActivity extends AppCompatActivity {
    private int[] numericButtons = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};
    private int[] operatorButtons = {R.id.btn_plus, R.id.btn_minus, R.id.btn_multiply, R.id.btn_divide};
    private boolean lastNumeric;
    private boolean stateError;
    EditText edtfirst, edtsecond;
    Button btnEql, btnclr;
    TextView txtAnswer;
    private boolean clicked = false;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtfirst = findViewById(R.id.firstvalue);
        edtsecond = findViewById(R.id.secondvalue);
        txtAnswer = findViewById(R.id.answer);
        btnEql = findViewById(R.id.btn_equals);
        btnclr = findViewById(R.id.btn_clear);
        setNumericOnClickListener();
        setOperatorOnClickListener();
        btnEql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation(edtfirst.getText().toString(), edtsecond.getText().toString());
                edtfirst.setText("");
                edtsecond.setText("");
            }
        });
        btnclr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtfirst.setText("");
                edtsecond.setText("");
                txtAnswer.setText("");
                clicked = false;
            }
        });
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (stateError) {
                    Log.e("hdwku", "jj");
                    edtfirst.setText(button.getText());
                    edtsecond.setText(button.getText());
                    stateError = false;
                } else {
                    if (clicked) {

                        edtsecond.append(button.getText());


                    } else {
                        edtfirst.append(button.getText());

                    }
                }


                lastNumeric = true;
            }
        };
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lastNumeric && !stateError) {
                    clicked = true;
                    lastNumeric = false;
                    Button button = (Button) v;


                    edtfirst.append(txtAnswer.getText());
                    edtfirst.append(button.getText());
                }
            }

            //            lastDot = false;


        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void operation(String value1, String value2) {
        int temp = 0;
        char[] oppbtn = {'+', '-', '*', '/'};
        Log.e("value", value1);
        Log.e("value2  :", value2);
        for (int i = 0; i < value1.length(); i++) {
            char iVal = value1.charAt(i);
            Log.e("yes", iVal + "");
            for (int j = 0; j < oppbtn.length; j++) {
                if (iVal == oppbtn[j]) {
                    int first = Integer.parseInt(value1.substring(0, i));
                    Log.e("fihf", String.valueOf(first));
                    int second = Integer.parseInt(value2);

                    switch (oppbtn[j]) {
                        case '+':
                            temp = first + second;
                            Log.e("wjknbvwjk", String.valueOf(temp));
                            txtAnswer.setText(String.valueOf(temp));
                            break;
                        case '-':
                            temp = first - second;
                            txtAnswer.setText(String.valueOf(temp));
                            break;
                        case '*':
                            temp = first * second;
                            txtAnswer.setText(String.valueOf(temp));
                            break;
                        case '/':
                            temp = first / second;
                            txtAnswer.setText(String.valueOf(temp));
                            break;

                    }
                }
            }
        }


    }
}