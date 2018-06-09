package com.example.abhishek.stylesnsmiles.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecycleParlourbeauticianlistCustomerBooking;
import com.example.abhishek.stylesnsmiles.PojoClass.BookingDetails;
import com.example.abhishek.stylesnsmiles.PojoClass.Connectivity;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookingDetailsList extends AppCompatActivity {
    String title;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    TextView tv, nodatatext;
    RecyclerView rv;
    AdapterrecycleParlourbeauticianlistCustomerBooking adapter;
    BookingDetails pojoParlourBeauticaian;
    List<BookingDetails> pojoParlourBeauticaians = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_beatician_list);
        if (Connectivity.isNetworkAvailable(BookingDetailsList.this)) {
            tv = findViewById(R.id.txt_book_title);
            rv = findViewById(R.id.recycler_book_beautician);
            nodatatext = findViewById(R.id.visibilitytext);
            Intent intent = getIntent();
            title = intent.getStringExtra("parlourtitle");
            tv.setText("BOOKING LIST");
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference(title + "Booking");

            readDB();

//        new Handler().postDelayed(new Runnable() {
//
//            /*
//             * Showing splash screen with a timer. This will be useful when you
//             * want to show case your app logo / company
//             */
//
//            @Override
//            public void run() {
//                // This method will be executed once the timer is over
//                // Start your app main activity
//
//
//              validate();
//
//                // close this activity
//            }
//        }, 10000);


//        Toast.makeText(ViewBeaticianList.this,title, Toast.LENGTH_SHORT).show();
        }else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BookingDetailsList.this);
            alertDialogBuilder.setTitle("Internet connection");
            alertDialogBuilder.setMessage("Please check your internet connection"
            );
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {




                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    }

    public void validate() {
        adapter = new AdapterrecycleParlourbeauticianlistCustomerBooking(this, pojoParlourBeauticaians,title,"parlour");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void readDB()

    {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pojoParlourBeauticaian = dataSnapshot.getValue(BookingDetails.class);
                pojoParlourBeauticaians.add(pojoParlourBeauticaian);

                validate();

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
