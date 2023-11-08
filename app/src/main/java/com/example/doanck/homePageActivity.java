package com.example.doanck;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;


import android.widget.PopupMenu;

import com.google.android.material.button.MaterialButton;


public class homePageActivity extends AppCompatActivity {

    private Button btn,btn1;
    private TextView txt_title, txt_or;
    private ImageButton img;

    boolean isChanged = false;

    private Button btn_sign_in,btn_sign_up,btn_google,btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        LoadElement();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage();
            }
        });
        btn_sign_in = findViewById(R.id.button);
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePageActivity.this, sign_in_activity.class);
                startActivity(intent);
            }
        });
        btn_sign_up = findViewById(R.id.button2);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(homePageActivity.this, sign_up_activity.class);
                startActivity(intent1);
            }
        });
    }
    private void LoadElement(){
        btn_sign_in = findViewById(R.id.button);
        btn_sign_up = findViewById(R.id.button2);
        img = findViewById(R.id.imageButton);
        txt_title = findViewById(R.id.HomepageTxt);
        txt_or = findViewById(R.id.textView2);
        btn_google = findViewById(R.id.button3);
        btn_reset = findViewById(R.id.button4);
    }
    private void ChangeLanguage(){
        isChanged =!isChanged;
        if(isChanged){
            img.setBackgroundResource(R.drawable.icon_vietnam);
            img.setImageDrawable(null);
            txt_title.setText("Dự báo khí tượng");
            btn_sign_in.setText("ĐĂNG NHẬP");
            txt_or.setText("HOẶC");
            btn_sign_up.setText("ĐĂNG KÝ");
            btn_google.setText("ĐĂNG NHẬP VỚI GOOGLE");
            btn_reset.setText("Bạn quên mật khẩu?");
        }else{
            img.setBackgroundResource(R.drawable.icon_uk);
            img.setImageDrawable(null);
            txt_title.setText("Air Quality Monitoring");
            btn_sign_in.setText("SIGN IN");
            txt_or.setText("OR");
            btn_sign_up.setText("SIGN UP");
            btn_google.setText("CONTINUE WITH GOOGLE");
            btn_reset.setText("Forgot your password?");
        }
    }

}