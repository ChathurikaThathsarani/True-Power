package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddRoutineActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button add;
    private DatabaseReference routine;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner_status);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Status");
        categories.add("Completed");
        categories.add("Not Yet");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        auth = FirebaseAuth.getInstance();
        routine = FirebaseDatabase.getInstance().getReference().child("routine").child(auth.getCurrentUser().getUid());
        add = findViewById(R.id.add_routine);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoutine();
            }


        });


    }

    private void addRoutine() {
        final EditText name = findViewById(R.id.routine_name);
        final EditText date = findViewById(R.id.day_to_complete);
        final EditText description = findViewById(R.id.description);
        final Spinner spinner = findViewById(R.id.spinner_status);

        String r_name = name.getText().toString();
        String r_date = date.getText().toString();
        String r_description = description.getText().toString();
        String r_status = spinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(r_name)) {
            name.setError("Name is required");
        }
        if (TextUtils.isEmpty(r_date)) {
            date.setError("Date is required");
        }
        if (TextUtils.isEmpty(r_description)) {
            description.setError("Description is required");
        }
        if (r_status.equals("Status")) {
            Toast.makeText(AddRoutineActivity.this, "Select a valid status", Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.isEmpty(r_name) && !TextUtils.isEmpty(r_date) && !TextUtils.isEmpty(r_description) && !TextUtils.isEmpty(r_status)) {
            String id = routine.push().getKey();
            Routine newRoutine = new Routine(id, r_name, r_description, r_status, r_date);
            routine.child(id).setValue(newRoutine).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AddRoutineActivity.this, "Routine added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddRoutineActivity.this, RoutineActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddRoutineActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void backToMyRoutine(View view) {
        Intent intent = new Intent(this, RoutineActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}