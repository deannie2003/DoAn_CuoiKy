package com.example.doanck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class intro_app_Adapter extends PagerAdapter {
    Context context;
    int images[]={
            R.drawable.first_intro,
            R.drawable.map_intro,
            R.drawable.dash_board_intro,
            R.drawable.graph_intro
    };
    int headings[]={
            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth
    };
    int description[]={
            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three,
            R.string.desc_fourth
    };

    public intro_app_Adapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.first_intro,container,false);

        ImageView introtitleimage = (ImageView) view.findViewById(R.id.titleImage);
        TextView introHeading = (TextView) view.findViewById(R.id.heading);
        TextView introDesciption = (TextView) view.findViewById(R.id.deccription);
        introtitleimage.setImageResource(images [position]);
        introHeading.setText(headings [position]);
        introDesciption.setText(description[position]);
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
