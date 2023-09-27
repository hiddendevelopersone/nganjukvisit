package com.polije.sem3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard extends AppCompatActivity {

    private BottomNavigationView btnView;
    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Dashboard.this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new Home())
                .commit();

        FloatingActionButton fab;
        btnView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        btnView.setBackground(null);

        //        disable menuitem
        MenuItem dashboardMenuItem = btnView.getMenu().findItem(R.id.placeholder);
        btnView.setSelectedItemId(R.id.placeholder);
        dashboardMenuItem.setEnabled(false);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnView.setSelectedItemId(R.id.placeholder);
                Toast.makeText(Dashboard.this, "this is home", Toast.LENGTH_SHORT).show();
                selectedFragment = new Home();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, new Home()).commit();
            }
        });


        btnView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.miBook:
                    // Handle book item click
                    Toast.makeText(this, "this is book", Toast.LENGTH_SHORT).show();
                    selectedFragment = new Book();
                    this.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame, new Book()).commit();
                    return true;
                case R.id.miFavs:
                    // Handle favorites item click
                    Toast.makeText(this, "this is favs", Toast.LENGTH_SHORT).show();
//                    selectedFragment = new Favs();
                    return true;
                case R.id.placeholder:
                    // Handle placeholder item click
                    return true;
                case R.id.miNotify:
                    // Handle notification item click
                    Toast.makeText(this, "this is notify", Toast.LENGTH_SHORT).show();
//                    selectedFragment = new Notify();
                    return true;
                case R.id.miProfiles:
                    // Handle profiles item click
                    Toast.makeText(this, "profiles", Toast.LENGTH_SHORT).show();
//                    selectedFragment = new Profiles();
                    return true;
            }

            return false;
        });

    }
}