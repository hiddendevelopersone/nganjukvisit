package com.polije.sem3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard extends AppCompatActivity {

    private BottomNavigationView btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        FloatingActionButton fab;
        btnView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        btnView.setBackground(null);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnView.setSelectedItemId(R.id.placeholder);
            }
        });

        btnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.miBook:
                        // Handle book item click
                        return true;
                    case R.id.miFavs:
                        // Handle favorites item click
                        return true;
                    case R.id.placeholder:
                        // Handle placeholder item click
                        return true;
                    case R.id.miNotify:
                        // Handle notification item click
                        return true;
                    case R.id.miProfiles:
                        // Handle profiles item click
                        return true;
                }
                return false;
            }
        });

//        disable menuitem
        MenuItem dashboardMenuItem = btnView.getMenu().findItem(R.id.placeholder);
        dashboardMenuItem.setEnabled(false);

    }
}