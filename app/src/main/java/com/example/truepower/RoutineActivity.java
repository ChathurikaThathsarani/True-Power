package com.example.truepower;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoutineActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference routine;
    RoutineAdapter adapter;
    private FirebaseAuth auth;
    private String obid="";
    private String name="";
    private String date="";
    private String description="";
    private String status="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_home);
        bottomNavigationView.setSelectedItemId(R.id.routine);

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
                        return true;
                    case R.id.workout:
                        startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }

                return false;
            }
        });
        auth=FirebaseAuth.getInstance();
        routine = FirebaseDatabase.getInstance().getReference().child("routine").child(auth.getCurrentUser().getUid());
        recyclerView = findViewById(R.id.routine_recycler);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Routine> options
                = new FirebaseRecyclerOptions.Builder<Routine>()
                .setQuery(routine, Routine.class)
                .build();
        adapter = new RoutineAdapter(options);
        recyclerView.setAdapter(adapter);
    }


    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }


    public void addRoutine(View view) {
        Intent intent = new Intent(this, AddRoutineActivity.class);
        startActivity(intent);
    }


    public void openAlarm(View view) {
        Intent intent = new Intent(this, AlarmNotification.class);
        startActivity(intent);
    }
}