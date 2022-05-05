package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class EditWorkoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText workoutName;
    private Spinner workoutRepetitionVar;
    private Spinner workoutTimeVar;
    private Button submit;
    private Bundle bundle;
    private FirebaseAuth auth;
    private DatabaseReference workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        workoutName = findViewById(R.id.wk_workout_name);
        submit = findViewById(R.id.btn_workout_submit);
        bundle = getIntent().getExtras();
        auth = FirebaseAuth.getInstance();
        workout = FirebaseDatabase.getInstance().getReference().child("workout").child(auth.getCurrentUser().getUid());
        workoutRepetitionVar = (Spinner) findViewById(R.id.wk_workout_repetition);
        workoutTimeVar = (Spinner) findViewById(R.id.wk_workout_time);

        workoutRepetitionVar.setOnItemSelectedListener(this);
        workoutTimeVar.setOnItemSelectedListener(this);

        // spinner dropdown  for workout
        List<String> workoutRepetition = new ArrayList<String>();
        workoutRepetition.add("Repetition");
        workoutRepetition.add("5");
        workoutRepetition.add("10");
        workoutRepetition.add("20");
        workoutRepetition.add("30");
        workoutRepetition.add("50");

        List<String> workoutTime = new ArrayList<String>();
        workoutTime.add("Time");
        workoutTime.add("5 min");
        workoutTime.add("10 min");
        workoutTime.add("20 min");
        workoutTime.add("30 min");

        //creating adapter
        ArrayAdapter<String> workoutRepAdapter = new ArrayAdapter<String>(EditWorkoutActivity.this, android.R.layout.simple_spinner_item, workoutRepetition);
        ArrayAdapter<String> workoutTimAdapter = new ArrayAdapter<String>(EditWorkoutActivity.this, android.R.layout.simple_spinner_item, workoutTime);


        //dropdown layout
        workoutRepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workoutRepetitionVar.setAdapter(workoutRepAdapter);

        workoutTimAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workoutTimeVar.setAdapter(workoutTimAdapter);

        displayWorkoutDetails();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateWorkoutDetails();
            }
        });



    }

    private void displayWorkoutDetails() {
        workoutName.setText(bundle.getString("WorkoutName"));

    }

    private void updateWorkoutDetails() {
        final EditText workoutName = findViewById(R.id.wk_workout_name);
        final Spinner workoutRepetitionVar = findViewById(R.id.wk_workout_repetition);
        final Spinner workoutTimeVar = findViewById(R.id.wk_workout_time);


        String W_workoutName = workoutName.getText().toString();
        String W_workoutRepetitionVar = workoutRepetitionVar.getSelectedItem().toString();
        String W_workoutTimeVar = workoutTimeVar.getSelectedItem().toString();
        String id = bundle.getString("id");
        if (TextUtils.isEmpty(W_workoutName)) {
            Toast.makeText(EditWorkoutActivity.this, "Workout name is required !", Toast.LENGTH_SHORT).show();
        }
        if (W_workoutRepetitionVar.equals("Workout Repetitions")) {
            Toast.makeText(EditWorkoutActivity.this, "Select a Valid Repetition !", Toast.LENGTH_SHORT).show();
        }
        if (W_workoutTimeVar.equals("Workout Time")) {
            Toast.makeText(EditWorkoutActivity.this, "Select a Valid Time ! ", Toast.LENGTH_SHORT).show();
        }
        Workout updateWorkout = new Workout(id, W_workoutName, W_workoutRepetitionVar, W_workoutTimeVar);
        workout.child(id).setValue(updateWorkout).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(EditWorkoutActivity.this, "Workout Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditWorkoutActivity.this, WorkoutActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(EditWorkoutActivity.this, "Workout Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void backToMyWorkout(View view) {
    Intent intent = new Intent(this, WorkoutActivity.class);

    startActivity(intent);

}
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}