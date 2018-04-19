package com.example.abhishek.stylesnsmiles.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecycleParlourcustomer;
import com.example.abhishek.stylesnsmiles.Adapter.DrawerListAdapter;
import com.example.abhishek.stylesnsmiles.ClientConstant;
import com.example.abhishek.stylesnsmiles.PojoClass.Album;
import com.example.abhishek.stylesnsmiles.R;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements ClientConstant {
    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mDrawerToggle;
    String[] strings = {"Share", "About", "LogOut"};
    Toolbar toolbar;
    SharedPreferences defaultPreferences;
    SharedPreferences.Editor editPreferences;
    private RecyclerView recyclerView;
    private AdapterrecycleParlourcustomer adapter;
    private List<Album> albumList;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Parlours");

        }
        defaultPreferences = getSharedPreferences(DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        editPreferences = defaultPreferences.edit();
        mDrawerLayout = findViewById(R.id.drawerLayout);
        recyclerView = findViewById(R.id.recycler);
        mDrawerList = findViewById(R.id.drawer_list_view);
        albumList = new ArrayList<>();
        adapter = new AdapterrecycleParlourcustomer(this, albumList);

        cliclistener();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        DrawerListAdapter drawerAdapter = new DrawerListAdapter(getApplicationContext(), strings);
        mDrawerList.setAdapter(drawerAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawers();
                if (strings[position].equals("Share")) {
                    Toast.makeText(Home.this,"Share feature comming soon ....."                   ,Toast.LENGTH_LONG).show();
                }

                if (strings[position].equals("About")) {
                    Intent intent = new Intent(Home.this, About.class);
                    startActivity(intent);
                }
                if (strings[position].equals("LogOut")) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Home.this);
                    alertDialogBuilder.setTitle("Log out");
                    alertDialogBuilder.setMessage("Are you sure you want to log out ?"
                    );
                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    editPreferences.putString(KEY_FULL_NAME, null);
                                    editPreferences.putString(KEY_MOBILE_NUM, null);
                                    editPreferences.apply();
                                    Intent intent = new Intent(Home.this, Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                                    finish();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
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
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            /* called when the slider is open */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /* called when the slider is closed */
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
        prepareAlbums();

    }

    public void cliclistener() {

    }

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
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);
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

