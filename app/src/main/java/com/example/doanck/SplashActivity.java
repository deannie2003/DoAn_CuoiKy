package com.example.doanck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation topAnim, botAnim;
    ImageView img;
    TextView app_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bot_animation);

        img = findViewById(R.id.splash_image);
        app_name = findViewById(R.id.heading);

        img.setAnimation(topAnim);
        app_name.setAnimation(botAnim);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);


        if(token != null) {
            // Người dùng đã đăng nhập, chuyển đến màn hình hello_user
            Intent intent = new Intent(SplashActivity.this, sign_in_activity.class);
            startActivity(intent);
            finish();
        }
        else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, intro_app_activity.class);
                        startActivity(i);
                        finish();
                    }
                }, SPLASH_SCREEN);
        }

    }

}
