package com.example.doanck;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.example.doanck.Model.Token;
import com.example.doanck.ModelLogin.Username;
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

        LocalBroadcastManager.getInstance(this).registerReceiver(languageChangeReceiver, new IntentFilter("LanguageChanged"));
    }


    // Xử lý sự kiện thay đổi ngôn ngữ
    private final BroadcastReceiver languageChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String selectedLanguage = intent.getStringExtra("selectedLanguage");
            updateLanguage(selectedLanguage);
        }
    };

    // Hàm cập nhật ngôn ngữ cho tất cả Fragment trong ViewPager
    private void updateLanguage(String selectedLanguage) {
        // Lấy số lượng Fragment từ adapter
        int fragmentCount = viewPageFragment.getCount();
        for (int i = 0; i < fragmentCount; i++) {
            Fragment fragment = viewPageFragment.getItem(i);
            if (fragment instanceof OnLanguageChangedListener) {
                ((OnLanguageChangedListener) fragment).onLanguageChanged(selectedLanguage);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Hủy đăng ký BroadcastReceiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(languageChangeReceiver);
    }

    // Hàm lấy các thành phần từ layout
    private void GetElements() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }
}
