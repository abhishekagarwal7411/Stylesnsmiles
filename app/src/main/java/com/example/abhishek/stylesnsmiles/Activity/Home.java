package com.example.abhishek.stylesnsmiles;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.abhishek.stylesnsmiles.Adapter.AdapterrecycleParlourcustomer;
import com.example.abhishek.stylesnsmiles.Adapter.DrawerListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private RecyclerView recyclerView;
    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mDrawerToggle;
    private AdapterrecycleParlourcustomer adapter;
    private List<Album> albumList;
    String[] strings={ "share","about","chat"};
    Toolbar toolbar;
   private  ListView mDrawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Parlours");

        }
        mDrawerLayout =findViewById(R.id.drawerLayout);
        recyclerView =findViewById(R.id.recycler);
        mDrawerList =findViewById(R.id.drawer_list_view);
        albumList = new ArrayList<>();
        adapter = new AdapterrecycleParlourcustomer(this, albumList);

        cliclistener();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        DrawerListAdapter drawerAdapter = new DrawerListAdapter(getApplicationContext(), strings);
        mDrawerList.setAdapter(drawerAdapter);
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

public void cliclistener(){

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
        Album a = new Album("Arabella Hair & Beauty", 13, covers[0],"Kormangala");
        albumList.add(a);

        a = new Album("Sparkliez Beauty & Nails", 8, covers[1],"Btm");
        albumList.add(a);

        a = new Album("Rebecca Salon", 11, covers[2],"Kengeri");
        albumList.add(a);

        a = new Album("Bettys Beauty Salon", 12, covers[3],"RajajiNagar");
        albumList.add(a);

        a = new Album("Armonika", 14, covers[4],"Yeswanthpur");
        albumList.add(a);

        a = new Album("Toni & Guy", 1, covers[5],"Shivajinagar");
        albumList.add(a);

        a = new Album("Aquayo", 11, covers[6],"MG Road");
        albumList.add(a);

        a = new Album("Skin Deep", 14, covers[7],"Vijaynagar");
        albumList.add(a);

        a = new Album("Luminous Nails", 11, covers[8],"MG Road");
        albumList.add(a);

        a = new Album("Draida", 17, covers[9],"Kormangala");
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
}

