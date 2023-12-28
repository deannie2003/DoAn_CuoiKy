package com.example.doanck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanck.API.ApiInterface;
import com.example.doanck.Model.AssetToken;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class sign_in_activity extends AppCompatActivity {
    SharedPreferences prefs;

    Button btn_sign_in, btn_back_to_homePage;
    EditText userName,Email,Password;
    TextView txt_sign_in;


    boolean isChanged = false;
    private void setLanguage() {
        boolean isVietnamese = prefs.getBoolean("isVietnamese", false);
        txt_sign_in.setText(isVietnamese ? "ĐĂNG NHẬP" :"SIGN IN");
        btn_sign_in.setText(isVietnamese ? "ĐĂNG NHẬP":"SIGN IN");
        btn_back_to_homePage.setText(isVietnamese ? "QUAY LẠI":"BACK");

    }

    @Override
    protected void onResume() {
        super.onResume();
        setLanguage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        loadElement();
        prefs = getSharedPreferences("LanguageSettings", MODE_PRIVATE);
        setLanguage();


        //nhấn button trở về home page
        btn_back_to_homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_in_activity.this ,homePageActivity.class);
                startActivity(intent);
            }
        });
        // nhấn button xử lý => vào dashboard/thông báo lỗi
        btn_sign_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Signin();
            }
        });
    }


    //Hàm để load các phần tử trong UI
    private void loadElement(){
        btn_sign_in = findViewById(R.id.button_sign_in);
        btn_back_to_homePage = findViewById(R.id.button_homepage);
        userName = findViewById(R.id.user_name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        txt_sign_in = findViewById(R.id.titleSignin);
    }

    //Hàm để xử lý đăng nhập
    private void Signin(){
        ApiInterface.apiInterface.authenticate("openremote",userName.getText().toString(),Password.getText().toString(),Email.getText().toString(),"password")
                .enqueue(new Callback<AssetToken>() {
                    //call API bat dong bo
                    @Override
                    public void onResponse(Call<AssetToken> call, Response<AssetToken> response) {
                        AssetToken assetToken = response.body();
                        String token = assetToken.getAccessToken();
                        if(token!= null){
                            Log.d("token", "token:" + token);
                            String username = userName.getText().toString();
                            String password = Password.getText().toString();
                            String email = Email.getText().toString();
                            Toast.makeText(sign_in_activity.this,"Đăng Nhập Thành Công!",Toast.LENGTH_SHORT).show();

                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("token", token);
                            myEdit.putString("username", username);
                            myEdit.putString("password", password);
                            myEdit.apply();


                            Intent intent = new Intent(sign_in_activity.this,Main_dash_board_activity.class);

                            startActivity(intent);

                            finishAffinity();
                        }
                        else{
                            Toast.makeText(sign_in_activity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AssetToken> call, Throwable t) {
                        Toast.makeText(sign_in_activity.this, "Call API lỗi !!", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}