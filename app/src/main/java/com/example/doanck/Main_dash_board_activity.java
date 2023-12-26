package com.example.doanck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

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

        String token = getIntent().getStringExtra("token");
        Token.SetToken(token);
        Log.d("Token:", Token.getToken());

        String userName = getIntent().getStringExtra("Username");
        Username.setName(userName);

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
