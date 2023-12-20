package com.example.doanck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import android.util.Log;


import com.example.doanck.ModelLogin.Username;
import com.example.doanck.Model.Token;

public class Main_dash_board_activity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  ViewPageFragment viewPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash_board);
        getElement();

        String token = getIntent().getStringExtra("token");
        Token.SetToken(token);
        Log.d("Token:",Token.getToken());

        String userName = getIntent().getStringExtra("Username");
        Username.setName(userName);


        viewPageFragment = new ViewPageFragment(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(viewPageFragment);

        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void getElement(){
       tabLayout = findViewById(R.id.tabLayout);
       viewPager = findViewById(R.id.viewPager);
    }
}