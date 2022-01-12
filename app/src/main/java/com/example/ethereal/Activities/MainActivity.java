package com.example.ethereal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.ethereal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import Fragments.HomeFragment;
import Fragments.ProfileFragment;
import Fragments.TasksFragment;
import Fragments.TherapistsFragment;

public class MainActivity extends AppCompatActivity {

    Fragment selectorFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    public void onBackPressed() {
        Intent j = new Intent(MainActivity.this, StartActivity.class);
        startActivity(j);
        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.dark_grey));
            }
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        selectorFragment = new HomeFragment();
                        break;
                    case R.id.tasks:
                        selectorFragment = new TasksFragment();
                        break;
                    case R.id.nancy:
                        selectorFragment = null;
                        startActivity(new Intent(MainActivity.this, NancyActivity.class));
                        break;
                    case R.id.therapists:
                        selectorFragment = new TherapistsFragment();
                        break;
                    case R.id.profile:
                        selectorFragment = new ProfileFragment();
                        break;
                }
                if (selectorFragment != null)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectorFragment).commit();
                }
                return true;
            }

        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }
}