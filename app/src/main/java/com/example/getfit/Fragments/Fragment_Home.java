package com.example.getfit.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getfit.Adapter.*;
import com.example.getfit.AddMealItem;
import com.example.getfit.Diet;
import com.example.getfit.R;
import com.example.getfit.Retrofit.HomeFragmentDetails;
import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;
import com.example.getfit.SearchActivity;
import com.example.getfit.userProfile;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment {
    private Diet dietActivity;
    private String userEmail;
    public RecyclerView recyclerViewLunch,recyclerViewBreakfast, recyclerViewDinner;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    private Button addBreakfastButton;
    private Button addLunchButton;
    private Button addDinnerButton;

    private TextView caloriesValue,fatValue, carbsValue, proteinValue;

    private ProgressBar caloriesProgress, fatProgress, carbsProgress, proteinProgress;


    public Fragment_Home() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        dietActivity = (Diet) context;
        userEmail = dietActivity.userEmail;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment__home, container, false);

        caloriesValue = view.findViewById(R.id.calorie_value);
        fatValue = view.findViewById(R.id.fats_val);
        carbsValue = view.findViewById(R.id.carbs_val);
        proteinValue = view.findViewById(R.id.protein_val);

        caloriesProgress = view.findViewById(R.id.calorie_progress);
        fatProgress = view.findViewById(R.id.fats_progress);
        carbsProgress = view.findViewById(R.id.carbs_progress);
        proteinProgress = view.findViewById(R.id.protein_progress);

        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        setDetails();
        setProgress();

        getBreakfast();

        getLunch();
        getDinner();

        ArrayList<AddMealItem> breakFastMealItems = new ArrayList<>();
        /*breakFastMealItems.add(new AddMealItem("Demo Brkfast Recipe 1","500 kcals"));
        breakFastMealItems.add(new AddMealItem("Demo Brkfast Recipe 2","700 kcals"));
*/
//        ArrayList<AddMealItem> lunchMealItems = new ArrayList<>();
//        lunchMealItems.add(new AddMealItem("Demo lunch Recipe 1","500 kcals"));
//        lunchMealItems.add(new AddMealItem("Demo lunch Recipe 2","700 kcals"));
//
//        ArrayList<AddMealItem> dinnerMealItems = new ArrayList<>();
//        dinnerMealItems.add(new AddMealItem("Demo dinner Recipe 1","500 kcals"));
//        dinnerMealItems.add(new AddMealItem("Demo dinner Recipe 2","700 kcals"));

        recyclerViewBreakfast = (RecyclerView)view.findViewById(R.id.addBreakfastList);


       recyclerViewLunch = (RecyclerView)view.findViewById(R.id.addLunchList);

       recyclerViewDinner = (RecyclerView)view.findViewById(R.id.addDinnerList);


        addBreakfastButton = view.findViewById(R.id.add_breakfast_button);
        addLunchButton = view.findViewById(R.id.addLuchButton);
        addDinnerButton = view.findViewById(R.id.addDinnerButton);

        addBreakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("MealType","Breakfast");
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        addLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchActivity.class);
                i.putExtra("MealType","Lunch");
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });

         addDinnerButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent1 = new Intent(getActivity(), SearchActivity.class);
                 intent1.putExtra("MealType", "Dinner");
                 getActivity().startActivity(intent1);

             }
         });
        return view;
    }



    private void getBreakfast() {

        Call<List<AddMealItem>>  dataBreakfast = retrofitInterface.getMeal(userEmail,"Breakfast");
        dataBreakfast.enqueue(new Callback<List<AddMealItem>>() {
            @Override
            public void onResponse(Call<List<AddMealItem>> call, Response<List<AddMealItem>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Some failure in fragment home", Toast.LENGTH_SHORT).show();
                }else{
                    List<AddMealItem> addBreakfastItems = response.body();

                    MealAdapter BreakfastMealAdapter = new MealAdapter(addBreakfastItems);
                    recyclerViewBreakfast.setAdapter(BreakfastMealAdapter);
                    RecyclerView.LayoutManager layoutManagerBreakfast = new LinearLayoutManager(getActivity());
                    recyclerViewBreakfast.setLayoutManager(layoutManagerBreakfast);


                }
            }

            @Override
            public void onFailure(Call<List<AddMealItem>> call, Throwable t) {
               /* Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();
*/
            }
        });
    }
    private void getLunch(){
        Call<List<AddMealItem>>  dataLunch = retrofitInterface.getMeal(userEmail,"Lunch");
        dataLunch.enqueue(new Callback<List<AddMealItem>>() {
            @Override
            public void onResponse(Call<List<AddMealItem>> call, Response<List<AddMealItem>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Some failure in fragment home", Toast.LENGTH_SHORT).show();
                }else{
                    List<AddMealItem> addLunchItems = response.body();

                    MealAdapter LunchMealAdapter = new MealAdapter(addLunchItems);
                    recyclerViewLunch.setAdapter(LunchMealAdapter);
                    RecyclerView.LayoutManager layoutManagerLunch= new LinearLayoutManager(getActivity());
                    recyclerViewLunch.setLayoutManager(layoutManagerLunch);


                }
            }

            @Override
            public void onFailure(Call<List<AddMealItem>> call, Throwable t) {
               /* Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();*/
            }
        });

    }

    private void getDinner() {
        Call<List<AddMealItem>>  dataDinner = retrofitInterface.getMeal(userEmail,"Dinner");
        dataDinner.enqueue(new Callback<List<AddMealItem>>() {
            @Override
            public void onResponse(Call<List<AddMealItem>> call, Response<List<AddMealItem>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Some failure in fragment home", Toast.LENGTH_SHORT).show();
                }else{
                    List<AddMealItem> addDinnerItems = response.body();

                    MealAdapter DinnerMealAdapter = new MealAdapter(addDinnerItems);
                    recyclerViewDinner.setAdapter(DinnerMealAdapter);
                    RecyclerView.LayoutManager layoutManagerDinner = new LinearLayoutManager(getActivity());
                    recyclerViewDinner.setLayoutManager(layoutManagerDinner);


                }
            }

            @Override
            public void onFailure(Call<List<AddMealItem>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDetails(){
        Log.d("Home Fragment Details",userEmail);
        final Call<HomeFragmentDetails> detailsCall = retrofitInterface.getHomeFragDetails(userEmail);
        detailsCall.enqueue(new Callback<HomeFragmentDetails>() {
            @Override
            public void onResponse(Call<HomeFragmentDetails> call, Response<HomeFragmentDetails> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Some failure in fragment home", Toast.LENGTH_SHORT).show();
                }else{

                    HomeFragmentDetails details = response.body();

                    float calories = details.getCalories();
                    float fats = details.getFat();
                    float carbs = details.getCarbohydrates();
                    float protein = details.getProtein();
                    float eatencalories = details.getEatenCalories();
                    float eatenfats = details.getEatenFats();
                    float eatencarbs = details.getEatenCarbs();
                    float eatenprotein = details.getEatenProtein();
                    int calProgValue = 0;
                    int carbsProgValue = 0;
                    int fatsProgValue = 0;
                    int proteinProgValue = 0;

                    caloriesValue.setText(String.valueOf(calories));
                    fatValue.setText(String.valueOf(fats));
                    carbsValue.setText(String.valueOf(carbs));
                    proteinValue.setText(String.valueOf(protein));

                    if(eatencalories < calories){
                        calProgValue = Math.round(eatencalories*100/calories);
                    }
                    else{
                        calProgValue = 100;
                    }

                    if(eatenfats < fats){
                        fatsProgValue = Math.round(eatenfats*100/fats);
                    }
                    else{
                        fatsProgValue = 100;
                    }

                    if(eatencarbs < carbs){
                        carbsProgValue = Math.round(eatencarbs*100/carbs);
                    }
                    else{
                        carbsProgValue = 100;
                    }

                    if(eatenprotein < protein) {
                        proteinProgValue = Math.round(eatenprotein*100/protein);
                    }
                    else {
                        proteinProgValue = 100;
                    }

                    caloriesProgress.setProgress(calProgValue);
                    carbsProgress.setProgress(carbsProgValue);
                    proteinProgress.setProgress(proteinProgValue);
                    fatProgress.setProgress(fatsProgValue);

                }
            }

            @Override
            public void onFailure(Call<HomeFragmentDetails> call, Throwable t) {
                Toast.makeText(getActivity(),"In set Details - Home Fragment" +t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setProgress(){

    }

}
