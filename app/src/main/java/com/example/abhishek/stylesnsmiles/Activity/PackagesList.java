package com.example.abhishek.stylesnsmiles.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecyclePackageList;
import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecycleParlourbeauticianlist;
import com.example.abhishek.stylesnsmiles.PojoClass.PackagesDetail;
import com.example.abhishek.stylesnsmiles.PojoClass.PojoParlourBeauticaian;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PackagesList extends AppCompatActivity {
Button create;
String title;
RecyclerView rcypackage;
    AdapterrecyclePackageList adapter;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    PackagesDetail packagesDetail;
    FloatingActionButton fab;
    List<PackagesDetail> packagesDetails= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_list);
        rcypackage=findViewById(R.id.recycler_view_package);
        Intent intentdata = getIntent();

        title = intentdata.getStringExtra("parlourtitle");
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PackagesList.this, PackageDetails.class);
                intent.putExtra("parlourtitle", title);
                startActivity(intent);
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Package list");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(title+"package");

//        readDB();


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
                packagesDetail = dataSnapshot.getValue(PackagesDetail.class);
                packagesDetails.add(packagesDetail);
//                Log.e("albumLiost", Integer.toString(packagesDetails.size()));

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
    public void validate() {
        adapter = new AdapterrecyclePackageList(this, packagesDetails,title);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcypackage.setLayoutManager(mLayoutManager);
        rcypackage.setItemAnimator(new DefaultItemAnimator());
        rcypackage.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        readDB();

        super.onResume();
    }
}
