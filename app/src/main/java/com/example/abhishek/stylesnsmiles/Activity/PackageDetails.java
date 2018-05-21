package com.example.abhishek.stylesnsmiles.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abhishek.stylesnsmiles.PojoClass.BookingDetails;
import com.example.abhishek.stylesnsmiles.PojoClass.Packages;
import com.example.abhishek.stylesnsmiles.PojoClass.PackagesDetail;
import com.example.abhishek.stylesnsmiles.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PackageDetails extends AppCompatActivity {
EditText packagetitle,itemone,itemtwo,itemthree,itemfour,itemprice;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String title;
    String pcktitle,pckone,pcktwo,pckthree,pckfour,pckprice;
Button btnadd;
    String ptit,pone,ptwo, pthree,pfour,pprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Package");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intentdata = getIntent();
        title = intentdata.getStringExtra("parlourtitle");
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(title+"package");
        packagetitle=findViewById(R.id.package_title);
        itemone=findViewById(R.id.package_one);
        itemtwo=findViewById(R.id.package_two);
        itemthree=findViewById(R.id.package_three);
        itemfour=findViewById(R.id.package_four);
        itemprice=findViewById(R.id.package_price);
        btnadd=findViewById(R.id.btn_add_package);

        Bundle b = getIntent().getExtras();
        Log.e("error", "index=" + b.getString("pctitle"));
        ptit = b.getString("pctitle");
        pone = b.getString("pcone");
        ptwo = b.getString("pctwo");
        pthree = b.getString("pcthree");
        pfour = b.getString("pcfour");
        pprice = b.getString("pcprice");
        packagetitle.setText(ptit);
        try {
            if (!ptit.equalsIgnoreCase(null)) {
                packagetitle.setEnabled(false);
                btnadd.setText("Update");

            } else {
                packagetitle.setEnabled(true);

            }
        }catch (Exception e){

        }

//        packagetitle.setEnabled(false);
        itemone.setText(pone);
        itemtwo.setText(ptwo);
        itemthree.setText(pthree);
        itemfour.setText(pfour);
        itemprice.setText(pprice);

btnadd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        validation();

    }
});

    }
   public void validation(){
        pcktitle = packagetitle.getText().toString();
        pckone = itemone.getText().toString();
       pcktwo = itemtwo.getText().toString();
       pckthree = itemthree.getText().toString();
       pckfour = itemfour.getText().toString();
       pckprice = itemprice.getText().toString();

       if(pcktitle.isEmpty()){
            Toast.makeText(PackageDetails.this, "Please enter Package title", Toast.LENGTH_SHORT).show();
        }else if(pckone.isEmpty()) {
            Toast.makeText(PackageDetails.this, "Please enter item one", Toast.LENGTH_SHORT).show();
        }else if(pcktwo.isEmpty()) {
            Toast.makeText(PackageDetails.this, "Please enter item two", Toast.LENGTH_SHORT).show();
        }
        else if(pckthree.isEmpty()) {
            Toast.makeText(PackageDetails.this, "Please enter item three", Toast.LENGTH_SHORT).show();
        }
        else if(pckfour.isEmpty()) {
            Toast.makeText(PackageDetails.this, "Please enter item four", Toast.LENGTH_SHORT).show();
        }else if(pckprice.isEmpty()) {
            Toast.makeText(PackageDetails.this, "Please enter package price", Toast.LENGTH_SHORT).show();
        }
        else {

            checkDB();
        }
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
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
    public void writeData() {
        Log.e("writeData", "writeData");
        PackagesDetail user = new PackagesDetail(pcktitle,pckone, pcktwo, pckthree, pckfour, pckprice);
        databaseReference.child(pcktitle).setValue(user);

        Toast.makeText(PackageDetails.this, "Package added Successfully", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(PackageDetails.this, PackagesList.class);
//        intent.putExtra("parlourtitle", title);
//        startActivity(intent);
//        finish();
        onBackPressed();

    }
}
