package com.example.doanck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class sign_in_activity extends AppCompatActivity {
    private Button btnBack2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnBack2 = findViewById(R.id.button2);
        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_in_activity.this, homePageActivity.class);
                startActivity(intent);
            }
        });
    }
}