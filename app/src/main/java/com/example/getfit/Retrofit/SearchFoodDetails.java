package com.example.getfit.Retrofit;

public class SearchFoodDetails {
    private String recipe_name;
    private String collection;
    private String category;
    private float calories;
    private float fats;
    private float carbohydrates;
    private float protein;
    private String img_url;

    public String getRecipe_name() {
        return recipe_name;
    }

    public String getCollection() {
        return collection;
    }

    public String getCategory() {
        return category;
    }

    public float getCalories() {
        return calories;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public float getProtein() {
        return protein;
    }

    public float getFats() {
        return fats;
    }

    public String getImg_url() {
        return img_url;
    }

}
