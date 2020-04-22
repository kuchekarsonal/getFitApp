package com.example.getfit;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.getfit.Adapter.viewPageAdapter;
import com.example.getfit.ModelClasses.DietPlanModel;
import com.example.getfit.ModelClasses.Model;
import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;


public class DietPlanSelected extends Fragment {
    DietPlanModel dietPlanModel;
    ViewPager viewPager;
    viewPageAdapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    List<DietPlanModel.Meal> BreakfastItems, BreakfastItems2;

    String email;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    public DietPlanSelected() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/

       email = ((Diet)getActivity()).userEmail;


        ///Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_diet_plan_selected, container, false);
        models = new ArrayList<>();
        //Retrofit Call
        Call<DietPlanModel> callDietPlan = retrofitInterface.getRecommendation(email);
        callDietPlan.enqueue(new Callback<DietPlanModel>() {
            @Override
            public void onResponse(Call<DietPlanModel> call, Response<DietPlanModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Some failure in getting meal - DietPlanSelected", Toast.LENGTH_SHORT).show();
                }
                else {
                    dietPlanModel = response.body();
                    Toast.makeText(getActivity(),String.valueOf(dietPlanModel.getTotalMeal().getCalories()), Toast.LENGTH_SHORT).show();
                    Log.d("Fetched Total Calories",String.valueOf(dietPlanModel.getTotalMeal().getCalories()));
                }
            }

            @Override
            public void onFailure(Call<DietPlanModel> call, Throwable t) {
                Toast.makeText(getActivity(),"In getting meal error - DietPlanSelected" +t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });


        String b1Receipe = BreakfastItems.get(0).getRecipe_name();
        float b1caloriecount = BreakfastItems.get(1).getCalories();
        float b1serving = BreakfastItems.get(2).getServings();

        String b2Receipe = BreakfastItems2.get(0).getRecipe_name();
        float b2caloriecount = BreakfastItems2.get(1).getCalories();
        float b2serving = BreakfastItems2.get(2).getServings();



        models.add(new Model(R.drawable.breakfast, "Breakfast",b1Receipe,b1caloriecount,b1serving,b2Receipe,b2caloriecount,b2serving));

      //  models.add(new Model(R.drawable.lunch, "Lunch","Butter Chicken","Chai","ButterMilk"));
        //models.add(new Model(R.drawable.dinner, "Dinner","Butter Chicken","Chai","ButterMilk"));
        adapter = new viewPageAdapter(models, getActivity());

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.colorGreenP1),
                getResources().getColor(R.color.colorOrangeLightP1),
                getResources().getColor(R.color.colorOrangeP1),
                getResources().getColor(R.color.colorPinkP2)
        };
        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }
}
