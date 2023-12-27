package com.example.doanck;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class homePageActivity extends AppCompatActivity {

    private Button btn,btn1;
    private TextView txt_or;
    private ImageButton img_language;
    private ImageView img_homepage;

    boolean isChanged = false;

    private Button btn_sign_in,btn_sign_up,btn_google,btn_forgot;

    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        LoadElement();

        btn_sign_in = findViewById(R.id.sign_in_btn);
        btn_sign_up = findViewById(R.id.sign_up_btn);
        img_language = findViewById(R.id.language_chance);
        txt_or = findViewById(R.id.or_txt);
        btn_google = findViewById(R.id.google_btn);
        btn_forgot = findViewById(R.id.forgot_pw);

        prefs = getSharedPreferences("LanguageSettings", MODE_PRIVATE);
        setLanguage();

        img_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLanguage();
            }
        });

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePageActivity.this, sign_in_activity.class);
                startActivity(intent);
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(homePageActivity.this, sign_up_activity.class);
                startActivity(intent1);

            }
        });

    }

    private void setLanguage() {
        boolean isVietnamese = prefs.getBoolean("isVietnamese", false);

        btn_sign_in.setText(isVietnamese ? "ĐĂNG NHÂP":"SIGN IN");
        txt_or.setText(isVietnamese ? "HOẶC":"OR");
        btn_sign_up.setText(isVietnamese ? "ĐĂNG KÝ":"SIGN UP");
        btn_google.setText((isVietnamese ? "TIẾP TỤC VỚI GOOGLE":"CONTINUE WITH GOOGLE"));
        btn_forgot.setText(isVietnamese ? "Bạn quên mật khẩu?":"Forgot your password?");
    }
    private void changeLanguage() {
        boolean isVietnamese = prefs.getBoolean("isVietnamese", false);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isVietnamese", !isVietnamese);
        editor.apply();
        setLanguage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setLanguage();
    }

    @SuppressLint("WrongViewCast")
    private void LoadElement(){
        btn_sign_in = findViewById(R.id.sign_in_btn);
        btn_sign_up = findViewById(R.id.sign_up_btn);
        img_language = findViewById(R.id.language_chance);
        img_homepage = findViewById(R.id.HomepageImg);
        txt_or = findViewById(R.id.or_txt);
        btn_google = findViewById(R.id.google_btn);
        btn_forgot = findViewById(R.id.forgot_pw);
    }
}