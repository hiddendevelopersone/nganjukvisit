package com.polije.sem3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    private BottomNavigationView btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        btnView.setBackground(null);

        btnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.miBook:
                        // Handle booking item click
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

        MenuItem dashboardMenuItem = btnView.getMenu().findItem(R.id.placeholder);
        dashboardMenuItem.setEnabled(false);

    }
}