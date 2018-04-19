package com.example.abhishek.stylesnsmiles.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abhishek.stylesnsmiles.PojoClass.RegistrationPojo;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class Registeration extends AppCompatActivity {
    EditText etusername, etmobile, etemail, etpassword, etconpassword;
    String username, contactNumber, password, confirmpassword, emailId;
    String MobilePattern = "[0-9]{10}";
    Button regiter;
    TextView registerText;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String isparlour;
    Boolean isfromParlour = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Register Now");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        isparlour = intent.getStringExtra("isparlour");
        if (isparlour.equals("isFromParlour")) {
            isfromParlour = true;
        }
        firebaseDatabase = FirebaseDatabase.getInstance();

        etusername = findViewById(R.id.user_name);
        etmobile = findViewById(R.id.mobile_num);
        regiter = findViewById(R.id.btn_register);
        etemail = findViewById(R.id.et_email_Id);
        etpassword = findViewById(R.id.et_password);
        etconpassword = findViewById(R.id.confirm_password);
        registerText=findViewById(R.id.registerText);
        if (isfromParlour) {
            databaseReference = firebaseDatabase.getReference("parlourregistered");
            registerText.setText("Parlour Registration");
        } else {
            databaseReference = firebaseDatabase.getReference("registered");
            registerText.setText("Customer Registration");

        }
        clicklistener();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void clicklistener() {
        regiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                username = etusername.getText().toString().trim();
                contactNumber = etmobile.getText().toString().trim();
                password = etpassword.getText().toString().trim();
                confirmpassword = etconpassword.getText().toString().trim();
                emailId = etemail.getText().toString().trim();
                if (username.isEmpty()) {
                    Toast.makeText(Registeration.this, "Please enter name", Toast.LENGTH_SHORT).show();

                } else if (contactNumber.isEmpty()) {
                    Toast.makeText(Registeration.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();

                } else if ((!contactNumber.matches(MobilePattern))) {
                    Toast.makeText(Registeration.this, "Please enter 10 digit number", Toast.LENGTH_SHORT).show();
                } else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches())) {
                    Toast.makeText(Registeration.this, "Please enter email Id", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(Registeration.this, "Please enter Password", Toast.LENGTH_SHORT).show();

                } else if (confirmpassword.isEmpty()) {
                    Toast.makeText(Registeration.this, "Please enter confirm Password", Toast.LENGTH_SHORT).show();

                } else if (!password.equals(confirmpassword)) {
                    Toast.makeText(Registeration.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                } else {
                    checkDB();


                }
            }
        });
    }

    public void checkDB() {

        if (databaseReference != null) {
            Log.e("database", "database");
            if (databaseReference.getParent() != null) {
                Log.e("database.getParent", "database.getParent");
//                    writeData();
                validateUserName();
            } else {
                Log.e("database.getParent.else", "database.getParent.else");
                writeData();
            }

        } else {
            Log.e("database.else", "database.else");

            writeData();
        }

//
    }

    public void writeData() {
        Log.e("writeData", "writeData");
        RegistrationPojo user = new RegistrationPojo(username, contactNumber, emailId, confirmpassword);
        databaseReference.child(username).setValue(user);
        Toast.makeText(Registeration.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Registeration.this, Login.class));
        finish();
    }

    public void validateUserName() {
        final ProgressDialog pd = new ProgressDialog(Registeration.this);
        pd.setMessage("Loading...");
        pd.show();
        String url = "https://stylesnsmiles-64578.firebaseio.com/registered.json";


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s.equals("null")) {
                    writeData();
//                    Toast.makeText(Registeration.this, "user not found", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        JSONObject obj = new JSONObject(s);
                        Log.e("obj", obj.toString());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        JsonParser jp = new JsonParser();
                        JsonElement je = jp.parse(s);
                        String prettyJsonString = gson.toJson(je);
                        Log.e("prettyJsonString", prettyJsonString);
                        if (!obj.has(username)) {
                            writeData();
                        } else {
                            Toast.makeText(Registeration.this, "user name already taken , please use different username", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                pd.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
                pd.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Registeration.this);
        rQueue.add(request);

    }
}
