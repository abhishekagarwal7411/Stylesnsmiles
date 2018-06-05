package com.example.abhishek.stylesnsmiles.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecycleParlourbeauticianlistCustomer;
import com.example.abhishek.stylesnsmiles.PojoClass.BookingDetails;
import com.example.abhishek.stylesnsmiles.PojoClass.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.abhishek.stylesnsmiles.ClientConstant.DEFAULT_PREFERENCE;
import static com.example.abhishek.stylesnsmiles.ClientConstant.KEY_USERNAME;

public class ParlourDetails extends AppCompatActivity {
    String title, images;
    //        int images;

    TextView tst;
    ImageView img;
    DatabaseReference databaseReference;
    DatabaseReference datbaseBook;
    FirebaseDatabase firebaseDatabase;
    RecyclerView rv;
    SharedPreferences defaultPreferences;
    SharedPreferences.Editor editPreferences;
    BookingDetails bookpojo;
    List<BookingDetails> bookpojos = new ArrayList<>();

    AdapterrecycleParlourbeauticianlistCustomer adapter;
    PojoParlourBeauticaian pojoParlourBeauticaian;
    List<PojoParlourBeauticaian> pojoParlourBeauticaians = new ArrayList<>();
String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parlour_details);
        Intent intent = getIntent();
        title = intent.getStringExtra("parlournameCustomer");
//        tst=(TextView)findViewById(R.id.tst);
        img = (ImageView) findViewById(R.id.img);
        rv = findViewById(R.id.recycler_view_beautician_cust);
        defaultPreferences = getSharedPreferences(DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        editPreferences = defaultPreferences.edit();
        username = defaultPreferences.getString(KEY_USERNAME, KEY_USERNAME);


//        tst.setText(title);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(title);
        datbaseBook = firebaseDatabase.getReference(title+ "Booking");
Log.e("title",title);
        readDB();
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

    public void validate() {
        adapter = new AdapterrecycleParlourbeauticianlistCustomer(this, pojoParlourBeauticaians,bookpojos,title);
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
                pojoParlourBeauticaian = dataSnapshot.getValue(PojoParlourBeauticaian.class);
                pojoParlourBeauticaians.add(pojoParlourBeauticaian);
                Log.e("albumLiost", Integer.toString(pojoParlourBeauticaians.size()));
//                if(pojoParlourBeauticaians.size() == 0){
//                    nodatatext.setVisibility(View.VISIBLE);
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
        datbaseBook.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                bookpojo = dataSnapshot.getValue(BookingDetails.class);
                try {
                    if (bookpojo.getUsername().equalsIgnoreCase(username)) {
                        bookpojos.add(bookpojo);
                        Log.e("count", Integer.toString(bookpojos.size()));
                    }
                }catch (Exception e){}
//                if(pojoParlourBeauticaians.size() == 0){
//                    nodatatext.setVisibility(View.VISIBLE);
//
//                }
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
        validate();
    }
}

