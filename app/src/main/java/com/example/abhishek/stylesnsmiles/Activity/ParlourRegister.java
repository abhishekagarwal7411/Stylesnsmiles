package com.example.abhishek.stylesnsmiles.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abhishek.stylesnsmiles.PojoClass.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

public class ParlourRegister extends AppCompatActivity {
    String title;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    EditText etname, etemail, etmob;
    Button addbeauty, viewbeauty,btn_Details;
    String MobilePattern = "[0-9]{10}";
    String username, contactNumber, emailId;
    String status = "Book";
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parlour_register);
        Intent intent = getIntent();
        title = intent.getStringExtra("parlourname");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(title);
        etname = findViewById(R.id.user_name_parlour);
        etemail = findViewById(R.id.etemail_parlour);
        etmob = findViewById(R.id.mobile_num_parlour);
        addbeauty = findViewById(R.id.btn_add_beautician);
        spinner = findViewById(R.id.tv_org_beauty);

        viewbeauty = findViewById(R.id.btn_viewall);
        btn_Details=findViewById(R.id.btn_Details);
        clickListener();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.special, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
//
//                interesting = spinner.getItemAtPosition(i).toString();
//                Log.e("looo",interesting);
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickListener() {
        btn_Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParlourRegister.this, BookingDetailsList.class);
                intent.putExtra("parlourtitle", title);
                startActivity(intent);
            }
        });
        viewbeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParlourRegister.this, ViewBeaticianList.class);
                intent.putExtra("parlourtitle", title);
                startActivity(intent);
            }
        });
        addbeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etname.getText().toString().trim();
                contactNumber = etmob.getText().toString().trim();
                emailId = etemail.getText().toString().trim();
                if (username.isEmpty()) {
                    Toast.makeText(ParlourRegister.this, "Please enter name", Toast.LENGTH_SHORT).show();

                } else if (contactNumber.isEmpty()) {
                    Toast.makeText(ParlourRegister.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();

                } else if ((!contactNumber.matches(MobilePattern))) {
                    Toast.makeText(ParlourRegister.this, "Please enter 10 digit number", Toast.LENGTH_SHORT).show();
                } else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches())) {
                    Toast.makeText(ParlourRegister.this, "Please enter email Id", Toast.LENGTH_SHORT).show();

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
        PojoParlourBeauticaian user = new PojoParlourBeauticaian(username, contactNumber, emailId, title, status);
        databaseReference.child(username).setValue(user);
        Toast.makeText(ParlourRegister.this, "Beautician Added Successfully", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(ParlourRegister.this, ParlourPage.class));
//        this.finish();
        onBackPressed();
    }

    public void validateUserName() {
        final ProgressDialog pd = new ProgressDialog(ParlourRegister.this);
        pd.setMessage("Loading...");
        pd.show();
        String url = "https://stylesnsmiles-64578.firebaseio.com/parlour.json";


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
                            Toast.makeText(ParlourRegister.this, "user name already taken , please use different username", Toast.LENGTH_LONG).show();
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

        RequestQueue rQueue = Volley.newRequestQueue(ParlourRegister.this);
        rQueue.add(request);

    }
}


