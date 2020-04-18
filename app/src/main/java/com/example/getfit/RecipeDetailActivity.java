package com.example.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;
import com.example.getfit.Retrofit.SearchFoodDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;

public class RecipeDetailActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    private String mealType,recipeNameString;
    private float calCountValue;
    private float carbs;
    private float fats;
    private float protein;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_details);
        Log.d("DetailRecipe","Activity started successfully");

        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        getIncomingIntent();

        Button add = findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserMeal(mealType,recipeNameString,calCountValue);
            }
        });

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("recipe_name") && getIntent().hasExtra("cal_count")){

            recipeNameString = getIntent().getStringExtra("recipe_name");
            calCountValue = getIntent().getFloatExtra("cal_count",0);
            String collection = getIntent().getStringExtra("collection");
            String category = getIntent().getStringExtra("category");
            mealType = getIntent().getStringExtra("MealType");

            getDetails(collection,recipeNameString,category);
            TextView RecipeName = findViewById(R.id.RecipeDetailName);
            TextView CalCount = findViewById(R.id.RecipeDetailCalCount);
            RecipeName.setText(recipeNameString);
            CalCount.setText(calCountValue +" cal");
        }
    }

    public void getDetails(String collection,String recipe_name,String category){


        Call<SearchFoodDetails> call = retrofitInterface.getFoodDetails(collection,recipe_name,category);
        call.enqueue(new Callback<SearchFoodDetails>() {
            @Override
            public void onResponse(Call<SearchFoodDetails> call, Response<SearchFoodDetails> response) {
                if (!response.isSuccessful()) {
//                    textView.setText("Code "+response.code());
                    return;
                }
                SearchFoodDetails foodDetails = response.body();

                TextView carbsTextView = findViewById(R.id.RecipeDetailCarbs);
                TextView fatsTextView = findViewById(R.id.RecipeDetailFats);
                TextView proteinTextView = findViewById(R.id.RecipeDetailProtein);
                ImageView recipeImage = findViewById(R.id.RecipeImage);

                carbs = foodDetails.getCarbohydrates();
                fats = foodDetails.getFats();
                protein = foodDetails.getProtein();
                carbsTextView.setText("Carbs : "+carbs+" g");
                fatsTextView.setText("Fats : "+fats+" g");
                proteinTextView.setText("Protein"+protein+" g");

                Glide.with(RecipeDetailActivity.this).load(foodDetails.getImg_url()).into(recipeImage);

            }

            @Override
            public void onFailure(Call<SearchFoodDetails> call, Throwable t) {
//                textView.setText(t.getMessage());
                Log.d("MainActivity","Error in Fetching Retrofit data from API");
            }
        });
    }

    private void addUserMeal(String mealType,String recipeNameString,float calCountValue){

            AddMealItem mealItem = new AddMealItem(recipeNameString,calCountValue,carbs,fats,protein);
            String userEmail = ((MyApplication) this.getApplication()).getUserEmail();
            Call<AddMealItem> call = retrofitInterface.addMeal(mealItem,userEmail,mealType);
            call.enqueue(new Callback<AddMealItem>() {
                @Override
                public void onResponse(Call<AddMealItem> call, Response<AddMealItem> response) {
                    Intent intent = new Intent(RecipeDetailActivity.this,Diet.class);
                    RecipeDetailActivity.this.startActivity(intent);
                    RecipeDetailActivity.this.finish();
                }

                @Override
                public void onFailure(Call<AddMealItem> call, Throwable t) {
                    Toast.makeText(RecipeDetailActivity.this,"In RecipeDetailActivity.java addUserMeal" +t.getMessage(),
                            LENGTH_SHORT).show();
                }
            });

    }
}
