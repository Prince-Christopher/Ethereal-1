package com.example.ethereal.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ethereal.HelperClasses.ViewPagerAdapter;
import com.example.ethereal.HelperClasses.ViewPagerHealth1Adapter;
import com.example.ethereal.HelperClasses.ViewPagerHealth1Adapter;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.User;

public class HealthCourseActivity1 extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tabDay1;
    TabItem tabDay2;
    TabItem tabDay3;
    TabItem tabDay4;
    TabItem tabDay5;
    TabItem tabDay6;
    ViewPager viewPager;
    ViewPagerHealth1Adapter viewPagerAdapter;

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        startActivity(new Intent(HealthCourseActivity1.this, HomeActivity.class));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_course1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            View decor = getWindow().getDecorView();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.aqua));
                decor.setSystemUiVisibility(0);
            }
        }

        tabLayout = findViewById(R.id.tablayout);
        tabDay1 = findViewById(R.id.tabDay1);
        tabDay2 = findViewById(R.id.tabDay2);
        tabDay3 = findViewById(R.id.tabDay3);
        tabDay4 = findViewById(R.id.tabDay4);
        tabDay5 = findViewById(R.id.tabDay5);
        tabDay6 = findViewById(R.id.tabDay6);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerHealth1Adapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

}