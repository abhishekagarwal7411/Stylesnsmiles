package com.example.abhishek.stylesnsmiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LoginUser extends AppCompatActivity {
TextView txt;
Button btn_login;
EditText editName,etPassword;
String userName,password,isparlour;
RegistrationPojo registrationPojo;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    List<RegistrationPojo> registrationPojos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        Intent intent = getIntent();
        isparlour = intent.getStringExtra("isparlourpage");
        firebaseDatabase= FirebaseDatabase.getInstance();

        databaseReference =  firebaseDatabase.getReference("registered");

        viewInitializer();
        onClickListener();
    }
   public void viewInitializer(){
       txt=findViewById(R.id.new_registration);
       btn_login=findViewById(R.id.btn_login);
       editName=findViewById(R.id.et_username);
       etPassword=findViewById(R.id.et_password);
   }
    public void onClickListener(){
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inent= new Intent(getApplicationContext(),Registeration.class);
                startActivity(inent);
finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userName = editName.getText().toString();
                password = etPassword.getText().toString();

                if (userName.isEmpty()) {
                    Toast.makeText(LoginUser.this, "Please enter Username", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginUser.this, "Please enter Password", Toast.LENGTH_SHORT).show();

                } else {
                    readDB();
                }
            }
        });

    }

    public void validate() {
        for (int i = 0; i < registrationPojos.size(); i++) {
            Log.e("user",userName);
            Log.e("nameee",registrationPojos.get(i).getUsername());
            if(userName.equalsIgnoreCase(registrationPojos.get(i).getUsername())) {

                if (password.equalsIgnoreCase(registrationPojos.get(i).getConfirmpassword())) {
//                    pd.dismiss();
//                    Toast.makeText(LoginUser.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent inent= new Intent(getApplicationContext(),Home.class);
                    startActivity(inent);
                    finish();
//                    if(memberList.get(i).getRo().equalsIgnoreCase("trainer"))


                } else {
//                    pd.dismiss();

//                    Toast.makeText(LoginUser.this, "please check your usename or password", Toast.LENGTH_SHORT).show();
                }
            }else{
//                Toast.makeText(LoginUser.this, "Please enter valid username", Toast.LENGTH_SHORT).show();
            }
        }

//        pd.dismiss();
//        Toast.makeText(Login.this, "User Not found", Toast.LENGTH_SHORT).show();
    }
    public void readDB()

    {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                registrationPojo = dataSnapshot.getValue(RegistrationPojo.class);
                registrationPojos.add(registrationPojo);
                validate();
//                if(registrationPojo.getRo().equalsIgnoreCase("trainer"))
//                {
//                    memberListTrainers.add(registrationPojo);
//                }else
//                {
//                    memberListCustomers.add(registrationPojo);
//
//                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
