package com.example.getfit;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getfit.Adapter.SearchFoodAdapter;
import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;
import com.example.getfit.Retrofit.SearchFoodItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchFoodAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    private String mealType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        if(getIntent().hasExtra("MealType")){
            mealType = getIntent().getStringExtra("MealType");
        }
        searchFood();
    }

    public void searchFood(){
        //        textView.setText("");
        //"http://192.168.43.46:3000" - redmi
        //"http://192.168.137.1:3000"
        //"http://192.168.42.84:3000" - moto
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.43.46:3000")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        FoodSearchApi foodSearchApi = retrofit.create(FoodSearchApi.class);

        Call<List<SearchFoodItem>> call = retrofitInterface.getFoodItems();
        call.enqueue(new Callback<List<SearchFoodItem>>() {
            @Override
            public void onResponse(Call<List<SearchFoodItem>> call, Response<List<SearchFoodItem>> response) {
                if(!response.isSuccessful()){
//                    textView.setText("Code "+response.code());
                    return;
                }
                List<SearchFoodItem> foodItems = response.body();


                adapter = new SearchFoodAdapter(SearchActivity.this,foodItems,mealType);
                recyclerView.setAdapter(adapter);
//                for(FoodItem item: foodItems){
//                    String content = "";
//                    content +="Food Item : "+ post.getRecipe_name()+"\n";
//                    content+="Calories : "+post.getCalories()+"\n";
//                    content+="Carbohydrates : "+post.getCarbohydrates()+"\n";
//                    content+="Protein : "+post.getProtein()+"\n";
//                    content+="Fat : "+post.getFat()+"\n\n";
//                    textView.append(content);
//                }
            }

            @Override
            public void onFailure(Call<List<SearchFoodItem>> call, Throwable t) {
//                textView.setText(t.getMessage());
                Log.d("MainActivity","Error in Fetching Retrofit data from API");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}
