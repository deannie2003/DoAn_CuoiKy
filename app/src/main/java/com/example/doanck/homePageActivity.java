package com.example.doanck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;


public class homePageActivity extends AppCompatActivity {
    private Button btn,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePageActivity.this, sign_in_activity.class);
                startActivity(intent);
            }
        });
        btn1 = findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
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