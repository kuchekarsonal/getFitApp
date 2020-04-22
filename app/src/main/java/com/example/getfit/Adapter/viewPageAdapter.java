package com.example.getfit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getfit.ModelClasses.Model;
import com.example.getfit.R;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class viewPageAdapter extends PagerAdapter {
    private List<Model> foodItems;
    private LayoutInflater layoutInflater;
    private Context context;

    public viewPageAdapter(List<Model> foodItems, Context context) {
        this.foodItems = foodItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_dietplan_selected,container,false);


        ImageView imageView;
        TextView title;
        TextView foodItem1;
        TextView foodItem1_calorie;
        TextView foodItem1_serves;

        TextView foodItem2;
        TextView foodItem2_calorie;
        TextView fooditem2_serves;

        TextView protein, carb, fat;


        imageView = (ImageView)view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);

        foodItem1 = view.findViewById(R.id.titlemealViewpager);
        foodItem1_calorie = view.findViewById(R.id.tvCalorieCountViewpager);
        foodItem1_serves = view.findViewById(R.id.tvServesViewpager);

        foodItem2 = view.findViewById(R.id.titleMeal2Viewpager);
        foodItem2_calorie = view.findViewById(R.id.tvCalorieCountViewpager2);
        fooditem2_serves = view.findViewById(R.id.tvServesViewpager2);

        protein = view.findViewById(R.id.tvProteinPercent);
        carb = view.findViewById(R.id.tvCarbPercent);
        fat = view.findViewById(R.id.tvFatPercent);


        imageView.setImageResource(foodItems.get(position).getImage());
        title.setText(foodItems.get(position).getTitle());

        foodItem1.setText(foodItems.get(position).getFoodItem1());
        foodItem1_calorie.setText(String.valueOf(foodItems.get(position).getFoodItem1_calorieCount()));
        foodItem1_serves.setText(String.valueOf(foodItems.get(position).getFoodItem1_serves()));



        foodItem2.setText(foodItems.get(position).getFoodItem2());
        foodItem2_calorie.setText(String.valueOf(foodItems.get(position).getFoodItem2_calorieCount()));
        foodItem1_serves.setText(String.valueOf(foodItems.get(position).getFoodItem2_serves()));

        protein.setText(String.valueOf(foodItems.get(position).getProtein_percent()));
        carb.setText(String.valueOf(foodItems.get(position).getCarb_percent()));
        fat.setText(String.valueOf(foodItems.get(position).getFat_percent()));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
