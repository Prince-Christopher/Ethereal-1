package com.example.ethereal.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.ethereal.HelperClasses.ViewPagerHealth2Adapter;
import com.example.ethereal.HelperClasses.ViewPagerHealth3Adapter;
import com.example.ethereal.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HealthCourseActivity3 extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tabDay1;
    TabItem tabDay2;
    TabItem tabDay3;
    TabItem tabDay4;
    TabItem tabDay5;
    ViewPager viewPager;
    ViewPagerHealth3Adapter viewPagerAdapter;

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        startActivity(new Intent(HealthCourseActivity1.this, HomeActivity.class));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_course3);

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
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerHealth3Adapter(getSupportFragmentManager(), tabLayout.getTabCount());
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