package com.example.ethereal.HelperClasses;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ethereal.HealthCourse1.Course1Day1;
import com.example.ethereal.HealthCourse1.Course1Day2;
import com.example.ethereal.HealthCourse1.Course1Day3;
import com.example.ethereal.HealthCourse1.Course1Day4;
import com.example.ethereal.HealthCourse1.Course1Day5;
import com.example.ethereal.HealthCourse1.Course1Day6;
import com.example.ethereal.HealthCourse2.Course2Day1;
import com.example.ethereal.HealthCourse2.Course2Day2;
import com.example.ethereal.HealthCourse2.Course2Day3;
import com.example.ethereal.HealthCourse2.Course2Day4;
import com.example.ethereal.HealthCourse2.Course2Day5;
import com.example.ethereal.HealthCourse2.Course2Day6;

public class ViewPagerHealth2Adapter extends FragmentPagerAdapter {

    int tabcount;

    public ViewPagerHealth2Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new Course2Day1();
            case 1: return new Course2Day2();
            case 2: return new Course2Day3();
            case 3: return new Course2Day4();
            case 4: return new Course2Day5();
            case 5: return new Course2Day6();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
