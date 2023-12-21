package com.example.doanck;

import android.os.Bundle;

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
        switch (position){
            case 0:{
                return new Map_Fragment();
            }
            case 1:{
                return new Weather_Fragment();
            }
            case 2:{
                return new Chart_Fragment();
            }
            case 3:{
                return new Setting_Fragment();
            }
            default:
                return  new Map_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
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
                title="SETTING";
            }
        }
        return title;
    }
}
