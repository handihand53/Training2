package com.example.training2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    private Button batalButton;
    private Button daftarButton;

    private EditText username;
    private EditText password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        daftarButton = findViewById(R.id.daftarButton);
        daftarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                check();
            }
        });

        batalButton = findViewById(R.id.batalButton);
        batalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
            }
        });
    }

    protected void check(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        if(!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "daftar berhasil!", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(SignupActivity.this, HomeActivity.class));
        }
        else if(username.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"username tidak boleh kosong", Toast.LENGTH_SHORT).show();
        else if(password.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"password tidak boleh kosong", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
    }
}
