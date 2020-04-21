package com.example.getfit.ModelClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DietPlanModel {

    private List<Meal> Breakfast;
    private  List<Meal> Lunch;
    private List<Meal> Dinner;
    private totalMeal total;


    public List<Meal> getMealBreakfast() {
        return Breakfast;
    }

    public List<Meal> getMealLunch() {
        return Lunch;
    }

    public List<Meal> getMealDinner() {
        return Dinner;
    }

    public DietPlanModel.totalMeal getTotalMeal() {
        return total;
    }

    public class Meal{
        String recipe_name;
        int servings;
        float calories;
        float carbs;
        float fats;
        float protein;

        public String getRecipe_name() {
            return recipe_name;
        }

        public int getServings() {
            return servings;
        }

        public float getCalories() {
            return calories;
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


    public class totalMeal{

        float Calories;
        float Carbohydrates;
        @SerializedName("Carbs percentage")
        float Carbspercentage;
        float Proteins;

        @SerializedName("Proteins percent")
        float Proteins_percent;

        float Fats;
        @SerializedName("Fats percent")
        float Fats_percent;

        public float getCalories() {
            return Calories;
        }

        public float getCarbohydrates() {
            return Carbohydrates;
        }

        public float getCarbspercentage() {
            return Carbspercentage;
        }

        public float getProteins() {
            return Proteins;
        }

        public float getProteins_percent() {
            return Proteins_percent;
        }

        public float getFats() {
            return Fats;
        }

        public float getFats_percent() {
            return Fats_percent;
        }
    }

}



/*
{
        "Breakfast": [
        {
        "recipe_name": "Indian Chole Aloo Tikki",
        "servings": 2,
        "calories": 628.0,
        "carbs": 87.2,
        "fats": 27.0,
        "protein": 13.8
        }

        ],
        "Lunch": [
        {
        "recipe_name": "Quick Whole Wheat Chapati",
        "servings": 2,
        "calories": 254.0,
        "carbs": 54.4,
        "fats": 1.4,
        "protein": 10.2
        },
        {
        "recipe_name": "Aloo Matar",
        "servings": 1,
        "calories": 487.0,
        "carbs": 81.7,
        "fats": 14.5,
        "protein": 9.0
        }
        ],
        "Dinner": [
        {
        "recipe_name": "Quick Whole Wheat Chapati",
        "servings": 2,
        "calories": 254.0,
        "carbs": 54.4,
        "fats": 1.4,
        "protein": 10.2
        }
        ],
        "total": {
        "Calories": 1623.0,
        "Carbohydrates": 277.7,
        "Carbs percentage": 70.0,
        "Proteins": 43.2,
        "Proteins percent": 11.0,
        "Fats": 44.3,
        "Fats percent": 24.0
        }
        }*/
