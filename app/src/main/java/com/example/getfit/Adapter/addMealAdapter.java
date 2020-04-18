package com.example.getfit.Adapter;


//Recyclerview Adapter for displaying add BreakFast, Lunch and dinner
//User add its own meal on fragment_fragment_home

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.getfit.Fragments.MyItemRecyclerViewAdapter;
import com.example.getfit.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class addMealAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_addmeal, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
/*
    private class mealHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView img;
        private TextView addText;  //for addBreakfast/ lunch/dinner
        private mealHolder(View itemView){
            super(itemView);hiy pull
            img =(ImageView)itemView.findViewById(R.id.addimg);
            addText = (TextView)itemView.findViewById(R.id.addText);
            itemView.setOnClickListener(this);
        }
    }*/
}
