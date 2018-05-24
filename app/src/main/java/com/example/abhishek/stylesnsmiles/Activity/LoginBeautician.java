package com.example.abhishek.stylesnsmiles.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abhishek.stylesnsmiles.ClientConstant;
import com.example.abhishek.stylesnsmiles.PojoClass.Connectivity;
import com.example.abhishek.stylesnsmiles.PojoClass.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.PojoClass.RegistrationPojo;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.example.abhishek.stylesnsmiles.ClientConstant.DEFAULT_PREFERENCE;

public class LoginBeautician extends AppCompatActivity implements ClientConstant{
    Spinner spinner;
    Button button_next_login_screen;
    EditText et_name_beauty,et_mobile_beauty;
    String beautyusername,contactNumberBeauty;
    DatabaseReference databaseReference;
    PojoParlourBeauticaian pojoParlourBeauticaian;
    List<PojoParlourBeauticaian> pojoParlourBeauticaians = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    SharedPreferences defaultPreferences;
    SharedPreferences.Editor editPreferences;
    String[] str = {"Arabella Hair and Beauty", "Sparkliez Beauty and Nails", "Rebecca Salon", "Bettys Beauty Salon", "Armonika", "Toni and Guy", "Aquayo", "Skin Deep", "Luminous Nails", "Draida"};
String interesting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_beautician);
        defaultPreferences = getSharedPreferences(DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        editPreferences = defaultPreferences.edit();
        spinner = findViewById(R.id.tv_org_name);
        et_name_beauty=findViewById(R.id.et_name_beauty);
        et_mobile_beauty=findViewById(R.id.et_mobile_beauty);
        button_next_login_screen = findViewById(R.id.button_next_login_screen);
        firebaseDatabase = FirebaseDatabase.getInstance();

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.name, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {

                interesting = spinner.getItemAtPosition(i).toString();
                Log.e("looo",interesting);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        button_next_login_screen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Connectivity.isNetworkAvailable(LoginBeautician.this)) {
                    beautyusername = et_name_beauty.getText().toString().trim();
                    contactNumberBeauty = et_mobile_beauty.getText().toString().trim();
                    if (interesting.equals("Select organisation")) {
                        Toast.makeText(LoginBeautician.this, "Please select organization", Toast.LENGTH_SHORT).show();
                    } else if (beautyusername.isEmpty()) {
                        Toast.makeText(LoginBeautician.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    } else if (contactNumberBeauty.isEmpty()) {
                        Toast.makeText(LoginBeautician.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference = firebaseDatabase.getReference(interesting);
                        readDB();
                    }
                }else{
                    Toast.makeText(LoginBeautician.this,"Please check your internet connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void readDB()

    {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pojoParlourBeauticaian = dataSnapshot.getValue(PojoParlourBeauticaian.class);
                pojoParlourBeauticaians.add(pojoParlourBeauticaian);
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
    public void validate() {
        for (int i = 0; i < pojoParlourBeauticaians.size(); i++) {
//            Log.e("user", userName);
//            Log.e("nameee", registrationPojos.get(i).getUsername());
            if (interesting.equalsIgnoreCase(pojoParlourBeauticaians.get(i).getTitle())) {


                if (beautyusername.equalsIgnoreCase(pojoParlourBeauticaians.get(i).getUsername()) && contactNumberBeauty.equalsIgnoreCase(pojoParlourBeauticaians.get(i).getMobilenumber())) {

//                    pd.dismiss();
//                    Toast.makeText(LoginUser.this, "Login Successfull", Toast.LENGTH_SHORT).show();


                        editPreferences.putString(ClientConstant.KEY_BEAUTY_NAME,beautyusername);

                        editPreferences.putString(ClientConstant.KEY_ORGANIZATION,interesting);

                        editPreferences.apply();
                                Intent inent = new Intent(getApplicationContext(), BookingDetailsListparticular.class);


                    startActivity(inent);
                    finish();
//                    if(memberList.get(i).getRo().equalsIgnoreCase("trainer"))


                } else {
//Toast.makeText(LoginBeautician.this, "Please enter valid username or password", Toast.LENGTH_SHORT).show();

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
}