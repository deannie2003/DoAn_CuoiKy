package com.example.doanck;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

=======
import android.widget.PopupMenu;
>>>>>>> 99112b6d4aba7f5dabef23d6f8defa121381fab7


public class homePageActivity extends AppCompatActivity {

    private Button btn,btn1;
    private TextView txt_title, txt_or;
    private ImageView img;

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

<<<<<<< HEAD
=======

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

>>>>>>> 99112b6d4aba7f5dabef23d6f8defa121381fab7
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
            img.setImageResource(R.drawable.icon_vietnam);
            txt_title.setText("Dự báo khí tượng");
            btn_sign_in.setText("ĐĂNG NHẬP");
            txt_or.setText("HOẶC");
            btn_sign_up.setText("ĐĂNG KÝ");
            btn_google.setText("ĐĂNG NHẬP VỚI GOOGLE");
            btn_reset.setText("Bạn quên mật khẩu?");
        }else{
            img.setImageResource(R.drawable.icon_uk);
            txt_title.setText("Air Quality Monitoring");
            btn_sign_in.setText("SIGN IN");
            txt_or.setText("OR");
            btn_sign_up.setText("SIGN UP");
            btn_google.setText("CONTINUE WITH GOOGLE");
            btn_reset.setText("Forgot your password?");
        }
    }

}