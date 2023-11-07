package com.example.doanck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class sign_up_activity extends AppCompatActivity {
    private Button btnBack,btnSignUp;
    private EditText email,password,confirm,user;
    private TextView txt_signup;
    private ImageView img_change;
    boolean isChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        LoadElement();

        img_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeLanguage();
            }
        });
        btnBack = findViewById(R.id.backBtn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_up_activity.this, homePageActivity.class);
                startActivity(intent);
            }
        });


    }
    private void LoadElement(){
        btnBack = findViewById(R.id.backBtn);
        btnSignUp = findViewById(R.id.signupBtn);
        user = findViewById(R.id.usernameEditTxt);
        email = findViewById(R.id.emailEditTxt);
        password = findViewById(R.id.passwordEditTxt);
        confirm = findViewById(R.id.reenterPassEditTxt);
        txt_signup = findViewById(R.id.registerTxt);
        img_change = findViewById(R.id.imgChange);
    }
    private void ChangeLanguage(){
        isChanged =!isChanged;
        if(isChanged){
            img_change.setImageResource(R.drawable.icon_vietnam);
            btnBack.setText("QUAY VỀ");
            btnSignUp.setText("ĐĂNG KÝ");
            user.setText("Tên người dùng");
            email.setText("Email");
            password.setText("Mật khẩu");
            confirm.setText("Nhập lại mật khẩu");
            txt_signup.setText("ĐĂNG KÝ");
        }else{
            img_change.setImageResource(R.drawable.icon_uk);
            btnBack.setText("BACK");
            btnSignUp.setText("SIGN UP");
            user.setText("Username");
            email.setText("Email");
            password.setText("Password");
            confirm.setText("Re-enter the password");
            txt_signup.setText("REGISTER");
        }
    }
}