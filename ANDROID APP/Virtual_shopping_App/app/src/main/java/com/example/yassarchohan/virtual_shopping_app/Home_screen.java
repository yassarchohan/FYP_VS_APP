package com.example.yassarchohan.virtual_shopping_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Home_screen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    Custom_adapter CA;
    SharedPreferences sp;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





        final List<Getter_methods> messages = new ArrayList<>();
        Getter_methods gm = new Getter_methods(R.drawable.listimg1,"product102","Men shirt","2000",2.5);
        Getter_methods gm1 = new Getter_methods(R.drawable.listimg7,"product601","men shirt","3000",4.5);
        Getter_methods gm2 = new Getter_methods(R.drawable.listimg3,"product256","ladies skirt","2500",3.5);
        Getter_methods gm3 = new Getter_methods(R.drawable.listimg4,"product606","Men plain Shirt","5000",2.6);
        Getter_methods gm4 = new Getter_methods(R.drawable.listimg5,"product546","Men TSHIRt","1200",4.0);
        Getter_methods gm5 = new Getter_methods(R.drawable.listimg6,"product787","Men cool shirt","2500",4.5);
        Getter_methods gm6 = new Getter_methods(R.drawable.listimg7,"product777","Men cool shirt","3500",4.5);
        messages.add(0,gm);
        messages.add(1,gm1);
        messages.add(2,gm2);
       // messages.add(3,gm3);
        messages.add(3,gm);
        messages.add(4,gm4);



       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list1);

       Custom_adapter mAdapter = new Custom_adapter(messages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Home_screen.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();




//        CA = new Custom_adapter(this, R.layout.activity_custom_view, messages);
//        ListView lv = (ListView) findViewById(R.id.list1);
//        lv.setAdapter(CA);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_signout) {

//            sp = getSharedPreferences("loginprevs",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.commit();
//            editor.clear();
//            finish();
//
//            TextView txt = (TextView)findViewById(R.id.forusername);
//            txt.setText(sp.toString());
            fauth = FirebaseAuth.getInstance();
            fauth.signOut();
            Intent intent = new Intent(Home_screen.this,MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.news_feed) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NavigationView mn = (NavigationView) findViewById(R.id.nav_view);
            mn.cancelLongPress();
            // Handle the camera action
        } else if (id == R.id.for_payment) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            intent = new Intent(Home_screen.this,Payment.class);
            startActivity(intent);

//        } else if (id == R.id.pics) {
//
//             intent = new Intent(Intent.ACTION_PICK,
//                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//            final int ACTIVITY_SELECT_IMAGE = 1234;
//            startActivityForResult(intent, ACTIVITY_SELECT_IMAGE);
//
//        } else if (id == R.id.Shareimages) {
//
//            try {
//                intent = new Intent(Home_screen.this, Class.forName(Intent.ACTION_SEND));
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }

        } else if (id == R.id.Feedback) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            intent = new Intent(Home_screen.this,feed_back.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
