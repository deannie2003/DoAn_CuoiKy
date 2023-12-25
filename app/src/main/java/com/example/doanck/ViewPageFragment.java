package com.example.doanck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPageFragment extends FragmentPagerAdapter {
    private Bundle fragmentBundle;
    public ViewPageFragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        fragmentBundle = new Bundle();
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
                fragment.setArguments(createBundleForIcon(R.drawable.ic_settings_24dp));
                break;
            }
            case 1:{
                fragment = new Map_Fragment();
                fragment.setArguments(createBundleForIcon(R.drawable.baseline_home_24));
                break;
            }
            case 2:{
                fragment = new Weather_Fragment();
                fragment.setArguments(createBundleForIcon(R.drawable.baseline_water_drop_24));
                break;
            }
            case 3:{
                fragment = new Chart_Fragment();
                fragment.setArguments(createBundleForIcon(R.drawable.baseline_location_on_24));
                break;
            }
            case 4:{
                fragment = new Setting_Fragment();
                fragment.setArguments(createBundleForIcon(R.drawable.baseline_person_24));
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
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:{
                title="MAP";
                break;
            }
            case 1:{
                title="WEATHER";
                break;
            }
            case 2:{
                title="CHART";
                break;
            }
            case 3:{
                title="WEATHER1";
                break;
            }
            case 4:{
                title="CHART1";
                break;
            }
        }
        return title;
    }
}