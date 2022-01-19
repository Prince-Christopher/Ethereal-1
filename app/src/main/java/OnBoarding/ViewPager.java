package OnBoarding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPager extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;
    public ViewPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                OnBoardingA a = new OnBoardingA();
                return a;
            case 1:
                OnBoardingB b = new OnBoardingB();
                return b;
            case 2:
                OnBoardingC c = new OnBoardingC();
                return c;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
