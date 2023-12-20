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
        Fragment fragment;
        switch (position){
            case 0:{
                fragment = new Map_Fragment();
                fragment.setArguments(createBundleForIcon(R.drawable.baseline_home_24));
                break;
            }
            case 1:{
                fragment = new Weather_Fragment();
                fragment.setArguments(createBundleForIcon(R.drawable.baseline_home_24));
                break;
            }
            case 2:{
                fragment = new Chart_Fragment();
                fragment.setArguments(createBundleForIcon(R.drawable.baseline_home_24));
                break;
            }
            case 3:{
                fragment = new Setting_Fragment();
                fragment.setArguments(createBundleForIcon(R.drawable.ic_settings_24dp));
                break;
            }
            default:
                fragment = new Map_Fragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

//    @Nullable
        private Bundle createBundleForIcon(int iconResource) {
       Bundle bundle = new Bundle();
       bundle.putInt("icon", iconResource);
       return bundle;
      }
}
