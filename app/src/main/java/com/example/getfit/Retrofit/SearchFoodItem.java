package com.example.getfit.Retrofit;

public class SearchFoodItem {
    private String _id;
    private String recipe_name;
    private float calories;
    private String category;
    private String collection;

    public float getCalories() {
        return calories;
    }

    public String getCategory() {
        return category;
    }

    public String getCollection() {
        return collection;
    }

    public String getRecipe_name() {
        return recipe_name;
    }
}
