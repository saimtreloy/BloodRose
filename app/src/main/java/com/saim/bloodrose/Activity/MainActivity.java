package com.saim.bloodrose.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.saim.bloodrose.Fragment.FragmentHome;
import com.saim.bloodrose.Fragment.FragmentProfile;
import com.saim.bloodrose.R;
import com.saim.bloodrose.Utils.SharedPrefDatabase;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    android.support.v7.app.ActionBarDrawerToggle actionBarDrawerToggle;
    android.support.v4.app.FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbarMainActivity);
        toolbar.setTitle("Blood Rose");
        setSupportActionBar(toolbar);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new FragmentHome());
        fragmentTransaction.commit();

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            Toast.makeText(getApplicationContext(), "Drawer Open", Toast.LENGTH_LONG).show();
        }

        NavigationItemClicked();
    }

    public void NavigationItemClicked() {
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_fullname = (TextView) hView.findViewById(R.id.txtHeadName);
        TextView nav_number = (TextView) hView.findViewById(R.id.txtHeadNumber);

        nav_fullname.setText(new SharedPrefDatabase(getApplicationContext()).RetriveFullName());
        nav_number.setText(new SharedPrefDatabase(getApplicationContext()).RetrivePhone());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.btnMenuProfile) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new FragmentProfile());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawers();
                }else if (item.getItemId() == R.id.btnMenuLogout) {
                    new SharedPrefDatabase(getApplicationContext()).StoreEmail("");
                    new SharedPrefDatabase(getApplicationContext()).StorePassword("");
                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    /*fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, new MainFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawers();*/
                } else if (item.getItemId() == R.id.btnMenuExit) {
                    finish();
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMenuLogout:
                new SharedPrefDatabase(getApplicationContext()).StoreEmail("");
                new SharedPrefDatabase(getApplicationContext()).StorePassword("");
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                return true;
            case R.id.btnMenuExit:
                finish();
        }
        return false;
    }
}
