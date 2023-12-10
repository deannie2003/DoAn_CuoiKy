package com.example.doanck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class intro_app_activity extends AppCompatActivity {
    ViewPager mIntroViewPager;
    LinearLayout mDotLayout;
    String s;
    Button backbtn,nextbtn,skipbtn;

    TextView[] dots;
    intro_app_Adapter introAdapter;
    SharedPreferences prefs;

    private void setLanguage() {
        boolean isVietnamese = prefs.getBoolean("isVietnamese", false);
        skipbtn.setText(isVietnamese ? "BỎ QUA" : "SKIP");
        nextbtn.setText(isVietnamese ? "TIẾP":"NEXT");
        backbtn.setText(isVietnamese ? "TRƯỚC":"BACK");
    }
    @Override
    protected void onResume() {
        super.onResume();
        setLanguage();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_app);
        backbtn = findViewById(R.id.back_btn);
        nextbtn = findViewById(R.id.next_btn);
        skipbtn = findViewById(R.id.skip_btn);
        prefs = getSharedPreferences("LanguageSettings", MODE_PRIVATE);
        setLanguage();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItem(0)>0){
                    mIntroViewPager.setCurrentItem(getItem(-1),true);
                }
            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getItem(0)<3)
                    mIntroViewPager.setCurrentItem(getItem(1),true);
                else {
                    Intent i = new Intent(intro_app_activity.this,homePageActivity.class);
                    startActivity(i);
                }
            }
        });
        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(intro_app_activity.this, homePageActivity.class);
                startActivity(intent);
            }
        });
        mIntroViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        introAdapter = new intro_app_Adapter(this);

        mIntroViewPager.setAdapter(introAdapter);

        setUpindicator(0);
        mIntroViewPager.addOnPageChangeListener(viewListener);
    }
    public void setUpindicator (int position){
        dots = new TextView[4];
        mDotLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++){
            dots[i]= new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor (getResources().getColor(R.color.inactive, getApplicationContext().getTheme()));
        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setUpindicator(position);
            if(position>0){
                backbtn.setVisibility(View.VISIBLE);
            }
            else {
                backbtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getItem(int i){
        return mIntroViewPager.getCurrentItem()+i;
    }
}


