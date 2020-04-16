package com.example.getfit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getfit.R;
import com.example.getfit.RecipeDetailActivity;
import com.example.getfit.Retrofit.SearchFoodDetails;
import com.example.getfit.Retrofit.SearchFoodItem;

import java.util.ArrayList;
import java.util.List;

public class SearchFoodAdapter extends RecyclerView.Adapter<SearchFoodAdapter.ViewHolder> implements Filterable {

    private List<SearchFoodItem> foodItems;
    private  List<SearchFoodItem> foodItemsFull;

    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView recipeName;
        public TextView calCount;
        public RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.RecipeName);
            calCount = itemView.findViewById(R.id.CalCount);
            parentLayout = itemView.findViewById(R.id.ItemLayout);
        }
    }

    public SearchFoodAdapter(Context context, List<SearchFoodItem> foodItems) {
        this.foodItems = foodItems;
        this.foodItemsFull = new ArrayList<>(foodItems);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_card,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SearchFoodItem currentItem = foodItems.get(position);
        holder.recipeName.setText(currentItem.getRecipe_name());
        holder.calCount.setText(currentItem.getCalories()+" cal");
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra("recipe_name",currentItem.getRecipe_name());
                intent.putExtra("cal_count",currentItem.getCalories());
                intent.putExtra("collection",currentItem.getCollection());
                intent.putExtra("category",currentItem.getCategory());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    @Override
    public Filter getFilter() {
        return recipeFilter;
    }

    private Filter recipeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<SearchFoodItem> filteredList = new ArrayList<>();
            if((charSequence == null) || (charSequence.length() == 0) ){
                filteredList.addAll(foodItemsFull);
            } else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(SearchFoodItem item: foodItemsFull) {
                    if(item.getRecipe_name().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return  results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            foodItems.clear();
            foodItems.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
