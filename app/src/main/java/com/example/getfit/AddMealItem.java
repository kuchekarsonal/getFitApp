package com.example.getfit;

public class AddMealItem {
    private String recipeName;
    private float caloriesCount;
    private float carbs;
    private float fats;
    private float protein;

    public AddMealItem(String recipeName,float caloriesCount,float carbs,float fats,float protein){
        this.recipeName = recipeName;
        this.caloriesCount = caloriesCount;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
    }

    public void setCaloriesCount(float caloriesCount) {
        this.caloriesCount = caloriesCount;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public float getCaloriesCount() {
        return caloriesCount;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public float getCarbs() {
        return carbs;
    }

    public float getFats() {
        return fats;
    }

    public float getProtein() {
        return protein;
    }
}
