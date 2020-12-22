package com.rait.registeractivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context)
    {
        this.context=context;
    }

    //Arrays
    public int[] slide_images ={
            R.drawable.learn,
            R.drawable.harvest,
            R.drawable.earn
    };


    public String[] slide_headings ={
      "LEARN",
      "HARVEST","EARN"
    };

    public String[] slide_description ={
            "Learn the best way to harvest your crops.Know which type of crop will have the maximum yield on your land." +
                    "Analyse the best fertilizers and pesticides and their correct amount.Feed what your crop needs.",
            "Harvest the grown crops in the right season at the right time to get the best results.",
            "Time to sell your harvested crops in the market.Know the current status of the market and earn the right profit."

    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_slide_layout,container,false);
        CircleImageView slideImageView = (CircleImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_description[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((RelativeLayout)object);
    }
}

