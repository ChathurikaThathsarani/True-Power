package com.example.truepower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditMealActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText mealName;
    private EditText calories;
    private Spinner mealCategory;
    private Spinner mealType;
    private Button submit;
    private Bundle bundle;
    private FirebaseAuth auth;
    private DatabaseReference meal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);

        mealName = findViewById(R.id.et_meal_name);

        calories = findViewById(R.id.et_meal_calories);
        submit = findViewById(R.id.btn_meal_submit);
        bundle = getIntent().getExtras();
        auth = FirebaseAuth.getInstance();
        meal = FirebaseDatabase.getInstance().getReference().child("meal").child(auth.getCurrentUser().getUid());
        mealCategory = (Spinner) findViewById(R.id.spinner_meal_category);
        final Spinner mealType = (Spinner) findViewById(R.id.spinner_meal_type);

        mealCategory.setOnItemSelectedListener(this);
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
        mealCategory.setAdapter(mealCatDataAdapter);

        mealTypeDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealType.setAdapter(mealTypeDataAdapter);

        displayMealDetails();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMealDetails();
            }
        });
    }
    private void displayMealDetails() {
        mealName.setText(bundle.getString("mealName"));
        calories.setText(bundle.getString("calories"));
    }

    @SuppressLint("SetTextI18n")
    private void updateMealDetails() {
        final EditText mealName = findViewById(R.id.et_meal_name);
        final Spinner mealCategory =  findViewById(R.id.spinner_meal_category);
        final Spinner mealType = findViewById((R.id.spinner_meal_type));
        final EditText calories = findViewById(R.id.et_meal_calories);

        String m_name = mealName.getText().toString();
        String m_category = mealCategory.getSelectedItem().toString();
        String m_type = mealType.getSelectedItem().toString();
        String m_calories = calories.getText().toString();
        String id = bundle.getString("id");


        // validations
        if(TextUtils.isEmpty(m_name)){
            mealName.setError("Meal name is required !");
        }
        if(m_category.equals("Meal Category")){
            TextView errorText = (TextView)mealCategory.getSelectedView();
            errorText.setTextColor(Color.RED);
            errorText.setText("Enter Meal Category");
            errorText.setError("Enter Meal Category");
            return;
        }
        if(m_type.equals("Meal Type")){
            TextView errorText = (TextView)mealType.getSelectedView();
            errorText.setTextColor(Color.RED);
            errorText.setText("Enter Meal Type");
            errorText.setError("Enter Meal Type");
            return;
        }
        if(TextUtils.isEmpty(m_calories)){
            calories.setError("Calorie count is required !");
        }


        Meal updateMeal = new Meal(id,m_name,m_category,m_type,m_calories);
        meal.child(id).setValue(updateMeal).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(EditMealActivity.this, "Meal Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditMealActivity.this, MealActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(EditMealActivity.this, "Meal Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void backToMyMeal(View view) {
        Intent intent = new Intent(this, MealActivity.class);
        startActivity(intent);
    }

    public EditMealActivity() {
    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}