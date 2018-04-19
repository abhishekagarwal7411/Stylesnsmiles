package com.example.abhishek.stylesnsmiles.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abhishek.stylesnsmiles.ClientConstant;
import com.example.abhishek.stylesnsmiles.PojoClass.BookingDetails;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.abhishek.stylesnsmiles.ClientConstant.DEFAULT_PREFERENCE;
import static com.example.abhishek.stylesnsmiles.ClientConstant.KEY_USERNAME;

public class BookingAppointment extends AppCompatActivity implements
        View.OnClickListener,ClientConstant {
    int i;
    int buttons = 5;
    ;
    RadioGroup rgp;
    TextView beautician_name;
    String[] str = {"Facials", "Waxing", "Nail care", "Eye care", "Foot care", "Make up"};
    String name, parlourname;
    LinearLayout service;
    String customermobile;
    Button btnDatePicker, btnTimePicker, btnbook;
    EditText txtDate, txtTime;
    SharedPreferences defaultPreferences;
    SharedPreferences.Editor editPreferences;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    //    List<String> array = new ArrayList<>();
    CheckBox cb;
    String date, time;
    String username, parlourmobile;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking_appointment);
        Bundle b = getIntent().getExtras();
        name = b.getString("title");
        parlourmobile = b.getString("mobile");
        parlourname = b.getString("name");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(parlourname);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(parlourname + "Booking");
        AppCompatRadioButton[] rb = new AppCompatRadioButton[buttons];
        defaultPreferences = getSharedPreferences(DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        editPreferences = defaultPreferences.edit();
        username = defaultPreferences.getString(KEY_USERNAME, KEY_USERNAME);
        customermobile=defaultPreferences.getString(KEY_MOBILE_NUM, KEY_MOBILE_NUM);
        Log.e("usernameBooking",defaultPreferences.getString(KEY_USERNAME, KEY_USERNAME));
        beautician_name = findViewById(R.id.beautician_name);
        btnbook = findViewById(R.id.btnbook);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        txtDate = (EditText) findViewById(R.id.in_date);
        txtTime = (EditText) findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnbook.setOnClickListener(this);
        beautician_name.setText(name + " from " + parlourname);
        service = findViewById(R.id.service);

        for (int i = 0; i < str.length; i++) {
            cb = new CheckBox(this);
            cb.setText(str[i]);
            cb.setId(i + 6);
            service.addView(cb);
//            if(cb.isChecked()){
//                    array.add(cb.getText().toString());
//        }
//            cb.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.e("array",cb.getText().toString());
//                }
//            });
//            if (cb.isChecked()) {
//                array.add(cb.getText().toString());
//                Log.e("array",strcheck[i]);
//            }
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnbook) {
            sendBookingData();
        }
    }

    public void sendBookingData() {

            date = txtDate.getText().toString();
            time = txtTime.getText().toString();
            if(date.isEmpty()){
                Toast.makeText(BookingAppointment.this, "Please enter Booking Date", Toast.LENGTH_SHORT).show();
            }else if(time.isEmpty()) {
                Toast.makeText(BookingAppointment.this, "Please enter Booking Time", Toast.LENGTH_SHORT).show();
            }else{

            checkDB();

//            Bundle bundle = new Bundle();

        }

//       bundle.putString("name", date);
//       bundle.putString("mobile", mob);
    }

    public void checkDB() {

        if (databaseReference != null) {
            Log.e("database", "database");
            if (databaseReference.getParent() != null) {
                Log.e("database.getParent", "database.getParent");
//                    writeData();
                writeData();
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
        BookingDetails user = new BookingDetails(username,customermobile, parlourname, name, parlourmobile, date, time);
        databaseReference.child(name).setValue(user);
        Toast.makeText(BookingAppointment.this, "Booking Success", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(BookingAppointment.this, Home.class));
        finish();
    }

}
//        rgp =findViewById(R.id.radio_group);
//        rgp.setOrientation(LinearLayout.VERTICAL);

//        for (i = 1; i <= buttons; i++) {
//            RadioButton rbn = new RadioButton(this);
//            rbn.setId(i + 1000);
//            rbn.setText(str[i]);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
//            rbn.setLayoutParams(params);
//            rbn.setChecked(true);
//            rbn.setPadding(10, 10, 10, 10);
//            rgp.addView(rbn);
//        }



