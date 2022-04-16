package com.example.truepower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        mealCategories.add("Main");
        mealCategories.add("Snack");
        mealCategories.add("Soup");
        mealCategories.add("Dessert");
        mealCategories.add("Drink");
        mealCategories.add("Salad");
        mealCategories.add("Meat");
        mealCategories.add("Other");

        List<String> mealTypes = new ArrayList<String>();
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