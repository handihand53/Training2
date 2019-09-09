package com.example.training2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class profileActivity extends AppCompatActivity {
    private Button exitButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        prepareFragment();


        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    private void prepareFragment(){
        this.getSupportFragmentManager().
                beginTransaction()
                .add(R.id.list, new FragmentMenu())
                .commit();
    }
}
