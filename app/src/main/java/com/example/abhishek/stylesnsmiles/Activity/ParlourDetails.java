package com.example.abhishek.stylesnsmiles.Activity;

import android.content.Intent;
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
import com.example.abhishek.stylesnsmiles.PojoClass.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ParlourDetails extends AppCompatActivity {
    String title, images;
    //        int images;
    TextView tst;
    ImageView img;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    RecyclerView rv;
    AdapterrecycleParlourbeauticianlistCustomer adapter;
    PojoParlourBeauticaian pojoParlourBeauticaian;
    List<PojoParlourBeauticaian> pojoParlourBeauticaians = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parlour_details);
        Intent intent = getIntent();
        title = intent.getStringExtra("parlournameCustomer");
//        tst=(TextView)findViewById(R.id.tst);
        img = (ImageView) findViewById(R.id.img);
        rv = findViewById(R.id.recycler_view_beautician_cust);

//        tst.setText(title);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(title);

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
        adapter = new AdapterrecycleParlourbeauticianlistCustomer(this, pojoParlourBeauticaians);
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

