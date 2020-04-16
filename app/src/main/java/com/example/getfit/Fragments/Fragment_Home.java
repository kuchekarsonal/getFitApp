package com.example.getfit.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.getfit.Adapter.*;
import com.example.getfit.AddMealItem;
import com.example.getfit.Diet;
import com.example.getfit.R;
import com.example.getfit.Retrofit.RetrofitClient;
import com.example.getfit.Retrofit.RetrofitInterface;
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


        //Init Service
        retrofit = RetrofitClient.getInstance();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        getBreakfast();

        getLunch();
        getDinner();

        ArrayList<AddMealItem> breakFastMealItems = new ArrayList<>();
        /*breakFastMealItems.add(new AddMealItem("Demo Brkfast Recipe 1","500 kcals"));
        breakFastMealItems.add(new AddMealItem("Demo Brkfast Recipe 2","700 kcals"));
*/
        ArrayList<AddMealItem> lunchMealItems = new ArrayList<>();
        lunchMealItems.add(new AddMealItem("Demo lunch Recipe 1","500 kcals"));
        lunchMealItems.add(new AddMealItem("Demo lunch Recipe 2","700 kcals"));

        ArrayList<AddMealItem> dinnerMealItems = new ArrayList<>();
        dinnerMealItems.add(new AddMealItem("Demo dinner Recipe 1","500 kcals"));
        dinnerMealItems.add(new AddMealItem("Demo dinner Recipe 2","700 kcals"));

        recyclerViewBreakfast = (RecyclerView)view.findViewById(R.id.addBreakfastList);


       recyclerViewLunch = (RecyclerView)view.findViewById(R.id.addLunchList);


        MealAdapter lunchMealAdapter = new MealAdapter(lunchMealItems);
        recyclerViewLunch.setAdapter(lunchMealAdapter);
        RecyclerView.LayoutManager layoutManagerLunch = new LinearLayoutManager(getActivity());
        recyclerViewLunch.setLayoutManager(layoutManagerLunch);

        recyclerViewDinner = (RecyclerView)view.findViewById(R.id.addDinnerList);
        MealAdapter dinnerMealAdapter = new MealAdapter(dinnerMealItems);
        recyclerViewDinner.setAdapter(dinnerMealAdapter);
        RecyclerView.LayoutManager layoutManagerDinner = new LinearLayoutManager(getActivity());
        recyclerViewDinner.setLayoutManager(layoutManagerDinner);

//        return inflater.inflate(R.layout.fragment_fragment__home, container, false);



        return view;
    }



    private void getBreakfast() {

        Call<List<AddMealItem>>  dataBreakfast = retrofitInterface.getBreakfast(userEmail);
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
                Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getLunch(){
        Call<List<AddMealItem>>  dataLunch= retrofitInterface.getLunch(userEmail);
        dataLunch.enqueue(new Callback<List<AddMealItem>>() {
            @Override
            public void onResponse(Call<List<AddMealItem>> call, Response<List<AddMealItem>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Some failure in fragment home", Toast.LENGTH_SHORT).show();
                }else{
                    List<AddMealItem> addLunchItems = response.body();

                    MealAdapter LunchMealAdapter = new MealAdapter(addLunchItems);
                    recyclerViewBreakfast.setAdapter(LunchMealAdapter);
                    RecyclerView.LayoutManager layoutManagerLunch= new LinearLayoutManager(getActivity());
                    recyclerViewBreakfast.setLayoutManager(layoutManagerLunch);


                }
            }

            @Override
            public void onFailure(Call<List<AddMealItem>> call, Throwable t) {

            }
        });

    }

    private void getDinner() {
        Call<List<AddMealItem>> dataDinner = retrofitInterface.getDinner(userEmail);
        dataDinner.enqueue(new Callback<List<AddMealItem>>() {
            @Override
            public void onResponse(Call<List<AddMealItem>> call, Response<List<AddMealItem>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(),"Some failure in fragment home", Toast.LENGTH_SHORT).show();
                }else{
                    List<AddMealItem> addDinnerItems = response.body();

                    MealAdapter DinnerMealAdapter = new MealAdapter(addDinnerItems);
                    recyclerViewBreakfast.setAdapter(DinnerMealAdapter);
                    RecyclerView.LayoutManager layoutManagerDinner = new LinearLayoutManager(getActivity());
                    recyclerViewBreakfast.setLayoutManager(layoutManagerDinner);


                }
            }

            @Override
            public void onFailure(Call<List<AddMealItem>> call, Throwable t) {

            }
        });
    }



}
