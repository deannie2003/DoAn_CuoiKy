package com.example.doanck;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPageFragment extends FragmentPagerAdapter {
    private Bundle fragmentBundle;
    private Context context;
    private int[] iconResources = {
            R.drawable.baseline_home_24,
            R.drawable.baseline_map_24,
            R.drawable.baseline_weather_unit_24,
            R.drawable.baseline_chart_24,
            R.drawable.baseline_account_circle_24
    };
    public ViewPageFragment(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    public void setFragmentBundle(Bundle data) {
        this.fragmentBundle = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:{
                fragment = new Hello_user_fragment();
                break;
            }
            case 1:{
                fragment = new Map_Fragment();
                break;
            }
            case 2:{
                fragment = new Weather_Fragment();
                break;
            }
            case 3:{
                fragment = new Chart_Fragment();
                break;
            }
            case 4:{
                fragment = new Setting_Fragment();
                break;
            }
            default:
                fragment = new Hello_user_fragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    //    @Nullable
    private Bundle createBundleForIcon(int iconResource) {
        Bundle bundle = new Bundle();
        bundle.putInt("icon", iconResource);
        return bundle;
    }

    @Nullable
    @Override
//    public CharSequence getPageTitle(int position) {
//        String title="";
//        switch (position){
//            case 0:{
//                title="Hello user";
//                break;
//            }
//            case 1:{
//                title="MAP";
//                break;
//            }
//            case 2:{
//                title="Weather";
//                break;
//            }
//            case 3:{
//                title="Chart";
//                break;
//            }
//            case 4:{
//                title="Settings";
//                break;
//            }
//        }
//        return title;
//    }
    public CharSequence getPageTitle(int position) {
        Drawable icon = context.getResources().getDrawable(iconResources[position]);
        icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(icon);
        // Chuyển đổi Drawable thành Spannable để hiển thị trong TabLayout
        SpannableString  spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}