package com.example.truepower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalculator extends AppCompatActivity {

    EditText wk_enter_weight,wk_enter_height;
    Button wk_bmi_calculate;
    TextView wk_bmi_answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);


        wk_enter_weight = findViewById(R.id.wk_enter_weight);
        wk_enter_height = findViewById(R.id.wk_enter_height);
        wk_bmi_calculate = findViewById(R.id.wk_bmi_calculate);
        wk_bmi_answer = findViewById(R.id.wk_bmi_answer);

        wk_bmi_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {

        String heightStr = wk_enter_height.getText().toString();
        String weightStr = wk_enter_weight.getText().toString();

        if (heightStr != null && ! "".equals(heightStr) && weightStr!= null && ! "".equals(weightStr)){
            float heightValue = Float.parseFloat(heightStr)/100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue/(heightValue*heightValue);
            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {

        String bmiLabel = "";

        if (Float.compare(bmi, 15f)<= 0) {
            bmiLabel = "very severely underweight";;
        }else if(Float.compare(bmi, 16f)>0 && Float.compare(bmi,18.5f)<=0) {
            bmiLabel = " underweight";
        }else if(Float.compare(bmi, 18.5f)>0 && Float.compare(bmi,25f)<=0) {
            bmiLabel = " Good Body Figure";
        }else if(Float.compare(bmi, 25f)>0 && Float.compare(bmi,30f)<=0) {
            bmiLabel = " Overweight";
        }else if(Float.compare(bmi, 30f)>0 && Float.compare(bmi,35f)<=0) {
            bmiLabel = " Obese";
        }

        bmiLabel = bmi +"\n"+ bmiLabel;
        wk_bmi_answer.setText(bmiLabel);
        }


    public void backToMyWorkout(View view) {
        Intent intent = new Intent(this, Workout.class);
        startActivity(intent);
        finish();

    }
}