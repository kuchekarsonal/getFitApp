package com.example.getfit;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.getfit.Adapter.MealAdapter;
import com.example.getfit.Adapter.viewPageAdapter;

import java.util.ArrayList;
import java.util.List;


public class DietPlanSelected extends Fragment {

    ViewPager viewPager;
    viewPageAdapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
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




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_diet_plan_selected, container, false);
        models = new ArrayList<>();
        models.add(new Model(R.drawable.breakfast, "Breakfast","Butter Chicken","Chai","ButterMilk"));
        models.add(new Model(R.drawable.lunch, "Lunch","Butter Chicken","Chai","ButterMilk"));
        models.add(new Model(R.drawable.dinner, "Dinner","Butter Chicken","Chai","ButterMilk"));
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
