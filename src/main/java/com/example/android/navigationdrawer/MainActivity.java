package com.example.android.navigationdrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private static final String FRAGMENT_TAG = "CURRENT_FRAGMENT";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        RecyclerView drawerOptions = (RecyclerView) findViewById(R.id.drawer_options);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
        drawerLayout.setStatusBarBackground(R.color.primary_dark);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        List<DrawerItem> drawerItems = Arrays.asList(
                new DrawerItem(DrawerItem.Type.HEADER),
                new DrawerMenu().setIconRes(R.drawable.ic_group).setText(getString(R.string.menu_template, 1)),
                new DrawerMenu().setIconRes(R.drawable.ic_map).setText(getString(R.string.menu_template, 2)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.drawable.ic_person).setText(getString(R.string.menu_template, 3)),
                new DrawerMenu().setIconRes(R.drawable.ic_search).setText(getString(R.string.menu_template, 4)),
                new DrawerItem(DrawerItem.Type.DIVIDER),
                new DrawerMenu().setIconRes(R.drawable.ic_settings).setText(getString(R.string.menu_template, 5)));
        drawerOptions.setLayoutManager(new LinearLayoutManager(this));
        DrawerItemAdapter adapter = new DrawerItemAdapter(drawerItems);
        adapter.setOnItemClickListener(new DrawerItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                onDrawerMenuSelected(position);
            }
        });
        drawerOptions.setAdapter(adapter);
        drawerOptions.setHasFixedSize(true);
        if (savedInstanceState == null) setupFragment(new SimpleFramgment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (currentFragment == null || !currentFragment.getClass().equals(fragment.getClass())) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment, FRAGMENT_TAG)
                    .commit();
        }
    }

    private void onDrawerMenuSelected(int position) {
        drawerLayout.closeDrawers();
    }
}