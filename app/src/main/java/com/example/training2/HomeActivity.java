package com.example.training2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private Button aboutButton;
    private Button exitButton;
    private Button fragmentBtn;
    private Button viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
            }
        });

        fragmentBtn = findViewById(R.id.fragment);
        fragmentBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeActivity.this, profileActivity.class));
            }
        });


        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        viewPager = findViewById(R.id.viewPager);
        viewPager.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(HomeActivity.this, ViewPage.class));
            }
        });
    }
}
