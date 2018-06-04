package com.example.abhishek.stylesnsmiles.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecycleParlourbeauticianlistCustomerBooking;
import com.example.abhishek.stylesnsmiles.ClientConstant;
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

public class BookingDetailsListparticular extends AppCompatActivity implements ClientConstant{
    String title;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    TextView tv, nodatatext;
    RecyclerView rv;
    AdapterrecycleParlourbeauticianlistCustomerBooking adapter;
    BookingDetails pojoParlourBeauticaian;
    List<BookingDetails> pojoParlourBeauticaians = new ArrayList<>();
    SharedPreferences defaultPreferences;
    SharedPreferences.Editor editPreferences;
    String beautyname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_beatician_list);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            beautyname = bundle.getString("beName");
        }

        if (Connectivity.isNetworkAvailable(BookingDetailsListparticular.this)) {
            tv = findViewById(R.id.txt_book_title);
            rv = findViewById(R.id.recycler_book_beautician);
            nodatatext = findViewById(R.id.visibilitytext);
            defaultPreferences = getSharedPreferences(DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
            editPreferences = defaultPreferences.edit();
            title = defaultPreferences.getString(KEY_ORGANIZATION, KEY_ORGANIZATION);
            beautyname=defaultPreferences.getString(KEY_BEAUTY_NAME, KEY_BEAUTY_NAME);

            tv.setText("BOOKING LIST");
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(title);
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BookingDetailsListparticular.this);
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
        adapter = new AdapterrecycleParlourbeauticianlistCustomerBooking(this, pojoParlourBeauticaians);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(adapter);


    }



    public void readDB()

    {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pojoParlourBeauticaian = dataSnapshot.getValue(BookingDetails.class);
                try {
                    if (pojoParlourBeauticaian.getParlourEmployeename().equalsIgnoreCase(beautyname)) {
                        pojoParlourBeauticaians.add(pojoParlourBeauticaian);
                    }

                }catch (Exception e){}
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.beuty, menu);
        return true;
    }
    private void newGame(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BookingDetailsListparticular.this);
        alertDialogBuilder.setTitle("Log out");
        alertDialogBuilder.setMessage("Are you sure you want to log out ?"
        );
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(BookingDetailsListparticular.this, Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        editPreferences.putString(ClientConstant.KEY_BEAUTY_NAME,null);

                        editPreferences.putString(ClientConstant.KEY_ORGANIZATION,null);

                        editPreferences.apply();

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_signout:

                newGame();

                return true;

            case R.id.chat:

                Intent intent = new Intent(BookingDetailsListparticular.this, GroupChat.class);
                intent.putExtra("cusName",beautyname);
                startActivity(intent);

                return true;
//            case R.id.help:
//                showHelp();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /* @method name : exitByBackKey
     * description : This method Display alert dialouge when we press back button
     * @author : Abhishek

     */
    protected void exitByBackKey() {

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("Are you sure you want to exit  from app ?");
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == resultCode) {
        }
    }

}
