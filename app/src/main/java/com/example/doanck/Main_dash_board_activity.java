package com.example.doanck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.doanck.ModelLogin.Username;
import com.example.doanck.Model.Token;
import com.google.android.material.tabs.TabLayout;

public class Main_dash_board_activity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageFragment viewPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash_board);
        GetElements();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String token = sharedPreferences.getString("token", "");


//        String token = getIntent().getStringExtra("token");
        Token.SetToken(token);

//        String userName = getIntent().getStringExtra("Username");
        Username.setName(username);

        viewPageFragment = new ViewPageFragment(getSupportFragmentManager(), this);

        viewPager.setAdapter(viewPageFragment);

        viewPager.setOffscreenPageLimit(5);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void GetElements() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }
}
