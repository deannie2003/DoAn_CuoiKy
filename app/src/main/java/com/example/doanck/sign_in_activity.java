package com.example.doanck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanck.API.ApiInterface;
import com.example.doanck.Model.AssetToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class sign_in_activity extends AppCompatActivity {

    Button bttn_sign_in, bttn_sign_up, bttn_back_to_homePage;
    EditText userName,Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        loadElement();


        //nhấn button trở về home page
        bttn_back_to_homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_in_activity.this ,homePageActivity.class);
                startActivity(intent);
            }
        });
        // nhấn button xử lý => vào dashboard/thông báo lỗi
        bttn_sign_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              Signin();
            }
        });

    }


    //Hàm để load các phần tử trong UI
    private void loadElement(){
        bttn_sign_in = findViewById(R.id.button_sign_in);
        bttn_back_to_homePage = findViewById(R.id.button_homepage);
        userName = findViewById(R.id.user_name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
    }
    //Hàm để xử lý đăng nhập
    private void Signin(){
        ApiInterface.apiInterface.authenticate("openremote",userName.getText().toString(),Password.getText().toString(),Email.getText().toString(),"password")
                .enqueue(new Callback<AssetToken>() {
                    //call API bat dong bo
                    @Override
                    public void onResponse(Call<AssetToken> call, Response<AssetToken> response) {
                        if(response.isSuccessful()){
                            AssetToken assetToken = response.body();
                            String token = assetToken.getAccessToken();
                            Toast.makeText(sign_in_activity.this,"Đăng Nhập Thành Công!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(sign_in_activity.this,dash_board_activity.class);
                            startActivity(intent);
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