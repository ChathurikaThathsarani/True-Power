package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AddMealActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        final Spinner mealCat = (Spinner) findViewById(R.id.spinner_meal_category);
        final Spinner mealType = (Spinner) findViewById(R.id.spinner_meal_type);

        mealCat.setOnItemSelectedListener(this);
        mealType.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> mealCategories = new ArrayList<String>();
        mealCategories.add("Meal Category");
        mealCategories.add("Main");
        mealCategories.add("Snack");
        mealCategories.add("Soup");
        mealCategories.add("Dessert");
        mealCategories.add("Drink");
        mealCategories.add("Salad");
        mealCategories.add("Meat");
        mealCategories.add("Other");

        List<String> mealTypes = new ArrayList<String>();
        mealTypes.add("Meal Type");
        mealTypes.add("Breakfast");
        mealTypes.add("Lunch");
        mealTypes.add("Brunch");
        mealTypes.add("Dinner");
        mealTypes.add("Other");

        // Creating adapter for spinner
        ArrayAdapter<String> mealCatDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mealCategories);
        ArrayAdapter<String> mealTypeDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mealTypes);

        // Drop down layout style - list view with radio button
        mealCatDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealCat.setAdapter(mealCatDataAdapter);

        mealTypeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealType.setAdapter(mealTypeDataAdapter);

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
                        startActivity(new Intent(getApplicationContext(), MealActivity.class));
                        overridePendingTransition(0, 0);
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


    }
    public void backToMyMeal(View view) {
        Intent intent = new Intent(this, MealActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}