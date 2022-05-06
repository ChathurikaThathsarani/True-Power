package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WorkoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference workout;
    WorkoutAdapter adapter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_home);
        bottomNavigationView.setSelectedItemId(R.id.workout);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.health:
                        startActivity(new Intent(getApplicationContext(), HealthActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.meal:
                        startActivity(new Intent(getApplicationContext(), MealActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.routine:
                        startActivity(new Intent(getApplicationContext(), RoutineActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.workout:
                        return true;
                }

                return false;
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.workout_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth=FirebaseAuth.getInstance();
        workout = FirebaseDatabase.getInstance().getReference().child("workout").child(auth.getCurrentUser().getUid());
        FirebaseRecyclerOptions<Workout> options
                = new FirebaseRecyclerOptions.Builder<Workout>()
                .setQuery(workout, Workout.class)
                .build();
        adapter = new WorkoutAdapter(options);
        recyclerView.setAdapter(adapter);
    }


    public void addWorkout(View view) {
        Intent intent = new Intent(this, AddWorkout.class);

        startActivity(intent);
    }
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    public void openTimer(View view) {
        Intent intent = new Intent(this, CountdownTimer.class);

        startActivity(intent);
    }
}