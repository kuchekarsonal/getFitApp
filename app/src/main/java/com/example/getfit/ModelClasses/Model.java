package com.example.getfit.ModelClasses;

public class Model {
    private int image;
    private String Title;
    private String foodItem1;
    private String fooodItem2;
    private String foodItem3;

    public Model(int image, String title, String foodItem1, String fooodItem2, String foodItem3) {
        this.image = image;
        Title = title;
        this.foodItem1 = foodItem1;
        this.fooodItem2 = fooodItem2;
        this.foodItem3 = foodItem3;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFoodItem1() {
        return foodItem1;
    }

    public void setFoodItem1(String foodItem1) {
        this.foodItem1 = foodItem1;
    }

    public String getFooodItem2() {
        return fooodItem2;
    }

    public void setFooodItem2(String fooodItem2) {
        this.fooodItem2 = fooodItem2;
    }

    public String getFoodItem3() {
        return foodItem3;
    }

    public void setFoodItem3(String foodItem3) {
        this.foodItem3 = foodItem3;
    }
}
