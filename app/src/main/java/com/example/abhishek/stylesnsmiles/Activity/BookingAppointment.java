package com.example.abhishek.stylesnsmiles.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecyclePackage;
import com.example.abhishek.stylesnsmiles.ClientConstant;
import com.example.abhishek.stylesnsmiles.PojoClass.BookingDetails;
import com.example.abhishek.stylesnsmiles.PojoClass.Connectivity;
import com.example.abhishek.stylesnsmiles.PojoClass.PackagesDetail;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookingAppointment extends AppCompatActivity implements
        View.OnClickListener, ClientConstant {
    int i;
    int x=1;
    int buttons = 5;
    ;
    RadioGroup rgp;
    TextView beautician_name, noItemPck;
                    int count = 0;

    String[] str = {"Facials", "Waxing", "Nail care", "Eye care", "Foot care", "Make up"};
//    String[] items = {" Easy "," Medium "," Hard "," Very Hard "};

    String name, parlourname;
    String titlelist;
    LinearLayout service;
    String customermobile;
    List<BookingDetails> pojoParlourBeauticaians = new ArrayList<>();
    PackagesDetail pack;
    CardView facialcard, waxcard, haircard;
    Button btnDatePicker, btnTimePicker, btnbook;
    EditText txtDate, txtTime;
    SharedPreferences defaultPreferences;
    SharedPreferences.Editor editPreferences;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferencepackage;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    //    List<String> array = new ArrayList<>();
    CheckBox cb;
    String date, time;
    String username, parlourmobile;
    BookingDetails pojoParlourBeauticaian;
    Integer positionbeautician;
    TextView item_facial;
    private AdapterrecyclePackage adapter;
    private List<PackagesDetail> albumList = new ArrayList<>();
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_booking_appointment);
        Bundle b = getIntent().getExtras();
        name = b.getString("title");
        parlourmobile = b.getString("mobile");
        parlourname = b.getString("name");
        positionbeautician = b.getInt("postion");
        facialcard = findViewById(R.id.cardfacial);
        waxcard = findViewById(R.id.cardwax);
        haircard = findViewById(R.id.cardhair);
        Log.e("position", positionbeautician.toString());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(parlourname);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(parlourname + "Booking");

        databaseReferencepackage = firebaseDatabase.getReference(parlourname + "package");
        AppCompatRadioButton[] rb = new AppCompatRadioButton[buttons];
        defaultPreferences = getSharedPreferences(DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        editPreferences = defaultPreferences.edit();
        username = defaultPreferences.getString(KEY_USERNAME, KEY_USERNAME);
        customermobile = defaultPreferences.getString(KEY_MOBILE_NUM, KEY_MOBILE_NUM);
        Log.e("usernameBooking", defaultPreferences.getString(KEY_USERNAME, KEY_USERNAME));
        beautician_name = findViewById(R.id.beautician_name);
        btnbook = findViewById(R.id.btnbook);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);
        txtDate = (EditText) findViewById(R.id.in_date);
        txtTime = (EditText) findViewById(R.id.in_time);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_packages);
        noItemPck = (TextView) findViewById(R.id.nopck);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        facialcard.setOnClickListener(this);
        waxcard.setOnClickListener(this);
        haircard.setOnClickListener(this);
        btnbook.setOnClickListener(this);
        beautician_name.setText(name + " from " + parlourname);
        service = findViewById(R.id.service);

        albumList = new ArrayList<>();
        adapter = new AdapterrecyclePackage(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        preparePackages();


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

//            // Get Current Date
//            final Calendar c = Calendar.getInstance();
//            mYear = c.get(Calendar.YEAR);
//            mMonth = c.get(Calendar.MONTH);
//            mDay = c.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                    new DatePickerDialog.OnDateSetListener() {
//
//                        @Override
//                        public void onDateSet(DatePicker view, int year,
//                                              int monthOfYear, int dayOfMonth) {
//
//                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//
//                        }
//                    }, mYear, mMonth, mDay);
//            datePickerDialog.show();
            final Calendar currentDate = Calendar.getInstance();
            final Calendar dates = Calendar.getInstance();

            DatePickerDialog.OnDateSetListener dateSetListener = new
                    DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int
                                monthOfYear, int dayOfMonth) {
                            dates.set(year, monthOfYear, dayOfMonth);
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            //use this date as per your requirement
                        }
                    };
            DatePickerDialog datePickerDialog = new
                    DatePickerDialog(BookingAppointment.this, dateSetListener,
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH));
            // Limiting access to past dates in the step below:
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
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
        if (v == facialcard) {
            alertfacial();
        }
        if (v == waxcard) {
            alertwax();
        }
        if (v == haircard) {
            alertHair();
        }
    }

    public void alertfacial() {


        final CharSequence[] items = {" Acne Reduction Facial ", " Fruit Facial ", " Galvanic Facials ", " Collagen Facial "};
// arraylist to keep the selected items

        final ArrayList seletedItems = new ArrayList();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Types of Facials")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            String abc;
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(indexSelected);

//                          for(i=0;i<seletedItems.size();i++){
//                            abc= ie
//                          }
//                           item_facial.setText();
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e("selectedindex", seletedItems.toString());
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                    }
                }).create();
        dialog.show();

    }

    public void alertwax() {


        final CharSequence[] items = {" Soft Wax ", " Fruit Wax ", " Choclate Wax ", " Sugar Wax "};
// arraylist to keep the selected items

        final ArrayList seletedItems = new ArrayList();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Types of Waxing")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            String abc;
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(indexSelected);

//                          for(i=0;i<seletedItems.size();i++){
//                            abc= ie
//                          }
//                           item_facial.setText();
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e("selectedindex", seletedItems.toString());
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                    }
                }).create();
        dialog.show();

    }

    public void alertHair() {


        final CharSequence[] items = {" Bob Cut ", "Ponytail ", " Ducktail ", " Layered hair "};
// arraylist to keep the selected items

        final ArrayList seletedItems = new ArrayList();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Types of Hair cut")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            String abc;
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(indexSelected);

//                          for(i=0;i<seletedItems.size();i++){
//                            abc= ie
//                          }
//                           item_facial.setText();
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e("selectedindex", seletedItems.toString());
                        //  Your code when user clicked on OK
                        //  You can write the code  to save the selected item here
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel
                    }
                }).create();
        dialog.show();

    }

    public void preparePackages() {


        databaseReferencepackage.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pack = dataSnapshot.getValue(PackagesDetail.class);
                albumList.add(pack);

                adapter.notifyDataSetChanged();
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

    public void sendBookingData() {
        if (Connectivity.isNetworkAvailable(BookingAppointment.this)) {
            date = txtDate.getText().toString();
            time = txtTime.getText().toString();
            if (date.isEmpty()) {
                Toast.makeText(BookingAppointment.this, "Please enter Booking Date", Toast.LENGTH_SHORT).show();
            } else if (time.isEmpty()) {
                Toast.makeText(BookingAppointment.this, "Please enter Booking Time", Toast.LENGTH_SHORT).show();
            } else {

                checkDB();
            }

//            Bundle bundle = new Bundle();

        } else {
            Toast.makeText(BookingAppointment.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }

//       bundle.putString("name", date);
//       bundle.putString("mobile", mob);
    }

    public void checkDB() {

        if (databaseReference != null) {
//            Log.e("database", databaseReference.getParent().getKey());

            if (databaseReference.getParent() != null) {
                Log.e("database.getParent", "database.getParent");
//                    writeData();

                readDB();
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
//databaseReference.addv
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            Log.i(dataSnapshot.getChildrenCount()+"Count");
//
//        }
//
//        @Override
//        public void onCancelled(DatabaseError databaseError) {
//
//        }
//    });
    public void readDB()

    {

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pojoParlourBeauticaian = dataSnapshot.getValue(BookingDetails.class);
                pojoParlourBeauticaians.add(pojoParlourBeauticaian);
//                  writeData();
                  databaseReference.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {
                          Log.e("count", dataSnapshot.getChildrenCount() + "Count");
                          for (int i = 0; i <= dataSnapshot.getChildrenCount(); i++) {
                              Log.e("name", name);
                              if (pojoParlourBeauticaians.size() == dataSnapshot.getChildrenCount()) {
                                  try {
                                      if (name.equalsIgnoreCase(pojoParlourBeauticaians.get(i).getParlourEmployeename())) {
                                          if (date.equalsIgnoreCase(pojoParlourBeauticaians.get(i).getDate()) && time.equalsIgnoreCase(pojoParlourBeauticaians.get(i).getTime())) {
                                              count = 1;
                                              break;
                                          }
                                      } else {
                                          count = 2;
                                      }
                                  } catch (Exception e) {
                                  }

                              }
                          }

                              if (count == 1 && x==1 ) {
                                  Toast.makeText(BookingAppointment.this, "This time slot already taken please select different one", Toast.LENGTH_SHORT).show();
//System.exit(0);
                                  x=2;
                                  startActivity(new Intent(BookingAppointment.this, Home.class));
                                  finish();

                              } else if(x!=2 ){
                                  writeData();
                                  x=3;


                          }
                      }




                      @Override
                      public void onCancelled(DatabaseError databaseError) {

                      }
                  });






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
//this.val();
    }
//    public void val(){
//if(count == 2){
//    writeData();
//}else{
//    Toast.makeText(BookingAppointment.this, "This time slot already taken please select different one", Toast.LENGTH_SHORT).show();
//}
//}

    public void writeData() {
        Log.e("writeData", "writeData");

        BookingDetails user = new BookingDetails(username, customermobile, parlourname, name, parlourmobile, date, time, "1");
        Log.e("pojoParlourBeauticaians",Integer.toString(pojoParlourBeauticaians.size()));
        String key = username.concat(name);
        databaseReference.child(key).setValue(user);
//        Toast.makeText(BookingAppointment.this, "Booking Success", Toast.LENGTH_SHORT).show();

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



