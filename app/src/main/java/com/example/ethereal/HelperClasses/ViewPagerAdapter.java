package com.example.ethereal.HelperClasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import Fragments.JournalFragment;
import Fragments.MoodsFragment;
import Fragments.NotificationsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    int tabcount;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new MoodsFragment();
            case 1: return new NotificationsFragment();
            case 2: return new JournalFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
