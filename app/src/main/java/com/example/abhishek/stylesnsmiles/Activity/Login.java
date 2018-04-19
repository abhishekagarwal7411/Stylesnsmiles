package com.example.abhishek.stylesnsmiles;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Login extends AppCompatActivity {
Button button;
Button btnmangaer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.btn_user);
        btnmangaer=findViewById(R.id.btnmanager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,LoginUser.class);
                startActivity(intent);

            }
        });
        btnmangaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Login.this,ParlourPage.class);
                intent.putExtra("isparlourpage","isfromParlour");
                startActivity(intent);

            }
        });

    }
}
