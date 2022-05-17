package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddHealth extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();
    private EditText next_checkup_date, last_checkup_date;
    private Button submit;

    private DatabaseReference health;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health);

        next_checkup_date = findViewById(R.id.et_next_checkup_date);
        last_checkup_date = findViewById(R.id.et_last_checkup_date);
        submit = findViewById(R.id.btn_Submit);

        loadNextCheckUpDate();
        loadLastCheckUpDate();

        auth= FirebaseAuth.getInstance();
        health= FirebaseDatabase.getInstance().getReference().child("health").child(auth.getCurrentUser().getUid());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHealth();
            }
        });

    }

    private void saveHealth(){

        EditText disease_name = findViewById(R.id.et_disease_name);
        EditText disease_description = findViewById(R.id.et_description);
        EditText next_checkup_date = findViewById(R.id.et_next_checkup_date);
        EditText last_checkup_date = findViewById(R.id.et_last_checkup_date);

        String getDiseaseName = disease_name.getText().toString().trim();
        String getDiseaseDescription = disease_description.getText().toString().trim();
        String getNextCheckupDate = next_checkup_date.getText().toString().trim();
        String getLastCheckupDate = last_checkup_date.getText().toString().trim();

        if(getDiseaseName.isEmpty()){
            disease_name.setError("DiseaseName is required");
        }else if(getDiseaseDescription.isEmpty()){
            disease_description.setError("Description is required");
        }else if(getNextCheckupDate.isEmpty()){
            next_checkup_date.setError("Next CheckUp Date is required");
        }else if(getLastCheckupDate.isEmpty()){
            last_checkup_date.setError("Last CheckUp Date is required");
        }else {

            String id = health.push().getKey();
            Health healthData = new Health(id,getDiseaseName, getDiseaseDescription, getNextCheckupDate, getLastCheckupDate);
            health.child(id).setValue(healthData).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AddHealth.this,"Health added successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(AddHealth.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void loadNextCheckUpDate(){


        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateNextChekUpDate();
            }
        };

        next_checkup_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddHealth.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void loadLastCheckUpDate(){


        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLastChekUpDate();
            }
        };

        last_checkup_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddHealth.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });






    }

    private void updateNextChekUpDate(){
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        next_checkup_date.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void updateLastChekUpDate(){
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        last_checkup_date.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void backToMyHealth(View view) {
        finish();
    }
}