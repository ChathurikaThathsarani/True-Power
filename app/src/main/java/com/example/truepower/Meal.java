package com.example.truepower;

public class Meal {
    String id,mealName,mealCategory,mealType,calories;

    public Meal(){}

    public Meal(String id, String mealName, String calories) {
        this.id = id;
        this.mealName = mealName;
        this.calories = calories;
    }

    public Meal(String id, String mealName, String mealCategory, String mealType, String calories) {
        this.id = id;
        this.mealName = mealName;
        this.mealCategory = mealCategory;
        this.mealType = mealType;
        this.calories = calories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
}
