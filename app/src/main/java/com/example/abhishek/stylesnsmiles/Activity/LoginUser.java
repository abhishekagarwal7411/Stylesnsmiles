package com.example.abhishek.stylesnsmiles.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhishek.stylesnsmiles.ClientConstant;
import com.example.abhishek.stylesnsmiles.PojoClass.Connectivity;
import com.example.abhishek.stylesnsmiles.PojoClass.RegistrationPojo;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.abhishek.stylesnsmiles.ClientConstant.DEFAULT_PREFERENCE;

public class LoginUser extends AppCompatActivity implements ClientConstant{
    TextView txt,loginheading;
    Button btn_login;
    EditText editName, etPassword;
    String userName, password, isparlour;
    RegistrationPojo registrationPojo;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Boolean isfromParlour=false;
    List<RegistrationPojo> registrationPojos = new ArrayList<>();
    SharedPreferences defaultPreferences;
    SharedPreferences.Editor editPreferences;
    String mobileno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        defaultPreferences = getSharedPreferences(DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        editPreferences = defaultPreferences.edit();
        Intent intent = getIntent();
        isparlour = intent.getStringExtra("isparlourpage");
        firebaseDatabase = FirebaseDatabase.getInstance();
        if(isparlour.equals("isfromParlour")){
            isfromParlour=true;
        }
        else{
            isfromParlour=false;
        }
if(isfromParlour){
    databaseReference = firebaseDatabase.getReference("parlourregistered");
}else {
    databaseReference = firebaseDatabase.getReference("registered");
}
        viewInitializer();
        onClickListener();
    }

    public void viewInitializer() {
        loginheading=findViewById(R.id.loginheading);
        txt = findViewById(R.id.new_registration);
        btn_login = findViewById(R.id.btn_login);
        editName = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        if(isfromParlour){
            loginheading.setText("Parlour Login") ;
        }
        else{
            loginheading.setText("Customer Login") ;

        }
    }

    public void onClickListener() {
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inent = new Intent(getApplicationContext(), Registeration.class);
                if(isfromParlour){
                    inent.putExtra("isparlour","isFromParlour")  ;
                }else{
                    inent.putExtra("isparlour","isnotFromParlour")  ;

                }
                startActivity(inent);

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Connectivity.isNetworkAvailable(LoginUser.this)) {
                    userName = editName.getText().toString();
                    password = etPassword.getText().toString();

                    if (userName.isEmpty()) {
                        Toast.makeText(LoginUser.this, "Please enter Username", Toast.LENGTH_SHORT).show();
                    } else if (password.isEmpty()) {
                        Toast.makeText(LoginUser.this, "Please enter Password", Toast.LENGTH_SHORT).show();

                    } else {
                        readDB();
                    }
                }else {
                    Toast.makeText(LoginUser.this, " Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void validate() {
        for (int i = 0; i < registrationPojos.size(); i++) {
            Log.e("user", userName);
            Log.e("nameee", registrationPojos.get(i).getUsername());
            if (userName.equalsIgnoreCase(registrationPojos.get(i).getUsername())) {


                if (password.equalsIgnoreCase(registrationPojos.get(i).getConfirmpassword())) {
                    mobileno=  registrationPojos.get(i).getMobilenumber();
//                    pd.dismiss();
//                    Toast.makeText(LoginUser.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                    Intent inent;
                    if(isfromParlour){
                        inent = new Intent(getApplicationContext(), ParlourPage.class);

                    }
                    else{



                        editPreferences.putString(ClientConstant.KEY_MOBILE_NUM,mobileno);

                        editPreferences.putString(ClientConstant.KEY_USERNAME,userName);
                        Log.e("username",userName);
editPreferences.apply();
                        inent = new Intent(getApplicationContext(), Home.class);

                    }
                    startActivity(inent);
                    finish();
//                    if(memberList.get(i).getRo().equalsIgnoreCase("trainer"))


                } else {
//
                }
            } else {
//                if(i== (registrationPojos.size()-1)){
//                    Toast.makeText(LoginUser.this, "Please enter valid username or password", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(LoginUser.this, "Please enter valid username", Toast.LENGTH_SHORT).show();
            }
        }

//        pd.dismiss();
//        Toast.makeText(LoginUser.this, "User Not found", Toast.LENGTH_SHORT).show();
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
