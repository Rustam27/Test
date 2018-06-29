package com.example.valiev_ra.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.valiev_ra.testapp.Main.Fragment.About.FragmentAbout;
import com.example.valiev_ra.testapp.Main.Fragment.Detail.FragmentDetail;
import com.example.valiev_ra.testapp.Main.Fragment.Schedule.FragmentSchedule;
import com.example.valiev_ra.testapp.Main.Fragment.Stations.FragmentStations;
import com.example.valiev_ra.testapp.Main.Listenner.OnSelectFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnSelectFragment {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_schedule);

        selectFragment(FragmentSchedule.getInstance());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragment == null || fragment == FragmentSchedule.getInstance())
                super.onBackPressed();
            else if (fragment == FragmentDetail.getInstance())
                selectFragment(FragmentStations.getInstance());
            else
                selectFragment(FragmentSchedule.getInstance());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_about:
                selectFragment(FragmentAbout.getInstance());
                break;
            case R.id.nav_schedule:
                selectFragment(FragmentSchedule.getInstance());
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void selectFragment(Fragment selectFragment) {
        FragmentManager fm = getSupportFragmentManager();

        if (fragment != null)
            fm.beginTransaction()
                    .hide(fragment)
                    .commitAllowingStateLoss();

        if (!selectFragment.isAdded())
            fm.beginTransaction()
                    .replace(R.id.content, selectFragment)
                    .commitAllowingStateLoss();
        else
            fm.beginTransaction()
                    .show(selectFragment)
                    .commitAllowingStateLoss();

        fragment = selectFragment;
    }
}
