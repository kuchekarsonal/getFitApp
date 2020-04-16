package com.example.getfit.Adapter;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getfit.AddMealItem;
import com.example.getfit.R;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.viewHolder> {
    private ArrayList<AddMealItem> mealItems;

    public static class viewHolder extends RecyclerView.ViewHolder {

        public TextView recipeNameView;
        public TextView calorieCountView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameView = itemView.findViewById(R.id.item_recipe_name);
            calorieCountView = itemView.findViewById(R.id.item_calorie_count);
        }
    }

    public MealAdapter(ArrayList<AddMealItem> mealItems) {
        this.mealItems = mealItems;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addmeal,parent,false);
        viewHolder vh = new viewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        AddMealItem currentItem = mealItems.get(position);

        holder.recipeNameView.setText(currentItem.getRecipeName());
        holder.calorieCountView.setText(currentItem.getCaloriesCount());
    }

    @Override
    public int getItemCount() {
        return mealItems.size();
    }
}
