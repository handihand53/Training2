package com.example.training2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    public static String FLAG = "INI_FLAG";
    private Button btnLogin;
    private Button fragment;
    private TextView signUp;
    private EditText email;
    private EditText password;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                email = findViewById(R.id.email);
                validateEmail(email.getText().toString());
            }
        });

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });


    }

    protected void validateEmail(String email){
        if (email.matches(emailPattern))
        {
            password = findViewById(R.id.password);
            if(!password.getText().toString().equals("")){
                finish();
                Intent home = new Intent(MainActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FLAG, email);
                home.putExtras(bundle);
                startActivity(home);
            }else{
                Toast.makeText(getApplicationContext(),"Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
        }
    }

}
