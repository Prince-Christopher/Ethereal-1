package com.example.ethereal.HelperClasses;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ethereal.HealthCourse2.Course2Day1;
import com.example.ethereal.HealthCourse2.Course2Day2;
import com.example.ethereal.HealthCourse2.Course2Day3;
import com.example.ethereal.HealthCourse2.Course2Day4;
import com.example.ethereal.HealthCourse2.Course2Day5;
import com.example.ethereal.HealthCourse2.Course2Day6;
import com.example.ethereal.HealthCourse3.Course3Day1;
import com.example.ethereal.HealthCourse3.Course3Day2;
import com.example.ethereal.HealthCourse3.Course3Day3;
import com.example.ethereal.HealthCourse3.Course3Day4;
import com.example.ethereal.HealthCourse3.Course3Day5;

public class ViewPagerHealth3Adapter extends FragmentPagerAdapter {

    int tabcount;

    public ViewPagerHealth3Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new Course3Day1();
            case 1: return new Course3Day2();
            case 2: return new Course3Day3();
            case 3: return new Course3Day4();
            case 4: return new Course3Day5();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
