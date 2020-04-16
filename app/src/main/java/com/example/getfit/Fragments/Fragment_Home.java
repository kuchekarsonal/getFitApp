package com.example.getfit.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.getfit.Adapter.*;
import com.example.getfit.Diet;
import com.example.getfit.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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

        RecyclerView recyclerViewBreakfast = (RecyclerView)view.findViewById(R.id.addBreakfastList);
        addMealAdapter addmealAdapter = new addMealAdapter();
        recyclerViewBreakfast.setAdapter(addmealAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewBreakfast.setLayoutManager(layoutManager);

        RecyclerView recyclerViewLunch = (RecyclerView)view.findViewById(R.id.addLunchList);
       // addMealAdapter addmealAdapter = new addMealAdapter();
        recyclerViewBreakfast.setAdapter(addmealAdapter);
        RecyclerView.LayoutManager layoutManagerLunch = new LinearLayoutManager(getActivity());
        recyclerViewLunch.setLayoutManager(layoutManagerLunch);

        RecyclerView recyclerViewDinner = (RecyclerView)view.findViewById(R.id.addDinnerList);
        //addMealAdapter addmealAdapter = new addMealAdapter();
        recyclerViewBreakfast.setAdapter(addmealAdapter);
        RecyclerView.LayoutManager layoutManagerDinner = new LinearLayoutManager(getActivity());
        recyclerViewDinner.setLayoutManager(layoutManagerDinner);




        return view;

    }

}
