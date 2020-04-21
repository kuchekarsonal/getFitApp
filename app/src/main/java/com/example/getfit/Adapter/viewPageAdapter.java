package com.example.getfit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.getfit.ModelClasses.Model;
import com.example.getfit.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class viewPageAdapter extends PagerAdapter {
    private List<Model> foodIteams;
    private LayoutInflater layoutInflater;
    private Context context;

    public viewPageAdapter(List<Model> foodIteams, Context context) {
        this.foodIteams = foodIteams;
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodIteams.size();
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
        TextView foodItem2, foodItem3;

        imageView = (ImageView)view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
       /* foodItem1 = view.findViewById(R.id.meal1);
        foodItem2= view.findViewById(R.id.meal2);
        foodItem3 = view.findViewById(R.id.meal3);*/

        imageView.setImageResource(foodIteams.get(position).getImage());
        title.setText(foodIteams.get(position).getTitle());
        /*foodItem2.setText(foodIteams.get(position).getFoodItem1());
        foodItem2.setText(foodIteams.get(position).getFooodItem2());
        foodItem3.setText(foodIteams.get(position).getFoodItem3());*/

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }
}
