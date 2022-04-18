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

public class MealActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private DatabaseReference meal;
    MealAdapter adapter;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_home);
        bottomNavigationView.setSelectedItemId(R.id.meal);

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

        recyclerView = (RecyclerView) findViewById(R.id.meal_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        auth= FirebaseAuth.getInstance();
        meal = FirebaseDatabase.getInstance().getReference().child("meal").child(auth.getCurrentUser().getUid());
        FirebaseRecyclerOptions<Meal> options
                = new FirebaseRecyclerOptions.Builder<Meal>()
                .setQuery(meal, Meal.class)
                .build();
        adapter = new MealAdapter(options);
        recyclerView.setAdapter(adapter);


    }
    public void addMeal(View view) {
        Intent intent = new Intent(this, AddMealActivity.class);
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
}