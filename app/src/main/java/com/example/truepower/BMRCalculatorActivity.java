package com.example.truepower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BMRCalculatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmrcalculator);

        Spinner spinner = findViewById(R.id.spinner_gender);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Male");
        categories.add("Female");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        calculate=findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMR();
            }


        });
    }

    private void calculateBMR() {
        final EditText weight = findViewById(R.id.weight);
        final EditText height = findViewById(R.id.height);
        final EditText age = findViewById(R.id.age);
        final Spinner spinner = findViewById(R.id.spinner_gender);
        final TextView answerV = findViewById(R.id.answer);

        Integer r_weight =Integer.parseInt(weight.getText().toString());
        Integer r_height=Integer.parseInt(height.getText().toString());
        Integer r_age=Integer.parseInt(age.getText().toString());
        String r_gender=spinner.getSelectedItem().toString();
        double answer;

        if(r_gender.equals("Male")){
            answer=88.4+(13.4*r_weight)+(4.8*r_height)-(5.68*r_age);
            answerV.setText(String.format("BMR : %.2f", answer));
        }
        if(r_gender.equals("Female")){
            answer=447.6+(9.25*r_weight)+(3.10*r_height)-(4.33*r_age);
            answerV.setText(String.format("BMR : %.2f", answer));
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}