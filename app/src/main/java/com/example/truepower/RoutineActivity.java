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
import com.google.firebase.database.DatabaseReference;

public class RoutineActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private DatabaseReference routine;

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
                        return true;
                    case R.id.health:
                        startActivity(new Intent(getApplicationContext(), HealthActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.meal:
                        startActivity(new Intent(getApplicationContext(), MealActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.routine:
                        return true;
                    case R.id.workout:
                        startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });

        recyclerview = findViewById(R.id.routine_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        FirebaseRecylerOptions<Routine> options = new FirebaseRecyclerOptions.Builder<Routine>()
//                .setQuery(routine,Routine.class)
//                .build();
    }

//    public void RoutineViewHolder extends RecyclerView.ViewHolder{
//        public RoutineViewHolder(View itemView){
//            super(itemView);
//        }
//    }

    public void addRoutine(View view) {
        Intent intent = new Intent(this, AddRoutineActivity.class);
        startActivity(intent);
    }

}