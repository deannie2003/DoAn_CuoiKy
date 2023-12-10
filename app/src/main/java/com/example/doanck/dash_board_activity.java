package com.example.doanck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class dash_board_activity extends AppCompatActivity {
    TextView hi_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        hi_User = findViewById(R.id.hi_user);


        // Lấy thông tin người dùng từ Intent và hiển thị lên màn hình
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String username = extras.getString("username");
            hi_User.setText("Welcome, " + username + " !");
        }

        //TabLayout tabLayout = findViewById(R.id.tab_layout);
        //
        //        // Thêm các tab vào TabLayout
        //        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        //        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        //
        //        TabLayout.Tab defaultTab = tabLayout.getTabAt(0);
        //        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        //            @Override
        //            public void onTabSelected(TabLayout.Tab tab) {
        //                int position = tab.getPosition();
        //
        //                // Xác định tab nào được chọn và thực hiện hành động tương ứng
        //                switch (position) {
        //
        //                    case 3:
        //                        // Hành động khi tab 4 được chọn
        //                        // Ví dụ: Chuyển đến màn hình Screen4Activity
        //                        startActivity(new Intent(dash_board_activity.this, profile_activity.class));
        //                        break;
        //                    default:
        //                        break;
        //                }
        //            }
        //
        //            @Override
        //            public void onTabUnselected(TabLayout.Tab tab) {
        //                // Code xử lý khi tab không còn được chọn
        //            }
        //
        //            @Override
        //            public void onTabReselected(TabLayout.Tab tab) {
        //                // Code xử lý khi tab đã được chọn lại
        //            }
        //        });
    }

}