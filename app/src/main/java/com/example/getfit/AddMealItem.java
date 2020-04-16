package com.example.getfit;

public class AddMealItem {
    private String recipeName;
    private String caloriesCount;

    public AddMealItem(String recipeName,String caloriesCount){
        this.recipeName = recipeName;
        this.caloriesCount = caloriesCount;
    }

    public void setCaloriesCount(String caloriesCount) {
        this.caloriesCount = caloriesCount;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCaloriesCount() {
        return caloriesCount;
    }

    public String getRecipeName() {
        return recipeName;
    }
}
