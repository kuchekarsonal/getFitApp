package com.example.getfit.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.getfit.Adapter.*;
import com.example.getfit.AddMealItem;
import com.example.getfit.Diet;
import com.example.getfit.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment {
    private Diet dietActivity;
    private String userEmail;
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

        ArrayList<AddMealItem> breakFastMealItems = new ArrayList<>();
        breakFastMealItems.add(new AddMealItem("Demo Brkfast Recipe 1","500 kcals"));
        breakFastMealItems.add(new AddMealItem("Demo Brkfast Recipe 2","700 kcals"));

        ArrayList<AddMealItem> lunchMealItems = new ArrayList<>();
        lunchMealItems.add(new AddMealItem("Demo lunch Recipe 1","500 kcals"));
        lunchMealItems.add(new AddMealItem("Demo lunch Recipe 2","700 kcals"));

        ArrayList<AddMealItem> dinnerMealItems = new ArrayList<>();
        dinnerMealItems.add(new AddMealItem("Demo dinner Recipe 1","500 kcals"));
        dinnerMealItems.add(new AddMealItem("Demo dinner Recipe 2","700 kcals"));

        RecyclerView recyclerViewBreakfast = view.findViewById(R.id.addBreakfastList);
        MealAdapter breakfastMealAdapter = new MealAdapter(breakFastMealItems);
        recyclerViewBreakfast.setAdapter(breakfastMealAdapter);
        RecyclerView.LayoutManager layoutManagerBreakfast = new LinearLayoutManager(getActivity());
        recyclerViewBreakfast.setLayoutManager(layoutManagerBreakfast);

        RecyclerView recyclerViewLunch = (RecyclerView)view.findViewById(R.id.addLunchList);
        MealAdapter lunchMealAdapter = new MealAdapter(lunchMealItems);
        recyclerViewLunch.setAdapter(lunchMealAdapter);
        RecyclerView.LayoutManager layoutManagerLunch = new LinearLayoutManager(getActivity());
        recyclerViewLunch.setLayoutManager(layoutManagerLunch);

        RecyclerView recyclerViewDinner = (RecyclerView)view.findViewById(R.id.addDinnerList);
        MealAdapter dinnerMealAdapter = new MealAdapter(dinnerMealItems);
        recyclerViewDinner.setAdapter(dinnerMealAdapter);
        RecyclerView.LayoutManager layoutManagerDinner = new LinearLayoutManager(getActivity());
        recyclerViewDinner.setLayoutManager(layoutManagerDinner);

//        return inflater.inflate(R.layout.fragment_fragment__home, container, false);

        return view;

    }

}
