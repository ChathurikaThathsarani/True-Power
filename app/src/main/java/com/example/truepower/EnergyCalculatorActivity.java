package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EnergyCalculatorActivity extends AppCompatActivity {

    EditText et_enter_calories;
    RadioButton rd_btn_kj;
    RadioButton rd_btn_j;
    Button btn_energy_calc;
    TextView tv_energy_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy_calculator);

        et_enter_calories = findViewById(R.id.et_enter_calories);
        rd_btn_j = findViewById(R.id.rd_btn_j);
        rd_btn_kj = findViewById(R.id.rd_btn_kj);
        btn_energy_calc = findViewById(R.id.btn_energy_calc);
        tv_energy_answer = findViewById(R.id.tv_energy_answer);

    }
    @Override
    protected void onResume() {
        super.onResume();
        btn_energy_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAnswer();
            }
        });
    }
    public void calculateAnswer(){

        EnergyCalculation cal = new EnergyCalculation();
        String value = et_enter_calories.getText().toString();
        if(TextUtils.isEmpty(value)){
            Toast.makeText(this, "Enter The Calories", Toast.LENGTH_SHORT).show();
        }else{
            Float calories = Float.parseFloat(value);
                if(rd_btn_kj.isChecked()){
                    calories = cal.calculateEnergyToKiloJoules(calories);
                }
                else if (rd_btn_j.isChecked()){
                    calories = cal.calculateEnergyToJoules(calories);
                }
                else{
                    Toast.makeText(this, "Choose The Radio Button", Toast.LENGTH_SHORT).show();
                    calories = 0.0f;
                }
            tv_energy_answer.setText(new Float(calories).toString());
        }
    }

    public void backToMyMeal(View view) {
        Intent intent = new Intent(this, MealActivity.class);
        startActivity(intent);
        finish();
    }
}