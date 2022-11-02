package com.csse.busticketingsystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
    };

    int headings[] = {
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three
    };

    int descriptions[] = {
            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageView = view.findViewById(R.id.slider_img);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView heading = view.findViewById(R.id.slider_heading);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
