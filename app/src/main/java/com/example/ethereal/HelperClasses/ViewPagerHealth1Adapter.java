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

public class ViewPagerHealth1Adapter extends FragmentPagerAdapter {

    int tabcount;

    public ViewPagerHealth1Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new Course1Day1();
            case 1: return new Course1Day2();
            case 2: return new Course1Day3();
            case 3: return new Course1Day4();
            case 4: return new Course1Day5();
            case 5: return new Course1Day6();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
