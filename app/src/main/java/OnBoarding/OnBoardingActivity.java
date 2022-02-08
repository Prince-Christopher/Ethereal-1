package OnBoarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.cuberto.liquid_swipe.LiquidPager;
import com.example.ethereal.Activities.MainActivity;
import com.example.ethereal.R;

public class OnBoardingActivity extends AppCompatActivity {

    LiquidPager pager;
    ViewPager viewPager;
    Animation anim;

    String prevStarted = "yes";
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            moveToMain();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_on_boarding);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(getColor(R.color.dark_grey));
            }
        }

        pager=findViewById(R.id.pager);
        anim = AnimationUtils.loadAnimation(this,R.anim.transition_anim);
        pager.startAnimation(anim);
        viewPager = new ViewPager(getSupportFragmentManager(), 1);
        pager.setAdapter(viewPager);

    }
    public void moveToMain(){
        // use an intent to travel from one activity to another.
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
