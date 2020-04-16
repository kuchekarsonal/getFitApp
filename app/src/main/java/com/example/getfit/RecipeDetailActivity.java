package com.example.getfit;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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

public class RecipeDetailActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_details);
        Log.d("DetailRecipe","Activity started successfully");

        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("recipe_name") && getIntent().hasExtra("cal_count")){

            String RecipeNameString = getIntent().getStringExtra("recipe_name");
            Float CalCountValue = getIntent().getFloatExtra("cal_count",0);
            String collection = getIntent().getStringExtra("collection");
            String category = getIntent().getStringExtra("category");

            getDetails(collection,RecipeNameString,category);
            TextView RecipeName = findViewById(R.id.RecipeDetailName);
            TextView CalCount = findViewById(R.id.RecipeDetailCalCount);
            RecipeName.setText(RecipeNameString);
            CalCount.setText(CalCountValue +" cal");
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

                carbsTextView.setText("Carbs : "+foodDetails.getCarbohydrates()+" g");
                fatsTextView.setText("Fats : "+foodDetails.getFats()+" g");
                proteinTextView.setText("Protein"+foodDetails.getProtein()+" g");

                Glide.with(RecipeDetailActivity.this).load(foodDetails.getImg_url()).into(recipeImage);

            }

            @Override
            public void onFailure(Call<SearchFoodDetails> call, Throwable t) {
//                textView.setText(t.getMessage());
                Log.d("MainActivity","Error in Fetching Retrofit data from API");
            }
        });
    }
}
