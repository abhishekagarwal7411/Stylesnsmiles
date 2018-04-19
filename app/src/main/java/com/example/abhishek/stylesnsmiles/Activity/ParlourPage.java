package com.example.abhishek.stylesnsmiles.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecycleParlour;
import com.example.abhishek.stylesnsmiles.PojoClass.Album;
import com.example.abhishek.stylesnsmiles.R;

import java.util.ArrayList;
import java.util.List;

public class ParlourPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterrecycleParlour adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parlour_page);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Parlours List");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AdapterrecycleParlour(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();


    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == android.R.id.home) {
//            onBackPressed();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.parlour1,
                R.drawable.parlour2,
                R.drawable.parlour3,
                R.drawable.parlour4,
                R.drawable.parlour5,
                R.drawable.parlour6,
                R.drawable.parlour7,
                R.drawable.parlour8,
                R.drawable.parlour9,
                R.drawable.parlour10,
                R.drawable.parlour11};

        Album a = new Album("Arabella Hair & Beauty", 13, covers[0], "Kormangala");
        albumList.add(a);

        a = new Album("Sparkliez Beauty & Nails", 8, covers[1], "Btm");
        albumList.add(a);

        a = new Album("Rebecca Salon", 11, covers[2], "Kengeri");
        albumList.add(a);

        a = new Album("Bettys Beauty Salon", 12, covers[3], "RajajiNagar");
        albumList.add(a);

        a = new Album("Armonika", 14, covers[4], "Yeswanthpur");
        albumList.add(a);

        a = new Album("Toni & Guy", 1, covers[5], "Shivajinagar");
        albumList.add(a);

        a = new Album("Aquayo", 11, covers[6], "MG Road");
        albumList.add(a);

        a = new Album("Skin Deep", 14, covers[7], "Vijaynagar");
        albumList.add(a);

        a = new Album("Luminous Nails", 11, covers[8], "MG Road");
        albumList.add(a);

        a = new Album("Draida", 17, covers[9], "Kormangala");
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        return super.onCreateOptionsMenu(menu);
    }


    /* @method name : onOptionsItemSelected
     * description : This method perform functionality of menu content in drawerlayout
     * @author : Abhishek
     * reviewer : T. Anil Kumar
     */

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
     * reviewer : T. Anil Kumar
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
    }//onActivityResult
}


