package com.example.abhishek.stylesnsmiles.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.abhishek.stylesnsmiles.R;

public class Login extends AppCompatActivity {
    Button button;
    Button btnmangaer,btnBeautician;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.btn_user);
        btnmangaer = findViewById(R.id.btnmanager);
        btnBeautician=findViewById(R.id.btnBeautician);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, LoginUser.class);
                intent.putExtra("isparlourpage", "isnotfromParlour");
                startActivity(intent);


            }
        });
        btnmangaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, LoginUser.class);
                intent.putExtra("isparlourpage", "isfromParlour");
                startActivity(intent);


            }
        });
        btnBeautician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, LoginBeautician.class);
                startActivity(intent);
            }
        });

    }
}
