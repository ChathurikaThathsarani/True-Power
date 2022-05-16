package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HealthActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference health;
    HealthAdapter adapter;
    private FirebaseAuth auth;

    private String obid="";
    private String name="";
    private String nextDate="";
    private String description="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_home);
        bottomNavigationView.setSelectedItemId(R.id.health);

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
                        startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }

                return false;
            }
        });
        auth=FirebaseAuth.getInstance();
        health = FirebaseDatabase.getInstance().getReference().child("health").child(auth.getCurrentUser().getUid());
        recyclerView = findViewById(R.id.health_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Health> options = new FirebaseRecyclerOptions.Builder<Health>().setQuery(health, Health.class).build();
        adapter = new HealthAdapter(options);
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

    public void addHealth(View view) {
        Intent intent = new Intent(this, AddHealth.class);
        startActivity(intent);
    }
}