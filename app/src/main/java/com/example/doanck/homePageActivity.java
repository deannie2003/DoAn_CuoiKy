package com.example.doanck;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class homePageActivity extends AppCompatActivity {
    private Button btn_sign_in,btn_sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
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



        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo PopupMenu và gán nó với Button
                PopupMenu popupMenu = new PopupMenu(homePageActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.languages_menu, popupMenu.getMenu());

                // Xử lý sự kiện khi một ngôn ngữ được chọn từ PopupMenu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.english:
                                // Xử lý khi chọn tiếng Anh
                                break;
                            case R.id.vietnamese:
                                // Xử lý khi chọn tiếng Việt
                                break;
                        }
                        return true;
                    }
                });

                // Hiển thị PopupMenu
                popupMenu.show();
            }
        });


    }
}